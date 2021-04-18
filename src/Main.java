import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		double n = 67.7345f;
		
		String s =String.valueOf(n);
		String x=s.format("%.2f", n);
		
	System.out.print(x);


	}

}
