package tw.com.fcb.sample.io.jilldolala25;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
