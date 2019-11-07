import AirlineInterfaceApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.*;

class AirlineImpl extends AirlineInterfacePOA {
    private ORB orb;
    public void setORB(ORB orb_val) {
        orb = orb_val;
    }
    // implement sayHello() method this definition can be replaced with own method
    
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
    
    
    public AirlineImpl() {
        
        /*    flights[0] = new Flight("Indigo", 15540, max);
         flights[1] = new Flight("Jet Airways", 19876, max);
         flights[2] = new Flight("Air India", 22110, max);
         */
        for(i = 0; i < Route.length; i++) {
            Tick[i][0] = max;
            Tick[i][1] = max;
        }
    }
    
    public String Inventory()  {
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
    
    public String BookTicket(String FName, String Name, String S, String D, int N)  {
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
    
    public String CancelTicket(String FName, String Name, String S, String D, int N) 
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
    
    public String PassengerInfo() {
        String m = "";
        for(int i = 0; i < passengerList.size(); i++) {
            passen = passengerList.get(i);
            m += "FN - " + passen.getFlightName() + "\tN - " + passen.getName() + "\tS - " + passen.getSource() + "\tD - "+passen.getDestination() + "\tET - " + passen.getEcoQuan() + "\tStatus - " + passen.getStatus() + "\n";
        }
        return m;
    }
    
    
    // implement shutdown() method
    public void shutdown()
    {
        orb.shutdown(true);
    }
}
public class AirlineServer
{
    public static void main(String args[])
    {
        try
        {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            // create servant and register it with the ORB
            AirlineImpl airlineImpl = new AirlineImpl();
            airlineImpl.setORB(orb);
            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(airlineImpl);
            AirlineInterface href = AirlineInterfaceHelper.narrow(ref);
            // get the root naming context
            org.omg.CORBA.Object objRef =
            orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            // bind the Object Reference in Naming
            String name = "Hello";
            NameComponent path[] = ncRef.to_name( name );
            ncRef.rebind(path, href);
            System.out.println("AirlineServer ready and waiting ...");
            // wait for invocations from clients
            orb.run();
        }
        catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
        System.out.println("AirlineServer Exiting ...");
    }
}
