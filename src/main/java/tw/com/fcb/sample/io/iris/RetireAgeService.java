package tw.com.fcb.sample.io.iris;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RetireAgeService {
	
	public List<RetireAge> loadFromFile() throws IOException {
        List<RetireAge> result = new ArrayList<RetireAge>();
        // 讀檔
        File file = new File("C:\\data\\110度臺北市政府所屬各機關公務人員退休人數按年齡分(行政機關).csv");
        FileReader  fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String lineData;
        int idx = 0;
        // 迴圈讀一行資料
        while ( (lineData = br.readLine()) != null){
            idx++;
//            System.out.println(lineData);

            // split 切割
            String[] data = lineData.split(",");

            // 設值
            RetireAge obj = new RetireAge();
            if (idx == 1){
                continue;
            }else{
                obj.setType(data[0]);
                obj.setVoluntary_cnt(Integer.valueOf(data[1]));
                obj.setAge_cnt(Integer.valueOf(data[2]));
                obj.setOrder_cnt(Integer.valueOf(data[3]));
            }

            // 放到 List 之中
            result.add(obj);
        }
        fr.close();
        br.close();
        return result;
    }

}
