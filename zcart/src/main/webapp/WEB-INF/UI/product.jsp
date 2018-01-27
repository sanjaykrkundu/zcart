<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Product | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<div class="main dir-row">

			<div class="category-list">
				<a href="../category/book" class="category">
					<h4>Books</h4>
				</a> <a href="../category/laptop" class="category">
					<h4>Laptops</h4>
				</a> <a href="../category/shoe" class="category">
					<h4>Shoes</h4>
				</a>
			</div>

			<main>
			<div class="show-item div-row">
				<div class="item">
					<p class="msg">${message}</p><br/>
					<div class="img">
						<img
							src="<c:out value='resources/img/product/${product.productID}.jpeg'/>"
							class="item-image" />
					</div>
					<h4 class="item-header">${product.productName}</h4>
					<h5 class="item-price">${product.productPrice}</h5>
				</div>
				
				<div class="product-details">
                    <h3 class="underline">${product.productName}</h3>
                    <h4>${product.productPrice}</h4>
                    <pre>${product.productDetail}</pre>
                    <form method="post" action="../cart/add/${product.productID}">
                    	<input type="number" class="small" name="quantity" min="1" value="1" max="${product.quantity}">
                        <input type="submit" value="Add to Cart">
                    </form>
                </div>

			</div>
			</main>

		</div>
	</div>

	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>