package tw.com.fcb.sample.io.weifan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseService {
	private String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
	private String username = "postgres";
	private String password = "postgres";
	public Statement ConnectDB() throws SQLException {
	Connection conn = DriverManager.getConnection(dbUrl, username, password);
	Statement stmt = conn.createStatement();
	return stmt;
	}
	public void CloseDB(Statement stmt) throws SQLException{
		stmt.close();
	}
}
