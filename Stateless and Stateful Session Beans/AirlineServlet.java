/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Stateful Session Beans
 */
package AirlineServ;

import airlineSession.AirlineSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pranavruparelia
 */
public class AirlineServlet extends HttpServlet {

    AirlineSessionLocal airlineSession = lookupAirlineSessionLocal();

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
            out.println("<title>Servlet AirlineServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            switch (ch) {
                case 1:
                    out.println("<h2>"+ airlineSession.BookTicket(Fname,Name,Source,Destination,Tickets) +"</h2>");
                    out.println();
                    break;
                case 2:
                    out.println("<h2>"+ airlineSession.CancelTicket(Fname,Name,Source,Destination,Tickets) +"</h2>");
                    out.println();
                    break;
                case 3:
                    out.println("<h2>" +airlineSession.PassengerInfo() + "</h2>");
                    out.println();
                    break;
                case 4:
                    out.println("<h2>" +airlineSession.Inventory() + "</h2>");
                    out.println();
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

    private AirlineSessionLocal lookupAirlineSessionLocal() {
        try {
            Context c = new InitialContext();
            return (AirlineSessionLocal) c.lookup("java:global/AirlineStateful/AirlineStateful-ejb/AirlineSession!airlineSession.AirlineSessionLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
