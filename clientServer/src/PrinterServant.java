import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class PrinterServant extends UnicastRemoteObject implements Printer {
    public PrinterServant() throws RemoteException{
        super();
    }


    public void print(String filename, String printer) throws RemoteException{

    } // prints file filename on the specified printer

    public void queue(String printer) throws RemoteException{

    } // lists the print queue for a given printer on the user's display in lines of
                                // the form <job number> <file name>

    public void topQueue(String printer, int job) throws RemoteException{

    } // moves job to the top of the queue

    public String start() throws RemoteException{
        return "started";
    } // starts the print server

    public void stop() throws RemoteException{

    } // stops the print server

    public void restart() throws RemoteException{

    } // stops the print server, clears the print queue and starts the print server
                    // again

    public void status(String printer) throws RemoteException{

    } // prints status of printer on the user's display

    public void readConfig(String parameter) throws RemoteException{

    } // prints the value of the parameter on the print server to the user's display

    public void setConfig(String parameter, String value) throws RemoteException{
        
    } // sets the parameter on the print server to value
    



}
