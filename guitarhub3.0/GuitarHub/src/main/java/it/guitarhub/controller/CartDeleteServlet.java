package it.guitarhub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.guitarhub.beans.Product;

@WebServlet("/CartDelete")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idProd");
		HttpSession session = request.getSession();
		ArrayList<Product> carrello = new ArrayList<>();
		
		if(session.getAttribute("cart")!= null) {
			carrello = (ArrayList<Product>) session.getAttribute("cart");
		Iterator<Product> i = carrello.iterator();
		while (i.hasNext()) {
		   Product s = i.next();
		   if(s.getId() == Integer.parseInt(id)) {
			   i.remove();
		   		}
			}
		}
		session.setAttribute("cart", carrello);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/cart.jsp");
		dispatcher.forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
