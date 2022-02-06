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
		
		try {
			listData = bankRoeService.loadFromFile();
			System.out.println("目前有 " + listData.size() + "筆資料");
			
			//印出資料
			for (int i = 0; i < listData.size(); i ++)
			{
				System.out.println(listData.get(i).toString());				
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
