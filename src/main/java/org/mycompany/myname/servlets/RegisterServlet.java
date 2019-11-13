package org.mycompany.myname.servlets;

import org.mycompany.myname.database.UserProfileDaoHib;
import org.mycompany.myname.model.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private String userCookieName = "loginedUser";
    private UserProfileDaoHib hib = new UserProfileDaoHib();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        UserProfile user = hib.findUserByLoginOrEmail(login);
        try {
            if(user != null){
                request.getSession().setAttribute("errLogin", true);
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            user = hib.findUserByLoginOrEmail(email);
            if(user != null){
                request.getSession().setAttribute("errMail", true);
                response.sendRedirect(request.getContextPath() + "/login");
            }
            else {
                hib.addUser(login,email,pass);
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