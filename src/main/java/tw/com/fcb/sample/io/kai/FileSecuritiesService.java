package tw.com.fcb.sample.io.kai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileSecuritiesService {
	List<FileSecurities> listSecurities = null;
	FileRepository fileRepository = null;
	
	public FileSecuritiesService() {}
	
	// readFile ... into db
	public void readFile() throws Exception {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileSecurities fileSecurities = null;
		fileRepository = new FileRepository();

		fileReader = new FileReader("data.csv");
		bufferedReader = new BufferedReader(fileReader);
			
		listSecurities = new ArrayList<FileSecurities>();
		String lineData = "";
		int idx = 0;
		while((lineData = bufferedReader.readLine()) != null) {
			if((++idx) == 1) {
				continue;
			}
				
			String[] tokenData = lineData.split(",");
			fileSecurities = new FileSecurities();
			fileSecurities.setSecuritiesOrder(tokenData[0]);
			fileSecurities.setStockCode(tokenData[1]);
			fileSecurities.setStockName(tokenData[2]);
			fileSecurities.setStockTransaction(tokenData[3]);
			fileSecurities.setEtfCode(tokenData[4]);
			fileSecurities.setEtfName(tokenData[5]);
			fileSecurities.setEtfTransaction(tokenData[6]);
				
			// INSERT INTO DB...
			fileRepository.insert(fileSecurities);
				
			// listSecurties
			listSecurities.add(fileSecurities);
//			System.out.println(fileSecurities);
		}
		System.out.println("讀取檔案共 " + listSecurities.size() + " 筆資料");
		closeReadFile(bufferedReader, fileReader);
	}
	public void closeReadFile(BufferedReader br, FileReader fr) throws Exception {
		br.close();
		fr.close();
	}
	
	// writeFile ... from db
	public void writeFile() throws Exception {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		fileWriter = new FileWriter("dataW.txt", true);
		bufferedWriter = new BufferedWriter(fileWriter);
		
		// findAllOrderData()
		fileRepository = new FileRepository();
		listSecurities = fileRepository.findAllOrderData();
			
		String resultString = "";
		for(int i = 0 ; i < listSecurities.size() ; i++) {
			resultString = securituesToString(i);
			bufferedWriter.write(resultString);
			bufferedWriter.newLine();
		}
		System.out.println("寫入檔案共 " + listSecurities.size() + " 筆資料");
		closeWriteFile(bufferedWriter, fileWriter);
	}
	public void closeWriteFile(BufferedWriter bw, FileWriter fw) throws Exception {
		bw.close();
		fw.close();
	}
	
	// findAll()
	public void findAll() throws Exception {
		fileRepository = new FileRepository();
		listSecurities = fileRepository.findAll();
		
		System.out.println("共有 " + listSecurities.size() + " 筆資料");
		for(int i = 0 ; i < listSecurities.size() ; i++) {
			System.out.println(listSecurities.get(i).toString());
		}
	}
	
	// findById
	public void findById(String stockOrder) throws Exception {
		fileRepository = new FileRepository();
		listSecurities = fileRepository.findById(stockOrder);
		
		for(int i = 0 ; i < listSecurities.size() ; i++) {
			System.out.println(listSecurities.get(i).toString());
		}
	}
	
	// insert
	public void insert(FileSecurities fileSecurities) throws Exception {
		fileRepository = new FileRepository();
		fileRepository.insert(fileSecurities);
		findAll();
	}
	
	// update
	public void update(String stockCode, String stockOrder) throws Exception {
		fileRepository = new FileRepository();
		
		System.out.println("修改前: ");
		findById(stockOrder);
		
		System.out.println("修改後: ");
		fileRepository.update(stockCode, stockOrder);
		findById(stockOrder);
	}
	
	// delete
	public void delete(String stockOrder) throws Exception {
		fileRepository = new FileRepository();
		fileRepository.delete(stockOrder);
		findAll();
	}
	
	// securituesToString()
	public String securituesToString(int i) {
		String result = "";
		result =  listSecurities.get(i).getSecuritiesOrder() + "," +
				listSecurities.get(i).getStockCode() + "," +
				listSecurities.get(i).getStockName() + "," +
				listSecurities.get(i).getStockTransaction() + "," + 
				listSecurities.get(i).getEtfCode() + "," + 
				listSecurities.get(i).getEtfName() + "," + 
				listSecurities.get(i).getEtfTransaction();
		
		return result;
	}
	
	// sortStockName()
	public void sortStockName() {
		Collections.sort(listSecurities, new Comparator<FileSecurities>() {
			public int compare(FileSecurities o1, FileSecurities o2) {
				return o1.getStockCode().compareTo(o2.getStockCode());
			}
		});
	}
}
