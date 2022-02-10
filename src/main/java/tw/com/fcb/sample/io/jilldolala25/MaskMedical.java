package tw.com.fcb.sample.io.jilldolala25;

public class MaskMedical {
    String medicalcode;
    String medicalname;
    String medicaladdress;
    String medicalphone;
    int aldultcount;
    int kidscount;
    String date;

    @Override
    public String toString() {
        return "MaskMedical{" +
                "medicalcode='" + medicalcode + '\'' +
                ", medicalname='" + medicalname + '\'' +
                ", medicaladdress='" + medicaladdress + '\'' +
                ", medicalphone='" + medicalphone + '\'' +
                ", aldultcount='" + aldultcount + '\'' +
                ", kidscount='" + kidscount + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getMedicalcode() {
        return medicalcode;
    }

    public void setMedicalcode(String medicalcode) {
        this.medicalcode = medicalcode;
    }

    public String getMedicalname() {
        return medicalname;
    }

    public void setMedicalname(String medicalname) {
        this.medicalname = medicalname;
    }

    public String getMedicaladdress() {
        return medicaladdress;
    }

    public void setMedicaladdress(String medicaladdress) {
        this.medicaladdress = medicaladdress;
    }

    public String getMedicalphone() {
        return medicalphone;
    }

    public void setMedicalphone(String medicalphone) {
        this.medicalphone = medicalphone;
    }

    public int getAldultcount() {
        return aldultcount;
    }

    public void setAldultcount(int aldultcount) {
        this.aldultcount = aldultcount;
    }

    public int getKidscount() {
        return kidscount;
    }

    public void setKidscount(int kidscount) {
        this.kidscount = kidscount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
