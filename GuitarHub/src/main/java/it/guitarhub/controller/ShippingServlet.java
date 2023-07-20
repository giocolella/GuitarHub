package it.guitarhub.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.guitarhub.beans.Address;
import it.guitarhub.beans.Order;
import it.guitarhub.beans.OrderItem;
import it.guitarhub.beans.Product;
import it.guitarhub.beans.User;
import it.guitarhub.model.OrderDAO;
import it.guitarhub.model.ProductDAO;
import it.guitarhub.model.UserDAO;

@WebServlet("/Shipping")
public class ShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ShippingServlet.class.getName());
       
    public ShippingServlet() {
        super();
    }
    
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = new User();
		
		if(request.getSession().getAttribute("User") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			u = (User) request.getSession().getAttribute("User");
			if(u.getFirstName() == null || u.getFirstName().equals("")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/home.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		
		String firstNameShipping = request.getParameter("firstName").trim();
		String lastNameShipping = request.getParameter("lastName").trim();
		String telephoneShipping = request.getParameter("telefonoInput").trim();
		String cityShipping = request.getParameter("city").trim();
		String provinceShipping = request.getParameter("province").trim();
		String postalCodeShipping = request.getParameter("postalCode").trim();
		String streetShipping = request.getParameter("street").trim();
		String civicNumberShipping = request.getParameter("civicNumber").trim();
		UserDAO ds = new UserDAO();
		OrderDAO ds2 = new OrderDAO();
		ProductDAO ds3 = new ProductDAO();
		Order order = new Order();
		Collection<OrderItem> collection = new ArrayList<>();
		ArrayList<Product> carrello = new ArrayList<>();
		double totalPaid = 0.0;
		int totalQuantity = 0;
		int actualQuantity = 0;
		

		u = (User) request.getSession().getAttribute("User");
		 
		
		Address address = new Address();
		
		address.setFirstName(firstNameShipping);
		address.setLastName(lastNameShipping);
		address.setCity(cityShipping);
		address.setProvince(provinceShipping);
		address.setPhone(telephoneShipping);
		address.setAddress(streetShipping);
		address.setCivicNumber(civicNumberShipping);
		address.setPostalCode(postalCodeShipping);
		u.setAddress(address);
		
		try {
			ds.doSaveCompleted(u);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		try {
			ds.doSaveAddress(u, address);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		if(session.getAttribute("cart") != null) {
			carrello = (ArrayList<Product>) session.getAttribute("cart");
		}
		
		for(Product product: carrello) {
			OrderItem o = new OrderItem();
			o.setName(product.getName());
			o.setPrice(product.getPrice());
			o.setShortDescription(product.getShortdescription());
			o.setTax(product.getTax());
			o.setWeight(product.getWeight());
			try {
				actualQuantity = ds3.doRetriveQuantity(product.getId());
			} catch (SQLException e1) {
				logger.log(Level.SEVERE, "An error occurred", e1);
			}
			
			if(product.getQuantity() > actualQuantity) {
				product.setQuantity(actualQuantity);
			}
			o.setQuantity(product.getQuantity());
			totalPaid += product.getPrice() * product.getQuantity();
			totalQuantity += product.getQuantity();
			collection.add(o);
			
			try {
				ds3.doUpdateQuantity(product, actualQuantity);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
		}
		
		order.setUser(u);
		order.setDestination(streetShipping + " " + cityShipping + " " + provinceShipping + " " + civicNumberShipping + " " + postalCodeShipping);
		order.setTrackNumber("In spedizione con Track Number: " +  generateRandomString(6));
		order.setItems(collection);
		if(totalPaid >= 500.00) {
			totalPaid += 25.00;
		}
	    order.setTotalPaid(totalPaid);
	    order.setTotalProducts(totalQuantity);
		
		try {
			ds2.doSave(order);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		session.setAttribute("User", u);
		session.removeAttribute("cart");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/orderCompleted.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
