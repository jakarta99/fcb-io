package tw.com.fcb.sample.io.kai;

// 定期定額前十名交易戶數證券統計
public class FileSecurities {
	private String securitiesOrder;
	private String stockCode;
	private String stockName;
	private String stockTransaction;
	private String etfCode;
	private String etfName;
	private String etfTransaction;
	
	@Override
	public String toString() {
		return "FileSecurities [securitiesOrder=" + securitiesOrder + ", stockCode=" + stockCode + ", stockName="
				+ stockName + ", stockTransaction=" + stockTransaction + ", etfCode=" + etfCode + ", etfName=" + etfName
				+ ", etfTransaction=" + etfTransaction + "]";
	}
	
	// 排序
	public String getSecuritiesOrder() {
		return securitiesOrder;
	}

	public void setSecuritiesOrder(String securitiesOrder) {
		this.securitiesOrder = securitiesOrder;
	}
	
	// 股票代號
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	// 股票名稱
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	// 股票交易戶數
	public String getStockTransaction() {
		return stockTransaction;
	}

	public void setStockTransaction(String stockTransaction) {
		this.stockTransaction = stockTransaction;
	}
	
	// ETF代號
	public String getEtfCode() {
		return etfCode;
	}

	public void setEtfCode(String etfCode) {
		this.etfCode = etfCode;
	}
	
	// ETF名稱
	public String getEtfName() {
		return etfName;
	}

	public void setEtfName(String etfName) {
		this.etfName = etfName;
	}
	
	// ETF交易戶數
	public String getEtfTransaction() {
		return etfTransaction;
	}

	public void setEtfTransaction(String etfTransaction) {
		this.etfTransaction = etfTransaction;
	}
}
