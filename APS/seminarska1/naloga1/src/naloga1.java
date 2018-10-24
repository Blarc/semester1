import java.io.*;
import java.util.Arrays;

public class naloga1 {

	public static void main(String[] args) throws IOException {
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		int n = br.read() -'0';
		br.read();
		br.read();
		int xStart = br.read() -'0';
		br.read();
		int yStart = br.read() -'0';
		br.read();
		br.read();
		int m = br.read() -'0';
		br.read();
		br.read();
		int[][] stranke = new int[m][5];
		
		System.out.printf("n: %d x: %d y: %d m: %d\n", n, xStart, yStart, m);
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < 5; j++) {
				stranke[i][j] = br.read()-'0';
				br.read();
			}
			br.read();
		}
		
		System.out.print(Arrays.deepToString(stranke));
	}

}
