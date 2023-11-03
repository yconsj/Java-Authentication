import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class client {
    static String sessionId = null;
    static String username = "user1";
    static String password = "correctpassword";

    public static void main(String[] args)
            throws MalformedURLException, NotBoundException, RemoteException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, ParseException {
        AuthenticationService authService = (AuthenticationService) Naming.lookup("rmi://localhost:5099/auth");
        PrinterService service = (PrinterService) Naming.lookup("rmi://localhost:5099/print");

        sessionId = authService.login(username, password);
        System.out.println(username + " trying to login with password: " + password);
        if (sessionId != null) {
            System.out.println("Successfully logged in!");
        } else {
            System.out.println("Failed to login :C");
        }

        /* Test functions */
        String filename = "file1";
        String printername = "printer1";
        int jobNumber = 1;
        String parameter = "paramenter";
        String value = "ready";

        System.out.println("Running all print server operations...");
        service.print(filename, printername, sessionId, username);

        service.queue(filename, sessionId, username);

        service.topQueue(printername, jobNumber, sessionId, username);

        service.start(sessionId, username);

        service.stop(sessionId, username);

        service.restart(sessionId, username);

        service.status(printername, sessionId, username);

        service.setConfig(parameter, value, sessionId, username);

        service.readConfig(parameter, sessionId, username);

    }
}
