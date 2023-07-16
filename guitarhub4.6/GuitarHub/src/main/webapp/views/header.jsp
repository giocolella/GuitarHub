<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/header.css">
<script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
</head>
<body>
	<section id="header">
		<a href="#"><img src="${pageContext.request.contextPath}/images/logoresdef.jpg" class="logo"
			alt=""></a>
		<nav>
			<ul id="navbar">
				<li><a class="active" href="./home.jsp">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/views/productSearch.jsp">Negozio</a></li>
				<li><a href="${pageContext.request.contextPath}/views/aboutus.jsp">Chi siamo</a></li>
				<li><a href="${pageContext.request.contextPath}/views/contacts.jsp">Contatti</a></li>
				<li><a href="${pageContext.request.contextPath}/views/login.jsp">Accedi</a></li>
				<li><a href="#" id="imgLogin"><img id="userProfilePicture" src="${pageContext.request.contextPath}/images/profile/user.png" alt="" class="user-pic" onclick="toggleMenu()"></a></li>
				<li><a href="${pageContext.request.contextPath}/views/cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a></li>
			</ul>
		
			
			<div class="sub-menu-wrap" id="subMenu">
				<div class="sub-menu">
					<div class="user-info">
						<img id="userProfilePicture" src="" alt="" class="user-pic">
						<h3 id="userName">Nome</h3>
					</div>
					<hr>

					<a href="${pageContext.request.contextPath}/views/orders.jsp" class="sub-menu-link"> 
						<img src="${pageContext.request.contextPath}/images/profile/setting.png" id="userPicture">
						<p>I tuoi ordini</p> <span>></span>
					</a>
					<a href="${pageContext.request.contextPath}/Logout" class="sub-menu-link"> 
						<img src="${pageContext.request.contextPath}/images/profile/logout.png" id="userPicture">
						<p>Logout</p> <span>></span>
					<a href="#" class="sub-menu-link" style="color:red;" onclick="confirmDelete()"> 
						<img src="${pageContext.request.contextPath}/images/profile/user.png" id="userPicture">
						<p style="color:red;">Elimina account</p> <span>></span>
					</a> 
					</a>
				</div>
			</div>
		</nav>
	</section>

	<script>
		let subMenu = document.getElementById("subMenu");

		function toggleMenu() {
			subMenu.classList.toggle("open-menu");
		}
	</script>
	
	<script>
    function confirmDelete() {
        var result = confirm("Are you sure you want to delete your account?");
        if (result) {
            // User clicked OK, proceed with account deletion
            window.location.href = "deleteAccount.jsp"; // Replace with the appropriate URL for account deletion
        } else {
            // User clicked Cancel, do nothing
        }
    }
</script>

</body>