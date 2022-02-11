package tw.com.fcb.sample.io.kai;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileRepository {
	List<FileSecurities> list = null;
	FileSecurities fileSecurities = null;
	
	// getConnection()
	public Connection getConnection() throws Exception {
		Properties props = new Properties();
		String dbUrl = null, username = null, password = null;
		
//		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
//		String username = "postgres";
//		String password = "postgres";
		props.load(new FileInputStream("jdbc.properties"));
		dbUrl = props.getProperty("dbUrl");
		username = props.getProperty("username");
		password = props.getProperty("password");
		
		return DriverManager.getConnection(dbUrl, username, password);
	}
	
	// findAll()
	public List<FileSecurities> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
			
		String selectSql = "SELECT * FROM stock";
		pStatement = connection.prepareStatement(selectSql);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<FileSecurities>();
		while(resultSet.next()) {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(resultSet.getString("stockorder"));
			fileSecurities.setStockCode(resultSet.getString("stockcode"));
			fileSecurities.setStockName(resultSet.getString("stockname"));
			fileSecurities.setStockTransaction(resultSet.getString("stocktransaction"));
			fileSecurities.setEtfCode(resultSet.getString("etfcode"));
			fileSecurities.setEtfName(resultSet.getString("etfname"));
			fileSecurities.setEtfTransaction(resultSet.getString("etftransaction"));
			list.add(fileSecurities);
		}
		
		return list ;
	}
	
	// findAllOrderData()
	public List<FileSecurities> findAllOrderData() throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
			
		String selectSql = "SELECT * FROM stock ORDER BY stockcode";
		pStatement = connection.prepareStatement(selectSql);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<FileSecurities>();
		while(resultSet.next()) {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(resultSet.getString("stockorder"));
			fileSecurities.setStockCode(resultSet.getString("stockcode"));
			fileSecurities.setStockName(resultSet.getString("stockname"));
			fileSecurities.setStockTransaction(resultSet.getString("stocktransaction"));
			fileSecurities.setEtfCode(resultSet.getString("etfcode"));
			fileSecurities.setEtfName(resultSet.getString("etfname"));
			fileSecurities.setEtfTransaction(resultSet.getString("etftransaction"));
			list.add(fileSecurities);
		}
		
		return list ;
	}
	
	// findById()
	public List<FileSecurities> findById(String stockOrder) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
			
		String selectSql = "SELECT * FROM stock WHERE stockOrder = ?";
		pStatement = connection.prepareStatement(selectSql);
		pStatement.setString(1, stockOrder);
		resultSet = pStatement.executeQuery();
		
		list = new ArrayList<FileSecurities>();
		if(resultSet.next()) {
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(resultSet.getString("stockorder"));
			fileSecurities.setStockCode(resultSet.getString("stockcode"));
			fileSecurities.setStockName(resultSet.getString("stockname"));
			fileSecurities.setStockTransaction(resultSet.getString("stocktransaction"));
			fileSecurities.setEtfCode(resultSet.getString("etfcode"));
			fileSecurities.setEtfName(resultSet.getString("etfname"));
			fileSecurities.setEtfTransaction(resultSet.getString("etftransaction"));
			list.add(fileSecurities);
		}
		else {
			System.out.println("The data of stockOrder = " + stockOrder + " not found!! ");
		}
		
		return list ;
	}
	
	// insert
	public void insert(FileSecurities fileSecurities) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

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
	
	// update
	public void update(String stockCode, String stockOrder) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

		connection = getConnection();
			
		String updateSql = "UPDATE stock SET stockcode = ? WHERE stockorder = ?";
		pStatement = connection.prepareStatement(updateSql);
		pStatement.setString(1, stockCode);
		pStatement.setString(2, stockOrder);
		
		int affectedRow = pStatement.executeUpdate();
		System.out.println("共 " + affectedRow + " 筆資料更新");
	}
	
	// delete
	public void delete(String stockOrder) throws Exception {
		Connection connection = null;
		PreparedStatement pStatement = null;

		connection = getConnection();
			
		String updateSql = "DELETE FROM stock WHERE stockorder = ?";
		pStatement = connection.prepareStatement(updateSql);
		pStatement.setString(1, stockOrder);
		
		int affectedRow = pStatement.executeUpdate();
		System.out.println("共 " + affectedRow + " 筆資料刪除");
	}
}
