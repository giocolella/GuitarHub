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

@WebServlet("/CartPopulator")
public class CartPopulatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartPopulatorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Product> carrello = new ArrayList<>();
		Gson gson = new Gson();
		System.err.println("hey1");
		
		if(session.getAttribute("cart") != null) {
			System.err.println("hey2");
			carrello = (ArrayList<Product>) session.getAttribute("cart");
			String jsonResponse = gson.toJson(carrello);
			response.setContentType("application/json");
            response.getWriter().print(jsonResponse);
            response.getWriter().flush();
            return;
		}else {
			System.err.println("hey3");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
