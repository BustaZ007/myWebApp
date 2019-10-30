package org.mycompany.myname.servlets;

import org.mycompany.myname.database.JDBCMyClass;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private String userCookieName = "loginedUser";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        Boolean userFind = JDBCMyClass.findUserByLogin(login);
        try {
            if(userFind){
                request.getSession().setAttribute("errLogin", true);
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            userFind = JDBCMyClass.findUserByMail(email);
            if(userFind){
                request.getSession().setAttribute("errMail", true);
                response.sendRedirect(request.getContextPath() + "/login");
            }
            else {
                JDBCMyClass.addUser(login,email,pass);
                request.getSession().setAttribute(userCookieName, login);
                response.sendRedirect(request.getContextPath() + "/");
            }
            System.out.println("Registr complete for " + login);
        }
        catch (NullPointerException ex){
            ex.getStackTrace();
        }

    }
}