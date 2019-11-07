

public class Passenger{
    String Name;
    String Source;
    String Destination;
    int EconomySeats;
    String Status;
    String flight;
    
    public Passenger(String FName, String name,String source,String destination,int eco, String Status){
        
        this.flight = FName;
        this.Name=name;
        this.Status=Status;
        this.Source=source;
        this.Destination=destination;
        EconomySeats = eco;
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
    
