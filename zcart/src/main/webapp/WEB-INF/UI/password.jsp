<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Change Password | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
<script type="text/javascript" src="<c:out value='resources/scripts/script.js'/>" ></script>
</head>
<body>
<div class="container">
<%@include file="header.jsp"%>
<div class="main">

<main>
	<br/><br/>
    <form:form action="changePassword.do" method="POST" onsubmit="return checkPassword('password','cpassword');">
    	<ul class="form">
    	
        	<p class="msg-danger">${message}</p><br/>
            <h3>Login</h3>
            <li>
                <input type="password" name="opassword" id="opassword" placeholder="Current Password" autofocus required/>
            </li>
            <li>
                <input type="password" name="password" id="password" placeholder="New Password" required/>
            </li>
            <li>
            	<input type="password" name="cpassword" id="cpassword" placeholder="Confirm Password" required/>
            </li>
            <li>
                <input type="submit" value="Update" class="btn"/>
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