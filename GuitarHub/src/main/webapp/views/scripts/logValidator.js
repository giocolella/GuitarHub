function validateLogForm(event) {
    let email = document.getElementById("emailLog").value;
    let password = document.getElementById("passLog").value;
  
    if (email.trim() === "" || password.trim() === "") {
	  event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
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
  
    
   let passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
   if (!passwordRegex.test(password)) {
      event.preventDefault(); 
      $('#errorDisplay').html("La password deve contenere almeno una lettera ed un numero");
      return false;
    }
  
    return true;
  }
  
function validateEmailLogForm(event) {
	let email = document.getElementById("emailLog").value;
	
	let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
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
	let password = document.getElementById("passLog").value;
	
	let passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
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