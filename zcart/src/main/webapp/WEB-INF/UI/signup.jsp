<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Sign Up | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
<script type="text/javascript" src="<c:out value='resources/scripts/script.js'/>" ></script>
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<div class="main">

			<main> 
			<br/><br/>
			<form:form action="signup.do" method="POST" commandName="customer" onsubmit="return checkPassword('password','cpassword');">
				<ul class="form">
					<p class="msg-danger">${message}</p><br/>
					<h3>Signup</h3>
					<li>
						<input type="text" name="customerName" placeholder="Enter Your Name" autofocus="autofocus" required maxlength="25"/>
					</li>
					<li>
						<input type="email" name="email" required placeholder="Enter Email" required="required" maxlength="256"/>
					</li>
					<li>
						<input type="password" name="password" id="password" placeholder="Password" required="required" maxlength="32"/>
					</li>
					<li>
						<input type="password" name="cpassword" id="cpassword" placeholder="Confirm Password" required="required" maxlength="32"/>
					</li>
					<li>
						<input type="tel" name="mobile"	placeholder="Enter Mobile No" required="required" pattern="(7|8|9)[0-9]{9}" title="10 digit mobile no"/>
					</li>
					<li class="form-group-button">
						<input type="submit" value="Signup" class="btn" />
						<input type="reset"	value="Clear" class="btn" />
					</li>
				</ul>
			</form:form> </main>

		</div>

	</div>
	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>