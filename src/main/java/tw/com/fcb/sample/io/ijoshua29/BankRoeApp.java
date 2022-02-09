package tw.com.fcb.sample.io.ijoshua29;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankRoeApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankRoeService bankRoeService = new BankRoeService();
		List<BankRoe> listData = new ArrayList<BankRoe>();
		int successUpdateResult = 0;
		int faulureUpdateResult = 0;
		boolean isWriteOK;
		
		try {
			listData = bankRoeService.loadFromFile();
			System.out.println("目前有 " + listData.size() + "筆資料");
			
			//印出資料
			for (int i = 0; i < listData.size(); i ++)
			{
				System.out.println(listData.get(i).toString());				
			}
			
			//insert db
			successUpdateResult = bankRoeService.insertDb(listData);
			//計算失敗筆數
			faulureUpdateResult = listData.size() - successUpdateResult;
			
			System.out.println("Update DB結果:成功" + successUpdateResult +
					"筆，失敗" + faulureUpdateResult + "筆");
			
			//從DB讀取資料
			List<BankRoe> fetchData = new ArrayList<BankRoe>();
			fetchData = bankRoeService.fetchDb();
			
			//寫出至CSV檔案
			isWriteOK = bankRoeService.writeFile(fetchData);
			
			if (isWriteOK)
			{
				System.out.println("寫入檔案outBankRoa_e.csv成功");
			}
			else
			{
				System.out.println("寫入檔案outBankRoa_e.csv失敗");
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("找不到檔案");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("檔案讀取有誤");
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			System.out.println("其他錯誤");
			e.printStackTrace();
		}
	}
}
