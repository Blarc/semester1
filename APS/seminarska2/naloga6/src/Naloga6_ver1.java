import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import java.util.HashMap;
import java.util.Iterator;

public class Naloga6_ver1 {
	public static class Path {
		int start;
		int end;
		int heigth;
		
		public Path (int start, int end, int heigth) {
			this.start = start;
			this.end = end;
			this.heigth = heigth;
		}
		
		@Override
		public String toString() {
			return "["+start+","+end+","+heigth+"]";
		}
	}
	
	public static boolean[] help;
	public static int count = 0;
	public static void fri(int atmPos, int end, int maxH, HashMap<Integer,LinkedList<Path>> paths) {
		
		if (atmPos == end) {
			count++;
			return;
		}
		
		Iterator<Path> iter = paths.get(atmPos).iterator();
		while (iter.hasNext()) {
			Path atm = iter.next();
			if (!help[atmPos]) {
				if (atm.heigth >= maxH || atm.heigth == -1) {
					help[atmPos] = true;
					fri(atm.end, end, maxH, paths);
					help[atmPos] = false;
				}
			}
		}
		
		return;
	}
	
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader("/home/jakob/Documents/semster1/APS/seminarska2/naloga6_testi/I_10.txt"));
		//BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		int n;
		
		String readLine;
		String[] line;
		
		readLine = br.readLine();
		line = readLine.split(" ");
		n = Integer.parseInt(line[0]);
		System.out.println("n: " + n);
		help = new boolean[n*2];
		
		HashMap<Integer,LinkedList<Path>> paths = new HashMap<Integer, LinkedList<Path>>();
		
		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			int heigth = Integer.parseInt(line[2]);
			
			if (paths.get(start) == null) {
				paths.put(start, new LinkedList<Path>());
			}
			
			paths.get(start).add(new Path(start, end, heigth));
			
			if (paths.get(end) == null) {
				paths.put(end, new LinkedList<Path>());
			}
			
			paths.get(end).add(new Path(end, start, heigth));
			
		}
		
		readLine = br.readLine();
		line = readLine.split(",");
		int start = Integer.parseInt(line[0]);
		int end = Integer.parseInt(line[1]);
		System.out.printf("Start: %d\nEnd: %d\n", start, end);
		
		readLine = br.readLine();
		line = readLine.split("");
		int maxH = Integer.parseInt(line[0]);
		System.out.printf("Max heigth: %d\n", maxH);
		
		br.close();
		
		System.out.println(paths.toString());
		
		fri(start, end, maxH, paths);
		
		PrintWriter writer = new PrintWriter(new FileWriter("/home/jakob/Documents/semster1/APS/seminarska2/naloga6_testi/testIzhod.txt"));
		
		System.out.println(count);
		writer.println(count);
		
		writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}
