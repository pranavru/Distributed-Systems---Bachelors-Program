/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevlets;

import AWSC.AirlineWebService_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author pranavruparelia
 */
public class Airline_WebServices extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Airline_WebService/Airline_WebService.wsdl")
    private AirlineWebService_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Fname = request.getParameter("Fname");
        String Name = request.getParameter("Name");
        String Source = request.getParameter("Source");
        String Destination = request.getParameter("Destination");
        int Tickets = Integer.parseInt(request.getParameter("NoofTickets"));
        int ch = Integer.parseInt(request.getParameter("choice"));
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Airline_WebServices</title>");            
            out.println("</head>");
            out.println("<body>");
            
            switch (ch) {
                case 1:
                    out.println("<h2>"+ bookTicket(Fname,Name,Source,Destination,Tickets) +"</h2>");
                    out.println("<br>");
                    break;
                case 2:
                    out.println("<h2>"+ cancelTicket(Fname,Name,Source,Destination,Tickets) +"</h2>");
                    out.println("<br>");
                    break;
                case 3:
                    
                    out.println("<h2>" + passengerInfo() + "</h2>");
                    out.println();
                    break;
                case 4:
                    out.println("<h2>" + inventory() + "</h2>");
                    out.println("<br>");
                    break;
                default:
                    out.println("<h2> Enter a Valid Choice</h2>");
                    out.println();
                    break;
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String bookTicket(java.lang.String fname, java.lang.String name, java.lang.String source, java.lang.String destination, int noofTickets) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AWSC.AirlineWebService port = service.getAirlineWebServicePort();
        return port.bookTicket(fname, name, source, destination, noofTickets);
    }

    private String cancelTicket(java.lang.String fname, java.lang.String name, java.lang.String source, java.lang.String destination, int noofTickets) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AWSC.AirlineWebService port = service.getAirlineWebServicePort();
        return port.cancelTicket(fname, name, source, destination, noofTickets);
    }

    private String inventory() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AWSC.AirlineWebService port = service.getAirlineWebServicePort();
        return port.inventory();
    }

    private String passengerInfo() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AWSC.AirlineWebService port = service.getAirlineWebServicePort();
        return port.passengerInfo();
    }

}
