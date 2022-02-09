package tw.com.fcb.sample.io.yuwei;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DividendService {

	public List<Dividend> loadFromFile() throws IOException, SQLException {

		// 讀檔
		String openApiFile = "C:\\fcb-workspace\\fcb-io\\fubon-opendate.csv";
		BufferedReader br = new BufferedReader(new FileReader(openApiFile));
		String lineData;
		int idx = 0;
		List<Dividend> result = new ArrayList<Dividend>();
		DividendRepository dividendRepository = new DividendRepository();
		Connection conn = dividendRepository.getConnection();
		// 於 try 關鍵字後的小括號中進行 resource 宣告及初始化
		// 在 try 後方小括號初始化的資源會在離開 try 區塊時自動呼叫 close()
		try (conn;br) {
			// 先清空資料
			dividendRepository.delete(conn);
			// 迴圈讀一行資料
			while ((lineData = br.readLine()) != null) {
				idx = idx + 1;
				if (idx == 1)
					continue;
				// split 切割
				String[] data = lineData.split(",");
				// 設值
				Dividend dividend = new Dividend();
				dividend.setAllocationOfAnnual(Integer.parseInt(data[0]));
				dividend.setCashDividend(Double.parseDouble(data[1]));
				dividend.setStockDividend(Double.parseDouble(data[2]));
				dividend.setTotal(Double.parseDouble(data[3]));
				dividend.setTotalCashDividendUnit(Double.parseDouble(data[4]));
				dividend.setShareholdingRatio(Double.parseDouble(data[5]));
				dividend.setIssuingCompany(data[6]);
				System.out.println(dividend);
				// 放到 List 之中
				result.add(dividend);

				//insert資料進DB
				dividendRepository.insert(dividend, conn);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return result;
	}

	public void fileWrite() throws IOException, SQLException {
		
		// 寫檔
		String writeFile = "C:\\fcb-workspace\\fcb-io\\write-file.txt";
		BufferedWriter bufwriter = new BufferedWriter(new FileWriter(writeFile,false));
		DividendRepository dividendRepository = new DividendRepository();
		Connection conn = dividendRepository.getConnection();
		String issuingCompany ="富邦金控";
		try (bufwriter; conn) {
			List<Dividend> result = new ArrayList<Dividend>();
			result = dividendRepository.getByIssuingCompany(issuingCompany, conn);
			bufwriter.write("AllocationOfAnnual  "+"CashDividend  "+"StockDividend  "+"Total  "+"TotalCashDividendUnit  "+"ShareholdingRatio  "+"IssuingCompany");
			bufwriter.newLine();
			for(Dividend dividend :result) {
			bufwriter.write(dividend.getAllocationOfAnnual()+"   ");
			bufwriter.write(String.valueOf(dividend.getCashDividend())+"   ");
			bufwriter.write(String.valueOf(dividend.getStockDividend())+"   ");
			bufwriter.write(String.valueOf(dividend.getTotal()+"   "));
			bufwriter.write(String.valueOf(dividend.getTotalCashDividendUnit())+"   ");
			bufwriter.write(String.valueOf(dividend.getShareholdingRatio())+"   ");
			bufwriter.write(dividend.getIssuingCompany());
			bufwriter.newLine();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		
		
	}
	
}
