package tw.com.fcb.sample.io.cj;

public class VehicleSharing {

    private int year;
    private int month;
    private String brand;
    private int type;
    private int amount;

    @Override
    public String toString() {
        return "VehicleSharing{" +
                "year=" + year +
                ", month=" + month +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
