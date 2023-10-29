import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {

    public String echo(String input) throws RemoteException;

    public String login(String username, String password) throws RemoteException;

}
