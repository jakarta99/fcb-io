package tw.com.fcb.sample.io.ijoshua29;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
}
