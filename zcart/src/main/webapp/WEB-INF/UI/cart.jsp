<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:url value='/resources/img/redcart.png'/>">
<title>Cart | Z-Cart</title>
<link rel="stylesheet" href="<c:url value='/resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<div class="main">

			<main>
			<div class="text-center ">
				<h2>My Cart</h2>
			</div>
			<br>
			<c:if test="${fn:length(cart.cartItems) gt 0}">
			
			<table class="cart">
				<tr>
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th></th>
				</tr>
				
				<c:forEach var="item" items="${cart.cartItems}">
					<tr>
						<td>${item.key.productName}</td>
						<td>${item.value}</td>
						<td>${item.key.productPrice * item.value}</td>
						<td><a class="link"
							href='<c:url value="/cart/remove/${item.key.productID}"/>'>Remove</a></td>
					</tr>
				</c:forEach>
				<tr>
					<th>Total</th>
					<th></th>
					<th>${cart.grandTotal}</th>
					<th></th>
				</tr>
			</table>
			
			<form action="cart/placeOrder" method="post" style="width:100%;padding:5px">
				<div class="text-center">
				<input type="submit" value="Place Order" />
				</div>
			</form>
			</c:if>
			<c:if test="${fn:length(cart.cartItems) le 0}">
				Nothing to show
			</c:if>			

			</main>

		</div>
	</div>

	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>