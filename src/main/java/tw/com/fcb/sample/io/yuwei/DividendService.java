package tw.com.fcb.sample.io.yuwei;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DividendService {

	public List<Dividend> loadFromFile() throws IOException {

		// 讀檔
		String openApiFile = "C:\\fcb-workspace\\fcb-io\\fubon-opendate.csv";
		BufferedReader br = new BufferedReader(new FileReader(openApiFile));
		String lineData;
		int idx = 0;
		List<Dividend> result = new ArrayList<Dividend>();
		// 迴圈讀一行資料
		while ((lineData = br.readLine()) != null) {
			idx = idx + 1;
//				System.out.println(lineData);

			if (idx == 1)
				continue;
			// split 切割
			String[] data = lineData.split(",");
			// 設值
			Dividend dividend = new Dividend();
			dividend.setAllocationOfAnnual(data[0]);
			dividend.setCashDividend(Double.parseDouble(data[1]));
			dividend.setStockDividend(Double.parseDouble(data[2]));
			dividend.setTotal(data[3]);
			dividend.setTotalCashDividendUnit(Double.parseDouble(data[4]));
			dividend.setShareholdingRatio(Double.parseDouble(data[5]));
			dividend.setIssuingCompany(data[6]);
			System.out.println(dividend);
			// 放到 List 之中
			result.add(dividend);
		}

		return result;
	}
}
