package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.Product;
import it.guitarhub.model.ProductDAO;

@WebServlet("/AddProduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddProductServlet.class.getName());
       
    public AddProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("productName").toString().trim();
		String quantity = request.getParameter("productQuantity").toString().trim();
		String description = request.getParameter("productDescription").toString().trim();
		String price = request.getParameter("productPrice").toString().trim();
		String brand = request.getParameter("productBrand").toString().trim();
		String weight = request.getParameter("productWeight").toString().trim();
		String imagePath = request.getParameter("imagePath").trim();
		String shortDescription = request.getParameter("shortDescription").trim();
		boolean exists = false;
		request.removeAttribute("alreadyExists");
		
		Product product = new Product();
		ProductDAO ds = new ProductDAO();
		
		try {
			exists = ds.doProductSearchByName(name);
		} catch (SQLException e1) {
			 logger.log(Level.SEVERE, "An error occurred", e1);
		}
		
		if(exists) {
			request.setAttribute("alreadyExists", "Il nome del prodotto gi&agrave esiste");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/addProduct.jsp");
			dispatcher.forward(request, response);
			return;
		}
		request.getSession().removeAttribute("alreadyExists");
		
		if(quantity != null && price != null && weight != null) {
			product.setBrand(brand);
			product.setDescription(description);
			product.setImagePath(imagePath);
			product.setShortdescription(shortDescription);
			product.setName(name);
			product.setPrice(Double.parseDouble(price));
			product.setWeight(Double.parseDouble(weight));
			product.setQuantity(Integer.parseInt(quantity));
			product.setAvailable(true);
			product.setDiscount(2.00);
			try {
				ds.doSave(product);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/adminSearch.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
