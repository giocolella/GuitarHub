package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.Product;
import it.guitarhub.beans.Review;
import it.guitarhub.beans.User;
import it.guitarhub.model.ReviewDAO;
import it.guitarhub.model.UserDAO;

@WebServlet("/ReviewsPopulator")
public class ReviewsPopulatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ReviewsPopulatorServlet.class.getName());
       
    public ReviewsPopulatorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId").trim();
		Product product = new Product();
		ReviewDAO ds = new ReviewDAO();
		UserDAO ds2 = new UserDAO();
		ArrayList<Review> reviews = new ArrayList<>();
		 Gson gson = new Gson();
	     response.setContentType("application/json");
		
		if(productId != null) {
			product.setId(Integer.parseInt(productId));
			try {
				reviews = (ArrayList<Review>) ds.doRetrieveByProduct(product);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			
			if(!reviews.isEmpty()) {
				User user = new User();
				for(Review review1 : reviews) {
					try {
						user = ds2.doRetriveByKey(review1.getIdUser());
					} catch (SQLException e) {
						logger.log(Level.SEVERE, "An error occurred", e);
					}
					review1.setUsername(user.getUsername());
				}
				
			     String jsonProd = gson.toJson(reviews);
			        
			     PrintWriter out = response.getWriter();
			     out.print(jsonProd);
			     out.flush();
			} else {
				 String jsonProd = gson.toJson("Nessuna recensione");
			        
			     PrintWriter out = response.getWriter();
			     out.print(jsonProd);
			     out.flush();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
