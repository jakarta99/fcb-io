package tw.com.fcb.sample.io.weifan;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FileIOApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*List<Language> result = new LanguageService().loadFromFile();
		for(int i = 0; i < result.size(); i++){
			System.out.println(result.get(i).toString());
		}*/
		
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		try {
			Connection conn = DriverManager.getConnection(dbUrl, username, password);
			Statement stmt = conn.createStatement();
			String sqlCmd = "select * from fruit";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while(rs.next()) {
			System.out.println(rs.getString("code"));
			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("price"));
			conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

}
