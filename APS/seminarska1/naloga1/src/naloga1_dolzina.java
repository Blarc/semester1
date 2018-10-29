import java.io.*;
import java.util.Arrays;

public class naloga1_dolzina {
	
	public static int abs(int n) {
		if (n < 0) {
			return -n;
		}
		return n;
	}
	public static int razdalja(int[] a, int[] b) {
		return abs(a[0] - b[0]) + abs(a[1] - b[1]);
	}
	
	
	public static int fun(int[] taxi, int[][] starti, int[][] cilji, int left, int m, int min) {
		int[] temp1;
		int[] temp2;
		
		if (left == 0) {
			//System.out.println(taxi[0] + " " + taxi[1]);
			//System.out.println("Prvi: " + razdalja);
			//System.out.println(razdalja + e);
			return 0;
		}
		
		//System.out.println(taxi[0] + " " + taxi[1]);
		int e = 0;
		min = 100;
		int[] tab = new int[m];
		for (int i = 0; i < m; i++) {
			
			if (starti[i] != null) {
				temp1 = starti[i];
				starti[i] = null;
				e = fun(temp1, starti, cilji, left, m, min) + razdalja(taxi, temp1);
				tab[i] = e;
				//System.out.println("e: "+ e);
				//System.out.println("r: " + razdalja);
				//System.out.println("s: " + (razdalja + e));
				starti[i] = temp1;
				
				//System.out.println("Ali je min: " + (razdalja + razdalja(taxi, temp1)));
				//System.out.println(e + "<" + min);
				if (e < min) {
					min = e;
					//System.out.println("min: " + min);
				}
				
				
				
				
			} 
			else if (cilji[i] != null) {
				temp2 = cilji[i];
				cilji[i] = null;
				e = fun(temp2, starti, cilji, left-1, m, min) + razdalja(taxi, temp2);
				tab[i] = e;
				//System.out.println("e: "+ e);
				//System.out.println("r: " + razdalja);
				//System.out.println("s: " + (razdalja + e));
				cilji[i] = temp2;
				
				//System.out.println(e + "<" + min);
				if (e < min) {
					min = e;
					//System.out.println("min: " + min);
				}
				
				//System.out.println(razdalja);
				//System.out.println(taxi[0] + " " + taxi[1]);
				
			}
			
			//System.out.println(min);
			//System.out.println(e);
			//System.out.println(e + " < " + min);
			

				
				
		}
		//System.out.println(taxi[0] + " " + taxi[1]);
		//System.out.println("Zadnji: " + razdalja);
		//System.out.println(razdalja + e);

		System.out.println(Arrays.toString(tab));
		return min;
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
		

		for (int i = 0; i < m; i++) {
			strankeNizi[i] = br.readLine().split(",");
			starti[i][0] = Integer.parseInt(strankeNizi[i][1]);
			starti[i][1] = Integer.parseInt(strankeNizi[i][2]);
			cilji[i][0] = Integer.parseInt(strankeNizi[i][3]);
			cilji[i][1] = Integer.parseInt(strankeNizi[i][4]);
		}
		
		int[] pot = new int[m*2];
		
		System.out.println(fun(taxi, starti, cilji, m, m, 100));
		
		br.close();
	}

}
