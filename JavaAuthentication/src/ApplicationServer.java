import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApplicationServer {
    private static ApplicationServer server = null;
    String currSessionID = null;
    static JSONObject logins;
    private static HashMap<String, String> currentLogins = new HashMap<String, String>();
    Random random = new Random();

    /* singleton */
    public static synchronized ApplicationServer getServer() {
        if (server == null) {
            server = new ApplicationServer();
        }
        return server;
    }

    public static void main(String[] args) throws RemoteException {
        /* create json logins */
        logins = new JSONObject();
        JSONParser parser = new JSONParser();
        try {
            logins = (JSONObject) parser.parse(new FileReader("src\\PUBLICPASSWORDS.json"));
        } catch (Exception e) {
            System.out.println("couldnt find file");
        }

        Registry registry = LocateRegistry.createRegistry(5099);

        registry.rebind("auth", new AuthenticationServant(getServer()));
        registry.rebind("print", new PrinterServant(getServer()));
    }

    public boolean validateRequest(String username, String token) {
        if (currentLogins.get(username) == null) {
            return false;
        }
        return currentLogins.get(username).equals(token);
    }

    public String loginRequest(String username, String password) {
        /* check if login is correct */
        try {
            String p = (String) logins.get(username);
            if (p.equals(password)) {
                // TODO save login.
                String sk = "SI" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())
                        + "_" + random.nextInt(10000);
                System.out.println(sk);
                currentLogins.put(username, sk);
                return sk;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
