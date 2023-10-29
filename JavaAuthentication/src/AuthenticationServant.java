import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationServant extends UnicastRemoteObject implements AuthenticationService {
    ApplicationServer server;

    protected AuthenticationServant(ApplicationServer serverArg) throws RemoteException {
        super();
        server = serverArg;
    }

    public String echo(String input) throws RemoteException {
        return "echo";
    }

    public String login(String username, String password) throws RemoteException {
        return server.loginRequest(username, password);
    }
}