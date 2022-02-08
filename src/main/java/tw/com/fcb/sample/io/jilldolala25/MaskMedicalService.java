package tw.com.fcb.sample.io.jilldolala25;


import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class MaskMedicalService {
    List<MaskMedical> maskList = null;
    MaskMedicalRepository maskMedicalRepository = new MaskMedicalRepository();
    Connection conn = maskMedicalRepository.getConnection();

    public MaskMedicalService() throws SQLException {
    }

    public void LoadMedicalFile() throws IOException, SQLException {
        String inputFile = "c:\\data\\maskdata.csv";
        BufferedReader fileInputStream = new BufferedReader(new FileReader(inputFile));
        String lineData;
        maskList = new ArrayList<>();
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
            maskMedical.setAldultcount(data[4]);
            maskMedical.setKidscount(data[5]);
            maskMedical.setDate(data[6]);

//            System.out.println(maskMedical);

            maskList.add(maskMedical);


            maskMedicalRepository.insert(maskMedical,conn);

        }
        System.out.println("MaskMedicalRepository select 5907360379= "
                + maskMedicalRepository.getByMedicalCode("5907360379"));
        conn.close();
        System.out.println("目前共有"+ maskList.size()+"筆 健保特約機構");

    }

    public void FileWriter() throws IOException {

        FileWriter fileWriter = new FileWriter("c:\\data\\outputFile.txt",false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        System.out.println("maskList size() =" +  maskList.size());
        for (int i = 0; i < maskList.size(); i++) {
            if (i == 0){
                bufferedWriter.write("來源資料時間" + ","+ "醫事機構代碼" + ","+ "醫事機構名稱"+ "," + "醫事機構地址"+ "," + "醫事機構電話"+ "," + "成人口罩剩餘數" + ","+ "兒童口罩剩餘數");
                bufferedWriter.newLine();
            }
            String resultSet =  ListFormat(i);
            System.out.println(resultSet);
            bufferedWriter.write(resultSet);
            bufferedWriter.newLine();

        }
        bufferedWriter.close();

    }
    public String ListFormat(int i){
        String result = maskList.get(i).getDate().substring(0,10)+ "," +
                maskList.get(i).getMedicalcode()+","+
                maskList.get(i).getMedicalname() + ","+
                maskList.get(i).getMedicaladdress()+ "," +
                maskList.get(i).getMedicalphone() + "," +
                maskList.get(i).getAldultcount() + "," +
                maskList.get(i).getKidscount() + "," ;


        return result;
    }
}
