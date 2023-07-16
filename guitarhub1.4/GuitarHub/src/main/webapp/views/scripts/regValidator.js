function validateRegForm(event) {
    var username = document.getElementById("username").value;
    var email = document.getElementById("emailInput").value;
    var password = document.getElementById("passInput").value;
 	 
 
    if (username.trim() === "" || email.trim() === "" || password.trim() === "") {
	  event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
	  event.preventDefault(); 
      $('#errorDisplay').html("L'email non &eacute valida!");
      return false;
    }

    var usernameRegex = /^[a-zA-Z0-9]*$/;
    if (!usernameRegex.test(username)) {
      event.preventDefault(); 
      $('#errorDisplay').html("L'username non &eacute valido");
      return false;
    }
  
    if (password.length < 8) {
	  event.preventDefault(); 
      $('#errorDisplay').html("La password deve contenere almeno 8 caratteri");
      return false;
    }
  
   var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
   if (!passwordRegex.test(password)) {
	  event.preventDefault(); 
	  $('#errorDisplay').html("Password must contain at least 8 character, one must be a letter and one must be a number");
      return false;
    }
    
    return true;
  }
  