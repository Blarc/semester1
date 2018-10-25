import java.io.*;
import java.util.Arrays;

public class naloga1 {
	
	/*public static class Oseba {
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
	}*/
	
	public static int abs(int n) {
		if (n < 0) {
			return -n;
		}
		return n;
	}
	public static int razdalja(int[] a, int[] b) {
		return abs(a[0] - b[0]) + abs(a[1] - b[1]);
	}
	
	public static int fun(int[] taxi, int[][] starti, int[][] cilji, int left, int razdalja, int m) {
		if (left == 0) {
			return razdalja;
		}
		int[] oTaxi = taxi;
		int[][] oStarti = starti;
		int oLeft = left;
		int oRazdalja = razdalja;
		
		
		for (int i = 0; i < m; i++) {
			if (starti[i] != null) {
				razdalja += razdalja(taxi, starti[i]);
				taxi = starti[i];
				
				if (cilji[i] == starti[i]) {
					left--;
					starti[i] = null;
					System.out.println(fun(taxi, starti, cilji, left, razdalja, m));
					
				} else {
					starti[i] = cilji[i];
					System.out.println(fun(taxi, starti, cilji, left, razdalja, m));
				}
				System.out.println(fun(oTaxi, oStarti, cilji, oLeft, oRazdalja, m));
				
			}
			
		}
		return 0;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String[] line;
		int n = Integer.parseInt((br.readLine().split(""))[0]);
		line = br.readLine().split(",");
		int[] taxi = {Integer.parseInt(line[0]), Integer.parseInt(line[1])} ;
		int m = Integer.parseInt((br.readLine().split(""))[0]);
		
		int[][] starti = new int[m][2];
		int[][] cilji = new int[m][2];
		
		String[][] strankeNizi = new String[m][5];
		
		//Oseba[] stranke = new Oseba[m];
		
		/*for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			stranke[i] = new Oseba(Integer.parseInt(strankeNizi[i][1]),
								   Integer.parseInt(strankeNizi[i][2]),
								   Integer.parseInt(strankeNizi[i][3]),
								   Integer.parseInt(strankeNizi[i][4]));
		}*/
		

		for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			starti[i][0] = Integer.parseInt(strankeNizi[i][1]);
			starti[i][1] = Integer.parseInt(strankeNizi[i][2]);
			cilji[i][0] = Integer.parseInt(strankeNizi[i][3]);
			cilji[i][1] = Integer.parseInt(strankeNizi[i][4]);
		}
		
		fun(taxi, starti, cilji, m, 0, m);
		
		
		br.close();
	}

}
