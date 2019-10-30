package org.mycompany.myname.servlets;

import org.mycompany.myname.database.JDBCMyClass;

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

        String user = (String)ses.getAttribute(userCookieName);

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
        Boolean userFind = JDBCMyClass.findUserByLogin(login);
        if (userFind == null || !userFind){
            userFind = JDBCMyClass.findUserByMail(login);
            if(userFind == null || !userFind) {
                request.getSession().setAttribute("errLogin", true);
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        }
        Boolean userConnect = JDBCMyClass.tryConnect(login,pass);
        if (userConnect == null || !userConnect){
            request.getSession().setAttribute("errPassword", true);
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else {
            request.getSession().setAttribute(userCookieName, login);
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}