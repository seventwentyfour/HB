
package servlets;

import business.Book;
import business.Store;
import business.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewInventoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg="";
        String url;
        boolean showuser = false;

        HttpSession session = request.getSession();
        
        ArrayList<Store> stores;
        try {
            stores = (ArrayList<Store>) request.getSession().getAttribute("sl");
            int storeid = Integer.parseInt(request.getParameter("storeid"));
            for (Store store : stores) {
                if (store.getStoreid() == storeid) {
                   session.setAttribute("store", store);
                }
                
            }

            String storeidd = String.valueOf(storeid);
           
            ArrayList<Book> bookinv = UserDB.storeInv(storeidd);
            session.setAttribute("bookinv", bookinv);
            url = "/WEB-INF/ViewInventory.jsp";
            
        } catch (Exception e) {
            url="/WEB-INF/StoreSelection.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("showuser", showuser);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        disp.forward(request, response);
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
