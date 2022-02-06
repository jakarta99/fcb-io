package tw.com.fcb.sample.io.h25949;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinancialIndicatorsService {

	public List<FinancialIndicators> FinancialIndicatorsFile() throws IOException {
		// 讀取金融指標檔案
		String openFile = "d:\\A17000000J.csv";
		FileReader  fr= new FileReader(openFile);
		BufferedReader bfr = new BufferedReader(fr);
		
		String str;
		int idx = 0;
		List<FinancialIndicators> result = new ArrayList<FinancialIndicators>();
		
		while ((str=bfr.readLine())!= null) {	//每次讀取一行，直到檔案結束
			idx = idx+1;
			if(idx == 1) continue;
			
			System.out.println(str);
			String[] strData = str.split(",");
			
			// 轉成資料物件
			FinancialIndicators financialIndicators = new FinancialIndicators();
			financialIndicators.setYear(Integer.valueOf(strData[0]));
			financialIndicators.setExchangeRate(new BigDecimal(strData[1]));
			financialIndicators.setForeign(new BigDecimal(strData[2]));
			financialIndicators.setStockIndex(new BigDecimal(strData[3]));
			financialIndicators.setStockAmount(new BigDecimal(strData[4]));
			
			// 放至集合物件
			System.out.println(financialIndicators);
			result.add(financialIndicators);
		}
		
		fr.close();
		return result;
	}
}
