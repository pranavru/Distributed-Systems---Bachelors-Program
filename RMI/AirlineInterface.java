import java.util.*;
import java.rmi.*;

public interface AirlineInterface extends Remote {
    public String BookTicket(String FName,String Name,String Source,String Destination, int NoOfTickets) throws RemoteException;
    public String CancelTicket(String FName,String Name,String Source,String Destination, int NoOfTickets) throws RemoteException;
    public String Inventory() throws RemoteException;
    public String PassengerInfo()throws RemoteException;
}
