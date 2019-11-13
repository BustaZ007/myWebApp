package org.mycompany.myname.servlets;

import org.mycompany.myname.model.FilesList;
import org.mycompany.myname.service.MakeFilesListService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DirectoryServlet extends HttpServlet {
    private String userCookieName = "loginedUser";

    private String homeDir = "/Users/pavelzaborin/MWA/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        try {
            HttpSession session = request.getSession();
            String login = (String) session.getAttribute(userCookieName);
            if(login == null){
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            String path = request.getParameter("path");

            if (path == null) {
                path = homeDir + login + "/";
            }

            path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            FilesList list = new MakeFilesListService().readPath(path);
            if ((path + "/").contains(homeDir + login + "/") && list != null) {
                request.setAttribute("dirs", list.getDirectories());
                request.setAttribute("files", list.getFiles());
                request.setAttribute("parent", list.getParent());
                request.setAttribute("homedir", homeDir + login + "/");
                request.setAttribute("uri", request.getRequestURI());

                getServletContext().getRequestDispatcher("/templates/index.jsp").forward(request, response);
            } else {
                request.setAttribute("log", login);
                getServletContext().getRequestDispatcher("/templates/error.jsp").forward(request, response);
            }
        } catch (NullPointerException ex) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(userCookieName, null);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
