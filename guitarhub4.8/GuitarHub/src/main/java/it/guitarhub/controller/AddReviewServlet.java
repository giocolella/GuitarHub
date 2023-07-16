package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import it.guitarhub.model.UserDAO;

@WebServlet("/AddReview")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddReviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String title = request.getParameter("title");
		String score = request.getParameter("score");
		String description = request.getParameter("description");
		System.err.println(productId);
		UserDAO ds1 = new UserDAO();
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
			return;
		}
		
		user = (User) request.getSession().getAttribute("User");
		
		try {
			product = ds4.doRetriveByKey(Integer.parseInt(productId));
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			isPurchased = ds2.checkDettagliOrdineExistence(user.getId(), product.getName());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(!isPurchased) {
			jsonProd = gson.toJson("Prodotto non acquistato");
			System.err.println("reviewing3");
			out.print(jsonProd);
	        out.flush();
	        return;
		}
		
		try {
			isSecond = ds3.doVerifySecond(user.getId(), Integer.parseInt(productId));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		if(isSecond) {
			jsonProd = gson.toJson("Recensione già aggiunta");
			System.err.println("reviewing1");
			out.print(jsonProd);
	        out.flush();
	        return;
		} else {
			jsonProd = gson.toJson("Recensione aggiunta");
			review.setDescription(description);
			review.setScore(Integer.parseInt(score));
			review.setTitle(title);
			review.setUser(user);
			review.setProduct(product);
			System.err.println("reviewing2");
			try {
				ds3.doSave(review);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        
        out.print(jsonProd);
        out.flush();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
