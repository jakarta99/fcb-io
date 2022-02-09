package tw.com.fcb.sample.io.iris;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetireRepository {
	private Connection getConnection() throws SQLException {
		//Class.forName("org.postgresql.Driver");
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);
	}
	
	 public RetireAge getByType(String type) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();

	        String sqlCmd = "select * from retire where type = '"+type+"'";
	        ResultSet rs = stmt.executeQuery(sqlCmd);

	        RetireAge retireAge = new RetireAge();
	        while(rs.next()) {
	            retireAge.setType(rs.getString("type"));
	            retireAge.setVoluntary_cnt(rs.getInt("voluntary_cnt"));
	            retireAge.setAge_cnt(rs.getInt("age_cnt"));
	            retireAge.setOrder_cnt(rs.getInt("order_cnt"));
	        }
	        return retireAge;
	    }
	 
	 public void inserDB(RetireAge obj) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();
	        String sqlCmd = "insert into retire values('" + obj.getType() + "',"
	        										  + obj.getVoluntary_cnt() +","
	        										  + obj.getAge_cnt() +","
	        										  + obj.getOrder_cnt() + ")";
	        stmt.executeUpdate(sqlCmd);
	    }
	 
	 public void deleteDB(String type) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();
	        String sqlCmd = "delete from retire where type = '" + type + "'";
	        stmt.executeUpdate(sqlCmd);
	    }

}
