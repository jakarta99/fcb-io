package tw.com.fcb.sample.io.h25949;

import java.math.BigDecimal;

public class FinancialIndicators {
	//資料物件
	private int year;
	private BigDecimal exchangeRate;
	private BigDecimal foreign;
	private BigDecimal stockIndex;
	private BigDecimal stockAmount;
	
	@Override
	public String toString() {
		return "FinancialIndicators [year=" + year + ", exchangeRate=" + exchangeRate + ", foreign=" + foreign
				+ ", stockIndex=" + stockIndex + ", stockAmount=" + stockAmount + "]";
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public BigDecimal getForeign() {
		return foreign;
	}
	public void setForeign(BigDecimal foreign) {
		this.foreign = foreign;
	}
	public BigDecimal getStockIndex() {
		return stockIndex;
	}
	public void setStockIndex(BigDecimal stockIndex) {
		this.stockIndex = stockIndex;
	}
	public BigDecimal getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(BigDecimal stockAmount) {
		this.stockAmount = stockAmount;
	}
}
