import java.rmi.Remote.*;

/*
print(String filename, String printer);   // prints file filename on the specified printer
queue(String printer);   // lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>
topQueue(String printer, int job);   // moves job to the top of the queue
start();   // starts the print server
stop();   // stops the print server
restart();   // stops the print server, clears the print queue and starts the print server again
status(String printer);  // prints status of printer on the user's display
readConfig(String parameter);   // prints the value of the parameter on the print server to the user's display
setConfig(String parameter, String value);   // sets the parameter on the print server to value
*/

interface Operations {
    /* NOTE: Maybe not void return type */
    void print(String filename, String printer); // prints file filename on the specified printer

    void queue(String printer); // lists the print queue for a given printer on the user's display in lines of
                                // the form <job number> <file name>

    void topQueue(String printer, int job); // moves job to the top of the queue

    void start(); // starts the print server

    void stop(); // stops the print server

    void restart(); // stops the print server, clears the print queue and starts the print server
                    // again

    void status(String printer); // prints status of printer on the user's display

    void readConfig(String parameter); // prints the value of the parameter on the print server to the user's display

    void setConfig(String parameter, String value); // sets the parameter on the print server to value
}

public class PrintServer implements Operations {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

}
