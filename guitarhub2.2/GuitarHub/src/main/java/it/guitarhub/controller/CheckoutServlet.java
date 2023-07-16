package it.guitarhub.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CheckoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postalCode = request.getParameter("postalCode");
		String street = request.getParameter("street");
		String telephone = request.getParameter("telefonoInput");
		String cardNumber = request.getParameter("cardNumber");
		String cardExpDate = request.getParameter("expDate");
		String civicNumber = request.getParameter("civicNumber");
		String cvv = request.getParameter("cvv");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
