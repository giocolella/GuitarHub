function validateReview(event) {
    var title = document.getElementById("review-title").value;
    //var score = document.getElementById("review-score").value;
    var description = document.getElementById("review-description").value;
  
    if (title.trim() === "" || description.trim() === "") {
     event.preventDefault(); 
      $('#errorDisplay').html("Ci sono dei campi vuoti!");
      return false;
    }
  
    var titleRegex = /^[a-zA-Z0-9]*$/;
    if (!titleRegex.test(title)) {
      event.preventDefault(); 
      $('#errorDisplay').html("Il titolo pu&ograve contenere solo lettere e numeri!");
      return false;
    }
  
    var descriptionRegex = /^[a-zA-Z0-9]*$/;
    if (!descriptionRegex.test(description)) {
     event.preventDefault(); 
      $('#errorDisplay').html("La descrizione pu&ograve contenere solo lettere e numeri!");
      return false;
    }
  
    return true;
  }
  