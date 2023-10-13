package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server.Task;

public class PrintServer implements Printer {

    public PrintServer() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) throws Exception {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Printer";
            Printer printer = new PrintServer();
            Printer stub = (Printer) UnicastRemoteObject.exportObject(printer, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("Printer bound");
        } catch (Exception e) {
            System.err.println("PrintServer exception:");
            e.printStackTrace();
        }
    }

}
