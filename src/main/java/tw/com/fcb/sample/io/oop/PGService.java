package tw.com.fcb.sample.io.oop;

public abstract class PGService {
	
	
	
	public void doIO() {
		read();
		
		System.out.println("READ RATE");
		
		save();
		
	}
	
	abstract void read();
	
	abstract void save(); 

}
