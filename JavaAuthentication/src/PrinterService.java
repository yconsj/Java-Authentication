import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrinterService extends Remote {

    public String echo(String input, String sessionId, String username) throws RemoteException;

    public void print(String filename, String printer, String sessionId, String username) throws RemoteException; // prints
                                                                                                                  // file
                                                                                                                  // filename
                                                                                                                  // on
                                                                                                                  // the
                                                                                                                  // specified
                                                                                                                  // printer

    public void queue(String printer, String sessionId, String username) throws RemoteException; // lists the print
                                                                                                 // queue for a given
                                                                                                 // printer on the
                                                                                                 // user's display in
                                                                                                 // lines of the form
                                                                                                 // <job number> <file
                                                                                                 // name>

    public void topQueue(String printer, int job, String sessionId, String username) throws RemoteException; // moves
                                                                                                             // job to
                                                                                                             // the top
                                                                                                             // of the
                                                                                                             // queue

    public void start(String sessionId, String username) throws RemoteException; // starts the print server

    public void stop(String sessionId, String username) throws RemoteException; // stops the print server

    public void restart(String sessionId, String username) throws RemoteException; // stops the print server, clears the
                                                                                   // print queue and starts the print
                                                                                   // server again

    public void status(String printer, String sessionId, String username) throws RemoteException; // prints status of
                                                                                                  // printer on the
                                                                                                  // user's display

    public void readConfig(String parameter, String sessionId, String username) throws RemoteException; // prints the
                                                                                                        // value of the
                                                                                                        // parameter on
                                                                                                        // the print
                                                                                                        // server to the
                                                                                                        // user's
                                                                                                        // display

    public void setConfig(String parameter, String value, String sessionId, String username) throws RemoteException; // sets
                                                                                                                     // the
                                                                                                                     // parameter
                                                                                                                     // on
                                                                                                                     // the
                                                                                                                     // print
                                                                                                                     // server
                                                                                                                     // to
                                                                                                                     // value

}
