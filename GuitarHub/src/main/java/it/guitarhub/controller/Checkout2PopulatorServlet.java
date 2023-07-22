package it.guitarhub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.guitarhub.beans.Address;
import it.guitarhub.beans.User;
import it.guitarhub.model.AddressDAO;

@WebServlet("/Checkout2Populator")
public class Checkout2PopulatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Checkout2PopulatorServlet.class.getName());
       
    public Checkout2PopulatorServlet() {
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
		Address address = new Address();
		AddressDAO ds = new AddressDAO();
		response.setContentType("application/json");
		
		if(request.getSession().getAttribute("User") != null) {
			user = (User) request.getSession().getAttribute("User");
			
			try {
				address = ds.doRetriveByUser(user.getId());
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			
			if(address != null) {
				writeJsonResponse(response, address);
			}else {
				writeJsonResponse(response, "Nessun dato");
			}
		}else {
			writeJsonResponse(response, "Nessun dato");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
