<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/login.css">
  <script src="./scripts/jquery.js" type="text/javascript"></script>
  <script src="./scripts/logValidator.js" type="text/javascript"></script>
  <title>GuitarHub - Login</title>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Login</h1>
        <form>
            <div class="input-group">
                <div class="input-field">
                    <input type="email" id="emailLog" name="emailLog" placeholder="Email" required>
                </div>
                <div class="input-field">
                    <input type="password" id="passLog" name="passLog" placeholder="Password" required>
                </div>
            </div>
            <input type="submit" class="btn-log" onclick="validateLogForm(event)" value="Login">
            <p id="errorDisplay" class="errorField" ></p>
        </form>
    </div>
</div>
</body>
</html>