package tw.com.fcb.sample.io.iris;

import java.io.*;
import java.util.List;

public class RetireApp {

	public static void main(String[] args) {
		File file = new File("c:\\data\\110度臺北市政府所屬各機關公務人員退休人數按年齡分(行政機關).csv");

        FileReader fr = null;
        FileInputStream fis = null;

        try {
            fr = new FileReader(file);

            int i = 0;
            while( (i = fr.read()) != -1) {
                System.out.print((char) i);
            }

            System.out.print("\n");

            List<RetireAge> result = new RetireAgeService().loadFromFile();
            System.out.println("目前已經有 "+result.size()+"筆資料");

        } catch (FileNotFoundException e) {
            System.out.println("找不到檔案");
        } catch (IOException e) {
            System.out.println("檔案已毀損");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown");
            e.printStackTrace();
        } finally {
            try {
                if(fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

	}

}
