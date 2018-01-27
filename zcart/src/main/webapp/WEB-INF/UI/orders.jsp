<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:url value='/resources/img/redcart.png'/>">
<title>Orders | Z-Cart</title>
<link rel="stylesheet" href="<c:url value='/resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<div class="main">

			<main> <c:forEach var="item" items="${orderList}">
			
			<br>
			</c:forEach>


			<div class="text-center ">
				<h2>My Orders</h2>
			</div>
			<br>
			<c:if test="${fn:length(orderList) gt 0}">

				<table class="cart order">
					<tr>
						<th>Product Name</th>
						<th>Quantity</th>
						<th>Price</th>
					</tr>

					<c:forEach var="item" items="${orderList}">
						<tr>
							<td>${item.value.productName}</td>
							<td>${item.key.quantity}</td>
							<td>${item.value.productPrice * item.key.quantity}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if> <c:if test="${fn:length(orderList) le 0}">
				Nothing to show
			</c:if> </main>

		</div>
	</div>

	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>