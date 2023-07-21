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


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
       
    public LoginServlet() {
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
    
    public static boolean validatePassword(String inputPassword, String storedHashedPassword) {
        String hashedPassword;
        try {
            hashedPassword = hashWithSHA512(inputPassword);
        } catch (NoSuchAlgorithmException e) {
        	logger.log(Level.SEVERE, "An error occurred", e);
            return false;
        }
        
        return BCrypt.checkpw(hashedPassword, storedHashedPassword);
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
			logger.log(Level.SEVERE, "An error occurred", e);
		}
		
		
		if (user == null || !validatePassword(password,user.getPassword())) {
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
