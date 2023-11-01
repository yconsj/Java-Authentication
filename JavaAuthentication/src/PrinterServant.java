import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import java.security.PublicKey;
import java.text.ParseException;

public class PrinterServant extends UnicastRemoteObject implements PrinterService {
    ApplicationServer server;

    protected PrinterServant(ApplicationServer serverArg) throws RemoteException {
        super();
        server = serverArg;
    }

    @Override
    public PublicKey getPublicKey() throws RemoteException {
        return server.getPublicKey();
    }

    @Override
    public void testEncryption(String encryptedMessage)
            throws RemoteException, IllegalBlockSizeException, BadPaddingException {
        String decryptedMessage = ApplicationServer.decryptMessage(encryptedMessage);
        String[] parts = decryptedMessage.split("ඞ");
        // Printing all parts
        for (String part : parts) {
            System.out.println(part);
        }
    }

   

    @Override
    public String echo(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException {
        // USe the processEncryptedMessage method to get the parts of the message
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        // Call echoDecrypted with the parts of the message
        return echoDecrypted(parts[0], parts[1], parts[2]);
    }

    public String echoDecrypted(String input, String sessionId, String username) throws RemoteException {
        return "From Server: " + input;
    }

    @Override
    public void print(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        printDecrypted(parts[0], parts[1], parts[2], parts[3]);
    }

    public void printDecrypted(String filename, String printer, String sessionId, String username)
            throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("print(String " + filename + ", String " + printer + ")");
        }
    }

    @Override
    public void queue(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        queueDecrypted(parts[0], parts[1], parts[2]);
    }

    public void queueDecrypted(String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("queue(String " + printer + ")");
        }
    }

    @Override
    public void topQueue(String encryptedMessage)
            throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        // Assuming that parts[1] can be converted to an integer value representing the
        // job number
        int job = Integer.parseInt(parts[1]);
        topQueueDecrypted(parts[0], job, parts[2], parts[3]);
    }

    public void topQueueDecrypted(String printer, int job, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("topQueue(String " + printer + ", int " + job + ")");
        }
    }

    @Override
    public void start(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        startDecrypted(parts[0], parts[1]);
    }

    public void startDecrypted(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("start()");
        }
    }

    @Override
    public void stop(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        stopDecrypted(parts[0], parts[1]);
    }

    public void stopDecrypted(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("stop()");
        }
    }

    @Override
    public void restart(String encryptedMessage)
            throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        restartDecrypted(parts[0], parts[1]);
    }

    public void restartDecrypted(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("restart()");
        }
    }

    @Override
    public void status(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        statusDecrypted(parts[0], parts[1], parts[2]);
    }

    public void statusDecrypted(String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("status(String " + printer + ")");
        }
    }

    @Override
    public void readConfig(String encryptedMessage)
            throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        readConfigDecrypted(parts[0], parts[1], parts[2]);
    }

    public void readConfigDecrypted(String parameter, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("readConfig(String " + parameter + ")");
        }
    }

    @Override
    public void setConfig(String encryptedMessage) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        String[] parts = MessageProcessor.processEncryptedMessage(encryptedMessage, server);
        if (server.validateRequest(parts[3], parts[2])) {
            System.out.println("setConfig(String " + parts[0] + ", String " + parts[1] + ")");
        }
    }

}
