import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

public class Naloga7 {
	public static class Vertex {
		int left;
		int right;
		int value;
		int x;
		int y;
		
		public Vertex (int value, int left, int right) {
			this.left = left;
			this.right = right;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return "{"+value+","+left+","+right+","+x+","+y+"}";
		}
	}
	
	
	public static int count;
	public static int fri(Vertex atm, int w, int h) {
		if (atm.left > -1) {
			Vertex temp = vertices.get(atm.left);
			w = fri(temp, w, h+1) + 1;
		}
		
		while (help[w]) {
			w++;
		}
		
		if (atm.right > -1) {
			Vertex temp = vertices.get(atm.right);
			fri(temp, w+1, h+1);
		}
			
		help[w] = true;
		
		atm.x = w;
		atm.y = h;
		count++;
		
		if (h == 0 && count == n) {
			return -1;
		}
		
		return w;
	}
	
	public static HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
	public static int n;
	public static boolean[] help;
	public static void main(String[] args) throws IOException {
		
		//long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String readLine;
		String[] line;
		
		readLine = br.readLine();
		line = readLine.split(" ");
		n = Integer.parseInt(line[0]);
		//System.out.println("n: " + n);
		
		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			
			int id = Integer.parseInt(line[0]);
			int value = Integer.parseInt(line[1]);
			int left = Integer.parseInt(line[2]);
			int right = Integer.parseInt(line[3]);
			
			vertices.put(id, new Vertex(value, left, right));
		}
		
		br.close();
		
		//System.out.println(vertices);
		
		
		Vertex root = null;
		for (Vertex iter : vertices.values()) {
			help = new boolean[n];
			count = 0;
			if (fri(iter, 0, 0) == -1) {
				root = iter;
				break;
			}
		}
		
		PrintWriter writer = new PrintWriter(args[1]);
		LinkedList<Vertex> progress = new LinkedList<Vertex>();
		progress.add(root);
		while (!progress.isEmpty()) {
			LinkedList<Vertex> current = new LinkedList<Vertex>();
			for (Vertex iter : progress) {
				writer.printf("%d,%d,%d\n", iter.value, iter.x, iter.y);
				if (iter.left > -1) {
					current.add(vertices.get(iter.left));
				}
				if (iter.right > -1) {
					current.add(vertices.get(iter.right));
				}
			}
			progress = current;
		}
		
		writer.close();
		
		//long stopTime = System.currentTimeMillis();
	    //long elapsedTime = stopTime - startTime;
	    //System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}
