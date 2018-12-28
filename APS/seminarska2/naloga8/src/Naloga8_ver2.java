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

public class Naloga8_ver2 {
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
	public static HashMap<Integer, PriorityQueue<Edge>> edges = new HashMap<Integer, PriorityQueue<Edge>>();
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 0) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		int test = 10;
		BufferedReader br = new BufferedReader(new FileReader("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/I_"+test+".txt"));
		Comparator<Edge> comparator = new EdgeComparator();
		
		String readLine = br.readLine();
		String[] line = readLine.split(" ");
		int n = Integer.parseInt(line[0]);
		System.out.printf("n: %d\n", n);
		
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
		
		readLine = br.readLine();
		line = readLine.split(" ");
		maxLen = Integer.parseInt(line[0]);
//		System.out.printf("maxLen: %d\n", maxLen);
		
		br.close();
		
		int[] result = new int[n];
		int count = 0;
		int res = 0;
		
		Random rand = new Random();
		int startV = rand.nextInt(edges.size()) + 1;
		System.out.println("Starting vertex: " + startV);
		
		//System.out.println(edges);
		boolean[] vertices = new boolean[n+1];
		vertices[startV] = true;
		
		LinkedList<PriorityQueue<Edge>> doneVertices = new LinkedList<PriorityQueue<Edge>>();
		doneVertices.add(edges.get(startV));
		
		for (int i = 0; i < edges.size(); i++) {
			int min = Integer.MAX_VALUE;
			PriorityQueue<Edge> minQueue = null;
			for (PriorityQueue<Edge> iter : doneVertices) {
				while (iter.peek() != null && (vertices[iter.peek().end] || iter.peek().len > maxLen)) {
					iter.remove();
				}
				if (iter.peek() != null && iter.peek().len < min) {
					min = iter.peek().len;
					minQueue = iter;
				}
			}
			if (minQueue != null) {
				Edge temp = minQueue.remove();
				vertices[temp.end] = true; 
				doneVertices.add(edges.get(temp.end));
				//System.out.println(temp.id);
				result[count] = temp.id;
				res += temp.len;
			}
			else {
				if (i < edges.size()-1) {
					System.out.println("Fail?");
				}
				break;
			}	
		}
		
		
		
		System.out.println("RES: " + res);
		
		BufferedReader br2 = new BufferedReader(new FileReader("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/O_"+test+".txt"));
		readLine = br2.readLine();
		line = readLine.split(",");
		br2.close();
		
		int actualResult = 0;
		for (int i = 0; i < line.length; i++) {
			actualResult += lenResult[Integer.parseInt(line[i])];
		}
		
		System.out.println("ACTUAL RES: " + actualResult);
		//PrintWriter writer = new PrintWriter(new FileWriter("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/testIzhod.txt"));

		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}