package tw.com.fcb.sample.io.cj;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FileApp {

    public static void main(String[] args) {

        String function;
        Integer year;
        Integer id;
        Integer amount;
        List<VehicleSharing> result;

        System.out.println("請選擇要執行的功能：1.讀檔輸入資料庫 2.依年度查詢 3.findAll 4.getById 5.update 6.delete");
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
            }else if (function.equals("3")){
                result = new VehicleRepository().findAll();
                System.out.println(result);
                System.out.println("共 "+result.size()+ " 筆資料");
            }else if (function.equals("4")){
                System.out.println("請輸入欲查詢之ID");
                id = inputReader.nextInt();
                VehicleRepository vehicleRepository = new VehicleRepository();
                System.out.println(vehicleRepository.getByID(id));
            }else if (function.equals("5")){
                System.out.println("請輸入欲變更金額之ID");
                id = inputReader.nextInt();
                System.out.println("請輸入欲變更之金額");
                amount = inputReader.nextInt();
                VehicleRepository vehicleRepository = new VehicleRepository();
                System.out.println(vehicleRepository.update(id, amount));
            }else if (function.equals("6")){
                System.out.println("請輸入欲刪除之ID");
                id = inputReader.nextInt();
                VehicleRepository vehicleRepository = new VehicleRepository();
                System.out.println(vehicleRepository.delete(id));
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
