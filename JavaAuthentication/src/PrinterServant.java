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
        if (server.validateRequest(username, sessionId) && server.checkAccess(username,"print")) {
            System.out.println("print(String " + filename + ", String " + printer + ")");
        }
    }

    @Override
    public void queue(String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId) && server.checkAccess(username,"queue")) {
            System.out.println("queue(String " + printer + ")");
        }
    }

    @Override
    public void topQueue(String printer, int job, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId) && server.checkAccess(username, "topQueue")) {
            System.out.println("topQueue(String " + printer + ", int " + job + ")");
        }
    }

    @Override
    public void start(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId) && server.checkAccess(username, "start")) {
            System.out.println("start()");
        }
    }

    @Override
    public void stop(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId) && server.checkAccess(username, "stop")) {
            System.out.println("stop()");
        }
    }

    @Override
    public void restart(String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username, sessionId) && server.checkAccess(username,"restart")) {
            System.out.println("restart()");
        }
    }

    @Override
    public void status(String printer, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException{
        if (server.validateRequest(username, sessionId) && server.checkAccess(username,"status")) {
            System.out.println("status(String " + printer + ")");
        }
    }
    
    @Override
    public void readConfig(String parameter, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException{
        if (server.validateRequest(username, sessionId) && server.checkAccess(username,"readConfig")) {
            System.out.println("readConfig(String " + parameter + ")");
        }
    }

    @Override
    public void setConfig(String parameter, String value, String sessionId, String username) throws RemoteException, IllegalBlockSizeException, BadPaddingException, ParseException {
        if (server.validateRequest(username,sessionId) && server.checkAccess(username,"setConfig")) {
            System.out.println("setConfig(String " + parameter+ ", String " + value + ")");
        }
    }

}
