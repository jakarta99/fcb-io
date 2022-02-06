package tw.com.fcb.sample.io.cj;

import java.io.*;
import java.util.List;

public class FileApp {

    public static void main(String[] args) {

        try {

            List<VehicleSharing> result = new VehicleSharingService().loadFromFile();
            System.out.println("目前已經有 "+result.size()+"筆資料");

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
