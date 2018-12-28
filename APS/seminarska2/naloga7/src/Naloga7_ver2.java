import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Naloga7_ver2 {
	public static class Vertex {
		int left;
		int right;
		int value;
		int x;
		int y;
		int parX;
		boolean side;
		
		public Vertex (int value, int left, int right) {
			this.left = left;
			this.right = right;
			this.value = value;
		}
		
		public boolean isLeft() {
			return !side;
		}
		
		@Override
		public String toString() {
			return "{"+value+","+left+","+right+","+x+","+y+"}";
		}
	}
	
	public static Vertex[] xmas(LinkedList<Vertex> progress) {
		Vertex[] result = new Vertex[n];
		
		int h = 0;
		int index = 0;
		
		while (!progress.isEmpty()) {
			LinkedList<Vertex> current = new LinkedList<Vertex>();
			for (Vertex iter : progress) {
				if (iter.isLeft()) {
					iter.x = iter.parX - 1;
				}
				else {
					iter.x = iter.parX + 1;	
				}
				iter.y = h;
				result[index] = iter;
				index++;
				
				if (iter.left > -1) {
					Vertex temp = vertices.get(iter.left);
					temp.parX = iter.x;
					current.add(temp);
				}
				
				if (iter.right > -1) {
					Vertex temp = vertices.get(iter.right);
					temp.parX = iter.x;
					temp.side = true;
					current.add(temp);
				}
				
			}
			progress = current;
			h++;
		}
		
		System.out.println(Arrays.toString(result));
		return result;
	}
	
	public static HashMap<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
	public static int n;
	public static void main(String[] args) throws IOException {
		
		//problem: find the root of a binary tree
		
		long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String readLine;
		String[] line;
		
		readLine = br.readLine();
		line = readLine.split("");
		n = Integer.parseInt(line[0]);	
		
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
		
		System.out.println(vertices.values());
		
		Vertex[] result = new Vertex[n];
		for (Vertex iter : vertices.values()) {
			LinkedList<Vertex> progress = new LinkedList<Vertex>();
			iter.side = true;
			iter.parX = -1;
			progress.add(iter);
			result = xmas(progress);
			if (result[n-1] != null) {
				break;
			}
		}
		
		for (int i = 0; i < result.length && result[i] != null; i++) {
			System.out.printf("%d,%d,%d\n", result[i].value, result[i].x+3, result[i].y);
		}
		//System.out.println(Arrays.toString(result));
		//PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		//writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}
