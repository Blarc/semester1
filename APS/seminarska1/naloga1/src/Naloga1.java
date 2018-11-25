import java.io.*;

public class Naloga1 {
	
	public static int abs(int n) {
		if (n < 0) {
			return -n;
		}
		return n;
	}
	public static int razdalja(int[] a, int[] b) {
		return abs(a[0] - b[0]) + abs(a[1] - b[1]);
	}
	
	
	public static int n;
	public static int m;
	public static int[][] starti;
	public static int[][] cilji;
	public static int[] best;
	public static int min = Integer.MAX_VALUE;
	
	public static void fun(int[] taxi, int[] ongoing, int[] completed, int left, int index, int atm, int raz, int[] tab) {
		if (left == 0) {
			if (raz < min) {
				min = raz;
				for (int i = 0; i < tab.length; i++) {
					best[i] = tab[i];
				}
			}
			return;
		}
		
		for (int i = 0; i < m; i++) {
			if (completed[i] < 1) {
				int oldOngoing = ongoing[i];
				int oldCompleted = completed[i];
				
				if (ongoing[i] > 0) {
					ongoing[i] = 0;
					completed[i] = 1;

					if (raz + razdalja(taxi, cilji[i]) < min) {
						tab[index] = cilji[i][2];
						fun(cilji[i], ongoing, completed, left-1, index+1, atm-1, raz + razdalja(taxi, cilji[i]), tab);
						tab[index] = 0;
					}

					
				}
				
				else if (atm < n) {
					ongoing[i] = 1;

					if (raz + razdalja(taxi, starti[i]) < min) {
						tab[index] = starti[i][2];
						fun(starti[i], ongoing, completed, left, index+1, atm+1, raz + razdalja(taxi, starti[i]), tab);
						tab[index] = 0;
					}

				}
				
				ongoing[i] = oldOngoing;
				completed[i] = oldCompleted;
			}
		}
		
		return;
			
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis();
		if(args.length < 2) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
	
		String[] line;
		n = Integer.parseInt((br.readLine().split(" "))[0]);
		line = br.readLine().split(",");
		int[] taxi = {Integer.parseInt(line[0]), Integer.parseInt(line[1])} ;
		line = br.readLine().split(",");
		m = Integer.parseInt(line[0]);
		
		starti = new int[m][3];
		cilji = new int[m][3];
		int[] completed = new int[m];
		int[] ongoing = new int[m];
		int[] tab = new int[m*2];
		best = new int[m*2];
		
		
		String[][] strankeNizi = new String[m][5];	

		for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			starti[i][0] = Integer.parseInt(strankeNizi[i][1]);
			starti[i][1] = Integer.parseInt(strankeNizi[i][2]);
			starti[i][2] = Integer.parseInt(strankeNizi[i][0]);
			cilji[i][0] = Integer.parseInt(strankeNizi[i][3]);
			cilji[i][1] = Integer.parseInt(strankeNizi[i][4]);
			cilji[i][2] = Integer.parseInt(strankeNizi[i][0]);
		}
		
		
		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		
		fun(taxi, ongoing, completed, m, 0, 0, 0, tab);
		
		for (int i = 0; i < m*2; i++) {
			if (i == m*2 - 1) {
				writer.write(best[i] + "");
			} else {
				writer.write(best[i] + ",");
			}
		}
		
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
		writer.close();
	}

}
