package tw.com.fcb.sample.io.weifan;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FileIOApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    //	List<Language> result = new LanguageService().loadFromFile();
		/*for(int i = 0; i < result.size(); i++){
			System.out.println(result.get(i).toString());
		}*/
		
		List<Language> result = new ArrayList<Language>();
		DataBaseService DB = new DataBaseService();
		Language Lan = new Language();
		//findALL
		try {
			result = DB.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < result.size(); i++){
		    System.out.println(result.get(i).toString());
		}
		
		//findByID
		try {
			Lan = DB.findByID(194);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Lan.toString());
	/*	try {
			DataBaseService DB = new DataBaseService();
		//	Statement stmt = DB.ConnectDB();
			for(int i = 0; i < result.size(); i++){
				String sqlCmd = result.get(i).toSqlcmd();
			//	System.out.println(result.get(i).toSqlcmd());
				stmt.executeUpdate(sqlCmd);
			}
			String sqlCmd = "select * from fruit";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while(rs.next()) {
			System.out.println(rs.getString("code"));
			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


		
	}

}
