package it.guitarhub.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ModifyProduct")
public class ModifyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("productName").toString();
		String quantity = request.getParameter("productQuantity").toString();
		String description = request.getParameter("productDescription").toString();
		String price = request.getParameter("productPrice").toString();
		String discount = request.getParameter("productDiscount").toString();
		String brand = request.getParameter("productBrand").toString();
		String weight = request.getParameter("productWeight").toString();
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
