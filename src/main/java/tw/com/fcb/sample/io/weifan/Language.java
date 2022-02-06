package tw.com.fcb.sample.io.weifan;

public class Language {

	private int Seq;
	private String CreateDate;
	private String year;
	private String ChineseName;
	private String Level;
	private String SignNum;
	private String JoinNum;
	private String PassNum;
	@Override
	public String toString() {
		return "Language [Seq=" + Seq + ", CreateDate=" + CreateDate + ", year=" + year + ", ChineseName=" + ChineseName
				+ ", Level=" + Level + ", SignNum=" + SignNum + ", JoinNum=" + JoinNum + ", PassNum=" + PassNum + "]";
	}
	public int getSeq() {
		return Seq;
	}
	public void setSeq(int seq) {
		Seq = seq;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getChineseName() {
		return ChineseName;
	}
	public void setChineseName(String chineseName) {
		ChineseName = chineseName;
	}
	public String getLevel() {
		return Level;
	}
	public void setLevel(String level) {
		Level = level;
	}
	public String getSignNum() {
		return SignNum;
	}
	public void setSignNum(String signNum) {
		SignNum = signNum;
	}
	public String getJoinNum() {
		return JoinNum;
	}
	public void setJoinNum(String joinNum) {
		JoinNum = joinNum;
	}
	public String getPassNum() {
		return PassNum;
	}
	public void setPassNum(String passNum) {
		PassNum = passNum;
	}

	
}
