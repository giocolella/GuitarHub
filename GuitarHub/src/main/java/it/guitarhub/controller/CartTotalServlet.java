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

@WebServlet("/CartTotal")
public class CartTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartTotalServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Product> carrello = new ArrayList<>();
		double subtotal = 0.00;
		Gson gson = new Gson();
		//System.err.println("the total1");
		
		if(session.getAttribute("cart") != null) {
			carrello = (ArrayList<Product>) session.getAttribute("cart");
			for(Product product : carrello) {
				subtotal += product.getPrice() * product.getQuantity();
				//System.err.println("the total2");
			}
		}
		
		String jsonSubtotal = gson.toJson(subtotal);
		response.setContentType("application/json");
        response.getWriter().print(jsonSubtotal);
        response.getWriter().flush();
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
