import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApplicationServer {
    private static ApplicationServer server = null;
    String currSessionID = null;
    static JSONObject logins;
    private static HashMap<String, String> currentLogins = new HashMap<String, String>();
    Random random = new Random();
    static PrivateKey privateKey;
    static PublicKey publicKey;
    static Cipher decryptCipher;
    static Cipher encryptCipher;

    /* singleton */
    public static synchronized ApplicationServer getServer() {
        if (server == null) {
            server = new ApplicationServer();
        }
        return server;
    }

    public static void main(String[] args) throws RemoteException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        /* Generate public private key */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);

        KeyPair kp = kpg.generateKeyPair();
        privateKey = kp.getPrivate();
        publicKey = kp.getPublic();
        decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        /* create json logins */
        logins = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            logins = (JSONObject) parser.parse(new FileReader("JavaAuthentication\\src\\PUBLICPASSWORDS.json"));
        } catch (Exception e) {
            System.out.println("couldnt find file");
        }

        //encrypt user password:
        String userPassword = (String) logins.get("user1");
        byte[] encryptedBytes = encryptCipher.doFinal(userPassword.getBytes());
        String encryptedPW = Base64.getEncoder().encodeToString(encryptedBytes);
        logins.replace("user1", encryptedPW);

        Registry registry = LocateRegistry.createRegistry(5099);

        registry.rebind("auth", new AuthenticationServant(getServer()));
        registry.rebind("print", new PrinterServant(getServer()));
    }

    public boolean validateRequest(String username, String token) throws IllegalBlockSizeException, BadPaddingException, ParseException {
        if (currentLogins.get(username) == null) {
            return false;
        }
        //also checks if session is more than 12 hours old
        return currentLogins.get(username).equals(token) && checkSessionId(token,24);
    }

    public String loginRequest(String username, String password) {
        /* check if login is correct */
        try {
            String p = decryptMessage((String) logins.get(username));
            if (p.equals(password)) {
                // TODO save login.
                String sk = "SI_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())
                        + "_" + random.nextInt(10000);
                currentLogins.put(username, sk);
                return sk;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String decryptMessage(String encryptedMessage) throws IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
        String decryptedMessage = new String(decryptedBytes);
        return decryptedMessage;
    }

    public static boolean checkSessionId(String sessionId, int hours) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String[] parts = sessionId.split("_");

        Date sessionDate = sdf.parse(parts[1]);

        Calendar now = Calendar.getInstance();
        Calendar sessionTime = Calendar.getInstance();
        sessionTime.setTime(sessionDate);
        sessionTime.add(Calendar.HOUR_OF_DAY, hours);

        return now.after(sessionTime);
    }


    public PublicKey getPublicKey() {
        return publicKey;
    }

}
