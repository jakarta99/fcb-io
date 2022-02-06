package tw.com.fcb.sample.io.weifan;

import java.io.IOException;
import java.util.List;

public class FileIOApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<Language> result = new LanguageService().loadFromFile();
		for(int i = 0; i < result.size(); i++){
			System.out.println(result.get(i).toString());
		}
	}

}
