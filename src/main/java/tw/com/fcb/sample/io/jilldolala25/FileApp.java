package tw.com.fcb.sample.io.jilldolala25;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class FileApp {
    public static void main(String[] args) {
        try {
            MaskMedicalRepository maskMedicalRepository = new MaskMedicalRepository();
            Connection conn = maskMedicalRepository.getConnection();
            // insert前先delete
            maskMedicalRepository.delete(conn);

            //
            MaskMedicalService maskWrite = new MaskMedicalService();
            maskWrite.LoadMedicalFile();

            maskWrite.FileWriter();

        } catch (FileNotFoundException e) {
            System.out.println("檔案找不到");

        } catch (IOException e) {
            System.out.println("檔案已毀損");
            e.printStackTrace();
        } catch(Exception e) {
            System.out.println("unknown");
            e.printStackTrace();
        }
    }
}
