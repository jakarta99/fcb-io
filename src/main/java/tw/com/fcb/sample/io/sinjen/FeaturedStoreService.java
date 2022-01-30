package tw.com.fcb.sample.io.sinjen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class FeaturedStoreService {

	private static final String getWorkingDirectory= "c:\\fcb\\fcb-io\\";
	private static final String TAB = "\t";
	private static final String NEW_LINE = "\r\n";
	private static final String REPORT_title = "新北市商圈特色商家";
	private static final String HEADING_1 = "商圈名稱";
	private static final String HEADING_2 = "特色商家";
	private static final String HEADING_3 = "店家電話";
	private static final String HEADING_4 = "店家地址";
	private static final String HEADING_5 = "店家商品";
	private static final String HEADING_6 = "twd97緯度";
	private static final String HEADING_7 = "twd97經度";
	private static final String HEADING_8 = "wgs84a緯度";
	private static final String HEADING_9 = "wgs84a經度";
	private static final String[] HEADING_ARRAY = {HEADING_1, HEADING_2, HEADING_3, HEADING_4, HEADING_5, HEADING_6, HEADING_7, HEADING_8, HEADING_9};
	private static final String txtTitle = HEADING_1+TAB+HEADING_2+TAB+HEADING_3+TAB+HEADING_4+TAB+HEADING_5+TAB
			+HEADING_6+TAB+HEADING_7+TAB+HEADING_8+TAB+HEADING_9;

	
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
		System.out.println(fileName+"讀檔完成，共："+result.size()+ "筆資料");
		br.close();
		return result;
	}
	
	public void writeTxtFile(List<StoreRow> listResult) throws IOException {
		StoreRow row = null;
		String fileName = "Featured Store.txt";
		FileWriter bw = new FileWriter(getWorkingDirectory+fileName);
		bw.write(txtTitle + NEW_LINE); //輸出標題
		
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
		System.out.println(fileName+"寫檔完成，共："+size+ "筆資料");
		bw.flush();
		bw.close();
	}
	
	public void writeXlsFile(List<StoreRow> listResult) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		int size = listResult.size();

		// 編輯excel
		HSSFSheet sheet = wb.createSheet(REPORT_title);
		// 設定樣式
		HSSFCellStyle textHCentel = wb.createCellStyle();
		textHCentel.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCellStyle textHRCentel = wb.createCellStyle();
		textHRCentel.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFCellStyle textBottomCentel = wb.createCellStyle();
		textBottomCentel.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		// 設定樣式
		HSSFCellStyle textHVCentel = wb.createCellStyle();
		textHVCentel.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		textHVCentel.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		textHVCentel.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 設定儲存格外框
		textHVCentel.setBorderTop(HSSFCellStyle.BORDER_THIN);
		textHVCentel.setWrapText(true);
		
		// 設定欄寬
		sheet.setColumnWidth((short) 0, 4500);
		sheet.setColumnWidth((short) 1, 8000);
		sheet.setColumnWidth((short) 2, 4000);
		sheet.setColumnWidth((short) 3, 8000);
		sheet.setColumnWidth((short) 4, 10000);
		sheet.setColumnWidth((short) 5, 3500);
		sheet.setColumnWidth((short) 6, 3500);
		sheet.setColumnWidth((short) 7, 4500);
		sheet.setColumnWidth((short) 8, 4500);
		HSSFRow hssfRow = null;
		HSSFCell cell = null;
		
		// 第一行title
		hssfRow = sheet.createRow(0);
		cell = hssfRow.createCell(0);
		cell.setCellValue(new HSSFRichTextString(REPORT_title));
		cell.setCellStyle(textHCentel);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,8));
		
		// 第二行編輯欄位名稱
		hssfRow = sheet.createRow(1);
		for (int i = 0; i < HEADING_ARRAY.length; i++) {
			cell = hssfRow.createCell(i);
			cell.setCellValue(new HSSFRichTextString(HEADING_ARRAY[i]));
			cell.setCellStyle(textHVCentel);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, i, i));
		}
		
		// 第3行開始為資料源寫入資料
		int currentRow = 2;
		for (StoreRow row : listResult) {
			hssfRow = sheet.createRow(currentRow);
			cell = hssfRow.createCell(0);
			cell.setCellValue(new HSSFRichTextString(row.getZone()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(1);
			cell.setCellValue(new HSSFRichTextString(row.getStore()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(2);
			cell.setCellValue(new HSSFRichTextString(row.getStoreTel()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(3);
			cell.setCellValue(new HSSFRichTextString(row.getStoreAdd()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(4);
			cell.setCellValue(new HSSFRichTextString(row.getStoreProduct()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(5);
			cell.setCellValue(new HSSFRichTextString(row.getTwd97X()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(6);
			cell.setCellValue(new HSSFRichTextString(row.getTwd97Y()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(7);
			cell.setCellValue(new HSSFRichTextString(row.getWgs84aX()));
			cell.setCellStyle(textHCentel);
			cell = hssfRow.createCell(8);
			cell.setCellValue(new HSSFRichTextString(row.getWgs84aY()));
			cell.setCellStyle(textHCentel);

			currentRow++;
		}
			
		// 產出xls檔案
		String fileName = "Featured Store.xls";
		File file = new File(getWorkingDirectory + fileName);
		FileOutputStream fileOut = new FileOutputStream(file);
		wb.write(fileOut);
		System.out.println(fileName + "寫檔完成，共：" + size + "筆資料");
		fileOut.flush();
		fileOut.close();
	}
}
