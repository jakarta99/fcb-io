package tw.com.fcb.sample.io.h25949;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tw.com.fcb.sample.io.gary.Fruit;

public class FinancialIndicatorsRepository {

	private Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";

		return DriverManager.getConnection(dbUrl, username, password);
	}

	public int insert(List<FinancialIndicators> result) throws SQLException {

		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		int count=0;
		
		for (FinancialIndicators FinancialIndicator : result) {
			String SQL = "INSERT INTO fruit values (" + "'" + FinancialIndicator.getYear() + "', " + "'"
					+ FinancialIndicator.getExchangeRate() + "', " + "'" + FinancialIndicator.getForeign() + "', " + "'"
					+ FinancialIndicator.getStockIndex() + "', " + "" + FinancialIndicator.getStockAmount() + "" + ")";
			// String SQL = "insert into
			// fruit(the_year,exchange_rate,the_foreign,stock_index,stock_amount)
			// values(2001,28.508,5299.11,14732.53,491825)";
			count = count + stmt.executeUpdate(SQL);
		}
		
		stmt.close();
		return count;
	}
	
	public List<FinancialIndicators> findAll() throws SQLException{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		
		String sqlCmd = "select * from fruit";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		
		List<FinancialIndicators> FinancialIndicators = new ArrayList<FinancialIndicators>();

		FinancialIndicators FinancialIndicator;
		while(rs.next()) {
			FinancialIndicator = new FinancialIndicators();
			FinancialIndicator.setYear(rs.getInt("the_yaer"));
			FinancialIndicator.setExchangeRate(rs.getBigDecimal("exchange_rate"));
			FinancialIndicator.setForeign(rs.getBigDecimal("the_foreign"));
			FinancialIndicator.setStockIndex(rs.getBigDecimal("stock_index"));
			FinancialIndicator.setStockAmount(rs.getBigDecimal("stock_amount"));
			
			FinancialIndicators.add(FinancialIndicator);
		}
		return FinancialIndicators;
	}

	public FinancialIndicators getById(int optionById) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();

		String sqlCmd = "select * from fruit where the_year = '" + optionById + "'";
		ResultSet rs = stmt.executeQuery(sqlCmd);

		FinancialIndicators FinancialIndicator = new FinancialIndicators();
		while (rs.next()) {
			FinancialIndicator.setYear(rs.getInt("the_yaer"));
			FinancialIndicator.setExchangeRate(rs.getBigDecimal("exchange_rate"));
			FinancialIndicator.setForeign(rs.getBigDecimal("the_foreign"));
			FinancialIndicator.setStockIndex(rs.getBigDecimal("stock_index"));
			FinancialIndicator.setStockAmount(rs.getBigDecimal("stock_amount"));
		}

		return FinancialIndicator;
	}

	public int update(FinancialIndicators financialIndicator) throws SQLException{
		String SQL="UPDATE fruit SET exchange_rate=? , the_foreign=?, stock_index=?,stock_amount =? WHERE the_year = ?";
		Connection conn = getConnection();
		PreparedStatement ps= conn.prepareStatement(SQL);
		
		ps.setBigDecimal(1,financialIndicator.getExchangeRate());
		ps.setBigDecimal(2,financialIndicator.getForeign());
		ps.setBigDecimal(3,financialIndicator.getStockIndex());
		ps.setBigDecimal(4,financialIndicator.getStockAmount());
		ps.setInt(5,financialIndicator.getYear());

		return ps.executeUpdate();
	}

	public int delete(int optionById) throws SQLException{
		String SQL = "DELETE FROM fruit WHERE the_year = ?";
		Connection conn = getConnection();
		PreparedStatement ps= conn.prepareStatement(SQL);
		
		ps.setInt(1,optionById);
		
		return ps.executeUpdate();
	}

	
}
