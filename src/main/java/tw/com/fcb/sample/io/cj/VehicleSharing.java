package tw.com.fcb.sample.io.cj;

import lombok.Data;

@Data
public class VehicleSharing {

    private int id;
    private int year;
    private int month;
    private VehicleBrandEnum brand;
    private int type;
    private int amount;

}
