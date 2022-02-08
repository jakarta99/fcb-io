package tw.com.fcb.sample.io.kai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileSecuritiesService {
	List<FileSecurities> listSecurities = null;
	FileRepository fileRepository = null;
	
	public FileSecuritiesService() {}
	
	// readFile ... into db
	public void readFile() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileSecurities fileSecurities = null;
		fileRepository = new FileRepository();

		try {
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
				
				// listSecurties
				listSecurities.add(fileSecurities);
				
				// INSERT INTO DB...
				fileRepository.insertData(fileSecurities);
				
				// sort data by sotckname
				sortStockName();
				System.out.println(fileSecurities);
			}
			System.out.println("共有 " + listSecurities.size() + " 筆資料");
			
			// 按照股票代號排序 (test...)
//			System.out.println("\n股票代號排序");
//			for(FileSecurities f : listSecurities) {
//				System.out.println(f);
//			}
		}
		catch(FileNotFoundException e) {
			System.out.println("檔案不存在");
		}
		catch(IOException e) {
			System.out.println("檔案讀取有誤");
		}
		catch(Exception e) {
			System.out.println("未知例外");
			e.printStackTrace();
		}
		finally {
			try {
				bufferedReader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fileReader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// writeFile ... from db
	public void writeFile() {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		ResultSet resultSet = null;
		fileRepository = new FileRepository();
		
		try {
			fileWriter = new FileWriter("dataW.txt", true);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			resultSet = fileRepository.selectOrderData();
			
			String result = "";
			while(resultSet.next()) {
				result = resultSet.getString("stockorder") + "," +
						resultSet.getString("stockcode") + "," +
						resultSet.getString("stockname") + "," +
						resultSet.getString("stocktransaction") + "," +
						resultSet.getString("etfcode") + "," +
						resultSet.getString("etfname") + "," +
						resultSet.getString("etftransaction");
				
				bufferedWriter.write(result);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			
//			for(int i = 0 ; i < listSecurities.size() ; i++) {
//				String resultString = securituesToString(i);
//				bufferedWriter.write(resultString);
//				bufferedWriter.newLine();
//			}
//			bufferedWriter.close();
			
		}
		catch(FileNotFoundException e) {
			System.out.println("檔案不存在");
		}
		catch(IOException e) {
			System.out.println("檔案讀取有誤");
		}
		catch(Exception e) {
			System.out.println("未知例外");
			e.printStackTrace();
		}
		finally {
			try {
				bufferedWriter.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void selectData() {
		fileRepository = new FileRepository();
		fileRepository.selectData();
	}
	
	public void sortStockName() {
		Collections.sort(listSecurities, new Comparator<FileSecurities>() {
			public int compare(FileSecurities o1, FileSecurities o2) {
				return o1.getStockCode().compareTo(o2.getStockCode());
			}
		});
	}
	
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
}
