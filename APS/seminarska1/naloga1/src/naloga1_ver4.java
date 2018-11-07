import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class naloga1_ver4 {
	
	
	public static class State {
		final int[] pos;
		final int[] completed;
		final int[] ongoing;
		
		public State (final int[] pos, final int[] ongoing, final int[] completed) {
			this.pos = pos;
			this.completed = completed;
			this.ongoing = ongoing;
		}
		
		public int makeKey() {
			int len = ongoing.length;
			int h = (1 << 2*len); //m
			for (int i = 0; i < len; i++) {
				if (i < len) {
					h = h | (completed[i] << (len - i -1));
				}
				else {
					h = h | (ongoing[i-len] << (len - i -1));
				}
			}
			return h;
		}
	}
	
	public static boolean compareArrays(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static int abs(int n) {
		if (n < 0) {
			return -n;
		}
		return n;
	}
	public static int razdalja(int[] a, int[] b) {
		return abs(a[0] - b[0]) + abs(a[1] - b[1]);
	}
	
	
	public static int fun(int[] taxi, int[][] starti, int[][] cilji, int[] ongoing, int[] completed, int left, int m, int index, int n, int atm, int[][][] states) {
		int[] tab = new int[m*2+1];
		int minVal = Integer.MAX_VALUE;
		
		if (left == 0) {
			return 0;
		}
		
		
		//SHOULD BE HASHMAP!!!
		State curState = new State(taxi, ongoing, completed);
		int key = curState.makeKey();
		if (states[taxi[0]][taxi[1]][key] != 0) {
			return states[taxi[0]][taxi[1]][key];
		}
		
	
		
		for (int i = 0; i < m; i++) {
			if (completed[i] < 1) {
				int oldOngoing = ongoing[i];
				int oldCompleted = completed[i];
				
				if (ongoing[i] > 0) {
					ongoing[i] = 0;
					completed[i] = 1;
					int val = razdalja(taxi, cilji[i]) + fun(cilji[i], starti, cilji, ongoing, completed, left-1, m, index+1, n, atm-1, states);
					tab[index] = i+1;
					
					if (val < minVal) {
						minVal = val;
					}
					
				} 
				else if (atm < n) {
					ongoing[i] = 1;
					int val = razdalja(taxi, starti[i]) + fun(starti[i], starti, cilji, ongoing, completed, left, m, index+1, n, atm+1, states);
					tab[index] = i+1;
					
					if (val < minVal) {
						minVal = val;
					}	
				}
				
				ongoing[i] = oldOngoing;
				completed[i] = oldCompleted;
			}			
		}
		
		
		states[taxi[0]][taxi[1]][key] = minVal;
		return minVal;
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
	
		String[] line;
		int n = Integer.parseInt((br.readLine().split(" "))[0]);
		line = br.readLine().split(",");
		int[] taxi = {Integer.parseInt(line[0]), Integer.parseInt(line[1])} ;
		line = br.readLine().split(",");
		int m = Integer.parseInt(line[0]);
		
		int[][] starti = new int[m][2];
		int[][] cilji = new int[m][2];
		int[] completed = new int[m];
		int[] ongoing = new int[m];
		
		int[][][] states = new int[200][200][20000];
		
		/*int[] a = {1,0,1};
		int[] b = {0,0,1};
		int[] pos = {17, 10};
		
		State nov = new State(pos, a, b);
		String key = nov.makeKey();
		states.put(key, 3);
		if (states.containsKey(key)) {
			System.out.println(states.get(key));
		}*/
		
		String[][] strankeNizi = new String[m][5];	

		for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			starti[i][0] = Integer.parseInt(strankeNizi[i][1]);
			starti[i][1] = Integer.parseInt(strankeNizi[i][2]);
			cilji[i][0] = Integer.parseInt(strankeNizi[i][3]);
			cilji[i][1] = Integer.parseInt(strankeNizi[i][4]);
		}
		
		long startTime = System.currentTimeMillis();
		System.out.println("n: " + n);
		System.out.println("taxi: " + Arrays.toString(taxi));
		System.out.println("m: " + m);
		System.out.println("Stranke: " + Arrays.deepToString(starti));
		
		System.out.println(fun(taxi, starti, cilji, ongoing, completed, m, m, 0, n, 0, states));
		
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
	}

}
