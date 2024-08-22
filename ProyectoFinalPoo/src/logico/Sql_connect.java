package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sql_connect {
	
	public static void main(String[] args) {
	    String connectionUrl =
	            "jdbc:sqlserver://192.168.100.118:1433;"
	                    + "database=clinica_stanley_eduardo;"
	                    + "user=s.gomez;" //TU USER
	                    + "password=Headphone1130Jack;" //TU CLAVE
	                    + "encrypt=true;"
	                    + "trustServerCertificate=true;"
	                    + "loginTimeout=30;";
	
	    try (Connection connection = DriverManager.getConnection(connectionUrl)) {
	    	/*
	    	Statement statement = connection.createStatement()
	        String selectSql = "SELECT * FROM lugar";
	        ResultSet resultSet = statement.executeQuery(selectSql);
	        
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id_lugar");
	            String nombre = resultSet.getString("nombre_lugar");
	            System.out.println("ID: " + id + ", Nombre: " + nombre);
	        }
	        */
	    	/*
	    	PreparedStatement stmt = conn.prepareStatement("INSERT INTO table_name(email, pass, date_created) VALUES (?, ?, ?)");

	    	stmt.setString(1, email);
	    	stmt.setString(2, password);
	    	stmt.setDate(3, new Date());

	    	stmt.executeUpdate();
	    	
	    	
	    	
	    	try(
	    	        PreparedStatement ps = conn.prepareStatement(
	    	            "insert into yourTable(field1, field2, field3) values (?,?,?)"
	    	) {
	    	    
	    	     //The question marks are placeholders for the values you will insert.
	    	     
	    	    ps.setString(1, "abc");
	    	    ps.setInt(2, 123);
	    	    ps.setDouble(3, 3.1416);
	    	    ps.execute(); // The insert is executed here
	    	} catch(SQLException e) {
	    	    // Your exception handling code
	    	}
	    	
	    	*/
	    	System.out.println("Connection established.");
	
	    } catch (SQLException e) {
	    	System.out.println("Error connection to the database");
	        e.printStackTrace();
	    }
	}
}
