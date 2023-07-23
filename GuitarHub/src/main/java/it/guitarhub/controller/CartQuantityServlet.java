package it.guitarhub.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.guitarhub.beans.Product;

@WebServlet("/CartQuantity")
public class CartQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CartQuantityServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String q = request.getParameter("quantity");
		String id = request.getParameter("id");
		ArrayList<Product> carrello = new ArrayList<>();
		
		if(q.isEmpty() || id.isEmpty() || id==null|| q == null) {
			return;
		}
		if(session.getAttribute("cart") != null) {
			carrello = (ArrayList<Product>) session.getAttribute("cart");
			for(Product product : carrello) {
				if(product.getId() == Integer.parseInt(id)) {
					product.setQuantity(Integer.parseInt(q));
				}
			}
		}
		
		session.setAttribute("cart", carrello);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
