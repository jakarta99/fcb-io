package tw.com.fcb.sample.io.gary;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fruit {
	
	Long id;

	String code;
	String name;
	int price;
	
	
}
