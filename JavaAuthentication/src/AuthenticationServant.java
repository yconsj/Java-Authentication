import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class AuthenticationServant extends UnicastRemoteObject implements AuthenticationService {
    ApplicationServer server;

    protected AuthenticationServant(ApplicationServer serverArg) throws RemoteException {
        super();
        server = serverArg;
    }

    public String echo(String input) throws RemoteException {
        return "echo";
    }

    public String login(String username, String password) throws RemoteException, IllegalBlockSizeException, BadPaddingException  {
        return server.loginRequest(username, password);
    }
}