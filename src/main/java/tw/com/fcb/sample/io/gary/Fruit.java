package tw.com.fcb.sample.io.gary;

public class Fruit {

	String code;
	String name;
	int price;
	
	
	
	@Override
	public String toString() {
		return "Fruit [code=" + code + ", name=" + name + ", price=" + price + "]";
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
