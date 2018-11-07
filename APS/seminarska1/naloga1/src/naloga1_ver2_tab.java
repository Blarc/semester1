import java.io.*;
import java.util.Arrays;

public class naloga1_ver2_tab {
	
	
	public static class State {
		int[] pos;
		int[] completed;
		int[] ongoing;
		int[] res;
		
		public State (int[] pos, int[] ongoing, int[] completed, int[] tab) {
			this.pos = pos;
			this.completed = completed;
			this.ongoing = ongoing;
			this.res = tab;
		}
		
		public boolean compareStates(State a) {
			if (a.pos == this.pos) {
				if (compareArrays(a.ongoing, this.ongoing)){
					if (compareArrays(a.completed, this.completed)) {
						return true;
					}
				}
			}
			return false;
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
	
	
	public static int count = 0;
	public static int[] fun(int[] taxi, int[][] starti, int[][] cilji, int[] ongoing, int[] completed, int left, int m, int index, int n, int atm, State[] states) {
		int[] tab = new int[m*2+1];
		int[] min = new int[m*2+1];
		min[m*2] = Integer.MAX_VALUE;
		
		if (left == 0) {
			return tab;
		}
		
		State curState = new State(taxi, ongoing, completed, tab);
		
		//SHOULD BE HASHMAP
		for (int i = 0; i < states.length && states[i] != null; i++) {
			if (curState.compareStates(states[i])) {
				return curState.res;
			}
		}
		
		for (int i = 0; i < m; i++) {
			if (completed[i] < 1) {
				int oldOngoing = ongoing[i];
				int oldCompleted = completed[i];
				
				if (ongoing[i] > 0) {
					ongoing[i] = 0;
					completed[i] = 1;
					tab = fun(cilji[i], starti, cilji, ongoing, completed, left-1, m, index+1, n, atm-1, states);
					tab[m*2] += razdalja(taxi, cilji[i]);
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
		
		curState.res = tab;
		states[count] = curState;
		count++;
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
		State[] states = new State[1000];
		
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
		
		System.out.println(Arrays.toString(fun(taxi, starti, cilji, ongoing, completed, m, m, 0, n, 0, states)));
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
	}

}
