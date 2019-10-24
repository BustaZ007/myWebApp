<%@ page import="javax.servlet.http.Cookie"%>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Boolean errLogin  = (Boolean)request.getSession().getAttribute("errLogin");
    Boolean errPassword = (Boolean)request.getSession().getAttribute("errPassword");
    Boolean errMail = (Boolean)request.getSession().getAttribute("errMail");
    if(errLogin == null){
        errLogin = false;
    }
    if(errPassword == null){
        errPassword = false;
    }
    if(errMail == null){
        errMail = false;
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
			        <input class="mail" type="text" name="email" placeholder="email" required />
			        <input class="password" type="password" name="password" placeholder="password" required />
		    	</div>
		        <input class="btn" type="submit" value="Register"/>
		    </form>

            <%
                if(errLogin){
                    out.println("<span class='error'>Login error</span>");
                    request.getSession().setAttribute("errLog", false);
                }
                if(errPassword){
                    out.println("<span class='error'>Password error</span>");
                    request.getSession().setAttribute("errMail",false);
                }
                if(errMail){
                    out.println("<span class='error'>Email error</span>");
                    request.getSession().setAttribute("errMail",false);
                }
            %>
		</div>
	</body>
	<script type="text/javascript" src="src/js/swforms.js"></script>
</html>