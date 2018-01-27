<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Contact Us | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<div class="main">

			<main> <br />
			<br />
			<form:form action="feedback.do" method="POST" commandName="feedback">
				<ul class="form">

					<p class="msg-danger">${message}</p>
					<br />
					<h3>Feedback</h3>
					<li><form:input path="name" placeholder="Enter Your Name" autofocus="true" required="required" /></li>
					<li>
					<textarea name="msg" id="msg" placeholder="Feedback" required></textarea>
					</li>
					<li><input type="submit" value="Post" class="btn" /> <input
						type="reset" value="Clear" class="btn" /></li>
				</ul>
			</form:form> </main>

		</div>
	</div>

	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>