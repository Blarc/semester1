import java.io.*;

public class naloga1_ver4 {
	public static final int PRIME = 201;
	public static final int PRIMEC = 47;
<<<<<<< HEAD

	
=======
>>>>>>> 49f701b527e14c2d539f8536369622ef243732fe
	
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
				int[] array = new int[ongoing.length * 2];
				for (int i = 0; i < ongoing.length; i++) {
					array[i] = ongoing[i];
					array[i+ongoing.length] = completed[i];
				}
				//System.out.println(Arrays.toString(array));
			
			    int h = (1 << array.length);
			    for (int i = 0; i < array.length; i++) {
			        h = h | (array[i] << (array.length - i - 1));
			    }
			    return h%PRIME;
		}
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
	
	public static int kC(int a) {
		return a%47;
	}
	
	
	public static int fun(int[] taxi, int[][] starti, int[][] cilji, int[] ongoing, int[] completed, int left, int index, int atm, int[][][][] states) {
		int[] tab = new int[m*2+1];
		int minVal = Integer.MAX_VALUE;
		
		if (left == 0) {
			return 0;
		}
		
		
		//SHOULD BE HASHTABLE!!!
		State curState = new State(taxi, ongoing, completed);
		int key = curState.makeKey();
		
		if (states[taxi[0]%47][taxi[1]%47][key][0] != key) {
			for (int i = 0; i < PRIME; i++) {
				if (states[taxi[0]%PRIMEC][taxi[1]%PRIMEC][(key+i)%PRIME][0] == key) {
					return states[taxi[0]%PRIMEC][taxi[1]%PRIMEC][(key+i)%PRIME][1];
				}
			}
		}
		
		for (int i = 0; i < m; i++) {
			if (completed[i] < 1) {
				int oldOngoing = ongoing[i];
				int oldCompleted = completed[i];
				
				if (ongoing[i] > 0) {
					ongoing[i] = 0;
					completed[i] = 1;
					int val = razdalja(taxi, cilji[i]) + fun(cilji[i], starti, cilji, ongoing, completed, left-1, index+1, atm-1, states);
					tab[index] = i+1;
					
					if (val < minVal) {
						minVal = val;
					}
					
				} 
				else if (atm < n) {
					ongoing[i] = 1;
					int val = razdalja(taxi, starti[i]) + fun(starti[i], starti, cilji, ongoing, completed, left, index+1, atm+1, states);
					tab[index] = i+1;
					
					if (val < minVal) {
						minVal = val;
					}	
				}
				
				ongoing[i] = oldOngoing;
				completed[i] = oldCompleted;
			}			
		}		
		
		for (int i = 0; i < PRIME; i++) {
			if (states[taxi[0]%PRIMEC][taxi[1]%PRIMEC][(key+i)%PRIME][0] == 0) {
				states[taxi[0]%PRIMEC][taxi[1]%PRIMEC][(key+i)%PRIME][0] = key;
				states[taxi[0]%PRIMEC][taxi[1]%PRIMEC][(key+i)%PRIME][1] = minVal;
				break;
			}
		}	
		return minVal;
	}
	
	
	public static int n;
	public static int m;
	
	public static void main(String[] args) throws IOException {
		
		if(args.length < 1) {
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
		
		int[][] starti = new int[m][2];
		int[][] cilji = new int[m][2];
		int[] completed = new int[m];
		int[] ongoing = new int[m];
		
		int[][][][] states = new int[47][47][PRIME][2];
		
		/*int[] a = {1,0,1};
		int[] b = {0,0,1};
		int[] pos = {17, 10};
		
		State nov = new State(pos, a, b);
		long key = nov.makeKey();
		System.out.println("Key: " + key);*/
		
		String[][] strankeNizi = new String[m][5];	

		for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			starti[i][0] = Integer.parseInt(strankeNizi[i][1]);
			starti[i][1] = Integer.parseInt(strankeNizi[i][2]);
			cilji[i][0] = Integer.parseInt(strankeNizi[i][3]);
			cilji[i][1] = Integer.parseInt(strankeNizi[i][4]);
		}
		
		long startTime = System.currentTimeMillis();
		//System.out.println("n: " + n);
		//System.out.println("taxi: " + Arrays.toString(taxi));
		//System.out.println("m: " + m);
		//System.out.println("Stranke: " + Arrays.deepToString(starti));
		
		
		System.out.println("The Shortest Path: " + fun(taxi, starti, cilji, ongoing, completed, m, 0, 0, states));
		
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
	}

}
