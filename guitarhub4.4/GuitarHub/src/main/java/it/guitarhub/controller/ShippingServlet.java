package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
       
    public ShippingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String firstNameShipping = request.getParameter("firstName");
		String lastNameShipping = request.getParameter("lastName");
		String telephoneShipping = request.getParameter("telefonoInput");
		String cityShipping = request.getParameter("city");
		String provinceShipping = request.getParameter("province");
		String postalCodeShipping = request.getParameter("postalCode");
		String streetShipping = request.getParameter("street");
		String civicNumberShipping = request.getParameter("civicNumber");
		User u = new User();
		UserDAO ds = new UserDAO();
		OrderDAO ds2 = new OrderDAO();
		ProductDAO ds3 = new ProductDAO();
		Order order = new Order();
		Collection<OrderItem> collection = new ArrayList<>();
		ArrayList<Product> carrello = new ArrayList<>();
		double totalPaid = 0.0;
		int totalQuantity = 0;
		int actualQuantity = 0;
		
		if(session.getAttribute("User") != null) {
			u = (User) request.getSession().getAttribute("User");
		} 
		
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
			e.printStackTrace();
		}
		
		try {
			ds.doSaveAddress(u, address);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(session.getAttribute("cart") != null) {
			carrello = (ArrayList<Product>) session.getAttribute("cart");
		}
		
		for(Product product: carrello) {
			OrderItem o = new OrderItem();
			o.setName(product.getName());
			o.setPrice(product.getPrice());
			o.setQuantity(product.getQuantity());
			o.setShortDescription(product.getShortdescription());
			o.setTax(product.getTax());
			o.setWeight(product.getWeight());
			totalPaid += product.getPrice() * product.getQuantity();
			totalQuantity += product.getQuantity();
			collection.add(o);
			try {
				actualQuantity = ds3.doRetriveQuantity(product.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				ds3.doUpdateQuantity(product, actualQuantity);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		order.setUser(u);
		order.setDestination(streetShipping + " " + cityShipping + " " + provinceShipping + " " + civicNumberShipping + " " + postalCodeShipping);
		order.setTrackNumber("In spedizione");
		order.setItems(collection);
		if(totalPaid >= 500.00) {
			totalPaid += 25.00;
		}
	    order.setTotalPaid(totalPaid);
	    order.setTotalProducts(totalQuantity);
		
		try {
			ds2.doSave(order);
		} catch (SQLException e) {
			e.printStackTrace();
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
