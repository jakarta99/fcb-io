package tw.com.fcb.sample.io.gary;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

class FruitRepositoryTest {

	@Test
	void testInsert() {
		Fruit fruit = Fruit.builder().code("A").name("Apple").price(100).build();
		
		
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
