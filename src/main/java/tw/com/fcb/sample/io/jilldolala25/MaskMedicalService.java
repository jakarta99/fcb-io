package tw.com.fcb.sample.io.jilldolala25;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaskMedicalService {

    public List<MaskMedical> LoadMedicalFile() throws IOException {
        String inputFile = "c:\\data\\maskdata.csv";
        BufferedReader fileInputStream = new BufferedReader(new FileReader(inputFile));
        String lineData;
        List<MaskMedical> maskList = new ArrayList<>();
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

            System.out.println(maskMedical);

            maskList.add(maskMedical);
        }

        return  maskList;
    }

}
