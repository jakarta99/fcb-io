package tw.com.fcb.sample.io.gary;

import java.math.BigDecimal;
import java.util.Objects;

public class SexRatio {
	
	private String type;
	private int year;
	private BigDecimal ratio;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	@Override
	public String toString() {
		return "SexRatio [type=" + type + ", year=" + year + ", ratio=" + ratio + "]";
	}
	public void setYear(int year) {
		this.year = year;
	}
	public BigDecimal getRatio() {
		return ratio;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	

}
