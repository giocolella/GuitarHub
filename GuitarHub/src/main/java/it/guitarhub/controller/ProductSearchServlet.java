package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.Product;
import it.guitarhub.model.ProductDAO;



@WebServlet("/productSearch")
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ProductSearchServlet.class.getName());
       
    
    public ProductSearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String query = request.getParameter("q").trim();
    	String checkboxValue = request.getParameter("checkbox");
    	boolean isChecked = Boolean.parseBoolean(checkboxValue); 
    	
        ProductDAO ds = new ProductDAO();
        
        List<Product> productList = new LinkedList<Product>();
        
        if(isChecked) {
        	try {
        		productList = (List<Product>) ds.doProductSearchByFilteredQuantity(query);
        	} catch (SQLException e) {
        		logger.log(Level.SEVERE, "An error occurred", e);
        	}
        }else {
        	try {
        		productList = (List<Product>) ds.doProductSearch(query);
        	} catch (SQLException e) {
        		logger.log(Level.SEVERE, "An error occurred", e);
        	}
        }
        
        if(productList.isEmpty()) {
        	return;
        }
        
        Gson gson = new Gson();
        String jsonProd = gson.toJson(productList);
        
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        out.print(jsonProd);
        out.flush();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
