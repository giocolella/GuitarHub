package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;

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
       
    public ModifyProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("productName").toString();
		String quantity = request.getParameter("productQuantity").toString();
		String description = request.getParameter("productDescription").toString();
		String price = request.getParameter("productPrice").toString();
		String brand = request.getParameter("productBrand").toString();
		String weight = request.getParameter("productWeight").toString();
		String imagePath = request.getParameter("imagePath");
		String shortDescription = request.getParameter("shortDescription");
		String productId = (String) request.getSession().getAttribute("idprod");
		boolean exists = false;
		
		Product product = new Product();
		ProductDAO ds = new ProductDAO();
		
		try {
			exists = ds.doProductSearchByName(name);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(exists) {
			request.getSession().setAttribute("alreadyExists", "Il nome del prodotto gi&agrave esiste");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/modifyProduct.jsp");
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
				e.printStackTrace();
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
