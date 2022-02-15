package tw.com.fcb.sample.io.jilldolala25;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileApp {
    public static void main(String[] args) {
        try {

            MaskMedicalService maskWrite = new MaskMedicalService();
            //讀取INPUT FILE，寫入DB
            maskWrite.LoadMedicalFile();
//            //練習 INESRT自動產生key,UpdateByKey,DeleteBykey,findAll 2022/02/11
            maskWrite.runCrud();
            // 練習enum，輸入欲查詢之醫療機構代碼，取得醫療機構(enum)種類 2022/02/15
            maskWrite.FindMedicalType();
//            // 輸入醫事機構代碼任意數，select DB後將結果寫file
//            maskWrite.FileWriter();


        } catch(Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }
}
