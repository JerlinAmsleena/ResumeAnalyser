package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Recruit;
import models.Requirement;
import models.Skill;

public class RecruitDAO {
	public static Connection conn = DBConnection.getConnection();

	public static Recruit getRecruitById(int recruitId) {

		String recruitQuery = "SELECT r.recruitId, r.recruitName, r.requirementId, r.maximumNumber, "
				+ "req.experience, req.qualification " + "FROM recruit r "
				+ "JOIN requirement req ON r.requirementId = req.requirementId " + "WHERE r.recruitId = ?";

//		String skillQuery = "SELECT s.skillId, s.skillName " + "FROM skills s "
//				+ "JOIN requirement_skill rs ON s.skillId = rs.skillId " + "WHERE rs.requirementId = ?";

		try (PreparedStatement pstmtRecruit = conn.prepareStatement(recruitQuery)) {
			pstmtRecruit.setInt(1, recruitId);
			try (ResultSet rsRecruit = pstmtRecruit.executeQuery()) {
				if (rsRecruit.next()) {
					int requirementId = rsRecruit.getInt("requirementId");
					Requirement requirement = new Requirement(
//                requirementId,
							rsRecruit.getInt("experience"), rsRecruit.getString("qualification"),
							getSkillsForRequirement(requirementId));
					requirement.setRequirementId(requirementId);

					Recruit recruit = new Recruit(
//              rsRecruit.getInt("recruitId"),
							rsRecruit.getString("recruitName"),
//              requirement,
							rsRecruit.getInt("maximumNumber"));
					recruit.setRecruitId(rsRecruit.getInt("recruitId"));
					recruit.setRequirement(requirement);

					return recruit;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error fetching Recruit: " + e.getMessage());
		}
		return null;
	}

	private static List<Skill> getSkillsForRequirement(int requirementId) {
		List<Skill> skills = new ArrayList<>();
		String skillQuery = "SELECT s.skillId, s.skillName " + "FROM skills s "
				+ "JOIN requirement_skill rs ON s.skillId = rs.skillId " + "WHERE rs.requirementId = ?";

		try (PreparedStatement pstmtSkill = conn.prepareStatement(skillQuery)) {
			pstmtSkill.setInt(1, requirementId);
			try (ResultSet rsSkill = pstmtSkill.executeQuery()) {
				while (rsSkill.next()) {
					Skill skill = new Skill(rsSkill.getString("skillName"));
					skill.setSkillId(rsSkill.getInt("skillId"));
					skills.add(skill);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error fetching Skills: " + e.getMessage());
		}
		return skills;
	}
	
	public static boolean insertRecruit(Recruit recruit,String userId) {
//        String recruitQuery = "INSERT INTO recruit (recruitName, requirementId, maximumNumber) VALUES (?, ?, ?)";
		String recruitQuery = "INSERT INTO recruit (recruitName, requirementId, userId, maximumNumber) VALUES (?, ?, ?, ?)";
        String requirementQuery = "INSERT INTO requirement (experience, qualification) VALUES (?, ?)";
        String skillQuery = "INSERT INTO requirement_skill (requirementId, skillId) VALUES (?, ?)";
//        Connection conn = null;
        PreparedStatement pstmtRecruit = null;
        PreparedStatement pstmtRequirement = null;
        PreparedStatement pstmtSkill = null;
        ResultSet generatedKeys = null;

        try {
//            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            pstmtRequirement = conn.prepareStatement(requirementQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtRequirement.setInt(1, recruit.getRequirement().getExperienceYear());
            pstmtRequirement.setString(2, recruit.getRequirement().getQualification());
            pstmtRequirement.executeUpdate();

            generatedKeys = pstmtRequirement.getGeneratedKeys();
            int requirementId;
            if (generatedKeys.next()) {
                requirementId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to get generated requirementId");
            }

            pstmtRecruit = conn.prepareStatement(recruitQuery, Statement.RETURN_GENERATED_KEYS);
//            pstmtRecruit.setString(1, recruit.getRecruitName());
//            pstmtRecruit.setInt(2, requirementId);
//            pstmtRecruit.setInt(3, recruit.getMaximumNumber());
//            pstmtRecruit.executeUpdate();
            pstmtRecruit.setString(1, recruit.getRecruitName());
            pstmtRecruit.setInt(2, requirementId);
            pstmtRecruit.setString(3, userId);
            pstmtRecruit.setInt(4, recruit.getMaximumNumber());
            pstmtRecruit.executeUpdate();

            pstmtSkill = conn.prepareStatement(skillQuery);
            for (Skill skill : recruit.getRequirement().getSkills()) {
                pstmtSkill.setInt(1, requirementId);
                pstmtSkill.setInt(2, skill.getSkillId());
                pstmtSkill.addBatch();
            }
            pstmtSkill.executeBatch();
//             
            conn.commit(); 
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (pstmtRequirement != null) pstmtRequirement.close();
                if (pstmtRecruit != null) pstmtRecruit.close();
                if (pstmtSkill != null) pstmtSkill.close();
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
	
	
}
