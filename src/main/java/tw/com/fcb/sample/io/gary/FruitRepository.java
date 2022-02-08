package tw.com.fcb.sample.io.gary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FruitRepository {
	
	private Connection getConnection() throws SQLException {
		//Class.forName("org.postgresql.Driver");
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);
			
	}
	
	public Fruit getByCode(String code) throws SQLException {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from fruit where code = '"+code+"'";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		
		Fruit fruit = new Fruit();
		while(rs.next()) {
			fruit.setCode(rs.getString("code"));
			fruit.setName(rs.getString("name"));
			fruit.setPrice(rs.getInt("price"));
		}
		
		return fruit;
	}
	
	public int insert(Fruit fruit) throws SQLException {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "INSERT INTO fruit values ("
				+ "'"+fruit.getCode()+"', "
				+ "'"+fruit.getName()+"', "
				+ "" +fruit.getPrice()+""
				+ ")";
		stmt.executeUpdate(sqlCmd);
		
		return -1;
	}
	

}
