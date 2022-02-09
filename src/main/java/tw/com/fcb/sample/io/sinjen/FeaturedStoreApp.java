package tw.com.fcb.sample.io.sinjen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeaturedStoreApp {

	public static void main(String[] args) {
		FeaturedStoreService fss = new FeaturedStoreService();
		FeaturedStoreRepository fsr = new FeaturedStoreRepository();
		List<StoreRow> row = new ArrayList<StoreRow>();
		List<StoreRow> dbRow = new ArrayList<StoreRow>();
		try {
			//讀取csv檔案
			row = fss.loadFromFile();
			//寫入txt檔案
			fss.writeTxtFile(row);
			//寫入DB檔案
			fsr.insertDB_featured_store(row);
			//讀取DB檔案
			dbRow = fsr.getDBQueryResult();
			//寫入xls檔案
			fss.writeXlsFile(dbRow);
			
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
