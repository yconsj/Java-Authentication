import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

public class AuthenticationServant extends UnicastRemoteObject implements AuthenticationService {
    ApplicationServer server;

    protected AuthenticationServant(ApplicationServer serverArg) throws RemoteException {
        super();
        server = serverArg;
    }

    public String echo(String input) throws RemoteException {
        return "echo";
    }

    public String login(String encryptedLoginString) throws RemoteException, IllegalBlockSizeException, BadPaddingException  {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedLoginString, server);
        String username = parts[0];
        String password = parts[1];
        return server.loginRequest(username, password);
    }
}