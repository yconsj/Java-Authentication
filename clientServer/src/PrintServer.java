
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PrintServer {

    public PrintServer() {
        super();
    }

    public static void main(String[] args) throws Exception {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {

            Registry registry = LocateRegistry.createRegistry(5099);
            registry.rebind("printer", new PrinterServant());
        } catch (Exception e) {
            System.err.println("PrintServer exception:");
            e.printStackTrace();
        }
    }

}
