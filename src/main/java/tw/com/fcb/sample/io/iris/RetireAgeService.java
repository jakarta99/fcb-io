package tw.com.fcb.sample.io.iris;

import java.io.*;
import java.sql.SQLException;
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
	
//	   2022/02/11 homework
//	   2022/02/15 hw 新增RetireEnum
	public void runCrud() throws SQLException {
		// run findAll
		RetireRepository rey = new RetireRepository();
		List<RetireAge> retires = rey.findAll();
		for (RetireAge inx : retires) {
			System.out.println(inx);
			}
		
		// List<Fruit> fruits.size = 0
		System.out.println("資料筆數共 = " + retires.size() + "筆");
		
		// insert
		RetireAge retObj = new RetireAge();
		retObj.setType("test");
		retObj.setVoluntary_cnt(0);
		retObj.setAge_cnt(0);
		retObj.setOrder_cnt(0);
		retObj.setRetireEnum(RetireEnum.M);
		rey.inserDB(retObj);
		retires = rey.findAll();
		System.out.println("新增後資料內容 = " + rey.getByType("test"));
		System.out.println("新增後資料筆數 = " + retires.size() + "筆");
		
		// update price
		rey.updateDB(Long.valueOf(4),5);
		
		// delete fruit by id
		rey.deleteDB("test");
		retires = rey.findAll();
		System.out.println("刪除後資料筆數 = " + retires.size() + "筆");
		
	}

}
