import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class ApplicationServer {
    private static ApplicationServer server = null;
    String currSessionID = null;
    static JSONObject logins;
    private static HashMap<String, String> currentLogins = new HashMap<String, String>();
    Random random = new Random();
    static MessageDigest md;

    /* singleton */
    public static synchronized ApplicationServer getServer() {
        if (server == null) {
            server = new ApplicationServer();
        }
        return server;
    }

    private static String genSalt() {
        return UUID.randomUUID().toString();
    }

    private static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws RemoteException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Static getInstance method is called with hashing SHA
        md = MessageDigest.getInstance("SHA-256");

        /* create json logins */
        logins = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            logins = (JSONObject) parser.parse(new FileReader("src\\Credentials.json"));
        } catch (Exception e) {
            System.out.println("couldnt find file");
        }

        /* Printing paswords hasing with salt */
        // System.out.println("password: MyPassword");
        // String newsalt = genSalt();
        // System.out.println("salt: " + newsalt);
        // System.out.println("password hashed: " + toHexString(getSHA("MyPassword")));
        // System.out.println("password hashed with salt: " +
        // toHexString(getSHA("MyPassword" + newsalt)));

        Registry registry = LocateRegistry.createRegistry(5099);

        registry.rebind("auth", new AuthenticationServant(getServer()));
        registry.rebind("print", new PrinterServant(getServer()));
    }

    public boolean validateRequest(String username, String token)
            throws IllegalBlockSizeException, BadPaddingException, ParseException {
        if (currentLogins.get(username) == null) {
            return false;
        }
        // also checks if session is more than 12 hours old
        return currentLogins.get(username).equals(token) && checkSessionId(token, 24);
    }

    public String loginRequest(String username, String password) {
        /* check if login is correct */
        try {
            /* find stored hash */
            String stored_password = (String) ((JSONObject) logins.get(username)).get("password");
            /* construction the hash to verify */
            String p_hash = toHexString(
                    getSHA(password + (String) ((JSONObject) logins.get(username)).get("salt")));
            if (p_hash.equals(stored_password)) {
                System.out.println("Access granted!");
                String sk = "SI_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())
                        + "_" + random.nextInt(10000);
                currentLogins.put(username, sk);
                return sk;
            } else
                System.out.println("Wrong password");
            return null;
        } catch (Exception e) {
            System.out.println("Wrong username");
            return null;
        }
    }

    public static boolean checkSessionId(String sessionId, int hours) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String[] parts = sessionId.split("_");

        Date sessionDate = sdf.parse(parts[1]);

        Calendar now = Calendar.getInstance();
        Calendar sessionTime = Calendar.getInstance();
        sessionTime.setTime(sessionDate);
        sessionTime.add(Calendar.HOUR_OF_DAY, hours);

        return now.before(sessionTime);
    }

}
