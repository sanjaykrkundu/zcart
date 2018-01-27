 <header style="min-height:50px">
 <img src="<c:out value='resources/img/redcart.png'/>" style="float:left; max-width:50px;min-width:50px;">
	<nav class="small-nav">
   		
       	<c:if test="${empty sessionScope.customer}">
        	<a href="login.html"><li>Login</li></a>
	    </c:if>
    	<c:if test="${not empty sessionScope.customer}">
    		<a href="index.html"><li>Home</li></a>
        	<a href="profile.html"><li>Profile</li></a>
        	<a href="logout.html"><li>Logout</li></a>
        </c:if>
        </nav>
</header>