import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
public interface PrinterService extends Remote {

// echoes the encrypted message back to the caller
public String echo(String message, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException;

// prints the encrypted file message
public void print(String filename, String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException ; // prints file filename on the specified printer

// lists the print queue for a given printer with the encrypted message
public void queue(String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException; // lists the print queue for a given printer on the user's display in lines of the form <job number> <file name>

// moves the job specified in the encrypted message to the top of the queue
public void topQueue(String printer, int job, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException; // moves job to the top of the queue

// starts the print server with the encrypted session message
public void start(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException ; // starts the print server

// stops the print server with the encrypted session message
public void stop(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException; // stops the print server

// restarts the print server with the encrypted session message
public void restart(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException; // stops the print server, clears the print queue and starts the print server again

// prints the status of the printer with the encrypted message
public void status(String encryptedMessage, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException; // prints status of printer on the user's display

// reads the configuration for the parameter specified in the encrypted message
public void readConfig(String parameter, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException ; // prints the value of the parameter on the print server to the user's display

// sets the configuration of the print server with the encrypted message
public void setConfig(String parameter, String value, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException; // sets the parameter on the print server to value


}
