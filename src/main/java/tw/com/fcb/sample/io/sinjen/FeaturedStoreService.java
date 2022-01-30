package tw.com.fcb.sample.io.sinjen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeaturedStoreService {

	private static final String getWorkingDirectory= "c:\\fcb\\fcb-io\\";
	private static final String title = "商圈名稱\t特色商家\t店家電話\t店家地址\t店家商品"
			+ "\ttwd97緯度\ttwd97經度\twgs84a緯度\twgs84a經度";
	private static final String TAB = "\t";
	private static final String NEW_LINE = "\r\n";
	
	public List<StoreRow> loadFromFile() throws IOException {

		//新北市商圈特色商家
		//https://data.gov.tw/dataset/125593
		String fileName = "Featured Store.csv";
		String openApiFile = getWorkingDirectory+fileName;
		List<StoreRow> result = new ArrayList<StoreRow>();

		BufferedReader br = new BufferedReader(new FileReader(openApiFile));
		String lineData;

		int idx = 0;

		while ((lineData = br.readLine()) != null) {

			idx = idx + 1;

			if (idx == 1)
				continue;

			//System.out.println(lineData);
			String[] data = lineData.split(",");

			StoreRow storeRow = new StoreRow();
			storeRow.setZone(data[0].replace("\"", "").trim());
			storeRow.setStore(data[1].replace("\"", "").trim());
			storeRow.setStoreTel(data[2].replace("\"", "").trim());
			storeRow.setStoreAdd(data[3].replace("\"", "").trim());
			storeRow.setStoreProduct(data[4].replace("\"", "").trim());
			storeRow.setTwd97X(data[5].replace("\"", "").trim());
			storeRow.setTwd97Y(data[6].replace("\"", "").trim());
			storeRow.setWgs84aX(data[7].replace("\"", "").trim());
			storeRow.setWgs84aY(data[8].replace("\"", "").trim());
			//System.out.println(storeRow);

			result.add(storeRow);

		}
		System.out.println("讀檔完成，共："+result.size()+ "筆資料");
		br.close();
		return result;
	}
	
	public void writeFile(List<StoreRow> listResult) throws IOException {
		StoreRow row = null;
		String fileName = "Featured Store.txt";
		FileWriter bw = new FileWriter(getWorkingDirectory+fileName);
		bw.write(title + NEW_LINE); //輸出標題
		
		int size = listResult.size();
		for (int i = 0; i < size; i++) {
			row = (StoreRow) listResult.get(i);
			bw.write(new StringBuffer(row.getZone()).append(TAB)
					.append(row.getStore()).append(TAB)
					.append(row.getStoreTel()).append(TAB)
					.append(row.getStoreAdd()).append(TAB)
					.append(row.getStoreProduct()).append(TAB)
					.append(row.getTwd97X()).append(TAB)
					.append(row.getTwd97Y()).append(TAB)
					.append(row.getWgs84aX()).append(TAB)
					.append(row.getWgs84aY()).append(TAB)
					.append(NEW_LINE).toString());
		}
		System.out.println("寫檔完成，共："+size+ "筆資料");
		bw.flush();
		bw.close();
	}
}
