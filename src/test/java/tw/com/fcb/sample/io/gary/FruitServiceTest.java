package tw.com.fcb.sample.io.gary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FruitServiceTest {

	@Test
	void testRunCrud() {
		new FruitService().runCrud();
	}

}
