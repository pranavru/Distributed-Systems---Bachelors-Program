/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Airline Stateless Code
 */
package AirlineInterface;

import javax.ejb.Local;

/**
 *
 * @author pranavruparelia
 */
@Local
public interface NewSessionBeanLocal {

    public String BookTicket(String Fname, String Name, String Source, String Destination, int NoofTickets);

    public String CancelTicket(String Fname, String Name, String Source, String Destination, int NoofTickets);

    public String Inventory();

    public String PassengerInfo();
}
