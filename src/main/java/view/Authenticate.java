package view;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnection;


/**
 * Servlet Filter implementation class Authenticate
 */
//@WebFilter("/Authenticate")
public class Authenticate extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Authenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
//	    String sessionId = null;
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	            if ("SESSION_ID".equals(cookie.getName())) {
//	                sessionId = cookie.getValue();
//	            }
//	        }
//	    }
//	    if (sessionId != null) {
//	    	boolean isValidSession = validateSession(sessionId);
//
//	        if (isValidSession) {
//	            chain.doFilter(request, response);
//	        } else {
//	            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid session.");
//	        }
//	    } else {
//	        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Session ID is missing.");
//	    }
////		chain.doFilter(request, response);
//	}
//	private boolean validateSession(String sessionId) {
//		Connection conn=DBConnection.getConnection();
//		
//	    boolean isValid = false;
//	    
//	    String query = "SELECT * FROM session WHERE sessionId = ?";
//	    
//	    try {
//	         PreparedStatement pstmt = conn.prepareStatement(query);
//	        
//	        pstmt.setString(1, sessionId);
//	        ResultSet rs = pstmt.executeQuery();
//	        
//	        if (rs.next()) {
//	            isValid = true;
//	        }
//	         
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    
//	    return isValid;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
