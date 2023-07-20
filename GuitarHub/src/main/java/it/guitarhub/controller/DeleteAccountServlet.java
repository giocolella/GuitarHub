package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.guitarhub.beans.User;
import it.guitarhub.model.UserDAO;

@WebServlet("/DeleteAccount")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DeleteAccountServlet.class.getName());
       
    public DeleteAccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDAO ds = new UserDAO();
		User user = new User();
		
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		if(isAdmin != null && isAdmin) {
			return;
		}
		
		if(session.getAttribute("User") == null) {
			return;
		}
		
		user = (User) session.getAttribute("User");
		try {
			ds.doDelete(user.getId());
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		session.removeAttribute("User");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
