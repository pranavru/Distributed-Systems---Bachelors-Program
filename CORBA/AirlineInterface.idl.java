module AirlineInterfaceApp {
    interface AirlineInterface {
        string BookTicket(in string FName,in string Name,in string Source,in string Destination,in  long NoOfTickets);
        string CancelTicket(in string FName,in string Name,in string Source,in string Destination,in  long NoOfTickets);
        string Inventory();
        string PassengerInfo();
        
        oneway void shutdown();
    };
};
