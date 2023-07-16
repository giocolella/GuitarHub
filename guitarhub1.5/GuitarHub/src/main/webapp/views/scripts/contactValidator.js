function validateContactsForm(event) {
    var username = document.getElementById("usernameInput").value;
    var email = document.getElementById("emailInput").value;
    var oggettoInput = document.getElementById("oggettoInput").value;
    var testoInput = document.getElementById("testoInput").value;
  
    if (username.trim() === "" || email.trim() === "" || testoInput.trim() === "") {
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
    
     var oggettoRegex = /^[a-zA-Z0-9]*$/;
    if (!oggettoRegex.test(oggettoInput)) {
     event.preventDefault(); 
      $('#errorDisplay').html("L'oggetto pu&ograve contenere solo lettere e numeri!");
      return false;
    }
    
    var testoRegex = /^[a-zA-Z0-9]*$/;
    if (!testoRegex.test(testoInput)) {
      event.preventDefault(); 
      $('#errorDisplay').html("La descrizione pu&ograve contenere solo lettere e numeri!");
      return false;
    }
    
    return true;
  }
  