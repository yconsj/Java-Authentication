import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface AuthenticationService extends Remote {

    public String echo(String input) throws RemoteException;

    public String login(String encryptedLoginString) throws RemoteException, IllegalBlockSizeException, BadPaddingException;

}
