package tw.com.fcb.sample.io.cj;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FileApp {

    public static void main(String[] args) {

        String function;
        Integer year;
        List<VehicleSharing> result;

        System.out.println("請選擇要執行的功能：1.讀檔輸入資料庫 2.依年度查詢");
        Scanner inputReader = new Scanner(System.in);
        function = inputReader.next();

        try {
            if (function.equals("1")){
                result = new VehicleSharingService().loadFromFile();
                System.out.println("寫入 "+result.size()+"筆資料");
            }else if (function.equals("2")){
                System.out.println("請輸入欲查詢之年度");
                year = inputReader.nextInt();
                VehicleRepository vehicleRepository = new VehicleRepository();
                System.out.println(vehicleRepository.getByYear(year));
            }else {
                System.out.println("輸入錯誤");
            }



        } catch (FileNotFoundException e) {
            System.out.println("找不到檔案");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("檔案已毀損");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown");
            e.printStackTrace();
        }

    }

















}
