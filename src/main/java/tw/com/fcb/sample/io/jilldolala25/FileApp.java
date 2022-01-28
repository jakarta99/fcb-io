package tw.com.fcb.sample.io.jilldolala25;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileApp {
    public static void main(String[] args) {
        try {
        File file = new File("C:\\data\\data.txt");


            FileReader fileReader = new FileReader(file);
            int i = 0;
            while(( i = fileReader.read()) != -1){
                System.out.print((char)i);
            }


            String inputFile = "c:\\data\\maskSupport.csv";
            BufferedReader fileInputStream = new BufferedReader(new FileReader(inputFile));
            String lineData ;
            List<MaskSupport> maskList = new ArrayList<>();
            int count = 0;
            while ((lineData = fileInputStream.readLine()) != null ){
                count ++;
                if (count == 1) continue;

                String[] data = lineData.split(",");

                MaskSupport maskSupport = new MaskSupport();
                maskSupport.setDate(data[0]);
                maskSupport.setMaskCount(data[1]);
                maskSupport.setName(data[2]);

                System.out.println(maskSupport);

                maskList.add(maskSupport);
            }
            System.out.println("目前共有"+ maskList.size()+"筆口罩響應者");

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
