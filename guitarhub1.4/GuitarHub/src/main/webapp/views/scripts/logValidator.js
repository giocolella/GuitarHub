function validateLogForm(event) {
    var email = document.getElementById("emailLog").value;
    var password = document.getElementById("passLog").value;
  
    if (email.trim() === "" || password.trim() === "") {
	  event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      event.preventDefault(); 
      $('#errorDisplay').html("L'email non &eacute valida");
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
      $('#errorDisplay').html("La password deve contenere almeno una lettera ed un numero");
      return false;
    }
  
    return true;
  }