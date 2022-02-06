package tw.com.fcb.sample.io.ijoshua29;

public class BankRoe {

	private int statisticalYear;
	private int statisticalMonth;
	private int averageAsset;		//平均資產-新臺幣億元
	private int averageNetWorth;	//平均淨值-新臺幣億元
	private int ebt;				//稅前盈餘-新臺幣億元
	private double roa;				//資產報酬率(ROA)-%
	private double roe;				//淨值報酬率(ROE)-%
	
	
	@Override
	public String toString() {
		return "BankAverageRoe [statisticalYear=" + statisticalYear + ", statisticalMonth=" + statisticalMonth
				+ ", averageAsset=" + averageAsset + ", averageNetWorth=" + averageNetWorth + ", ebt=" + ebt + ", roa="
				+ roa + ", roe=" + roe + "]";
	}
	
	public int getStatisticalYear() {
		return statisticalYear;
	}
	public void setStatisticalYear(int statisticalYear) {
		this.statisticalYear = statisticalYear;
	}
	public int getStatisticalMonth() {
		return statisticalMonth;
	}
	public void setStatisticalMonth(int statisticalMonth) {
		this.statisticalMonth = statisticalMonth;
	}
	public int getAverageAsset() {
		return averageAsset;
	}
	public void setAverageAsset(int averageAsset) {
		this.averageAsset = averageAsset;
	}
	public int getAverageNetWorth() {
		return averageNetWorth;
	}
	public void setAverageNetWorth(int averageNetWorth) {
		this.averageNetWorth = averageNetWorth;
	}
	public int getEbt() {
		return ebt;
	}
	public void setEbt(int ebt) {
		this.ebt = ebt;
	}
	public double getRoa() {
		return roa;
	}
	public void setRoa(double roa) {
		this.roa = roa;
	}
	public double getRoe() {
		return roe;
	}
	public void setRoe(double roe) {
		this.roe = roe;
	}
}
