<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Admin Login | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
<div class="container">
<%@include file="adminHeader.jsp"%>
<div class="main">

<main>
	<br/><br/>
    <form:form action="login.do" method="POST" commandName="customer">
    	<ul class="form">
    	
        	<p class="msg-danger">${message}</p><br/>
            <h3>Admin Login</h3>
            <li>
                <input type="email" name="email" id="email" placeholder="Enter Your Email" autofocus required/>
            </li>
            <li>
                <input type="password" name="password" id="password" placeholder="Enter Your Password" required/>
            </li>
            <li>
                <input type="submit" value="Login" class="btn"/>
                <input type="reset" value="Clear" class="btn"/>
            </li>
        </ul>
    </form:form>

</main>

</div>
</div>

<footer>
    2017 &copy; www.zcart.com
</footer>

</body>
</html>