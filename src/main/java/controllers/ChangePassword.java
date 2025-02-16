package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONObject;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.PasswordChangeService;
/**
 * Servlet implementation class ChangePassword
 */
//@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ChangePassword() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("inside changepassword");
        response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json");
//        JSONObject responseJson = new JSONObject();
//        String newPassword = request.getParameter("newPassword");
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JSONObject requestJson = new JSONObject(sb.toString());
        String newPassword = requestJson.getString("newPassword");
        
//        String sessionId = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("SESSION_ID".equals(cookie.getName())) {
//                    sessionId = cookie.getValue();
//                    break;
//                }
//            }
//        }
//        
//        JSONObject responseJson = new JSONObject();
//        if (sessionId == null) {
//            responseJson.put("status", "error");
//            responseJson.put("message", "Session expired. Please log in again.");
//            response.getWriter().write(responseJson.toString());
//            return;
//        }

        JSONObject result = PasswordChangeService.changePassword("session_123", newPassword);
        response.getWriter().write(result.toString());
	}

}
