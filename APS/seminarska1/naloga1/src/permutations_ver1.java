import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class permutations_ver1 {
	public static int globalMin = Integer.MAX_VALUE;
	
	public static int abs(int n) {
		if (n < 0) {
			return -n;
		}
		return n;
	}
	public static int razdalja(int[] a, int[] b) {
		return abs(a[0] - b[0]) + abs(a[1] - b[1]);
	}
	
	public static int[][] swap(int[][] array, int i, int j) {
		int[] temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		return array;
	}
	
	
	public static void permute(int[][] array, int[] taxi, int[] ongoing, int[] completed, int l, int r, int m, int n) {
		if (l == r) {
			int atm = 0;
			int distance = 0;
			int[] start = taxi;
			for (int i = 0; i < array.length; i++) {
				if (array[i][3] == 0) {
					if (ongoing[array[i][2]] == 1) {
						atm--;
						ongoing[array[i][2]] = 0;
						completed[array[i][2]] = 1;
						distance += razdalja(start, array[i]);
						if (distance >= globalMin) {
							break;
						}
					} else {
						break;
					}
				}
				else if (atm < n){
					atm++;
					ongoing[array[i][2]] = 1;
					distance += razdalja(start, array[i]);
					if (distance >= globalMin) {
						break;
					}
				}
				start = array[i];
				
				
				if (i == array.length - 1) {
					if (distance < globalMin) {
						globalMin = distance;
					}
				}
			}
			
		} else {
			for (int i = l; i < array.length; i++) {
				array = swap(array, l, i);
				permute(array, taxi, ongoing, completed, l+1, r, m, n);
				array = swap(array, l, i);
			}
		}
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
		
		int[][] tocke = new int[m*2][4];
		int[] completed = new int[m];
		int[] ongoing = new int[m];
		
		String[][] strankeNizi = new String[m][5];	

		for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			tocke[i][3] = 0;
			tocke[i][2] = i;
			tocke[i][0] = Integer.parseInt(strankeNizi[i][1]);
			tocke[i][1] = Integer.parseInt(strankeNizi[i][2]);
			tocke[i][3] = 1;
			tocke[i+m][2] = i;
			tocke[i+m][0] = Integer.parseInt(strankeNizi[i][3]);
			tocke[i+m][1] = Integer.parseInt(strankeNizi[i][4]);
		}
		
		long startTime = System.currentTimeMillis();
			
		permute(tocke, taxi, ongoing, completed, 0, tocke.length - 1, m, n);
		System.out.println(globalMin);
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
	}

}
