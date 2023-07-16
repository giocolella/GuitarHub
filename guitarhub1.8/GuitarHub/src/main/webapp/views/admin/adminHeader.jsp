<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/header.css">
<script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
</head>
<body>
	<section id="header">
		<a href="#"><img src="../../images/logoresdef.jpg" class="logo"
			alt=""></a>
		<nav>
			<ul id="navbar">
				<li><a href="./adminSearch.jsp">Ricerca Prodotti</a></li>
				<li><a href="./adminOrders.jsp">Ricerca Ordini</a></li>
				<li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
			</ul>
		</nav>
	</section>

	<script>
		let subMenu = document.getElementById("subMenu");

		function toggleMenu() {
			subMenu.classList.toggle("open-menu");
		}
	</script>

</body>
</html>