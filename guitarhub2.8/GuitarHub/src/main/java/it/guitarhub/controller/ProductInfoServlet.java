package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.Product;
import it.guitarhub.model.ProductDAO;

@WebServlet("/ProductInfo")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codice = request.getParameter("codice");
		
		ProductDAO ds = new ProductDAO();
		
		Product product = null;
		try {
			product = ds.doRetriveByKey(Integer.parseInt(codice));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(product != null) {
			request.getSession().setAttribute("productSearched", product);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product.jsp");
		dispatcher.forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
