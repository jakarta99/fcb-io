package tw.com.fcb.sample.io.kai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FileRepository {
	public Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		
		return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public void selectData() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			statement = connection.createStatement();
			
			String selectSql = "SELECT * FROM stock";
			resultSet = statement.executeQuery(selectSql);
			ResultSetMetaData rMetaData = resultSet.getMetaData();
			int columns = rMetaData.getColumnCount();
			for(int i = 1 ; i <= columns ; i++)
				System.out.printf("%-8s\t", rMetaData.getColumnName(i));
			System.out.println();
			while(resultSet.next()) {
				for(int i = 1 ; i <= columns ; i++)
					System.out.printf("%-8s\t", resultSet.getObject(i));
				System.out.println();
			}
			
//			while(resultSet.next()) {
//				System.out.print(resultSet.getString("stockorder") + "\t");
//				System.out.print(resultSet.getString("stockcode") + "\t");
//				System.out.print(resultSet.getString("stockname") + "\t");
//				System.out.print(resultSet.getString("stocktransaction") + "\t");
//				System.out.print(resultSet.getString("etfcode") + "\t");
//				System.out.print(resultSet.getString("etfname") + "\t");
//				System.out.print(resultSet.getString("etftransaction") + "\t");
//				System.out.println();
//			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet selectOrderData() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			statement = connection.createStatement();
			
			String selectSql = "SELECT * FROM stock ORDER BY stockcode";
			resultSet = statement.executeQuery(selectSql);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void insertData(FileSecurities fileSecurities) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		
		try {
			connection = getConnection();
			
			String insertSql = "INSERT INTO stock VALUES(?, ?, ?, ?, ?, ?, ?)";
			pStatement = connection.prepareStatement(insertSql);
			pStatement.setString(1, fileSecurities.getSecuritiesOrder());
			pStatement.setString(2, fileSecurities.getStockCode());
			pStatement.setString(3, fileSecurities.getStockName());
			pStatement.setString(4, fileSecurities.getStockTransaction());
			pStatement.setString(5, fileSecurities.getEtfCode());
			pStatement.setString(6, fileSecurities.getEtfName());
			pStatement.setString(7, fileSecurities.getEtfTransaction());
			pStatement.executeUpdate();
			pStatement.clearParameters();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
