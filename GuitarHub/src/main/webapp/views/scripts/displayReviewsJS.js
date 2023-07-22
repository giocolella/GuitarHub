function displayReviews(reviewsData) {
  let reviewsContainer = document.getElementById("reviews-container");

  reviewsContainer.innerHTML = "";

  reviewsData.forEach(function(review) {
    let reviewBox = document.createElement("div");
    reviewBox.classList.add("review-box");

    let reviewParagraph = document.createElement("p");
    reviewParagraph.textContent = "Username: " + review.username;
    reviewParagraph.classList.add("reviewParagraph");
    reviewBox.appendChild(reviewParagraph);
    
    reviewParagraph = document.createElement("p");
    reviewParagraph.classList.add("reviewParagraph");
    for (let i = 0; i < review.score; i++) {
      let starIcon = document.createElement("i");
      starIcon.classList.add("fa-solid", "fa-star");
      reviewParagraph.appendChild(starIcon);
    }
    reviewBox.appendChild(reviewParagraph);

    reviewParagraph = document.createElement("p");
    reviewParagraph.textContent = "Titolo: " + review.title;
    reviewParagraph.classList.add("reviewParagraph");
    reviewBox.appendChild(reviewParagraph);

    reviewParagraph = document.createElement("p");
    reviewParagraph.textContent = "Descrizione: " + review.description;
    reviewParagraph.classList.add("reviewParagraph");
    reviewBox.appendChild(reviewParagraph);

    reviewParagraph = document.createElement("p");
    reviewParagraph.textContent = "Data: " + review.reviewDate;
    reviewParagraph.classList.add("reviewParagraph");
    reviewBox.appendChild(reviewParagraph);

    reviewsContainer.appendChild(reviewBox);

    reviewsContainer.appendChild(document.createElement("br"));
  });
}
