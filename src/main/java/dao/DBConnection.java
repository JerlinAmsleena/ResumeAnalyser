package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DBConnection {
static Connection connection=null;
	
	private DBConnection() {
		
	}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String url = "jdbc:mysql://localhost:3306/resume_analyser"; 
	    String name = "root";  
	    String pass = "amsLeena@11";
	    if(connection==null) {
	    	try{

	    		connection=DriverManager.getConnection(url,name,pass);
		    }
		    catch(SQLException e) {
		    	System.out.println(e.getMessage());

		    }
	    }
	    return connection;
	    
	}
}
