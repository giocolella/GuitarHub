package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.Product;
import it.guitarhub.beans.Review;
import it.guitarhub.beans.User;
import it.guitarhub.model.OrderDAO;
import it.guitarhub.model.ProductDAO;
import it.guitarhub.model.ReviewDAO;

@WebServlet("/AddReview")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddReviewServlet.class.getName());
       
    public AddReviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId").trim();
		String title = request.getParameter("title").trim();
		String score = request.getParameter("score").trim();
		String description = request.getParameter("description").trim();
		OrderDAO ds2 = new OrderDAO();
		ReviewDAO ds3 = new ReviewDAO();
		ProductDAO ds4 = new ProductDAO();
		User user = new User();
		Product product = new Product();
		Review review = new Review();
		boolean isSecond = false;
		boolean isPurchased = false;
		Gson gson = new Gson();
		String jsonProd = "weird";
		
		response.setContentType("application/json");
		 PrintWriter out = response.getWriter();
		
		if(request.getSession().getAttribute("User")== null) {
			jsonProd = gson.toJson("Non sei autenticato");
			out.print(jsonProd);
	        out.flush();
			return;
		}
		
		user = (User) request.getSession().getAttribute("User");
		
		try {
			product = ds4.doRetriveByKey(Integer.parseInt(productId));
		} catch (NumberFormatException | SQLException e1) {
			logger.log(Level.SEVERE, "An error occurred", e1);
		}
		
		try {
			isPurchased = ds2.checkDettagliOrdineExistence(user.getId(), product.getName());
		} catch (SQLException e1) {
			logger.log(Level.SEVERE, "An error occurred", e1);
		}
		
		if(!isPurchased) {
			jsonProd = gson.toJson("Prodotto non acquistato");
			out.print(jsonProd);
	        out.flush();
	        return;
		}
		
		try {
			isSecond = ds3.doVerifySecond(user.getId(), Integer.parseInt(productId));
		} catch (NumberFormatException | SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		if(isSecond) {
			jsonProd = gson.toJson("Recensione già aggiunta");
			out.print(jsonProd);
	        out.flush();
	        return;
		} else {
			jsonProd = gson.toJson("Recensione aggiunta");
			review.setDescription(description);
			review.setScore(Integer.parseInt(score));
			review.setTitle(title);
			review.setIdUser(user.getId());
			review.setIdProd(product.getId());
			try {
				ds3.doSave(review);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
		}
        
        out.print(jsonProd);
        out.flush();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
