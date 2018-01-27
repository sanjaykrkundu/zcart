<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Add Stock | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
	<div class="container">
		<%@include file="adminHeader.jsp"%>
		<div class="main">

			<main> <br />
			<br />
			<form:form action="addStock.do" method="POST">
				<ul class="form">

					<p class="msg-danger">${message}</p>
					<br />
					<h3>Add Stock</h3>
					<li><select name="productId" id="productId" required>
							<c:forEach items="${productList}" var="product">
								<option value="${product.productID}">${product.productName}</option>
							</c:forEach>
					</select></li>
					<li><input type="number" name="productQuantity"	id="productQuantity" placeholder="Product Price" required
						pattern="\d+(\.\d{1,})?" title="Enter valid Price" min="1" max="1000"/></li>
					<li><input type="submit" value="Add" class="btn" /> <input
						type="reset" value="Clear" class="btn" /></li>
				</ul>
			</form:form> </main>

		</div>
	</div>

	<footer> 2017 &copy; www.zcart.com </footer>

</body>
</html>