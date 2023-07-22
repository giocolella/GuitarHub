package it.guitarhub.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.User;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CheckoutServlet.class.getName());
    
    public CheckoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String gender = request.getParameter("gender").trim();
		String birthday = request.getParameter("birthday").trim();
		String city = request.getParameter("city").trim();
		String province = request.getParameter("province").trim();
		String postalCode = request.getParameter("postalCode").trim();
		String street = request.getParameter("street").trim();
		String cardNumber = request.getParameter("cardNumber").trim();
		String cardExpDate = request.getParameter("expDate").trim();
		String civicNumber = request.getParameter("civicNumber").trim();
		String cvv = request.getParameter("cvv").trim();
		String billingAddress = street.trim() + " " +  city.trim() + " " + province.trim() + " " + civicNumber.trim() + " " + postalCode.trim();
		User u = new User();
		
		if(request.getSession().getAttribute("User") != null) {
			u = (User) request.getSession().getAttribute("User");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = new Date();
		Date parsed2 = new Date();
		try {
			parsed = format.parse(cardExpDate);
			parsed2 = format.parse(birthday);
		} catch (ParseException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		LocalDate date = LocalDate.ofInstant(parsed.toInstant(), ZoneId.systemDefault());
		LocalDate date2 = LocalDate.ofInstant(parsed2.toInstant(), ZoneId.systemDefault());
		User user = new User();
		
		user.setId(u.getId());
		user.setUsername(u.getUsername());
		user.setGender(gender);
		user.setBirthday(date2);
		user.setEmail(u.getEmail());
		user.setRole(u.getRole());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCardNumber(cardNumber);
		user.setCardExpDate(date);
		user.setCvv(cvv);
		user.setBillingAddress(billingAddress);

		request.getSession().setAttribute("User",user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/checkout2.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
