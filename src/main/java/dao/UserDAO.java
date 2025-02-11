package dao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

//import com.mysql.cj.jdbc.Driver;

import models.User;

public class UserDAO {
	
//	public User user;
	
	public static Connection conn=DBConnection.getConnection();
	
	public static boolean insertUser(String username,String email,String password) {

		password = encodePassword(password);
   
        String userId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

        String query = "INSERT INTO userDetails (userId, userName, email, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {  
            pstmt.setString(1, userId);
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; 
        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
        }
        return false;
	}
	
	public static User getUser(String email,String password) {
		
		String hashedPassword = encodePassword(password); 

        String query = "SELECT userId, userName, email, password FROM userDetails WHERE email = ? AND password = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) { 
            pstmt.setString(1, email);
            pstmt.setString(2, hashedPassword);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                  
                    User user = new User(rs.getString("userName"), rs.getString("email"), rs.getString("password"));
                    user.setUserId(rs.getString("userId"));
                    return user; 
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error in getUser: " + e.getMessage());
        }
        return null; 
	}
	
	
	
	public static String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String decodePassword(String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes);
    }
}
