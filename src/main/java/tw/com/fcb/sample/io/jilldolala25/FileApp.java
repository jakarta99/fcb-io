package tw.com.fcb.sample.io.jilldolala25;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileApp {
    public static void main(String[] args) {
        try {
//        File file = new File("C:\\data\\data.txt");
//
//
//            FileReader fileReader = new FileReader(file);
//            int i = 0;
//            while(( i = fileReader.read()) != -1){
//                System.out.print((char)i);
//            }


            String inputFile = "c:\\data\\maskdata.csv";
            BufferedReader fileInputStream = new BufferedReader(new FileReader(inputFile));
            String lineData ;
            List<MaskSupport> maskList = new ArrayList<>();
            int count = 0;
            while ((lineData = fileInputStream.readLine()) != null ){
                count ++;
                if (count == 1) continue;

                String[] data = lineData.split(",");

                MaskSupport maskSupport = new MaskSupport();
                maskSupport.setMedicalcode(data[0]);
                maskSupport.setMedicalname(data[1]);
                maskSupport.setMedicaladdress(data[2]);
                maskSupport.setMedicalphone(data[3]);
                maskSupport.setAldultcount(data[4]);
                maskSupport.setKidscount(data[5]);
                maskSupport.setDate(data[6]);

                System.out.println(maskSupport);

                maskList.add(maskSupport);
            }
            System.out.println("目前共有"+ maskList.size()+"筆 健保特約機構");

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
