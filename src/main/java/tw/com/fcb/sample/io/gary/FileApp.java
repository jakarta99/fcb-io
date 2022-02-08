package tw.com.fcb.sample.io.gary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FileApp {

	public static void main(String[] args)  {
		
		File file = new File("c:\\data\\data.txt");
		
		FileReader fr = null;
		FileInputStream fis = null;
		
		try {
			fr = new FileReader(file);
			
			int i = 0;
			while( (i = fr.read()) != -1) {
				System.out.print( i+":"+Integer.toHexString(i)+":");
				System.out.print((char) i+";");
			}
			
			System.out.println("");
			System.out.print("\n");
			
			fis = new FileInputStream(file);
			
			int j = 0;
			while( (j = fis.read()) != -1) {
				System.out.print((int) j+":");
				System.out.print((char) j+";");
			}
			
			List<SexRatio> result = new SexRatioService().loadFromFile();
			System.out.println("目前已經有 "+result.size()+"筆資料");
			
		} catch (FileNotFoundException e) {
			System.out.println("找不到檔案");
		} catch (IOException e) {
			System.out.println("檔案已毀損");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknown");
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close(); 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
