import java.rmi.Remote;
import java.rmi.RemoteException;

public class PrintServer implements ServerInterface {

    public PrintServer() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

}
