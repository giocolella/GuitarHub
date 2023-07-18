package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.Product;
import it.guitarhub.model.ProductDAO;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("productId");
	    String quantity = request.getParameter("productQuantity");
	    Gson gson = new Gson();
	    ArrayList<Product> cart = new ArrayList<>();
	    Product product = new Product();
	    if(id == null || id.equals("")) {
	    	String jsonResponse = gson.toJson("Problema");
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse);
            response.getWriter().flush();
	    	return;
	    }
	    product.setId(Integer.parseInt(id));
	    if(quantity == null) {
	    	String jsonResponse = gson.toJson("L'oggetto non &egrave; disponibile");
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse);
            response.getWriter().flush();
            return;
	    }
	    
	    if(quantity.equals("")) {
	    	String jsonResponse = gson.toJson("Seleziona");
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse);
            response.getWriter().flush();
            return;
	    }
	    
	    product.setQuantity(Integer.parseInt(quantity));

	    if (request.getSession().getAttribute("cart") != null) {
	        cart = (ArrayList<Product>) request.getSession().getAttribute("cart");
	        for (Product product1 : cart) {
	            if (product1.getId() == Integer.parseInt(id)) {
	                String jsonResponse = gson.toJson("L'oggetto &egrave; gi&agrave; nel carrello");
	                response.setContentType("application/json");
	                response.getWriter().print(jsonResponse);
	                response.getWriter().flush();
	                return;
	            }
	        }
	    }
	    
	    ProductDAO ds = new ProductDAO();
	    
	    try {
			product = ds.doRetriveByKey(Integer.parseInt(id));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	    
	    product.setQuantity(Integer.parseInt(quantity));
	    cart.add(product);
	    request.getSession().setAttribute("cart", cart);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
