/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Airline Stateless Code
 */
package AirlineInterface;


import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author pranavruparelia
 */
@Stateless
public class NewSessionBean implements NewSessionBeanLocal {

    String str = "", S = "", D = "", Name = "", FName = "";
    int N, i = 0, j = 0, max = 20, att = 2, data = 40, NoOfLettersInName = 3;
    
    String[][] Route = {
    {"Mumbai", "Delhi"},
    {"Delhi", "Chennai"},
    {"Chennai", "Goa"},
    {"Goa", "Chennai"},
    };
    
    ArrayList<Passenger> passengerList = new ArrayList<>();
    Passenger passen;

    int Tick[][] = new int[Route.length][att];

    public NewSessionBean() {
        for(i = 0; i < Route.length; i++) {
            Tick[i][0] = max;
            Tick[i][1] = max;
        }
    }
    
    @Override
    public String Inventory() {
        for(i = 0; i < Route.length; i++) {
            str += "\n";
            for(j = 0; j < att; j++) {
                str += Route[i][j] + "\t\t";
            }
            str += Tick[i][1];
        }
       return str;
    }
    
    @Override
    public String BookTicket(String Fname, String Name, String Source, String Destination, int NoofTickets) {
    if (Name.length() < NoOfLettersInName) {
            return "\nEnter a valid name.";
        }
        for(i = 0; i < Route.length; i++) {
           
            if(Source.equalsIgnoreCase(Route[i][0]) && Destination.equalsIgnoreCase(Route[i][1])) {
            
                if(NoofTickets > 0 && Tick[i][1] >= NoofTickets) {
                    Tick[i][1] -= NoofTickets;
                    passen = new Passenger(Fname, Name, Source, Destination, NoofTickets, "Booked");
                    passengerList.add(passen);
                    return "\n" + NoofTickets + " tickets sucessfully booked. \nName: " + Name + " ,Source: " + Source + " ,Destination: " + Destination;
                }
                else {
                    if(Tick[i][1] < NoofTickets)
                    {
                        return "\nTickets couldn't be booked. No of Available Tickets are: " + Tick[i][1];
                    }
                    else if (NoofTickets <= 0){
                        return "\nCheck the no. of tickets.";
                    }
                }
            }
        }
        return "\nEnter the Correct Route.";
    }

    /**
     *
     * @param Fname
     * @param Name
     * @param Source
     * @param Destination
     * @param NoofTickets
     * @return
     */
    
    @Override
    public String CancelTicket(String Fname, String Name, String Source, String Destination, int NoofTickets) {
    if (Name.length() < NoOfLettersInName)
        {
            return "\nEnter a valid name.";
        }
        for(i = 0; i < Route.length; i++)
        {
            if(Source.equalsIgnoreCase(Route[i][0]) && Destination.equalsIgnoreCase(Route[i][1]))
            {
                if(NoofTickets > 0 && ((Tick[i][1] + NoofTickets) <= Tick[i][0]))
                {
                    Tick[i][1] += NoofTickets;
                    passen = new Passenger(Fname, Name, Source, Destination, NoofTickets, "Cancelled");
                    passengerList.add(passen);
                    return "\n" + NoofTickets + " tickets sucessfully cancelled. \nName: " + Name + " ,Source: " + Source + " ,Destination: " + Destination;
                }
                else
                {
                    if((Tick[i][1] + NoofTickets) <= Tick[i][0])
                    {
                        return "\nTickets couldn't be cancelled. Try Again.";
                    }
                    else if (NoofTickets <= 0){
                        return "\nCheck the no. of tickets.";
                    }
                }
            }
        }
        return "\nEnter the Correct Route.";
    }
    
    /**
     *
     * @return
     */
    @Override
    public String PassengerInfo() {
        String m = "";
        for(int i1 = 0; i1 < passengerList.size(); i1++) {
            passen = passengerList.get(i1);
            m += "FN - " + passen.getFlightName() + "\tN - " + passen.getName() + "\tS - " + passen.getSource() + "\tD - "+passen.getDestination() + "\tET - " + passen.getEcoQuan() + "\tStatus - " + passen.getStatus() + "\n";
        }
        return m;
    }
}

class Passenger {

    /**
     *
     */
    public String Status;
    public int EconomySeats;
    public String Destination;
    public String Source;
    public String Name;
    public String flight;
    
    public Passenger(String FName, String name,String source,String destination,int eco, String Status){
        this.Status = Status;
        this.flight = FName;
        this.Name=name;
        this.Source=source;
        this.Destination=destination;
        this.EconomySeats = eco;
        
    }
    
    String getFlightName() {
        return this.flight;
    }
    
    String getName() {
        return this.Name;
    }
    
    String getSource() {
        return this.Source;
    }
    String getDestination() {
        return this.Destination;
    }
    int getEcoQuan() {
        return this.EconomySeats;
    }
    
    String getStatus() {
        return this.Status;
    }
}

