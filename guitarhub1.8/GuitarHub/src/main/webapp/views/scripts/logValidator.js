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
  
function validateEmailLogForm(event) {
	var email = document.getElementById("emailLog").value;
	
	 var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      event.preventDefault(); 
      $('#emailLog').css("border","1px solid red");
      $('#errorDisplay').html("L'email non &eacute valida");
      return false;
    }
    
     $('#emailLog').css("border","2px solid green");
    $('#errorDisplay').html("");
    return true;
}

function validatePassLogForm(event) {
	var password = document.getElementById("passLog").value;
	
	  var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
   if (!passwordRegex.test(password)) {
      event.preventDefault(); 
      $('#passLog').css("border","1px solid red");
      $('#errorDisplay').html("La password deve contenere almeno una lettera ed un numero");
      return false;
    }
    
    $('#passLog').css("border","2px solid green");
    $('#errorDisplay').html("");
    return true;
}