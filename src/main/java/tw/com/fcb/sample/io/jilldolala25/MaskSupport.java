package tw.com.fcb.sample.io.jilldolala25;

public class MaskSupport {
    String date;
    String maskCount;
    String name;

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "MaskSupport{" +
                "date='" + date + '\'' +
                ", maskCount='" + maskCount + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaskCount() {
        return maskCount;
    }

    public void setMaskCount(String maskCount) {
        this.maskCount = maskCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
