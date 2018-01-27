<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='./resources/img/redcart.png'/>">
<title>Admin | Z-Cart</title>
<link rel="stylesheet"
	href="<c:out value='./resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">

		<%@include file="adminHeader.jsp"%>
		<div class="main">

			<div id="categories">
				<a href="addproduct.html" class="category"><h4>Add Product</h4></a>
			</div>
			<div id="categories">
				<a href="addstock.html" class="category"><h4>Add Stock</h4></a>
			</div>
		</div>
	</div>

	<footer> 2017 &copy; www.zcart.com </footer>
</body>
</html>
