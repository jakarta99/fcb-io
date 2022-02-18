package tw.com.fcb.sample.io.weifan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tw.com.fcb.sample.io.kai.FileSecurities;

public class DataBaseService {
	private String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
	private String username = "postgres";
	private String password = "postgres";
	public Connection ConnectDB() throws SQLException {
	Connection conn = DriverManager.getConnection(dbUrl, username, password);
	return conn;
	}
	
	public List<Language> findAll() throws Exception {
		Connection connection = ConnectDB();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		List<Language> list = null;
		
		String selectSql = "SELECT * FROM language";
		pStatement = connection.prepareStatement(selectSql);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<Language>();
		while(resultSet.next()) {
			Language Lan = new Language();
			Lan.setSeq(Integer.valueOf(resultSet.getString("seq")));
			Lan.setCreateDate(resultSet.getString("createdate"));
			Lan.setYear(resultSet.getString("year"));
			Lan.setChineseName(resultSet.getString("chinesename"));
			Lan.setLevel(LanguageLevelEnum.valueOf(resultSet.getString("level")));
			Lan.setSignNum(resultSet.getString("signnum"));
			Lan.setJoinNum(resultSet.getString("joinnum"));
			Lan.setPassNum(resultSet.getString("passnum"));
			list.add(Lan);
		}
		
		return list ;
	}
	public Language findByID(int ID) throws Exception {
		Language Lan = new Language();
		Connection connection = ConnectDB();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String selectSql = "SELECT * FROM language where seq = " + ID;
		pStatement = connection.prepareStatement(selectSql);
		resultSet = pStatement.executeQuery();
		resultSet.next();
		Lan.setSeq(Integer.valueOf(resultSet.getString("seq")));
		Lan.setCreateDate(resultSet.getString("createdate"));
		Lan.setYear(resultSet.getString("year"));
		Lan.setChineseName(resultSet.getString("chinesename"));
		Lan.setLevel(LanguageLevelEnum.valueOf(resultSet.getString("level")));
		Lan.setSignNum(resultSet.getString("signnum"));
		Lan.setJoinNum(resultSet.getString("joinnum"));
		Lan.setPassNum(resultSet.getString("passnum"));
		
		return Lan;
	}
	
	public void CloseDB(Statement stmt) throws SQLException{
		stmt.close();
	}
}
