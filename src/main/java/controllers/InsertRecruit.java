package controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.RecruitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Recruit;
import models.Requirement;
import models.Skill;
import validation.GetUserIdBySessionId;

/**
 * Servlet implementation class InsertRecruit
 */
//@WebServlet("/InsertRecruit")
public class InsertRecruit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public InsertRecruit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String jsonString = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		JSONObject jsonData = new JSONObject(jsonString);

		String recruitName = jsonData.getString("recruitName");
		int maximumNumber = jsonData.getInt("maximumNumber");

		JSONObject requirementData = jsonData.getJSONObject("requirement");
		int experienceYear = requirementData.getInt("experienceYear");
		String qualification = requirementData.getString("qualification");

		List<Skill> skills = new ArrayList<>();
		JSONArray skillArray = requirementData.getJSONArray("skills");
		for (int i = 0; i < skillArray.length(); i++) {
			JSONObject skillObj = skillArray.getJSONObject(i);
			Skill skill = new Skill(skillObj.getString("skillName"));
			skill.setSkillId(skillObj.getInt("skillId"));
			skills.add(skill);
		}

		Requirement requirement = new Requirement(experienceYear, qualification, skills);
		Recruit recruit = new Recruit(recruitName, maximumNumber);
		recruit.setRequirement(requirement);

		JSONObject responseJSON = new JSONObject();
		Cookie[] cookies = request.getCookies();
		
		String sessionId=null;
		
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION_ID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }

        if (sessionId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session ID not found.");
            return;
        }
		String userId=GetUserIdBySessionId.getUserID(sessionId);
		
		try {
			boolean isInserted = RecruitDAO.insertRecruit(recruit,userId);
			responseJSON.put("success", isInserted);
			responseJSON.put("message", isInserted ? "Recruit inserted successfully" : "Recruit insertion failed");
		} catch (Exception e) {
			responseJSON.put("success", false);
			responseJSON.put("message", "Error: " + e.getMessage());
		}

		response.getWriter().write(responseJSON.toString());
	}

}
