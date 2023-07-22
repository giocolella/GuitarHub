package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.User;
import it.guitarhub.model.UserDAO;

@WebServlet("/CheckoutPopulator")
public class CheckoutPopulatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CheckoutPopulatorServlet.class.getName());
       
    public CheckoutPopulatorServlet() {
        super();
    }
    
    private static void writeJsonResponse(HttpServletResponse response, Object object) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(object);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		User user2 = new User();
		UserDAO ds = new UserDAO();
		response.setContentType("application/json");
		
		if(request.getSession().getAttribute("User") != null) {
			user = (User) request.getSession().getAttribute("User");
			
			try {
				user2 = ds.doRetriveByKey(user.getId());
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			
			if(user2.getFirstName() != null && user2.getAddress().getCity() != null) {
				writeJsonResponse(response, user2);
			} else {
				writeJsonResponse(response, "Nessun dato");
			}
		} else {
			writeJsonResponse(response, "Nessun dato");
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
