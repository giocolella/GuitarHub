package it.guitarhub.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.guitarhub.beans.User;

@WebFilter(filterName="LoginFilter", urlPatterns = "/*")
public class SessionFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;


	public SessionFilter() {
        super();
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		User isLogged = (User) httpRequest.getSession().getAttribute("User");
		String path = httpRequest.getServletPath();
		if((path.contains("/login.jsp") || path.contains("/registration.jsp")) && (isLogged != null)) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/home.jsp");
			return;
		}
		
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
