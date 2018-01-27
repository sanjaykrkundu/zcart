<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Add Product | Z-Cart</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
<div class="container">
<%@include file="adminHeader.jsp"%>
<div class="main">

<main>
	<br/><br/>
    <form:form action="addProduct.do" method="POST" commandName="product" enctype="multipart/form-data">
    	<ul class="form">
    	
        	<p class="msg-danger">${message}</p><br/>
            <h3>Add Product</h3>
            <li>
                <input type="text" name="productName" id="productName" placeholder="Product Name" autofocus required/>
            </li>
            <li>
                <select name="productCategory" id="productCategory" required>
                	<option>Book</option>
                	<option>Laptop</option>
                	<option>Shoe</option>
                </select>
            </li>
            <li>
                <input type="text" name="productPrice" id="productPrice" placeholder="Product Price" required pattern="\d+(\.\d{1,})?" title="Enter valid Price"/>
            </li>
            <li>
                <textarea name="productDetail" id="productDetail" placeholder="Product Detail" required></textarea>
            </li>
            <li>
            	<input type="file" name="productImage" id="productImage" required/>
            </li>
            <li>
                <input type="submit" value="Add" class="btn"/>
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