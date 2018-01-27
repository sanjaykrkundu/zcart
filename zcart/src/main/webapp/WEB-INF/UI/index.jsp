<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:out value='resources/img/redcart.png'/>">
<title>Z-Cart | Buy Online</title>
<link rel="stylesheet" href="<c:out value='resources/css/layout.css'/>" />
</head>
<body>
<div class="container">
<header>
       <nav class="small-nav">
		<c:url var="link_cart" value="/cart.html"></c:url>
		<a href="${link_cart}"><li>Cart</li></a>
		<c:url var="link_order" value="/orders.html"></c:url>
		<a href="${link_order}"><li>Orders</li></a>
		<c:if test="${empty sessionScope.customer}">
			<c:url var="link_login" value="/login.html"></c:url>
			<a href="${link_login}"><li>Login</li></a>
			<c:url var="link_signup" value="/signup.html"></c:url>
			<a href="${link_signup}"><li>Signup</li></a>
		</c:if>

		<c:if test="${not empty sessionScope.customer}">
			<c:url var="link_profile" value="/profile.html"/>
			<a href="${link_profile}"><li>Profile</li></a>
			<c:url var="link_logout" value="/logout.html"></c:url>
			<a href="${link_logout}"><li>Logout</li></a>
		</c:if>
	</nav>
      <nav>
		<c:url var="link_home" value="/index.html"></c:url>
		<a href="${link_home}"><li>Home</li></a> <a href="#"><li
			class="has-child">Category
				<ul>
					<c:url var="link_book" value="/category/book"></c:url>
					<a href="${link_book}"><li>Book</li></a>
					<c:url var="link_laptop" value="/category/laptop"></c:url>
					<a href="${link_laptop}"><li>Laptop</li></a>
					<c:url var="link_shoe" value="/category/shoe"></c:url>
					<a href="${link_shoe}"><li>Shoe</li></a>
				</ul>
		</li></a>
			<c:url var="link_about" value="/aboutus.html"></c:url> 
			<a href="${link_about}"><li>About Us</li></a> 
			<c:url var="link_contact" value="/contactus.html"></c:url>
			<a href="${link_contact}"><li>Contact Us</li></a>
	</nav>
    </header>

<div class="main">
	<main>
		<div>
			<header style="background-color:transparent">
				  <div class="head">
		            <div class="logo">
		                <img src="<c:out value='resources/img/redcart.png'/>">
		                <h2 style="color:#2d2d2d !important">Cart</h2>&nbsp;
		            </div>
		        </div>
		    </header>    
			<form action='<c:url value="/search"/>' method="post">
				<input type="text" name="query" class="input-300" required/>
		        <input type="submit" value="Search"/>
		    </form>
	    </div>
    </main>
	
	<div id="categories">
		<a href="category/book" class="category"><img alt="Books" src="<c:out value='resources/img/books.jpg'/>"></a> 
		<a href="category/laptop" class="category"><img alt="Laptops" src="<c:out value='resources/img/laptops.jpg'/>"></a>
		<a href="category/shoe" class="category"><img alt="Shoes" src="<c:out value='resources/img/shoes.jpg'/>"></a>
	</div>
	
    

</div>
</div>

<footer>
    2017 &copy; www.zcart.com
</footer>

</body>
</html>
