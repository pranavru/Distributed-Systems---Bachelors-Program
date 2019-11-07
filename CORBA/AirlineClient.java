import AirlineInterfaceApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;

public class AirlineClient
{
    static AirlineInterface ai;
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);
            // get the root naming context
            org.omg.CORBA.Object objRef =
            orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            // resolve the Object Reference in Naming
            String name = "Hello";
            ai = AirlineInterfaceHelper.narrow(ncRef.resolve_str(name));
            
            System.out.println("\n---Welcome to the Airline Services---");
            int Choice;
            int Password = 123;
            do {
                System.out.println("\nTo view the Routes: Press 0\nTo Book a Ticket: Press 1\nTo Cancel a Ticket: Press 2\nTo View Passenger Details: Press 3\nTo Exit: Press 4");
                System.out.println("Enter Choice: ");
                Choice = sc.nextInt();
                String S,D,Name, FName;
                int N;
                switch(Choice) {
                    case 0:
                        System.out.println("\nSource \t\t Destination \t Tickets Available");
                        System.out.println(ai.Inventory());
                        break;
                    case 1:                                                                         //Book Ticket
                        System.out.println("\nWelcome to the Booking Window");
                        System.out.println("Enter the Source: ");
                        S = sc.next();
                        System.out.println("Enter the Destination: ");
                        D = sc.next();
                        System.out.println("Enter the no. of tickets to be booked: ");
                        N = sc.nextInt();
                        System.out.println("Enter your name: ");
                        Name = sc.next();
                        System.out.println("Enter your Flight Name: ");
                        FName = sc.next();
                        System.out.println(ai.BookTicket(FName, Name, S, D, N));
                        break;
                    case 2:                                                                         //Cancel Ticket
                        System.out.println("\n\nWelcome to the Cancellation Window");
                        System.out.println("Enter your Flight Name: ");
                        FName = sc.next();
                        System.out.println("Enter your name: ");
                        Name = sc.next();
                        System.out.println("Enter the Source: ");
                        S = sc.next();
                        System.out.println("Enter the Destination: ");
                        D = sc.next();
                        System.out.println("Enter the no. of tickets to be cancelled: ");
                        N = sc.nextInt();
                        System.out.println(ai.CancelTicket(FName, Name, S, D, N));
                        break;
                    case 3:                                                                         //To Print the Database
                        System.out.println("To view the details enter the password of the Server: ");
                        Password = sc.nextInt();
                        String m = ai.PassengerInfo();
                        if(Password == 1234) {
                            System.out.println("-------Password Verified-------");
                            System.out.println(m);
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Enter a valid Choice");
                        break;
                }
            } while (Choice != 4);
            
            ai.shutdown();
        }
        catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }
}
