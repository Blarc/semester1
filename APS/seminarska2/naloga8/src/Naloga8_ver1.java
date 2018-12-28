import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Naloga8_ver1 {
	public static class Edge {
		int id;
		int start;
		int end;
		int len;
		boolean free;
		
		public Edge(int id, int start, int end, int len) {
			this.id = id;
			this.start = start;
			this.end = end;
			this.len = len;
			this.free = true;
		}
		
		public int getEnd(int town) {
			if (town == end) {
				return start;
			}
			return end;
		}
		
		@Override
		public String toString() {
			return "["+id+","+start+","+end+","+len+"]";
		}
	}
	
	public static Edge fri(int town) {
		
		LinkedList<Edge> paths = edges.get(town);
		if (paths == null) {
			return null;
		}
		
		Edge minEdge = null;
		int min = Integer.MAX_VALUE;
		for (Edge iter : paths) {
			if(iter.free) { 
				if (!free[iter.id]) {
					if (!towns[iter.getEnd(town)]) {
						if (iter.len <= maxLen) {
							if (iter.len < min) {
								min = iter.len;
								minEdge = iter;
							}
						}
					}
				}
				else {
					iter.free = false;
					Edge temp = fri(iter.getEnd(town));
					iter.free = true;
					if (temp != null && temp.len < min) {
						min = temp.len;
						minEdge = temp;
					}
				}
			}
		}
		return minEdge;
	}

	public static int maxLen;
	public static boolean[] free;
	public static boolean[] towns;
	public static HashMap<Integer, LinkedList<Edge>> edges = new HashMap<Integer, LinkedList<Edge>>();
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		int test = 7;
		BufferedReader br = new BufferedReader(new FileReader("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/I_"+test+".txt"));
		
		String readLine = br.readLine();
		String[] line = readLine.split(" ");
		int n = Integer.parseInt(line[0]);
		System.out.printf("n: %d\n", n);
		
		free = new boolean[n+1];
		towns = new boolean[n+1];
		int[] lenResult = new int[n+1];
		Edge[] edgesById = new Edge[n+1];
		
		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			int id = Integer.parseInt(line[0]);
			int start = Integer.parseInt(line[1]);
			int end = Integer.parseInt(line[2]);
			int len = Integer.parseInt(line[3]);
			
			lenResult[id] = len;
			
			Edge edge = new Edge(id, start, end, len);
			if (edges.get(start) == null) {
				edges.put(start, new LinkedList<Edge>());
			}
			
			edges.get(start).add(edge);
			
			if (edges.get(end) == null) {
				edges.put(end, new LinkedList<Edge>());
			}
			
			edges.get(end).add(edge);
			edgesById[id] = edge;
		}
		
		readLine = br.readLine();
		line = readLine.split(" ");
		maxLen = Integer.parseInt(line[0]);
		System.out.printf("maxLen: %d\n", maxLen);
		
		br.close();
		
//		System.out.println(fri(1).id);		
//		System.out.println(fri(2).id);		
//		System.out.println(fri(3).id);		
//		System.out.println(fri(4).id);
//		
//		System.out.println("--------");
//		
//		System.out.println(fri(1).id);
//		free[2] = true;
//		System.out.println(fri(2).id);
//		free[1] = true;
//		System.out.println(fri(3).id);
//		free[5] = true;
//		System.out.println(fri(4).id);
		
		int res = 0;
		int[] result = new int[n];
		/*
		for (int i = 1; i < edges.size(); i++) {
			Edge temp = fri(i);
			if (temp == null) {
				break;
			}
			free[temp.id] = true;
			result[i-1] = temp.id;
			res += temp.len;
		}
		*/
		
		int x = 1;
		towns[x] = true;
		for (int i = 1; i < edges.size(); i++) {
			Edge temp = fri(x);
			if (temp == null) {
				break;
			}
			free[temp.id]= true;
			result[i-1] = temp.id;
			res += temp.len;
			x = temp.getEnd(x);
			towns[x] = true;
			//System.out.println(temp);
		}
		
		System.out.println(Arrays.toString(result));
		
//		for (int i = 0; i < result.length; i++) {
//			System.out.println(edgesById[result[i]]);
//			if (result[i] == 0) {
//				break;
//			}
//		}
		
		System.out.println("RES: " + res);
		Arrays.sort(result);
		
		BufferedReader br2 = new BufferedReader(new FileReader("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/O_"+test+".txt"));
		readLine = br2.readLine();
		line = readLine.split(",");
		//System.out.println(Arrays.toString(line));
		br2.close();
		
		int actualResult = 0;
		for (int i = 0; i < line.length; i++) {
			actualResult += lenResult[Integer.parseInt(line[i])];
		}
		System.out.println("ACTUAL RES: " + actualResult);
		
		//System.out.println(edges);
//		int g = 1;
//		for (LinkedList<Edge> iter : edges.values()) {
//			System.out.println(g+":"+iter);
//			g++;
//		}
		
		PrintWriter writer = new PrintWriter(new FileWriter("/home/jakob/Documents/semster1/APS/seminarska2/naloga8_testi/testIzhod.txt"));
		for (int i = 0; i < n-1; i++) {
			if (result[i] != 0) {
				//System.out.print(result[i] + ",");
				writer.print(result[i] + ",");
			}
		}
		//System.out.println(result[n-1]);
		writer.println(result[n-1]);
		
		writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}