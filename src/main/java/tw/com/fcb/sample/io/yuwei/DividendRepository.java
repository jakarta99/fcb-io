package tw.com.fcb.sample.io.yuwei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DividendRepository {

	public Connection getConnection() throws SQLException {
		
		//Class.forName("org.postgresql.Driver");
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public List<Dividend> getByIssuingCompany(String issuingCompany,Connection conn) throws SQLException{

		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from dividend where issuing_company = '"+issuingCompany+"'";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		List<Dividend> rsList = new ArrayList<Dividend>();
		while(rs.next()) {
			Dividend dividend = new Dividend();
			dividend.setAllocationOfAnnual(rs.getInt("allocation_of_annual"));
			dividend.setCashDividend(rs.getDouble("cash_dividend"));
			dividend.setStockDividend(rs.getDouble("stock_dividend"));
			dividend.setTotal(rs.getDouble("total"));
			dividend.setTotalCashDividendUnit(rs.getDouble("total_cash_dividend_Unit"));
			dividend.setShareholdingRatio(rs.getDouble("shareholding_ratio"));
			dividend.setIssuingCompany(rs.getString("issuing_company"));
			System.out.println("Query " + dividend);
			rsList.add(dividend);
		}
		rs.close();
		stmt.close();
		return rsList;
	}
	
	
	public int insert(Dividend dividend,Connection conn) throws SQLException{
//		Statement stmt = conn.createStatement();
//		String sqlCmd = "INSERT INTO dividend values ("
//				+"'"+dividend.getAllocationOfAnnual()+"',"
//				+"'"+dividend.getCashDividend()+"',"
//				+"'"+dividend.getStockDividend()+"',"
//				+"'"+dividend.getTotal()+"',"
//				+"'"+dividend.getTotalCashDividendUnit()+"',"
//				+"'"+dividend.getShareholdingRatio()+"',"
//				+"'"+dividend.getIssuingCompany()+"')";
//		stmt.executeUpdate(sqlCmd);
		
		String sqlCmd = "INSERT INTO dividend values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sqlCmd);
		stmt.setInt(1, dividend.getAllocationOfAnnual());
		stmt.setDouble(2, dividend.getCashDividend());
		stmt.setDouble(3, dividend.getStockDividend());
		stmt.setDouble(4, dividend.getTotal());
		stmt.setDouble(5, dividend.getTotalCashDividendUnit());
		stmt.setDouble(6, dividend.getShareholdingRatio());
		stmt.setString(7, dividend.getIssuingCompany());
		System.out.println("sqlCmd Insert "+sqlCmd);
		stmt.executeUpdate();
		stmt.clearParameters();
		stmt.close();
		return -1;
	}
	
	
	public void delete(Connection conn) throws SQLException{
		Statement stmt = conn.createStatement();
		String sqlCmd = "Delete from dividend";
		stmt.executeUpdate(sqlCmd);
		stmt.close();
	}
}
