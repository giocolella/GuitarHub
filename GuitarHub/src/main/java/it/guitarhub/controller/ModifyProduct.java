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

@WebServlet("/ModifyProduct")
public class ModifyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ModifyProduct.class.getName());
       
    public ModifyProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("productName").trim();
		String quantity = request.getParameter("productQuantity").trim();
		String description = request.getParameter("productDescription").trim();
		String price = request.getParameter("productPrice").trim();
		String brand = request.getParameter("productBrand").trim();
		String weight = request.getParameter("productWeight").trim();
		String imagePath = request.getParameter("imagePath").trim();
		String shortDescription = request.getParameter("shortDescription").trim();
		String productId = (String) request.getSession().getAttribute("idprod");
		boolean exists = false;
		request.removeAttribute("alreadyExists");
		
		Product product = new Product();
		Product product2 = new Product();
		ProductDAO ds = new ProductDAO();
		
		try {
			exists = ds.doProductSearchByName(name);
		} catch (SQLException e1) {
			logger.log(Level.SEVERE, "An error occurred", e1);
		}
		
		try {
			product2 = ds.doRetriveByKey(Integer.parseInt(productId));
		} catch (NumberFormatException | SQLException e1) {
			logger.log(Level.SEVERE, "An error occurred", e1);
		}
		
		if(exists && !product2.getName().equals(name.trim())) {
			request.setAttribute("alreadyExists", "Il nome del prodotto gi&agrave esiste");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/modifyProduct.jsp?productId=" + productId);
			dispatcher.forward(request, response);
			return;
		}
		
		
		if(quantity != null && price != null && weight != null && productId != null) {
			product.setId(Integer.parseInt(productId));
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
				ds.doUpdate(product);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
		}
		
		request.getSession().removeAttribute("idprod");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/adminSearch.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
