import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        PrinterService service = (PrinterService) Naming.lookup("rmi://localhost:5099/print");
        System.out.println("--- " +  service.echo("Hey server"));

    }
}
