package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.User;
import it.guitarhub.model.UserDAO;


@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("usernameReg");
		String email = request.getParameter("emailReg");
		String password = request.getParameter("passReg");
		
		UserDAO db = new UserDAO();
		
		User user = new User();
		try {
			user = db.doRetrieveByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(user != null) {
			request.setAttribute("errors", "Username già esistente!");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
			return;
		}
		
		try {
			user = db.doRetrieveByEmailLogin(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(user != null) {
			request.setAttribute("errors", "Email già esistente!");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
			return;
		} else {
			user= new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setUsername(username);
			user.setRole("utente");
			request.getSession().setAttribute("User", user);
			try {
				db.doSaveReg(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/views/home.jsp");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
