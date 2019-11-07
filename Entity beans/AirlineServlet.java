/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Entity Services
*/
package AS;

import ASE.AirlineServEntity;
import ASE.AirlineServEntityFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pranavruparelia
 */
public class AirlineServlet extends HttpServlet {

    @EJB
    private AirlineServEntityFacadeLocal airlineServEntityFacade;

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
        int Cid = Integer.parseInt(request.getParameter("CID"));
    
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            AirlineServEntity ae = new AirlineServEntity();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AirlineServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            switch (ch) {
                case 0:
                    ae.setCid(Cid);
                    ae.setFname(Fname);
                    ae.setName(Name);
                    ae.setSource(Source);
                    ae.setDestination(Destination);
                    ae.setNoofTickets(Tickets);
                    airlineServEntityFacade.create(ae);
                    out.println("Tickets booked Successfully!! on ID: <h1>" + ae.getId() + "</h1>");
                    break;
                
                case 1:
                    
                    ae.setId((long)Cid);
                    ae.setFname(Fname);
                    ae.setName(Name);
                    ae.setSource(Source);
                    ae.setDestination(Destination);
                    ae.setNoofTickets(Tickets);
                    
                    airlineServEntityFacade.edit(ae);
                    out.println("Data Successfully Updated!!");
                    break;
                    
                
                case 2:
   
                    ae.setId((long)Cid);
                    airlineServEntityFacade.remove(ae);
                    out.println("Successfully Record Deleted!!");
                    break;
                    
                case 3:
                    List<AirlineServEntity> aS = airlineServEntityFacade.findAll();
                    
                   
                    for(int i = 0; i < 100; i++)
                    {
                        if(aS.get(i).getId() == null)
                        {
                            out.println("<h1>No more records Found.</h1>");
                        }
                        out.println("<h3>ID : " +aS.get(i).getId() + "\tFname : " + aS.get(i).getFname() + "\tName : " + aS.get(i).getName() + "\tSource : " + aS.get(i).getSource() + "\tDestination : " + aS.get(i).getDestination() + "\tNoofTickets : " + aS.get(i).getNoofTickets() + "</h3>");
                    }
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

}
