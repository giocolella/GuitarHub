<%@ page isErrorPage="true" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<link rel="stylesheet" href="../css/error404.css">
<script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
<title>GuitarHub - Error</title>
</head>
<body>
	<div class="container">
		<h2>Oops! La richiesta ha impiegato troppo tempo</h2>
		<h1>502</h1>
		<p>Ci scusiamo per il disiagio</p>
		<a href="${pageContext.request.contextPath}/views/home.jsp">Torna alla home</a>
	</div>
</body>
</html>
