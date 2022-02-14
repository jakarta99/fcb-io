package tw.com.fcb.sample.io.h25949;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FinancialIndicatorsApp {
	public static void main(String[] args) {

		System.out.print("輸入選項：");
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();

		try {
			switch (option) {
			case 1:
				// insert檔案資料-每年國內主要金融指標
				List<FinancialIndicators> result = new FinancialIndicatorsService().FinancialIndicatorsFile();
				System.out.println("檔案內容有 " + result.size() + "筆資料");
				new FinancialIndicatorsService().insert(result);
				break;
				
			case 2:
				// Select所有-每年國內主要金融指標
				new FinancialIndicatorsService().findAll();
				break;
			
			case 3:
				// 依年份Select-每年國內主要金融指標
				new FinancialIndicatorsService().getById();		
				break;
				
			case 4:
				// 依年份Update-每年國內主要金融指標
				new FinancialIndicatorsService().update();
				break;
				
			case 5:
				// 依年份Delete-每年國內主要金融指標
				new FinancialIndicatorsService().delete();
				break;
				
			default:
				 System.out.println("選項有誤");
			}

		} catch (FileNotFoundException e) {
			System.out.println("找不到檔案");
		} catch (IOException e) {
			System.out.println("檔案已毀損");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		scanner.close();
	}
}
