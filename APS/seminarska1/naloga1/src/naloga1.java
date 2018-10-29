import java.io.*;
import java.util.Arrays;

public class naloga1 {
	
	public static int abs(int n) {
		if (n < 0) {
			return -n;
		}
		return n;
	}
	public static int razdalja(int[] a, int[] b) {
		return abs(a[0] - b[0]) + abs(a[1] - b[1]);
	}

	public static int fun() {
		
		return 0;
	}
	
	public static int[] swap(int[] tab, int i, int j) 
    { 
		int temp = tab[i];
		tab[i] = tab[j];
		tab[j] = temp;
		return tab;
    } 
	
	public static void permute(int[]tab, int l, int r) 
    { 
        if (l == r) 
            System.out.println(Arrays.toString(tab)); 
        else
        { 
            for (int i = l; i <= r; i++) 
            { 
                tab = swap(tab,l,i); 
                permute(tab, l+1, r); 
                tab = swap(tab,l,i); 
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
		int n = Integer.parseInt((br.readLine().split(""))[0]);
		line = br.readLine().split(",");
		int[] taxi = {Integer.parseInt(line[0]), Integer.parseInt(line[1])} ;
		line = br.readLine().split(",");
		int m = Integer.parseInt(line[0]);
		
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
		
		br.close();
		
		//System.out.println("n: " + n);
		//System.out.println("m: "+ m);
		
		int tab[] = {1,2,3,4,5,6,7,8,9,10,11};
		permute(tab, 0, 10);
		
	}

}
