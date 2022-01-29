package tw.com.fcb.sample.io.gary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SexRatioService {

	
	public List<SexRatio> loadFromFile() throws IOException {
		
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
		
		return result;
		
	}
} 
