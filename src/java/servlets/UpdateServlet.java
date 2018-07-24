package servlets;

import business.Book;
import business.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String url = (String) session.getAttribute("url");
        String bookcd = request.getParameter("bookcd");
        String storeid = request.getParameter("storeid");
        String msg = request.getAttribute("msg").toString();

        ArrayList<Book> books;
        books = (ArrayList<Book>) request.getSession().getAttribute("bookinv");

        for (Book book : books) {
            if (book.getBookcd().equals(bookcd)) {
                session.setAttribute("update", book);
            }
        }

        try {
            if (action.equals("Edit Record")) {
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            if (action.equals("Update Inventory") && msg.isEmpty()) {
                UserDB.updateInventory(bookcd, storeid, (String) request.getAttribute("bookqty"));
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
        } catch (IOException | ServletException e) {
            msg = "Unexpected action " + e;
            request.setAttribute("msg", msg);
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
        String url = "/WEB-INF/ViewInventory.jsp";
        String msg = "Please enter a valid book code.";
        String bookcd = request.getParameter("bookcd"); 
        
        HttpSession session = request.getSession();
        
        if (bookcd != null && !bookcd.isEmpty()) {
            ArrayList<Book> books;
            books = (ArrayList<Book>) request.getSession().getAttribute("bookinv");
            
            for (Book book : books) {
                if (book.getBookcd().equals(bookcd)) {
                    session.setAttribute("update", book);
                    url = "/WEB-INF/Update.jsp";
                    msg = "";                    
                }
            }
                    
        }
        request.setAttribute("msg", msg);  
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
        String url = "/WEB-INF/Update.jsp";
        String msg = "Please enter a valid quantity on hand.";
        String bookcd = request.getParameter("bookcd");
        String storeid = request.getParameter("storeid");
        
        HttpSession session = request.getSession();
        String bookqty = request.getParameter("bookqty".trim());

       if (bookqty != null && !bookqty.isEmpty()) {
           try {
               int qty = Integer.parseInt(bookqty);
               session.setAttribute("qty", qty);
               UserDB.updateInventory(bookcd, storeid, bookqty);
               msg = "Update Successful";
               url = "/WEB-INF/StoreSelection.jsp";
           } catch (NumberFormatException e) {
               msg = "Please enter a valid number.  " + e;
           }
       }
       
       request.setAttribute("msg", msg);
       request.setAttribute("url", url);
       getServletContext().getRequestDispatcher(url).forward(request, response);
        
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
