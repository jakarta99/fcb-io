package tw.com.fcb.sample.io.jilldolala25;

import lombok.Data;

@Data
public class MaskMedical {
    Long id;
    String medicalcode;
    String medicalname;
    String medicaladdress;
    String medicalphone;
    int aldultcount;
    int kidscount;
    String date;
    MaskMedicalEnum maskMedicalEnum;


}
