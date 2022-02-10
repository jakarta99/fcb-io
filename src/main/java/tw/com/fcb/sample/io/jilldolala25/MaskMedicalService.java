package tw.com.fcb.sample.io.jilldolala25;


import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MaskMedicalService {
    List<MaskMedical> maskList = null;
    MaskMedicalRepository maskMedicalRepository;


    public MaskMedicalService()  {
    }

    public void LoadMedicalFile()  {
        String inputFile = "c:\\data\\maskdata.csv";

        maskMedicalRepository = new MaskMedicalRepository();

        try ( Connection conn = maskMedicalRepository.getConnection()){
            BufferedReader fileInputStream = new BufferedReader(new FileReader(inputFile));
            String lineData;
            maskList = new ArrayList<>();


            // insert前先將資料清空
            maskMedicalRepository.delete(conn);

            int count = 0;
            while ((lineData = fileInputStream.readLine()) != null) {
                count++;
                if (count == 1) continue;

                String[] data = lineData.split(",");

                MaskMedical maskMedical = new MaskMedical();
                maskMedical.setMedicalcode(data[0]);
                maskMedical.setMedicalname(data[1]);
                maskMedical.setMedicaladdress(data[2]);
                maskMedical.setMedicalphone(data[3]);
                maskMedical.setAldultcount(Integer.parseInt(data[4]));
                maskMedical.setKidscount(Integer.parseInt(data[5]));
                maskMedical.setDate(data[6]);

//            System.out.println(maskMedical);
                // add to list
                maskList.add(maskMedical);

                //insert into db
                maskMedicalRepository.insert(maskMedical,conn);

            }
            System.out.println("資料庫內目前共有"+ maskMedicalRepository.count(conn)+"筆 健保特約機構");
            // close db connection
//            conn.close();
            System.out.println("目前共有"+ maskList.size()+"筆 健保特約機構");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void FileWriter() throws IOException {

        FileWriter fileWriter = new FileWriter("c:\\data\\outputFile.txt",false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

//        for (int i = 0; i < maskList.size(); i++) {
//            if (i == 0){
//                bufferedWriter.write("來源資料時間" + ","+ "醫事機構代碼" + ","+ "醫事機構名稱"+ "," + "醫事機構地址"+ "," + "醫事機構電話"+ "," + "成人口罩剩餘數" + ","+ "兒童口罩剩餘數");
//                bufferedWriter.newLine();
//            }
//            String resultSet =  ListFormat(i);
//            System.out.println(resultSet);
//            bufferedWriter.write(resultSet);
//            bufferedWriter.newLine();
//
//        }
//        bufferedWriter.close();
//從資料庫讀出，再寫檔

        try ( Connection conn = maskMedicalRepository.getConnection()) {
            List<MaskMedical> result = maskMedicalRepository.getByMedicalCode("233", conn);
            for (int i = 0; i < result.size(); i++) {
                if (i == 0){
                bufferedWriter.write("來源資料時間" + ","+ "醫事機構代碼" + ","+ "醫事機構名稱"+ "," + "醫事機構地址"+ "," + "醫事機構電話"+ "," + "成人口罩剩餘數" + ","+ "兒童口罩剩餘數");
                bufferedWriter.newLine();}

                String resultSet = result.get(i).getDate() + ","
                        + result.get(i).getMedicalcode() + ","
                        + result.get(i).getMedicalname() + ","
                        + result.get(i).getMedicaladdress() + ","
                        + result.get(i).getAldultcount() + ","
                        + result.get(i).getKidscount();
                System.out.println(resultSet);
                bufferedWriter.write(resultSet);
                bufferedWriter.newLine();
            } {


        }
        bufferedWriter.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String ListFormat(int i){
        String outPutFormat = maskList.get(i).getDate().substring(0,10)+ "," +
                maskList.get(i).getMedicalcode()+","+
                maskList.get(i).getMedicalname() + ","+
                maskList.get(i).getMedicaladdress()+ "," +
                maskList.get(i).getMedicalphone() + "," +
                maskList.get(i).getAldultcount() + "," +
                maskList.get(i).getKidscount() + "," ;


        return outPutFormat;
    }
}
