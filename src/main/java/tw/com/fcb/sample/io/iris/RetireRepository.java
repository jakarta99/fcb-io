package tw.com.fcb.sample.io.iris;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetireRepository {
	private Connection getConnection() throws SQLException {
		//Class.forName("org.postgresql.Driver");
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public List<RetireAge> findAll() throws  SQLException{
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();

        String sqlCmd = "select * from retireID";
        ResultSet rs = stmt.executeQuery(sqlCmd);
        List<RetireAge> arr = new ArrayList<RetireAge>();
        RetireAge retireAge;
        while (rs.next()){
            retireAge = new RetireAge();
            retireAge.setType(rs.getString("type"));
            retireAge.setVoluntary_cnt(rs.getInt("voluntary_cnt"));
            retireAge.setAge_cnt(rs.getInt("age_cnt"));
            retireAge.setOrder_cnt(rs.getInt("order_cnt"));
            arr.add(retireAge);
        }
        return arr;
    }
	 public RetireAge getByType(String type) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();

	        String sqlCmd = "select * from retireID where type = '"+type+"'";
	        ResultSet rs = stmt.executeQuery(sqlCmd);

	        RetireAge retireAge = new RetireAge();
	        while(rs.next()) {
	            retireAge.setType(rs.getString("type"));
	            retireAge.setVoluntary_cnt(rs.getInt("voluntary_cnt"));
	            retireAge.setAge_cnt(rs.getInt("age_cnt"));
	            retireAge.setOrder_cnt(rs.getInt("order_cnt"));
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	        return retireAge;
	    }
	 
	 public void inserDB(RetireAge obj) throws SQLException{

	        Connection conn = getConnection();	        
//	        String sqlCmd = "insert into retireID values('" + obj.getType() + "',"
//	        										  + obj.getVoluntary_cnt() +","
//	        										  + obj.getAge_cnt() +","
//	        										  + obj.getOrder_cnt() + ")";
	        String sqlCmd = "INSERT INTO retireID(obj.getType,getVoluntary_cnt,getAge_cnt,getOrder_cnt)" +
                            "VALUES(?,?,?,?,?,?,?) returning id";
	        PreparedStatement  stmt = conn.prepareStatement(sqlCmd);
	        stmt.setString(1,obj.getType());
	        stmt.setInt(2,obj.getVoluntary_cnt() );
	        stmt.setInt(3,obj.getAge_cnt());
	        stmt.setInt(4,obj.getOrder_cnt());
	        stmt.executeUpdate(sqlCmd);
	        
	        stmt.close();
	        conn.close();
	    }
	 
	 public void updateDB(Long id, int voluntary_cnt) throws SQLException {

	        Connection conn = getConnection();

	        String sqlCmd = "update retireID set voluntary_cnt = '" + voluntary_cnt +"' where id = " + id;
	        PreparedStatement stmt = conn.prepareStatement(sqlCmd);

	        stmt.executeUpdate();
	        System.out.println("已將id = " + id + "的自願退休筆數更新為" +voluntary_cnt);
	        stmt.close();
	        conn.close();
	    }
	 public void deleteDB(String type) throws SQLException{

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();
	        String sqlCmd = "delete from retireID where type = '" + type + "'";
	        stmt.executeUpdate(sqlCmd);
	        stmt.close();
	        conn.close();
	    }

}
