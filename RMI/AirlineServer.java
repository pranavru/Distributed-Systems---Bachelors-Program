import java.rmi.*;

public class AirlineServer
{
    public static void main(String args[])
    {
        try
        {
            Naming.rebind("Airline",new Airline());
            System.out.println("Server is connected and waiting for the client");
        }
        catch(Exception e){
            System.out.println("Server could not connect: "+e);
        }
    }
}
