package tw.com.fcb.sample.io.gary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class FruitRepositoryTest {

	@Test
	void testFindAll() {
		List<Fruit> fruits;
		try {
			fruits = new FruitRepository().findAll();
			assertEquals(4, fruits.size());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
