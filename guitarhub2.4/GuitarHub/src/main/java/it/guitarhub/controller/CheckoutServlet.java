package it.guitarhub.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.Address;
import it.guitarhub.beans.User;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CheckoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postalCode = request.getParameter("postalCode");
		String street = request.getParameter("street");
		String telephone = request.getParameter("telefonoInput");
		String cardNumber = request.getParameter("cardNumber");
		String cardExpDate = request.getParameter("expDate");
		String civicNumber = request.getParameter("civicNumber");
		String cvv = request.getParameter("cvv");
		Address address = new Address();
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
			e.printStackTrace();
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
		address.setCity(city);
		address.setProvince(province);
		address.setPhone(telephone);
		address.setAddress(street);
		address.setCivicNumber(civicNumber);
		address.setPostalCode(postalCode);
		user.setAddress(address);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
