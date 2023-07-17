package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.Product;
import it.guitarhub.model.ProductDAO;

@WebServlet("/GetProduct")
public class GetProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		ProductDAO ds = new ProductDAO();
		Product product = new Product();
		
		if(!productId.isEmpty() && productId != null) {
			request.getSession().setAttribute("idprod", productId);
			try {
				product = ds.doRetriveByKey(Integer.parseInt(productId));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		Gson gson = new Gson();
        String jsonProd = gson.toJson(product);
        
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        out.print(jsonProd);
        out.flush();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
