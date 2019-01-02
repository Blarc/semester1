import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class Naloga8_ver3 {
	public static class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge x, Edge y) {
			return x.len - y.len;
		}
	}
	
	public static class Edge {
		int id;
		int start;
		int end;
		int len;
		
		public Edge(int id, int start, int end, int len) {
			this.id = id;
			this.start = start;
			this.end = end;
			this.len = len;
		}
		
		@Override
		public String toString() {
			return "["+id+","+start+","+end+","+len+"]";
		}
	}
	

	public static int maxLen;
	public static HashMap<Integer, PriorityQueue<Edge>> edges;
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		int numOfVertices;
		int n;
		
		if(args.length < 0) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		int test = 10;
		BufferedReader br = new BufferedReader(new FileReader("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/I_"+test+".txt"));
		Comparator<Edge> comparator = new EdgeComparator();
	
		
		String readLine = br.readLine();
		String[] line = readLine.split(" ");
		n = Integer.parseInt(line[0]);
		System.out.printf("n: %d\n", n);
		
		edges = new HashMap<Integer, PriorityQueue<Edge>>(n/2);
		
		int[] lenResult = new int[n+1];

		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			int id = Integer.parseInt(line[0]);
			int start = Integer.parseInt(line[1]);
			int end = Integer.parseInt(line[2]);
			int len = Integer.parseInt(line[3]);
			
			if (edges.get(start) == null) {
				edges.put(start, new PriorityQueue<Edge>(5, comparator));
			}
			
			edges.get(start).add(new Edge(id, start, end, len));
			
			if (edges.get(end) == null) {
				edges.put(end, new PriorityQueue<Edge>(5, comparator));
			}
			
			edges.get(end).add(new Edge(id, end, start, len));
			
			lenResult[id] = len;
		}
		
		numOfVertices = edges.size();
		
		readLine = br.readLine();
		line = readLine.split(" ");
		maxLen = Integer.parseInt(line[0]);
//		System.out.printf("maxLen: %d\n", maxLen);
		br.close();
		
//		ALGORITHM
		
//		Picks a random starting vertex
		Random rand = new Random();
		int startV = rand.nextInt(numOfVertices + 1);
		
//		initializations
		int res = 0;
		boolean[] reached = new boolean[numOfVertices+1];
		int[] keys = new int[numOfVertices+1];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = Integer.MAX_VALUE;
		}
		
		keys[1] = 0;
		
//		code
		for (int i = 0; i < numOfVertices; i++) {
			
			//find the minimum key
			int min = Integer.MAX_VALUE;
			int minKey = -1;
			for (int j = 1; j < keys.length; j++) {
				if (keys[j] < min) {
					if (!reached[j]) {
						min = keys[j];
						minKey = j;
					}
				}
			}
			
			reached[minKey] = true;
			res += min;
//			System.out.println(edges.get(minKey));
			for (Edge iter : edges.get(minKey)) {
				if (keys[iter.end] > iter.len) {
					keys[iter.end] = iter.len;
				}
			}
		}
		

//		prints
		System.out.println("Starting vertex: " + startV);
		//System.out.println(edges);
		System.out.println("RESULT: " + res);
		//System.out.println(Arrays.toString(keys));
		
		
//		ACTUAL RESULT CALCULATION
		BufferedReader br2 = new BufferedReader(new FileReader("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/O_"+test+".txt"));
		readLine = br2.readLine();
		line = readLine.split(",");
		br2.close();
		
		int actualResult = 0;
		for (int i = 0; i < line.length; i++) {
			actualResult += lenResult[Integer.parseInt(line[i])];
		}
		System.out.println("ACTUAL RESULT: " + actualResult);
		
		
		//PrintWriter writer = new PrintWriter(new FileWriter("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/testIzhod.txt"));

		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}