package tw.com.fcb.sample.io.yuwei;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DividendRepositoryTest {

	@Test
	void testInsert() {
		Dividend dividend = new Dividend();
		dividend.setAllocationOfAnnual(999);
		dividend.setCashDividend(9);
		dividend.setStockDividend(9);
		dividend.setTotal(418);
		dividend.setTotalCashDividendUnit(99.24);
		dividend.setShareholdingRatio(99.11);
		dividend.setIssuingCompany("富邦金控");
		DividendRepository dividendRepository = new DividendRepository();
		try {
			dividendRepository.insertData(dividend);
			Dividend actualDividend = dividendRepository.getById(dividend.getId());
			System.out.println(dividend);
			System.out.println(actualDividend);
			assertEquals(dividend.toString(), actualDividend.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
