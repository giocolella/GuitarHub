<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/registration.css">
  <title>GuitarHub - Registrazione</title>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Registrazione</h1>
        <form>
            <div class="input-group">
                <div class="input-field">
                    <input type="text" placeholder="Username" required>
                </div>
                <div class="input-field">
                    <input type="email" placeholder="Email" required>
                </div>
                <div class="input-field">
                    <input type="password" placeholder="Password" required>
                </div>
            </div>
            <input type="submit" class="btn-reg" value="Registrati">
        </form>
    </div>
</div>
</body>
</html>