package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.guitarhub.beans.Order;
import it.guitarhub.beans.OrderItem;
import it.guitarhub.beans.User;
import it.guitarhub.model.OrderDAO;

@WebServlet("/Orders")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OrdersServlet.class.getName());
       
    public OrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		OrderDAO ds = new OrderDAO();
		User user = new User();
		ArrayList<Order> orders = new ArrayList<>();
		ArrayList<OrderItem> orderItems = new ArrayList<>();
		
		if(session.getAttribute("User") != null) {
			user = (User) session.getAttribute("User");
			try {
				orders = (ArrayList<Order>) ds.doRetrieveByUser(user);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
		}
		
		for(Order order1 : orders) {
			try {
				orderItems = (ArrayList<OrderItem>) ds.doRetrieveOrderItemsByOrder(order1);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			order1.setItems(orderItems);
		}
		
		 Gson gson = new Gson();
	     String jsonProd = gson.toJson(orders);
	        
	     response.setContentType("application/json");
	        
	     PrintWriter out = response.getWriter();
	     out.print(jsonProd);
	     out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
