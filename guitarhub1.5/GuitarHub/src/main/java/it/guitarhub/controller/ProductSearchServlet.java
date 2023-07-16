package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.Product;
import it.guitarhub.model.ProductDAO;


@WebServlet("/productSearch")
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProductSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("q");
      
		ProductDAO ds = new ProductDAO();
        
        List<Product> productList = new LinkedList<Product>();
		try {
			productList = (List<Product>) ds.doSearchByName(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/productSearch.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
