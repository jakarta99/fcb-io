package tw.com.fcb.sample.io.ijoshua29;

public class Movie {
	
	Long id;
	String code;
	String name;
	int price;
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", code=" + code + ", name=" + name + ", price=" + price + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
