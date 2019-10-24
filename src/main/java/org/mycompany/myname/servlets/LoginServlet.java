package org.mycompany.myname.servlets;

import org.mycompany.myname.model.UserProfile;
import org.mycompany.myname.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private String userCookieName = "loginedUser";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession();

        UserProfile user = (UserProfile) ses.getAttribute(userCookieName);

        if (user == null) {
            getServletContext().getRequestDispatcher("/templates/login.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        UserProfile user = AccountService.getUserByLogin(login);
        if (user == null){
            user = AccountService.getUserByMail(login);
            if(user == null) {
                request.getSession().setAttribute("errLogin", true);
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        }
        if (user.getPass().equals(pass)){
            request.getSession().setAttribute(userCookieName, user);
            response.sendRedirect(request.getContextPath() + "/");
        }
        else {
            request.getSession().setAttribute("errPassword", true);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}