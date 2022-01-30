package tw.com.fcb.sample.io.sinjen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeaturedStoreApp {

	public static void main(String[] args) {
		FeaturedStoreService fss = new FeaturedStoreService();
		List<StoreRow> row = new ArrayList<StoreRow>();
		try {
			row = fss.loadFromFile();
			fss.writeTxtFile(row);
			fss.writeXlsFile(row);
			
		} catch (FileNotFoundException e) {
			System.out.println("找不到檔案");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("檔案已毀損");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknown");
			e.printStackTrace();
		} finally {
		}

	}

}
