package tw.com.fcb.sample.io.gary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
			
			LocalDateTime startTime = LocalDateTime.now();
			
			for(int i=0; i<100; i++) {
				fruitRepository.insert(fruit);
			}
			
			LocalDateTime stopTime = LocalDateTime.now();
			
			Long diff = ChronoUnit.MILLIS.between(startTime, stopTime);
			
			System.out.println("total "+diff+" msec");
			
			System.out.println(fruit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
