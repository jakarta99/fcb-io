package tw.com.fcb.sample.io.kai;

public class FileSecuritiesApp {
	public static void main(String[] args) {
		FileSecuritiesService fileSecuritiesService = new FileSecuritiesService();
		fileSecuritiesService.readFile();
		fileSecuritiesService.writeFile();
	}
}
