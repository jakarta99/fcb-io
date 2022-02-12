package tw.com.fcb.sample.io.copykobe0103;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileApp {

    public static void main(String[] args) {
        String inputNumber;
        ConsumptionService consumptionService = new ConsumptionService();
        ConsumptionRepository consumptionRepository = new ConsumptionRepository();
        List<Consumption> resultList = new ArrayList<Consumption>();
        Scanner input = new Scanner(System.in);
        System.out.println("======================================================================");
        System.out.println("信用卡跨境消費資料庫練習作業，請輸入選項:");
        System.out.println("======================================================================");
        System.out.println("1.讀取信用卡跨境消費檔案資料insert to table");
        System.out.println("2.查詢全部table資料");
        System.out.println("3.查詢table某年月資料");
        System.out.println("4.更新table某跨境消費比重資料");
        System.out.println("5.刪除table某年月跨境消費比重資料");
        System.out.println("6.查詢信用卡跨境消費比重按大到小排序");
        System.out.println("0.離開");
        System.out.println("======================================================================");
        System.out.print("請輸入選項 : ");
        try {
//            while ((inputNumber = input.nextInt()) != 0) {
            while (!(inputNumber = input.next()).equals("0")) {
                switch (inputNumber) {
                    case "1":
                            resultList = consumptionService.inputFile();

                            System.out.println("目前已經有 " + resultList.size() + "筆資料");
                        break;
                    case "2":
                        resultList = consumptionRepository.selectAll();
                        System.out.println(resultList);
                        System.out.println("目前table共" + resultList.size() + "筆資料");
                        break;
                    case "3":
                        System.out.print("請輸入欲查詢年月(YYYYMM) : ");
                        String yearMonth = input.next();
                        resultList = consumptionRepository.selectYearMonth(yearMonth);
                        System.out.println(resultList);
                        System.out.println("目前table " + yearMonth.substring(0,4) + "年" +
                                yearMonth.substring(4,6) + "月 共"+ resultList.size() + "筆資料");
                        break;
                    case "4":
                        System.out.println("請輸入欲更新之資料id : ");
                        Long id = input.nextLong();
                        System.out.println("請輸入欲更新之跨境消費比重 : ");
                        String percentage = input.next();
                        consumptionRepository.updatePercentage(id,percentage);
                        break;
                    case "5":
                        System.out.println("請輸入欲刪除之資料年月 : ");
                        String deleteYearMonth = input.next();
                        consumptionRepository.deleteYearMonth(deleteYearMonth);
                        break;
                    case "6":
                        resultList = consumptionRepository.selectAllSortByPercentage();

                        System.out.println("目前table共" + resultList.size() + "筆資料");
                        System.out.println("比重最高前五筆資料如下:");
                        System.out.println(resultList.get(0));
                        System.out.println(resultList.get(1));
                        System.out.println(resultList.get(2));
                        System.out.println(resultList.get(3));
                        System.out.println(resultList.get(4));
                        break;
                    default:
                        System.err.println("輸入錯誤，請重新輸入");
                        break;
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("如有需要，請繼續選擇下列選項");
                System.out.println("1.讀取信用卡跨境消費檔案資料insert to table");
                System.out.println("2.查詢全部table資料");
                System.out.println("3.查詢table某年月資料");
                System.out.println("4.更新table某跨境消費比重資料");
                System.out.println("5.刪除table某年月跨境消費比重資料");
                System.out.println("6.查詢信用卡跨境消費比重按大到小排序");
                System.out.println("0.離開");
                System.out.println("======================================================================");
                System.out.print("請輸入選項 : ");
            }
            System.out.println("轉核亨通!!₍₍ ◝('ω'◝) ⁾⁾ ₍₍ (◟'ω')◟ ⁾⁾");
        } catch (FileNotFoundException e) {
            System.out.println("找不到檔案");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("檔案已毀損");
            e.printStackTrace();
        } catch (Exception e ){
            System.out.println("你來亂的，請離開");
        }
    }


}


