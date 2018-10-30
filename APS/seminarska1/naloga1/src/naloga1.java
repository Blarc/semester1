import java.io.*;
import java.util.Arrays;

public class naloga1 {
	
	public static class Oseba {
		private int[] cilj = new int[2];
		private int[] start = new int[2];
		
		public Oseba(int xStart, int yStart, int xCilj, int yCilj) {
			this.start[0] = xStart;
			this.start[1] = yStart;
			this.cilj[0] = xCilj;
			this.cilj[1] = yCilj;
			
		}
		
		public int[] getCilj() {
			return cilj;
		}
		
		public int[] getStart() {
			return start;
		}
	}
	
	public int abs(int n) {
		if (n < 0) {
			return -n;
		}
		return n;
	}
	public int razdalja(int[] a, int[] b) {
		return abs(a[0] - b[0]) + abs(a[1] - b[1]);
	}
	
	/*public int fun(int stStrank, int razdalja, int xTaxi, int yTaxi, Oseba[] stranke, int atmStrank) {
		if (stStrank == 0) {
			return razdalja;
		}
		
		
		return 0;
	}*/
	
	public static void main(String[] args) throws IOException {
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String[] line;
		int n = Integer.parseInt((br.readLine().split(""))[0]);
		line = br.readLine().split(",");
		int xTaxi = Integer.parseInt(line[0]);
		int yTaxi = Integer.parseInt(line[0]);
		int m = Integer.parseInt((br.readLine().split(""))[0]);
		
		String[][] strankeNizi = new String[m][5];
		Oseba[] stranke = new Oseba[m];
		
		for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			stranke[i] = new Oseba(Integer.parseInt(strankeNizi[i][1]),
								   Integer.parseInt(strankeNizi[i][2]),
								   Integer.parseInt(strankeNizi[i][3]),
								   Integer.parseInt(strankeNizi[i][4]));
		}
		
		
		for (int i = 0; i < m; i++) {
			
		}
		
	}

}
