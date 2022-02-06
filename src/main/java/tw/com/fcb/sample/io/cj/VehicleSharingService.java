package tw.com.fcb.sample.io.cj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehicleSharingService {

    List<VehicleSharing> result = new ArrayList<VehicleSharing>();


    public List<VehicleSharing> loadFromFile() throws IOException {


        String openApiFile = "/Users/xiechengrong/Desktop/workspace/fcb-io/臺北市共享運具許可情形.csv";
        BufferedReader br = new BufferedReader(new FileReader(openApiFile));
        String lineData;
        int idx = 0;

        while ((lineData = br.readLine()) != null) {
            idx = idx+1;
            if(idx == 1) continue;
            System.out.println(lineData);
            String[] data = lineData.split(",");

            VehicleSharing vehicleSharing = new VehicleSharing();
            vehicleSharing.setYearMonth(data[0]);
            vehicleSharing.setBrand(data[1]);
            vehicleSharing.setType(data[2]);
            vehicleSharing.setAmount(data[3]);

            System.out.println(vehicleSharing);
            result.add(vehicleSharing);
        }

        return result;
    }

}
