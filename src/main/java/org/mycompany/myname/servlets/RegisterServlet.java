package org.mycompany.myname.servlets;

import org.mycompany.myname.model.UserProfile;
import org.mycompany.myname.service.AccountService;

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
        UserProfile user = AccountService.getUserByLogin(login);
        if(user != null){
            request.getSession().setAttribute("errLogin", true);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        user = AccountService.getUserByMail(email);
        if(user != null){
            request.getSession().setAttribute("errMail", true);
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else {
            user = new UserProfile(login, pass, email);
            AccountService.addNewUser(user);
            request.getSession().setAttribute(userCookieName, user);
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}