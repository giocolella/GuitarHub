function validateRegForm(event) {
    let username = document.getElementById("username").value;
    let email = document.getElementById("emailInput").value;
    let password = document.getElementById("passInput").value;
 	 
 
    if (username.trim() === "" || email.trim() === "" || password.trim() === "") {
	  event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
	  event.preventDefault(); 
      $('#errorDisplay').html("L'email non &eacute valida!");
      return false;
    }

    let usernameRegex = /^[a-zA-Z0-9]*$/;
    if (!usernameRegex.test(username)) {
      event.preventDefault(); 
      $('#errorDisplay').html("L'username non &eacute valido");
      return false;
    }
  
    if (password.length < 8) {
	  event.preventDefault(); 
      $('#errorDisplay').html("La password deve contenere almeno 8 caratteri!");
      return false;
    }
  
   let passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
   if (!passwordRegex.test(password)) {
	  event.preventDefault(); 
	  $('#errorDisplay').html("La password deve contenere almeno 8 caratteri di cui almeno una lettera ed un numero! ");
      return false;
    }
    
    return true;
  }
  
  function validateRegEmailForm(event) {
    let email = document.getElementById("emailInput").value;
  
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
	  event.preventDefault();
	  $('#emailInput').css("border","1px solid red");
      $('#errorDisplay').html("L'email non &eacute valida!");
      return false;
    }
    
    $('#emailInput').css("border","2px solid green");
    $('#errorDisplay').html(""); 
    return true;
  }
  
  function validateRegUsernameForm(event) {
    let username = document.getElementById("username").value;	 

    let usernameRegex = /^[a-zA-Z0-9]*$/;
    if (!usernameRegex.test(username)) {
      event.preventDefault(); 
      $('#username').css("border","1px solid red");
      $('#errorDisplay').html("L'username non &eacute valido!");
      return false;
    }
  
	$('#username').css("border","2px solid green");
    $('#errorDisplay').html("");  
    return true;
  }
  
  function validateRegPassForm(event) {
    let password = document.getElementById("passInput").value;
  
    if (password.length < 8) {
	  event.preventDefault();
	  $('#passInput').css("border","1px solid red");
      $('#errorDisplay').html("La password deve contenere almeno 8 caratteri!");
      return false;
    }
  
   let passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
   if (!passwordRegex.test(password)) {
	  event.preventDefault(); 
	  $('#passInput').css("border","1px solid red");
	  $('#errorDisplay').html("La password deve contenere almeno 8 caratteri di cui almeno una lettera ed un numero!");
      return false;
    }
    
    $('#passInput').css("border","2px solid green");
    $('#errorDisplay').html("");
    return true;
  }
  