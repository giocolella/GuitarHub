package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.model.ProductDAO;

@WebServlet("/ProductDelete")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codice = request.getParameter("theId");
		ProductDAO ds = new ProductDAO();
		
		if(codice != null) {
			try {
				ds.doDelete(Integer.parseInt(codice));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
