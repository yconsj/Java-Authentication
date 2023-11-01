import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
public interface PrinterService extends Remote {

    public PublicKey getPublicKey() throws RemoteException;

 public void testEncryption(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException;

// echoes the encrypted message back to the caller
public String echo(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException;

// prints the encrypted file message
public void print(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // prints file filename on the specified printer

// lists the print queue for a given printer with the encrypted message
public void queue(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // lists the print queue for a given printer on the user's display in lines of the form <job number> <file name>

// moves the job specified in the encrypted message to the top of the queue
public void topQueue(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // moves job to the top of the queue

// starts the print server with the encrypted session message
public void start(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // starts the print server

// stops the print server with the encrypted session message
public void stop(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // stops the print server

// restarts the print server with the encrypted session message
public void restart(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // stops the print server, clears the print queue and starts the print server again

// prints the status of the printer with the encrypted message
public void status(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // prints status of printer on the user's display

// reads the configuration for the parameter specified in the encrypted message
public void readConfig(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // prints the value of the parameter on the print server to the user's display

// sets the configuration of the print server with the encrypted message
public void setConfig(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException; // sets the parameter on the print server to value


}
