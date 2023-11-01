import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
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
        String username = "user1";
        String password = "correctpassword";

        //get public key from server
        PublicKey serverPublicKey = service.getPublicKey();
        encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);

        /* Try to login */
        String encryptedLoginString = prepareEncryptedInput(username, password);
        sessionId = authService.login(encryptedLoginString);
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
        
        String encryptedPrint = prepareEncryptedInput(filename, printername, sessionId, username);
        service.print(encryptedPrint);
    
        String encryptedQueue = prepareEncryptedInput(filename, sessionId, username);
        service.queue(encryptedQueue);

        String encryptedTopQueue = prepareEncryptedInput(printername, String.valueOf(jobNumber), sessionId, username);
        service.topQueue(encryptedTopQueue);

        String encryptedStart = prepareEncryptedInput(sessionId, username);
        service.start(encryptedStart);

        String encryptedStop = prepareEncryptedInput(sessionId, username);
        service.stop(encryptedStop);

        String encryptedRestart = prepareEncryptedInput(sessionId, username);
        service.restart(encryptedRestart);

        String encryptedStatus = prepareEncryptedInput(printername, sessionId, username);
        service.status(encryptedStatus);

        String encryptedSetConfig = prepareEncryptedInput(parameter, value, sessionId, username);
        service.setConfig(encryptedSetConfig);

        String encryptedReadConfig = prepareEncryptedInput(parameter, sessionId, username);
        service.readConfig(encryptedReadConfig);


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
