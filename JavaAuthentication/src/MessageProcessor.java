import java.rmi.RemoteException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class MessageProcessor{

   static public String[] processEncryptedMessage(String encryptedMessage, ApplicationServer server)
            throws RemoteException, IllegalBlockSizeException, BadPaddingException {
        // Decrypt the message using the server's method
        String decryptedMessage = ApplicationServer.decryptMessage(encryptedMessage);

        // Split the decrypted message into parts using a specified delimiter
        String[] parts = decryptedMessage.split("ඞ");

        // Return the array of parts
        return parts;
    }
}
