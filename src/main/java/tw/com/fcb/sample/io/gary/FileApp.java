package tw.com.fcb.sample.io.gary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
				System.out.print((char) i);
			}
			
			System.out.println("");
			System.out.print("\n");
			
			fis = new FileInputStream(file);
			
			int j = 0;
			while( (j = fis.read()) != -1) {
				System.out.print((char) j);
			}
			
			String openApiFile = "c:\\data\\A17000000J-030266-pJX.csv";
			BufferedReader br = new BufferedReader(new FileReader(openApiFile));
			String lineData;
			
			int idx = 0;
			
			List<SexRatio> result = new ArrayList<SexRatio>();
			
			while( (lineData = br.readLine()) != null) {
				
				idx = idx+1;
				
				if(idx == 1) continue;
				
				System.out.println(lineData);
				String[] data = lineData.split(",");
				
				
				SexRatio sexRatio = new SexRatio();
				sexRatio.setType(data[0]);
				
				String tempYear = data[1];
				tempYear = tempYear.substring(0, tempYear.indexOf("年"));
				sexRatio.setYear(Integer.valueOf(tempYear));
				sexRatio.setRatio(new BigDecimal(data[2]));
				
				System.out.println(sexRatio);
				
				result.add(sexRatio);
				
			}
			
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