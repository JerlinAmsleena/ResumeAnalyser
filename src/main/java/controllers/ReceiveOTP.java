package controllers;

import java.io.BufferedReader;
import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ReceiveOTP
 */
//@WebServlet("/ReceiveOTP")
public class ReceiveOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReceiveOTP() {
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
		HttpSession session = request.getSession();
		
	    System.out.println("Received");
	    System.out.println(session.getId()); 
	    System.out.println(session.getAttribute("otp"));
	    Integer generatedOtp = (Integer) session.getAttribute("otp");
	    
	    System.out.println(generatedOtp+" session");
//	    String enteredOtpStr = request.getParameter("otp");
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    
	    JSONObject json = new JSONObject(sb.toString());
	    String enteredOtpStr = json.getString("otp");

	    JSONObject responseJson = new JSONObject();

	    if (enteredOtpStr != null && !enteredOtpStr.isEmpty() && generatedOtp != null) {
	        try {
	            int enteredOtp = Integer.parseInt(enteredOtpStr);

	            if (enteredOtp == generatedOtp) {
	                responseJson.put("status", "success");
	                responseJson.put("message", "OTP verification successful.");
	            } else {
	                responseJson.put("status", "error");
	                responseJson.put("message", "Invalid OTP. Please try again.");
	            }
	        } catch (NumberFormatException e) {
	            responseJson.put("status", "error");
	            responseJson.put("message", "Invalid OTP format.");
	        }
	    } else {
	        responseJson.put("status", "error");
	        responseJson.put("message", "OTP is missing or expired.");
	    }

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(responseJson.toString());

	}

}
