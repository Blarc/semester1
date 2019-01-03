import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Naloga8 {
	public static class Edge {
		int id;
		int dest;
		int len;
		
		public Edge(int id, int dest, int len) {
			this.id = id;
			this.dest = dest;
			this.len = len;
		}
		
		@Override
		public String toString() {
			return "["+id+","+dest+","+len+"]";
		}
	}
	
	public static class VertexComparator implements Comparator<Vertex> {
		@Override
		public int compare(Vertex a, Vertex b) {
			return a.key - b.key;
		}
	}
	
	public static class Vertex {
		int id;
		int key;
		int edgeId;
		
		public Vertex() {
			
		}
		
		@Override
		public String toString() {
			return "["+id+","+key+"]";
		}
	}
	

	public static int maxLen;
	public static HashMap<Integer, LinkedList<Edge>> edges;
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		int numOfVertices;
		int n;
		
		if(args.length < 0) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
//		int test = 1;
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
	
		
		String readLine = br.readLine();
		String[] line = readLine.split(" ");
		n = Integer.parseInt(line[0]);
		//System.out.printf("n: %d\n", n);
		
		edges = new HashMap<Integer, LinkedList<Edge>>();
		
		int[] lenResult = new int[n+1];

		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			int id = Integer.parseInt(line[0]);
			int start = Integer.parseInt(line[1]);
			int end = Integer.parseInt(line[2]);
			int len = Integer.parseInt(line[3]);
			
			if (edges.get(start) == null) {
				edges.put(start, new LinkedList<Edge>());
			}
			edges.get(start).add(new Edge(id, end, len));
			
			if (edges.get(end) == null) {
				edges.put(end, new LinkedList<Edge>());
			}
			edges.get(end).add(new Edge(id, start, len));
			
			lenResult[id] = len;
		}
		
		numOfVertices = edges.size();
		
		readLine = br.readLine();
		line = readLine.split(" ");
		maxLen = Integer.parseInt(line[0]);
//		System.out.printf("maxLen: %d\n", maxLen);
		br.close();
		
//		ALGORITHM
		
//		Picks a starting vertex
		int startV = 1;
		
//		initializations
		int res = 0;
		int count = 0;
		int[] result = new int[n+1];
		boolean[] mstSet = new boolean[numOfVertices+1];
		Vertex[] vertices = new Vertex[numOfVertices+1];
		
		
		for (int i = 1; i < numOfVertices+1; i++) {
			vertices[i] = new Vertex();
		}
		
		for (int i = 1; i < numOfVertices+1; i++) {
			mstSet[i] = false;
			vertices[i].key = Integer.MAX_VALUE;
			vertices[i].id = i;
		}
		
		mstSet[startV] = true;
		vertices[startV].key = 0;
		
		PriorityQueue<Vertex> minHeap = new PriorityQueue<Vertex>(numOfVertices, new VertexComparator());
		for (int i = 1; i < numOfVertices+1; i++) {
			minHeap.add(vertices[i]);
		}
		
//		code
		Vertex minV;
		while (!minHeap.isEmpty()) {
			//poll the minimum key
			minV = minHeap.poll();
			mstSet[minV.id] = true;
			res += minV.key;
			count++;
			result[minV.edgeId] = minV.edgeId;
			
			//add new edges
			for (Edge iter : edges.get(minV.id)) {
				if (mstSet[iter.dest] == false) {
					if (vertices[iter.dest].key > iter.len) {
						minHeap.remove(vertices[iter.dest]);
						vertices[iter.dest].key = iter.len;
						vertices[iter.dest].edgeId = iter.id;
						minHeap.add(vertices[iter.dest]);
					}
				}
			}
		}
		

//		prints
//		System.out.println("Starting vertex: " + startV);
		
		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		boolean bol = false;
		for (int i = 1; i < result.length; i++) {
			if (result[i] != 0) {
				if (!bol) {
					//System.out.print(result[i]);
					writer.print(result[i]);
					bol = true;
				}
				else {
					//System.out.print("," + result[i]);
					writer.print("," + result[i]);
				}	
			}
		}
		//System.out.println();
		writer.println();
		writer.close();
		

		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}