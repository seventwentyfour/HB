
package servlets;


import business.Store;
import business.User;
import business.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String url = "/Logon.jsp";
        String msg;

        String userid = request.getParameter("userid").trim();
        String pwdattempt = request.getParameter("password").trim();            
            
        if (userid == null || pwdattempt == null || userid.isEmpty() || pwdattempt.isEmpty()) {
            msg = "Please fill out both text boxes.";
        }
        else {
            int password = Integer.parseInt(request.getParameter("password").trim());
            User user = new User(userid, password);
            HttpSession session = request.getSession();

            if (UserDB.userAuth(user) == true) {
                UserDB.userInfo(user);
                msg = "User authenticated.";
                session.setAttribute("user", UserDB.userInfo(user));

                ArrayList<Store> sl = UserDB.storeList();
                session.setAttribute("sl", sl);

                Cookie uid = new Cookie("userid", userid);
                uid.setMaxAge(60*10);
                uid.setPath("/");
                response.addCookie(uid);

                url = "/WEB-INF/StoreSelection.jsp";

            } else {
                msg = "User failed to authenticate.";
                session.removeAttribute("user");
                url = "/Logon.jsp";
            }

        }
        request.setAttribute("msg", msg);        
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        disp.forward(request, response);
        
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
