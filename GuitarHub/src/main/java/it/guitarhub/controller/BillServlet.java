package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.Order;
import it.guitarhub.beans.OrderItem;
import it.guitarhub.beans.User;
import it.guitarhub.model.OrderDAO;
import it.guitarhub.model.UserDAO;

@WebServlet("/Bill")
public class BillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BillServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdine = request.getParameter("orderId");
		OrderDAO ds = new OrderDAO();
		Order order = new Order();
		User user = new User();
		UserDAO userDAO = new UserDAO();
		ArrayList<OrderItem> orderItems = new ArrayList<>();
		if(request.getSession().getAttribute("User")!= null) {
			user = (User) request.getSession().getAttribute("User");
			int id = user.getId();
			try {
				user = userDAO.doRetriveByKey(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(idOrdine!= null) {
			try {
				order = ds.doRetriveByKeyBill(Integer.parseInt(idOrdine));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			orderItems = (ArrayList<OrderItem>) ds.doRetrieveOrderItemsByOrder(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		order.setUser(user);
		order.setItems(orderItems);
		Gson gson = new Gson();
	    String jsonProd = gson.toJson(order);
	    
	    response.setContentType("application/json");
	        
	    PrintWriter out = response.getWriter();
	    out.print(jsonProd);
	    out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
