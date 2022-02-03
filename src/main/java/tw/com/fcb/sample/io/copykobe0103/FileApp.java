package tw.com.fcb.sample.io.copykobe0103;

import tw.com.fcb.sample.io.yuwei.Dividend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileApp {

    public static void main(String[] args) {
        ConsumptionService consumptionService = new ConsumptionService();
        try {
            List<Consumption> resultList = consumptionService.inputFile();
            System.out.println("目前已經有 " + resultList.size() + "筆資料");
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
