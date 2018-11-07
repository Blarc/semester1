import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class naloga1_ver3_tab {
	
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
	
	public static String tooString(int[] array) {
		String nov = "";
		for (int i = 0; i < array.length; i++) {
			nov += array[i] + ",";
		}
		return nov;
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
	
	
	public static int[] fun(int[] taxi, int[][] starti, int[][] cilji, int[] ongoing, int[] completed, int left, int m, int index, int n, int atm, int[][][][] states) {
		int[] tab = new int[m*2+1];
		int[] min = new int[m*2+1];
		min[m*2] = Integer.MAX_VALUE;
		
		if (left == 0) {
			return tab;
		}
		
		State curState = new State(taxi, ongoing, completed);
		int key = curState.makeKey();
		if (states[taxi[0]][taxi[1]][key] != null) {
			System.out.println(key);
			return states[taxi[0]][taxi[1]][key];
		}
		
		for (int i = 0; i < m; i++) {	
			if (completed[i] < 1) {
				int oldOngoing = ongoing[i];
				int oldCompleted = completed[i];
				
				if (ongoing[i] > 0) {
					ongoing[i] = 0;
					completed[i] = 1;
					tab = fun(cilji[i], starti, cilji, ongoing, completed, left-1, m, index+1, n, atm-1, states);
					tab[index] = i+1;
					
					if (tab[m*2] < min[m*2]) {
						min = tab;
					}
					
				} 
				else if (atm < n) {
					ongoing[i] = 1;
					tab = fun(starti[i], starti, cilji, ongoing, completed, left, m, index+1, n, atm+1, states);
					tab[m*2] += razdalja(taxi, starti[i]);
					tab[index] = i+1;
					
					if (tab[m*2] < min[m*2]) {
						min = tab;
					}	
				}
				
				ongoing[i] = oldOngoing;
				completed[i] = oldCompleted;
			}			
		}
		
		states[taxi[0]][taxi[1]][key] = min;
		return min;
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
		
		String[][] strankeNizi = new String[m][5];
		int[][][][] states = new int[200][200][200][m*2+1];
		
		/*
		int[] a = {1,0,1,1};
		int[] b = {0,0,0,0};
		int[] pos = {15, 15};
		
		State nov = new State(pos, a, b);
		System.out.println(nov.makeKey());
		*/

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
		
		

		System.out.println(Arrays.toString(fun(taxi, starti, cilji, ongoing, completed, m, m, 0, n, 0, states)));
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
	}

}
