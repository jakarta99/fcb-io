package tw.com.fcb.sample.io.kai;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileSecuritiesApp {
	public static void main(String[] args) {
		FileSecuritiesService fileSecuritiesService = new FileSecuritiesService();
		FileSecurities fileSecurities = null;
		
		Scanner scanner = new Scanner(System.in);
		try {
			while(true) {
				System.out.print("Enter the option: 1).read-file 2).write-file 3).findAll 4).findById "
						+ "5).insert 6).update 7).delete 8).Order -1)Quit: ");
				int option = scanner.nextInt();
				
				if(option == 1) {
					fileSecuritiesService.readFile();
				}
				else if(option == 2) {
					fileSecuritiesService.writeFile();
				}
				else if(option == 3) {
					fileSecuritiesService.findAll();
				}
				else if(option == 4) {
					System.out.print("Enter the Stock Order : ");
					String stockOrder = scanner.next();
					fileSecuritiesService.findById(stockOrder);
				}
				else if(option == 5) {
					fileSecurities = new FileSecurities();
					System.out.print("Enter the stockOrder: ");
					String stockOrder = scanner.next();
					fileSecurities.setSecuritiesOrder(stockOrder);
					
					System.out.print("Enter the stockCode: ");
					String stockCode = scanner.next();
					fileSecurities.setStockCode(stockCode);
					
					System.out.print("Enter the stockName: ");
					String stockName = scanner.next();
					fileSecurities.setStockName(stockName);
					
					System.out.print("Enter the stockTrans: ");
					String stockTrans = scanner.next();
					fileSecurities.setStockTransaction(stockTrans);
					
					System.out.print("Enter the etfCode: ");
					String etfCode = scanner.next();
					fileSecurities.setEtfCode(etfCode);
					
					System.out.print("Enter the etfName: ");
					String etfName = scanner.next();
					fileSecurities.setEtfName(etfName);
					
					System.out.print("Enter the etfTrans: ");
					String etfTrans = scanner.next();
					fileSecurities.setEtfTransaction(etfTrans);
					
					System.out.print("Enter the currCode: ");
					String currCode = scanner.next();
					
					if(currCode.equals("USD") || currCode.equals("01")) {
						fileSecurities.setCurrencyEnum(FileCurrencyEnum.USD);
					}
					else if(currCode.equals("JPY") || currCode.equals("11")) {
						fileSecurities.setCurrencyEnum(FileCurrencyEnum.JPY);
					}
					else if(currCode.equals("ZAR") || currCode.equals("15")) {
						fileSecurities.setCurrencyEnum(FileCurrencyEnum.ZAR);
					}
					else if(currCode.equals("EUR") || currCode.equals("21")) {
						fileSecurities.setCurrencyEnum(FileCurrencyEnum.EUR);
					}
					else if(currCode.equals("TWD") || currCode.equals("99")) {
						fileSecurities.setCurrencyEnum(FileCurrencyEnum.TWD);
					}
					else {
						System.out.println("Wrong Currency Code !!!");
					}
					
//					System.out.println(fileSecurities.getCurrencyEnum().name());
					fileSecuritiesService.insert(fileSecurities);
				}
				else if(option == 6) {
					System.out.print("Enter the stockCode: ");
					String stockCode = scanner.next();
					System.out.print("Enter the stockOrder: ");
					String stockOrder = scanner.next();
					
					fileSecuritiesService.update(stockCode, stockOrder);
				}
				else if(option == 7) {
					System.out.print("Enter the stockOrder: ");
					String stockOrder = scanner.next();
					
					fileSecuritiesService.delete(stockOrder);
				}
				else if(option == 8) {
					System.out.print("Enter the column: ");
					String column = scanner.next();
					
					fileSecuritiesService.findOrderDataByCols(column);
				}
				else if(option == -1) {
					System.out.println("Quit...");
					break;
				}
				else {
					System.out.println("Wrong Option!!! ");
				}
				System.out.println();
			}
		} 
		catch(Exception e) {
			if(e instanceof SQLException) {
				e.printStackTrace();
				System.out.println("?????????????????????");
			}
			else if(e instanceof InputMismatchException) {
				e.printStackTrace();
				System.out.println("??????????????????");
			}
			else if(e instanceof FileNotFoundException) {
				e.printStackTrace();
				System.out.println("???????????????");
			}
			else if(e instanceof IOException) {
				e.printStackTrace();
				System.out.println("??????????????????");
			}
			else {
				e.printStackTrace();
				System.out.println("????????????");
			}
		}
		finally {
			scanner.close();
		}
	}
}
