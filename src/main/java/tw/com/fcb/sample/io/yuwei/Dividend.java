package tw.com.fcb.sample.io.yuwei;

public class Dividend {
	
	private int allocationOfAnnual;
	
	private double cashDividend;
	
	private double stockDividend;
	
	private double  total;
	
	private double totalCashDividendUnit;
	
	private double shareholdingRatio;
	
	private String issuingCompany;

	
	@Override
	public String toString() {
		return "Dividend [allocationOfAnnual=" + allocationOfAnnual + ", cashDividend=" + cashDividend
				+ ", stockDividend=" + stockDividend + ", total=" + total + ", totalCashDividendUnit="
				+ totalCashDividendUnit + ", shareholdingRatio=" + shareholdingRatio + ", issuingCompany="
				+ issuingCompany + "]";
	}


	public int getAllocationOfAnnual() {
		return allocationOfAnnual;
	}


	public void setAllocationOfAnnual(int allocationOfAnnual) {
		this.allocationOfAnnual = allocationOfAnnual;
	}


	public double getCashDividend() {
		return cashDividend;
	}


	public void setCashDividend(double cashDividend) {
		this.cashDividend = cashDividend;
	}


	public double getStockDividend() {
		return stockDividend;
	}


	public void setStockDividend(double stockDividend) {
		this.stockDividend = stockDividend;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public double getTotalCashDividendUnit() {
		return totalCashDividendUnit;
	}


	public void setTotalCashDividendUnit(double totalCashDividendUnit) {
		this.totalCashDividendUnit = totalCashDividendUnit;
	}


	public double getShareholdingRatio() {
		return shareholdingRatio;
	}


	public void setShareholdingRatio(double shareholdingRatio) {
		this.shareholdingRatio = shareholdingRatio;
	}


	public String getIssuingCompany() {
		return issuingCompany;
	}


	public void setIssuingCompany(String issuingCompany) {
		this.issuingCompany = issuingCompany;
	}




}
