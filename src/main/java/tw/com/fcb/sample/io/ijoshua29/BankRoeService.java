package tw.com.fcb.sample.io.ijoshua29;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankRoeService {
	
	public List<BankRoe> loadFromFile() throws IOException
	{		
		String openFile = "C:\\bankRoa_e.csv";
		BufferedReader br = new BufferedReader(new FileReader(openFile));		
		int index = 0;
		String rowData;
		List<BankRoe> listData = new ArrayList<BankRoe>();
		
		while ((rowData = br.readLine()) != null)
		{
			index ++;
			//第一筆為標題列不處理
			if (index == 1)
				continue;
			
			String[] tmpData = rowData.split(",");
			BankRoe bankRoe = new BankRoe();
			bankRoe.setStatisticalYear(Integer.parseInt(tmpData[0]));
			bankRoe.setStatisticalMonth(Integer.parseInt(tmpData[1]));
			bankRoe.setAverageAsset(Integer.parseInt(tmpData[2]));
			bankRoe.setAverageNetWorth(Integer.parseInt(tmpData[3]));
			bankRoe.setEbt(Integer.parseInt(tmpData[4]));
			bankRoe.setRoa(Double.parseDouble(tmpData[5]));
			bankRoe.setRoe(Double.parseDouble(tmpData[6]));
			listData.add(bankRoe);
		}

		return listData;
	}
	
	
	private Connection getConnection() throws SQLException {
		//Class.forName("org.postgresql.Driver");
		String dbUrl = "jdbc:postgresql://localhost:5432/testdb";
		String username = "postgres";
		String password = "postgres";
		return DriverManager.getConnection(dbUrl, username, password);
			
	}
	
	public int insertDb(List<BankRoe> listData) throws SQLException {
		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		int sqlResult = 0;
		int successSqlResult = 0;
		
		for (int i = 0; i < listData.size(); i ++)
		{
			System.out.println(listData.get(i).toString());	
			String sqlCmd = "INSERT INTO bankRoe values ("
					+ listData.get(i).getStatisticalYear() + ", "
					+ listData.get(i).getStatisticalMonth() + ", "
					+ listData.get(i).getAverageAsset() + ", "
					+ listData.get(i).getAverageNetWorth() + ", "
					+ listData.get(i).getEbt() + ", "
					+ listData.get(i).getRoa() + ", "
					+ listData.get(i).getRoe()
					+ ")";
			
			sqlResult = stmt.executeUpdate(sqlCmd);
			
			if (sqlResult == 1)
				successSqlResult++;
		}
		
		stmt.close();
		conn.close();
		return successSqlResult;
	}
	
	public List<BankRoe> fetchDb() throws SQLException
	{		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sqlCmd = "select * from bankroe";
		ResultSet rs = stmt.executeQuery(sqlCmd);
		List<BankRoe> listData = new ArrayList<BankRoe>();
		
		while(rs.next())
		{
			BankRoe bankRoe = new BankRoe();
			bankRoe.setStatisticalYear(rs.getInt("statisticalYear"));
			bankRoe.setStatisticalMonth(rs.getInt("statisticalMonth"));
			bankRoe.setAverageAsset(rs.getInt("averageAsset"));
			bankRoe.setAverageNetWorth(rs.getInt("averageNetWorth"));
			bankRoe.setEbt(rs.getInt("ebt"));
			bankRoe.setRoa(rs.getDouble("roa"));
			bankRoe.setRoe(rs.getDouble("roe"));
			listData.add(bankRoe);
		}
		
		stmt.close();
		conn.close();
		return listData;
	}
	
	public boolean writeFile(List<BankRoe> fetchData) 
	{		
		boolean isWriteOK = true;
		
		try
		{			
			//建立檔案
			String filePath = "D:\\outBankRoa_e.csv";
			File file = new File(filePath);
			file.createNewFile();
			
			FileWriter fw = new FileWriter(filePath, true);
			StringBuilder sb = new StringBuilder();
			
			//寫title
			sb.append("統計年度");
			sb.append(",");
			sb.append("統計月份");
			sb.append(",");
			sb.append("平均資產-新臺幣億元");
			sb.append(",");
			sb.append("平均淨值-新臺幣億元");
			sb.append(",");
			sb.append("稅前盈餘-新臺幣億元");
			sb.append(",");
			sb.append("資產報酬率(ROA)-%");
			sb.append(",");
			sb.append("淨值報酬率(ROE)-%");
			sb.append("\n");
			
			for (int i = 0; i < fetchData.size(); i ++)
			{
				sb.append(fetchData.get(i).getStatisticalYear());
				sb.append(",");
				sb.append(fetchData.get(i).getStatisticalMonth());
				sb.append(",");
				sb.append(fetchData.get(i).getAverageAsset());
				sb.append(",");
				sb.append(fetchData.get(i).getAverageNetWorth());
				sb.append(",");
				sb.append(fetchData.get(i).getEbt());
				sb.append(",");
				sb.append(fetchData.get(i).getRoa());
				sb.append(",");
				sb.append(fetchData.get(i).getRoe());
				sb.append("\n");
			}
			
			fw.write(sb.toString());
			fw.close();
		}
		catch (IOException e) 
		{
			System.out.println("檔案存取有誤");
			e.printStackTrace();
			isWriteOK = false;
		} 
		catch (Exception e) 
		{
			System.out.println("其他錯誤");
			e.printStackTrace();
			isWriteOK = false;
		}
		
		return isWriteOK;
	}
}
