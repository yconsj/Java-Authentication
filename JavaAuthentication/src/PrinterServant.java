import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PrinterServant extends UnicastRemoteObject implements PrinterService {

    protected PrinterServant() throws RemoteException {
        super();
    }

    @Override
    public String echo(String input) throws RemoteException {
        return "From Server: " + input ;
    }

    public void print(String filename, String printer) throws RemoteException {
        System.out.println("print(String " + filename + ", String " + printer + ")");
    }

    @Override
    public void queue(String printer) throws RemoteException {
        System.out.println("queue(String " + printer + ")");

    }

    @Override
    public void topQueue(String printer, int job) throws RemoteException{
        System.out.println("topQueue(String " + printer + ", int" + job +")");

    }

    @Override
    public void start() throws RemoteException {
        System.out.println("start()");
    }

    @Override
    public void stop() throws RemoteException {
        System.out.println("stop()");
    }

    @Override
    public void restart() throws RemoteException {
        System.out.println("restart()");

    }

    @Override
    public void status(String printer) throws RemoteException {
        System.out.println("status(String " + printer + ")" );

    }

    @Override
    public void readConfig(String parameter) throws RemoteException {
        System.out.println("readConfig(String " + parameter + ")" );

    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        System.out.println("setConfig(String " + parameter + ", String " + value + ")" );

    }


}
