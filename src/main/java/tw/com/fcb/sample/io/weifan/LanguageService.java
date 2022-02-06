package tw.com.fcb.sample.io.weifan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import tw.com.fcb.sample.io.gary.SexRatio;

public class LanguageService {
	
	public List<Language> loadFromFile() throws IOException {
		
		String FileName = "f:\\A53000000A-110067-003.csv";
		FileReader fr = new FileReader(FileName);
		BufferedReader br = new BufferedReader(fr);
		List<Language> result = new ArrayList<Language>();
		
		String lineData;
		
		while( (lineData = br.readLine()) != null) {
			
			String[] data = lineData.split(",");
			
			
			Language language = new Language();
			
			try {
			language.setSeq(Integer.valueOf(data[0]));
			language.setCreateDate(data[1]);
			language.setYear(data[2]);
			language.setChineseName(data[3]);
			language.setLevel(data[4]);
			language.setSignNum(data[5]);
			language.setJoinNum(data[6]);
			language.setPassNum(data[7]);
			result.add(language);
			}catch(NumberFormatException e) {
				System.out.println("資料型態錯誤");
			}
			
		}

		
		return result;
		
	}
	
	
}
