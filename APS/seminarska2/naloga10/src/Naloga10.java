import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Naloga10 {
	public static class Edge {
		Vertex end;
		int cost;
		
		public Edge(Vertex end, int cost) {
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "{"+end.id+","+cost+"}";
		}
	}
	
	public static class Vertex {
		int id;
		int grade;
		LinkedList<Edge> edges;
		
		
		public Vertex(int id) {
			this.id = id;
			this.edges = new LinkedList<Edge>();
		}
		
		public Edge min(boolean[] visited) {
			int min = Integer.MAX_VALUE;
			Edge best = null;
			
			if (edges.isEmpty()) {
				return new Edge(new Vertex(-1), Integer.MAX_VALUE);
			}
			
			for (Edge iter : this.edges) {
				if (!visited[iter.end.id]) {
					int sum = iter.cost + iter.end.grade;
					if (sum < min) {
						min = sum;
						best = iter;
					}
				}
			}
			return best;
		}
		
		@Override
		public String toString() {
			return this.id + "->" + this.edges.toString() + "-" + this.grade;
		}
	}
	


	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 2) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		String readLine;
		String[] line;
		readLine = br.readLine();
		line = readLine.split(" ");
		int n = Integer.parseInt(line[0]);
		System.out.println("n: " + n);
		
		Vertex[] vertices = new Vertex[n];
		
		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			int cost = Integer.parseInt(line[2]);
			
			if (vertices[start] == null) {
				vertices[start] = new Vertex(start);
			}
			
			if (vertices[end] == null) {
				vertices[end] = new Vertex(end);
			}
			
			vertices[start].edges.add(new Edge(vertices[end], cost));
		}
		
		readLine = br.readLine();
		line = readLine.split(",");
		int start = Integer.parseInt(line[0]);
		int end = Integer.parseInt(line[1]);
		//System.out.printf("Start: %d, End: %d\n", start, end);
		//System.out.println(Arrays.toString(vertices));
		
		br.close();
		

		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		
		boolean result = false;
		int[] path = new int[n];
		while (!result) {
			
			result = true;
			
			int i = 0;
			boolean[] visited = new boolean[n];
			visited[start] = true;
			Vertex pos = vertices[start];
			
			while (pos.id != end) {
				
				if (path[i] != pos.id) {
					path[i] = pos.id;
					result = false;
				}
				
				Edge edge = pos.min(visited);
				if (edge == null) {
					break;
				}
				else if (edge.end.id == -1) {
					if (edge.end.grade != Integer.MAX_VALUE) {
						edge.end.grade = Integer.MAX_VALUE;
						result = false;
					}
					break;
					
				}
				else {
					//System.out.println(edge);
					if (pos.grade != edge.cost + edge.end.grade) {
						pos.grade = edge.cost + edge.end.grade;
						result = false;
					}
					
					pos = edge.end;
					visited[pos.id] = true;
				}
				
				i++;
			}
			
			if (path[i] != pos.id) {
				path[i] = pos.id;
				result = false;
			}
			
			for (int j = 0; j < i; j++) {
				//System.out.print(path[j]+",");
				writer.print(path[j]+",");
			}
			//System.out.println(path[i]);
			writer.println(path[i]);
		}
		
		
		writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}