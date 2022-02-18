package tw.com.fcb.sample.io.yuwei;


public enum IndustryEnum {
	financial,semiconductor(),steel,biomedical
	

}


//CREATE type industry_enum as enum('financial','semiconductor','steel','biomedical');
//ALTER TABLE Dividend ADD COLUMN industry industry_enum;
//select * from Dividend;