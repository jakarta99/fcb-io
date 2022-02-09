package tw.com.fcb.sample.io.h25949;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FinancialIndicatorsApp {

	public static void main(String[] args) {
		// 提供每年國內主要金融指標
		try {
			List<FinancialIndicators> result = new FinancialIndicatorsService().FinancialIndicatorsFile();
			System.out.println("目前已經有 " + result.size() + "筆資料");
			int count = new FinancialIndicatorsRepository().insert(result);
			System.out.println("目前已寫入 " + count + "筆資料");
			
		} catch (FileNotFoundException e) {
			System.out.println("找不到檔案");
		} catch (IOException e) {
			System.out.println("檔案已毀損");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
