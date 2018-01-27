<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>My Profile | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<div class="main">

			<main> 
			<br/><br/>
			<form:form action="editProfile.do" method="POST" commandName="customer">
			
			
				<ul class="form">
					<p class="msg-danger">${message}</p><br/>
					<h3>Profile</h3>
					<li>
						<form:input path="customerName" placeholder="Enter Your Name" autofocus="autofocus" required="required" maxlength="25"/>
					</li>
					<li>
						<form:input path="email" placeholder="Enter Email" required="required" maxlength="256" disabled="disabled"/>
					</li>
					<li>
						<form:input path="mobile"	placeholder="Enter Mobile No" required="required" pattern="(7|8|9)[0-9]{9}" title="10 digit mobile no"/>
					</li>
					<li class="form-group-button">
						<input type="submit" value="Update" class="btn" />
						<input type="reset"	value="Clear" class="btn" />
					</li>
					
					<li><a href="password.html" class="link">Change Password</a></li>
				</ul>
			</form:form> 
			</main>

		</div>

	</div>
	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>