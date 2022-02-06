package tw.com.fcb.sample.io.cj;

public class VehicleSharing {

    private String yearMonth;
    private String brand;
    private String type;
    private String amount;


    @Override
    public String toString() {
        return "VehicleSharing{" +
                "yearMonth='" + yearMonth + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
