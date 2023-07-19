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

import com.google.gson.Gson;

import it.guitarhub.beans.Order;
import it.guitarhub.beans.User;
import it.guitarhub.model.OrderDAO;
import it.guitarhub.model.UserDAO;

@WebServlet("/SearchOrders")
public class SearchOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SearchOrdersServlet.class.getName());
       
    public SearchOrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = request.getParameter("q").trim();
		String query1 = request.getParameter("query1").trim();
		String query2 = request.getParameter("query2").trim();
		UserDAO ds = new UserDAO();
		OrderDAO ds2 = new OrderDAO();
		User user = new User();
		ArrayList<Order> orders = new ArrayList<>();
		
		if(q != null) {
			try {
				user = ds.doRetrieveByUsername(q);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			try {
				orders = (ArrayList<Order>) ds2.doRetrieveByUser(user);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
		}else if(query1 != null && query2 != null) {
			
		}
		
		Gson gson = new Gson();
        String jsonProd = gson.toJson(q);
        
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        out.print(jsonProd);
        out.flush();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
