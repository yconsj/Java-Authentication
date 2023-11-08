import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class client {
    static Cipher encryptCipher;
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, ParseException {
        AuthenticationService authService = (AuthenticationService) Naming.lookup("rmi://localhost:5099/auth");
        PrinterService service = (PrinterService) Naming.lookup("rmi://localhost:5099/print");
        String sessionId = null;
        String username = "David";
        String password = "davidpassword";


        sessionId = authService.login(username,password);
        if (sessionId != null) {
            System.out.println("Successfully logged in!");
        } else {
            System.out.println("Failed to login :C");
        }

        /* Test functions */
        String filename = "file1";
        String printername = "printer1";
        int jobNumber = 1;
        String parameter = "paramenter";
        String value = "ready";
        

        service.print(filename, printername, sessionId, username);
    
        service.queue(filename, sessionId, username);

        service.topQueue(printername, jobNumber, sessionId, username);

        service.start(sessionId, username);

        service.stop(sessionId, username);

        service.restart(sessionId, username);

        service.status(printername, sessionId, username);

        service.setConfig(parameter, value, sessionId, username);

        service.readConfig(parameter, sessionId, username);


    }

    public static String prepareEncryptedInput(String... inputs) throws IllegalBlockSizeException, BadPaddingException {
        String delimiter = "ඞ";
        String combined = String.join(delimiter, inputs);
        return encryptMessage(combined);
    }

    public static String encryptMessage(String message) throws IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedBytes = encryptCipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
