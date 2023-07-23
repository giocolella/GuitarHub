package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.*;

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

@WebServlet("/SearchOrders")
public class SearchOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SearchOrdersServlet.class.getName());
       
    public SearchOrdersServlet() {
        super();
    }
    
    private static ArrayList<Timestamp> convertToTimestamp(String startDate,String endDate) {
    	ArrayList<Timestamp> arr = new ArrayList<>();
    	
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDateLocal = LocalDate.parse(startDate, dateFormatter);
            LocalDate endDateLocal = LocalDate.parse(endDate, dateFormatter);

            LocalDateTime startDateTime = startDateLocal.atStartOfDay();
            LocalDateTime endDateTime = endDateLocal.atStartOfDay();

            long startTimestamp = startDateTime.toEpochSecond(ZoneOffset.UTC);
            long endTimestamp = endDateTime.toEpochSecond(ZoneOffset.UTC);
            
            arr.add(new Timestamp(startTimestamp * 1000));
            arr.add(new Timestamp(endTimestamp * 1000));
            
        } catch (Exception e) {
        	logger.log(Level.SEVERE, "An error occurred", e);
        }
		return arr;
    }
    
    private static void writeJsonResponse(HttpServletResponse response, Object object) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(object);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = request.getParameter("q");
		String startDate = request.getParameter("q1");
		String endDate = request.getParameter("q2");
		UserDAO ds = new UserDAO();
		OrderDAO ds2 = new OrderDAO();
		User user = new User();
		ArrayList<Order> orders = new ArrayList<>();
		ArrayList<Order> ordersDate = new ArrayList<>();
		ArrayList<OrderItem> orderItems = new ArrayList<>();
		ArrayList<OrderItem> orderItemsDate = new ArrayList<>();
		ArrayList<Timestamp> timestamps = new ArrayList<>();
		
		if(q == null || startDate == null || endDate == null) {
			writeJsonResponse(response, orders);
		    return;
		}
		
		if(!q.equals("") && !startDate.equals("") && !endDate.equals("")) {
			try {
				user = ds.doRetrieveByUsername(q);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			if(user == null) {
				writeJsonResponse(response, orders);
			    return;
			}
			timestamps = convertToTimestamp(startDate,endDate);
			try {
				orders = (ArrayList<Order>) ds2.doRetrieveOrdersBetweenDatesAndByUsername(user.getId(), timestamps.get(0), timestamps.get(1));
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			for(Order order1 : orders) {
				try {
					orderItems = (ArrayList<OrderItem>) ds2.doRetrieveOrderItemsByOrder(order1);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "An error occurred", e);
				}
				order1.setItems(orderItems);
				order1.setUser(user);
			}
			 writeJsonResponse(response, orders);
		     return;
		}
		
		if(!q.equals("")) {
			try {
				user = ds.doRetrieveByUsername(q);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			if(user == null) {
				writeJsonResponse(response, orders);
			    return;
			}
			try {
				orders = (ArrayList<Order>) ds2.doRetrieveByUser(user);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			for(Order order1 : orders) {
				try {
					orderItems = (ArrayList<OrderItem>) ds2.doRetrieveOrderItemsByOrder(order1);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "An error occurred", e);
				}
				order1.setItems(orderItems);
				order1.setUser(user);
			}
			 writeJsonResponse(response, orders);
		     return;
		}
			
		if(!startDate.equals("") && !endDate.equals("")) {
			timestamps = convertToTimestamp(startDate,endDate);
			try {
				ordersDate = (ArrayList<Order>) ds2.doRetrieveOrdersBetweenDates(timestamps.get(0), timestamps.get(1));
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			
			for(Order order1 : ordersDate) {
				try {
					orderItemsDate = (ArrayList<OrderItem>) ds2.doRetrieveOrderItemsByOrder(order1);
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "An error occurred", e);
				}
				try {
					user = ds.doRetriveByKey(order1.getUser().getId());
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "An error occurred", e);
				}
				order1.setItems(orderItemsDate);
				order1.setUser(user);
			}
			writeJsonResponse(response, ordersDate);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
