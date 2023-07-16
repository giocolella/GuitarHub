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
import javax.servlet.http.*;

@WebFilter(filterName="AdminFilter", urlPatterns = "/*")
public class AdminFilter extends HttpFilter implements Filter {
   
	private static final long serialVersionUID = 7187587794517004616L;


	public AdminFilter() {
        super();
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		Boolean isAdmin = (Boolean) httpRequest.getSession().getAttribute("isAdmin");
		String path = httpRequest.getServletPath();
		if(path.contains("/admin/") && (isAdmin == null || !isAdmin)) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/login.jsp");
			return;
		} else if(!path.contains("/admin/") && (isAdmin != null && isAdmin)) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/admin/adminSearch.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
