/*public abstract class UserRole {
    protected PrintServer printServer;

    public UserRole(PrintServer printServer) {
        this.printServer = printServer;
    }

    public void print(String filename, String printer) {
        printServer.print(filename, printer);
    }

    public void queue(String printer) {
        printServer.queue(printer);
    }

    // Add any other methods common to all users
}

public class Manager extends UserRole {
    public Manager(PrintServer printServer) {
        super(printServer);
    }

    public void start() {
        printServer.start();
    }

    public void stop() {
        printServer.stop();
    }

    // Implement other methods specific to the Manager role
}

public class Technician extends UserRole {
    public Technician(PrintServer printServer) {
        super(printServer);
    }

    public void start() {
        printServer.start();
    }

    public void stop() {
        printServer.stop();
    }

    // Implement other methods specific to the Technician role
}

public class PowerUser extends UserRole {
    public PowerUser(PrintServer printServer) {
        super(printServer);
    }

    // PowerUser can do everything an OrdinaryUser can, plus more
    public void restart() {
        printServer.restart();
    }

    // Implement other methods specific to the PowerUser role
}

public class OrdinaryUser extends UserRole {
    public OrdinaryUser(PrintServer printServer) {
        super(printServer);
    }

    // Implement methods specific to the OrdinaryUser role, if any
}

public class PrintServer {
    public void print(String filename, String printer) {
        // Implementation
    }

    public void queue(String printer) {
        // Implementation
    }

    // Implement other PrintServer operations
}
*/