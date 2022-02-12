package tw.com.fcb.sample.io.copykobe0103;

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

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", yearMonth='" + yearMonth + '\'' +
                ", region='" + region + '\'' +
                ", crossBorderPercentage='" + crossBorderPercentage + '\'' +
                ", cardCount=" + cardCount +
                ", totalTradeCount=" + totalTradeCount +
                ", totalTradeAmount=" + totalTradeAmount +
                ", crossBorderCount=" + crossBorderCount +
                ", totalCrossBorderAmount=" + totalCrossBorderAmount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCrossBorderPercentage() {
        return crossBorderPercentage;
    }

    public void setCrossBorderPercentage(String crossBorderPercentage) {
        this.crossBorderPercentage = crossBorderPercentage;
    }

    public double getCardCount() {
        return cardCount;
    }

    public void setCardCount(double cardCount) {
        this.cardCount = cardCount;
    }

    public double getTotalTradeCount() {
        return totalTradeCount;
    }

    public void setTotalTradeCount(double totalTradeCount) {
        this.totalTradeCount = totalTradeCount;
    }

    public double getTotalTradeAmount() {
        return totalTradeAmount;
    }

    public void setTotalTradeAmount(double totalTradeAmount) {
        this.totalTradeAmount = totalTradeAmount;
    }

    public double getCrossBorderCount() {
        return crossBorderCount;
    }

    public void setCrossBorderCount(double crossBorderCount) {
        this.crossBorderCount = crossBorderCount;
    }

    public double getTotalCrossBorderAmount() {
        return totalCrossBorderAmount;
    }

    public void setTotalCrossBorderAmount(double totalCrossBorderAmount) {
        this.totalCrossBorderAmount = totalCrossBorderAmount;
    }
}
