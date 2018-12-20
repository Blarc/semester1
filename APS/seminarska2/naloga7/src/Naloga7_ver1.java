import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Naloga7_ver1 {
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
	
	
	public static boolean fri(Vertex atm, Vertex[] result, int index, int h, int w) {
		//System.out.printf("h: %d, w: %d\n", h, w);
		
		if (index == n) {
			//System.out.println("Yay!");
			//System.out.println(Arrays.toString(result));
			return true;
		}
		
		
		//System.out.println(atm);
		if (atm.left > -1 && atm.right > -1) {
			//System.out.println("First!");
			result[index] = vertices.get(atm.left);
			result[index].y = h;
			result[index].x = w-1;
			index++;
			result[index] = vertices.get(atm.right);
			result[index].y = h;
			result[index].x = w+1;
			
			if(fri(vertices.get(atm.left), result, index += 1, h += 1, w -= 2)) {
				return true;
			}
			
			/*if(fri(result[index], result, index += 2)) {
				return true;
			}*/
			
			return fri(result[index], result, index += 2, h += 1, w += 2);
			
			/*if(fri(result[index], result, index += 2)) {
				return true;
			}*/
		}
		
		else if (atm.left > -1) {
			//System.out.println("Second!");
			result[index] = vertices.get(atm.left);
			result[index].y = h;
			result[index].x = w-1;
			//fri(result[index], result, index += 1);
			
			return fri(result[index], result, index += 1, h += 1, w -= 1);
		}
		
		else if (atm.right > -1) {
			//System.out.println("Third!");
			result[index] = vertices.get(atm.right);
			result[index].y = h;
			result[index].x = w+1;
			//fri(result[index], result, index += 1);
			
			return fri(result[index], result, index += 1, h += 1, w += 1);
		}
		
		return false;
		
		
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
			//System.out.println("Chosen one: " + iter);
			result[0] = iter;
			if (fri(iter, result, 1, 1, 0)) {
				break;
			}
		}
		
		for (int i = 0; i < result.length; i++) {
			System.out.printf("%d,%d,%d\n", result[i].value, result[i].x + 3, result[i].y);
		}
		//System.out.println(Arrays.toString(result));
		//PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		//writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}
