import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        AuthenticationService authService = (AuthenticationService) Naming.lookup("rmi://localhost:5099/auth");
        PrinterService service = (PrinterService) Naming.lookup("rmi://localhost:5099/print");
        String sessionId = null;
        String username = "cock norris";
        String password = "wrongpassword";

        /* Try to login */
        sessionId = authService.login(username, password);
        if (sessionId != null) {
            System.out.println("Successfully logged in!");
        } else {
            System.out.println("Failed to login :C");
        }
        /* Test functions */
        service.print("file1", "printer1", sessionId, username);
        service.queue("file1", sessionId, username);
        service.topQueue("printer1", 1, sessionId, username);
        service.start(sessionId, username);
        service.stop(sessionId, username);
        service.restart(sessionId, username);
        service.status("printer1", sessionId, username);
        service.setConfig("paramenter", "ready", sessionId, username);
        service.readConfig("paramenter", sessionId, username);

    }
}
