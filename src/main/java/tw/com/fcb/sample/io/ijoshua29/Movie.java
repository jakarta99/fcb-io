package tw.com.fcb.sample.io.ijoshua29;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
	
	private Long id;
	private String code;
	private String name;
	private int price;
	private MovieRoomEnum room;
	
}