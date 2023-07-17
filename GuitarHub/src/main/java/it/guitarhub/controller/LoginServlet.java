package it.guitarhub.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.User;
import it.guitarhub.model.UserDAO;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailLog").trim();
		String password = request.getParameter("passLog").trim();
		request.removeAttribute("errors");
		
		UserDAO db = new UserDAO();
		
		User user = new User();
		try {
			user = db.doRetrieveByEmailLogin(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(user == null) {
			request.setAttribute("errors", "Credenziali non valide!");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		} else if (!user.getPassword().equals(password)) {
			request.setAttribute("errors", "Credenziali non valide!");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		} else if ("admin".equals(user.getRole())) {
			request.getSession().setAttribute("isAdmin", true);
			response.sendRedirect(request.getContextPath() + "/views/admin/adminSearch.jsp");
		} else {
			request.getSession().setAttribute("User", user);
			response.sendRedirect(request.getContextPath() + "/views/home.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
