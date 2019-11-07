import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Airline extends UnicastRemoteObject implements AirlineInterface {
    
    ArrayList<Passenger> passengerList = new ArrayList<>();
    Passenger passen;
    //ArrayList<Flight> flightList = new ArrayList<>();
    //Flight flights;
    String S,D,Name, FName;
    int N;
    int i = 0, j = 0;
    int max = 20;
    int att = 2, data = 40, NoOfLettersInName = 3, NoOfFlights = 3;
    
   // static Flight[] flights = new Flight[NoOfFlights];
    
    String[][] Route = {
        {"Mumbai", "Delhi"},
        {"Delhi", "Chennai"},
        {"Chennai", "Goa"},
        {"Goa", "Chennai"},
    };
    
    int Tick[][] = new int[Route.length][att];
    
    public Airline() throws RemoteException {
        
    /*    flights[0] = new Flight("Indigo", 15540, max);
        flights[1] = new Flight("Jet Airways", 19876, max);
        flights[2] = new Flight("Air India", 22110, max);
    */
        for(i = 0; i < Route.length; i++) {
            Tick[i][0] = max;
            Tick[i][1] = max;
        }
    }
    
    public String Inventory() throws RemoteException {
        String str = "";
        for(i = 0; i < Route.length; i++) {
            str += "\n";
            for(j = 0; j < att; j++) {
                str += Route[i][j] + "\t\t";
            }
            str += Tick[i][1];
        }
        return str;
    }

    public String BookTicket(String FName, String Name, String S, String D, int N) throws RemoteException {
        if (Name.length() < NoOfLettersInName) {
                return "\nEnter a valid name.";
        }
/*        for(i = 0; i < flightList.get(i); i++) {
            if(flightList.get(i).getName() == Fname)
                flights = flightList.get(i);
        }*/
        for(i = 0; i < Route.length; i++) {
            if(S.equalsIgnoreCase(Route[i][0]) && D.equalsIgnoreCase(Route[i][1])) {
                if(N > 0 && Tick[i][1] >= N) {
                    Tick[i][1] -= N;
                    passen = new Passenger(FName, Name, S, D, N, "Booked");
                    passengerList.add(passen);
                    return "\n" + N + " tickets sucessfully booked. \nName: " + Name + " ,Source: " + S + " ,Destination: " + D;
                }
                else {
                    if(Tick[i][1] < N)
                    {
                        return "\nTickets couldn't be booked. No of Available Tickets are: " + Tick[i][1];
                    }
                    else if (N <= 0){
                        return "\nCheck the no. of tickets.";
                    }
                }
            }
        }
        return "\nEnter the Correct Route.";
	}
    
    public String CancelTicket(String FName, String Name, String S, String D, int N) throws RemoteException
    {
        if (Name.length() < NoOfLettersInName)
        {
            return "\nEnter a valid name.";
        }
        for(i = 0; i < Route.length; i++)
        {
            if(S.equalsIgnoreCase(Route[i][0]) && D.equalsIgnoreCase(Route[i][1]))
            {
                if(N > 0 && ((Tick[i][1] + N) <= Tick[i][0]))
                {
                    Tick[i][1] += N;
                    passen = new Passenger(FName, Name, S, D, N, "Cancelled");
                    passengerList.add(passen);
                    return "\n" + N + " tickets sucessfully cancelled. \nName: " + Name + " ,Source: " + S + " ,Destination: " + D;
                }
                else
                {
                    if((Tick[i][1] + N) <= Tick[i][0])
                    {
                        return "\nTickets couldn't be cancelled. Try Again.";
                    }
                    else if (N <= 0){
                        return "\nCheck the no. of tickets.";
                    }
                }
            }
        }
        return "\nEnter the Correct Route.";
    }
    
    public String PassengerInfo() throws RemoteException{
        String m = "";
        for(int i = 0; i < passengerList.size(); i++) {
            passen = passengerList.get(i);
            m += "FN - " + passen.getFlightName() + "\tN - " + passen.getName() + "\tS - " + passen.getSource() + "\tD - "+passen.getDestination() + "\tET - " + passen.getEcoQuan() + "\tStatus - " + passen.getStatus() + "\n";
        }
        return m;
    }
}
