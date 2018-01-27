<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Search | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<div class="main dir-row">

			<div class="category-list">
				<a href='<c:url value="category/book"/>' class="category">
					<h4>Books</h4>
				</a> <a href='<c:url value="category/laptop"/>' class="category">
					<h4>Laptops</h4>
				</a> <a href='<c:url value="category/shoe"/>' class="category">
					<h4>Shoes</h4>
				</a>
			</div>

			<main>
			<div class="items">

				<c:forEach items="${productList}" var="product">

					<a href='<c:url value="product/${product.productID}"/>'>
						<div class="item">
							<div class="img">
								<img
									src="<c:out value='resources/img/product/${product.productID}.jpeg'/>"
									class="item-image" />
							</div>
							<h4 class="item-header">${product.productName}</h4>
							<h5 class="item-price">${product.productPrice}</h5>
						</div>
					</a>
				</c:forEach>

			</div>
			</main>

		</div>
	</div>

	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>