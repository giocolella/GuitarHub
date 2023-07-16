<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/registration.css">
  <script src="./scripts/jquery.js" type="text/javascript"></script>
  <script src="./scripts/regValidator.js" type="text/javascript"></script>
  <title>GuitarHub - Registrazione</title>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Registrazione</h1>
        <form action="${pageContext.request.contextPath}/Registration" method="post">
            <div class="input-group">
                <div class="input-field">
                    <input type="text" id="username" name="usernameReg" onchange="validateRegUsernameForm(event)" placeholder="Username" required>
                </div>
                <div class="input-field">
                    <input type="email" id="emailInput" name="emailReg" onchange="validateRegEmailForm(event)" placeholder="Email" required>
                </div>
                <div class="input-field">
                    <input type="password" id="passInput" name="passReg" onchange="validateRegPassForm(event)" placeholder="Password" required>
                </div>
            </div>
            <input type="submit" class="btn-reg" onclick="validateRegForm(event)" value="Registrati">
            <p id="errorDisplay" class="errorField" ></p>
        </form>
    </div>
</div>
</body>
</html>