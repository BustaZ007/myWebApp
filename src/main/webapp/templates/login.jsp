<%@ page import="javax.servlet.http.Cookie"%>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Boolean errLog  = (Boolean)request.getSession().getAttribute("errLog");
    Boolean errPass = (Boolean)request.getSession().getAttribute("errPass");
    Boolean isLgnForm = (Boolean)request.getSession().getAttribute("isLgnForm");
    if(errLog == null){
        errLog = false;
    }
    if(errPass == null){
        errPass = false;
    }
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset='UTF-8' />
	    <title>InDirectory</title>
	</head>
	<body>
		<div class="wrapper">
			<form class="logform" action="/login" method="post" name="login" >
				<p> Login </p>
				<div class="inputs">
					<input class="login" type="text" name="login" placeholder="login" required />
					<input class="password" type="password" name="password" placeholder="password" required />
				</div>
				<input class="btn" type="submit" value="Login"/>
			</form>

		    <form class="regform" action="/register" method="post" name="register" >
		        <p>Register</p>
		        <div class="inputs">
			        <input class="login" type="text" name="login" placeholder="login" required />
			        <input class="password" type="password" name="password" placeholder="password" required />
		    	</div>
		        <input class="btn" type="submit" value="Register"/>
		    </form>

            <%
                if(errLog){
                    out.println("<span class='error'>Login error</span>");
                    request.getSession().setAttribute("errLog", false);
                }
                if(errPass){
                    out.println("<span class='error'>Password error</span>");
                    request.getSession().setAttribute("errMail",false);
                }
            %>
		</div>
	</body>
	<script type="text/javascript" src="src/js/swforms.js"></script>
</html>