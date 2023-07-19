<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/login.css">
  <script src="${pageContext.request.contextPath}/views/scripts/jquery.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/views/scripts/logValidator.js" type="text/javascript"></script>
  <title>GuitarHub - Login</title>
</head>
<body>
<div class="container">
    <div class="form-box">
        <h1>Login</h1>
        <form action="${pageContext.request.contextPath}/Login" method="post">
            <div class="input-group">
                <div class="input-field">
                    <input type="email" id="emailLog" name="emailLog" onchange="validateEmailLogForm(event)" placeholder="Email" required>
                </div>
                <div class="input-field">
                    <input type="password" id="passLog" name="passLog" onchange="validatePassLogForm(event)" placeholder="Password" required>
                </div>
            </div>
            <div class="aDiv">
            <a href="${pageContext.request.contextPath}/views/registration.jsp" class="aReg">Non sei registrato? Allora <span id="lineReg">Registrati</span></a>
            </div>
            <input type="submit" class="btn-log" onclick="validateLogForm(event)" value="Login">
            <p id="errorDisplay" class="errorField" >
            
             <% String errors = (String) request.getAttribute("errors");
                   if (errors != null) {
                       out.println(errors);
                       request.removeAttribute("errors");
                   }
                %>
            
            </p>
        </form>
    </div>
</div>
</body>
</html>