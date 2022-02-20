package tw.com.fcb.sample.io.iris;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RetireRepositoryTest {

	@Test
	void testFindAll() throws SQLException {
//		fail("Not yet implemented");
		RetireRepository retireRepository = new RetireRepository();
//		RetireAge retireAgeExpect = new RetireAge();
//		retireAgeExpect.builder().type("xxx").voluntary_cnt(6).age_cnt(0).order_cnt(0).build();
		RetireAge retireAgeExpect= RetireAge.builder().id((long)11).type("xxx").voluntary_cnt(6).age_cnt(0).order_cnt(0).build();
		RetireAge retireAgeActual = new RetireAge();
		retireAgeActual = retireRepository.getByType("xxx");
		
		assertEquals(retireAgeExpect.toString(), retireAgeActual.toString());
	} 

}
