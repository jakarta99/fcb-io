package tw.com.fcb.sample.io.copykobe0103;

import lombok.Data;

@Data
public class Consumption {
    private Long id;
    private String yearMonth;
    private String region;
    private String crossBorderPercentage;
    private double cardCount;
    private double totalTradeCount;
    private double totalTradeAmount;
    private double crossBorderCount;
    private double totalCrossBorderAmount;

}
