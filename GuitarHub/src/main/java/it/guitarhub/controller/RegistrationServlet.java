package it.guitarhub.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import it.guitarhub.beans.User;
import it.guitarhub.model.UserDAO;


@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int SALT_ROUNDS = 12;
	private static final Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
       
    
    public RegistrationServlet() {
        super();
    }
    
    private static String hashWithSHA512(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hashedBytes = md.digest(password.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("usernameReg").trim();
		String email = request.getParameter("emailReg").trim();
		String password = request.getParameter("passReg").trim();
		request.removeAttribute("errors");
		
		UserDAO db = new UserDAO();
		
		User user = new User();
		User u = new User();
		try {
			user = db.doRetrieveByUsername(username);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		if(user != null) {
			request.setAttribute("errors", "Username già esistente!");
			request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
			return;
		}
		
		try {
			user = db.doRetrieveByEmailLogin(email);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		if(user != null) {
			request.setAttribute("errors", "Email già esistente!");
			request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
			return;
		} else {
			user= new User();
			user.setEmail(email);
			String hashedPassword = password;
			try {
				hashedPassword = hashWithSHA512(password);
			} catch (NoSuchAlgorithmException e1) {
				logger.log(Level.SEVERE, "An error occurred", e1);
			}
		    String saltedHash = BCrypt.hashpw(hashedPassword, BCrypt.gensalt(SALT_ROUNDS));
			user.setPassword(saltedHash);
			user.setUsername(username);
			user.setRole("utente");
			try {
				db.doSaveReg(user);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			
			try {
				u = db.doRetrieveByEmailLogin(email);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "An error occurred", e);
			}
			
			user.setId(u.getId());
			request.getSession().setAttribute("User", user);
			response.sendRedirect(request.getContextPath() + "/views/home.jsp");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
