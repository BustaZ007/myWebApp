
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String login = (String)request.getAttribute("log");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8' />
        <title></title>
    </head>
    <body>
        <div>Error in parameter "path". Use parameter "path" in addres line.
        Or use root directory: <br>
        <%
            out.println("<a href = 'http://localhost:8088/?path=/Users/pavelzaborin/MWA/" + login + "'>Root directory</a>");
        %>
        </div>
    </body>
</html>