package controllers;

import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.RecruitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Recruit;

/**
 * Servlet implementation class GetRecruit
 */
//@WebServlet("/GetRecruit")
public class GetRecruit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetRecruit() {
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
		String jsonString = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        JSONObject jsonData = new JSONObject(jsonString);

        int recruitId = jsonData.getInt("recruitId");

        Recruit recruit = RecruitDAO.getRecruitById(recruitId);

        ObjectMapper objectMapper = new ObjectMapper();
        String recruitJson = objectMapper.writeValueAsString(recruit);
//        JSONObject recruitJson=new JSONObject();
//        recruitJson.put("", false)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(recruitJson);
	}

}
