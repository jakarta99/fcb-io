package tw.com.fcb.sample.io.gary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class FruitRepositoryTest {

	@Test
	void testInsert() {
		Fruit fruit = new Fruit();
		fruit.setCode("A");
		fruit.setName("Apple");
		fruit.setPrice(100);
		
		FruitRepository fruitRepository = new FruitRepository();
		try {
			fruitRepository.insert(fruit);
			System.out.println(fruit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
