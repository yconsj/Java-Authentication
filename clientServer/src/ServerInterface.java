import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    <T> T executeTask(Task<T> t) throws RemoteException;

    /* NOTE: Maybe not void return type */
    void print(String filename, String printer); // prints file filename on the specified printer

    void queue(String printer); // lists the print queue for a given printer on the user's display in lines of
                                // the form <job number> <file name>

    void topQueue(String printer, int job); // moves job to the top of the queue

    void start(); // starts the print server

    void stop(); // stops the print server

    void restart(); // stops the print server, clears the print queue and starts the print server
                    // again

    void status(String printer); // prints status of printer on the user's display

    void readConfig(String parameter); // prints the value of the parameter on the print server to the user's display

    void setConfig(String parameter, String value); // sets the parameter on the print server to value
}
