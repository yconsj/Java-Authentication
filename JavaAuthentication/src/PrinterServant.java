import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import java.text.ParseException;

public class PrinterServant extends UnicastRemoteObject implements PrinterService {
    ApplicationServer server;

    protected PrinterServant(ApplicationServer serverArg) throws RemoteException {
        super();
        server = serverArg;
    }

    @Override
    public String echo(String input, String sessionId, String username) throws RemoteException {
        return "From Server: " + input;
    }

    @Override
    public void print(String filename, String printer, String sessionId, String username)
            throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("print(String " + filename + ", String " + printer + ")");
        }
    }

    @Override
    public void queue(String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("queue(String " + printer + ")");
        }
    }

    @Override
    public void topQueue(String printer, int job, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("topQueue(String " + printer + ", int " + job + ")");
        }
    }

    @Override
    public void start(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("start()");
        }
    }

    @Override
    public void stop(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("stop()");
        }
    }

    @Override
    public void restart(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId)) {
            System.out.println("restart()");
        }
    }

    @Override
    public void status(String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException{
        if (server.validateRequest(username, sessionId)) {
            System.out.println("status(String " + printer + ")");
        }
    }
    
    @Override
    public void readConfig(String parameter, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException{
        if (server.validateRequest(username, sessionId)) {
            System.out.println("readConfig(String " + parameter + ")");
        }
    }

    @Override
    public void setConfig(String parameter, String value, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username,sessionId)) {
            System.out.println("setConfig(String " + parameter+ ", String " + value + ")");
        }
    }

}
