import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Naloga9_ver1 {
	
	public static class Person {
		int id;
		LinkedList<Person> friends;
		
		public Person(int id) {
			this.id = id;
			this.friends = new LinkedList<Person>();
		}
	}

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		// variables
		int n;
		String readLine;
		String[] line;
		Person[] people;
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		readLine = br.readLine();
		line = readLine.split(" ");
		n = Integer.parseInt(line[0]);
		people = new Person[n];
		
		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			int id1 = Integer.parseInt(line[0])-1;
			int id2 = Integer.parseInt(line[1])-1;
			
			if (people[id1] == null) {
				people[id1] = new Person(id1);
			}
			
			if (people[id2] == null) {
				people[id2] = new Person(id2);
			}
			
			if (!people[id1].friends.contains(people[id2])) {
				for (Person iter : people[id1].friends) {
					iter.friends.add(people[id2]);
				}
				people[id1].friends.add(people[id2]);
			}
			
			else {
				System.out.printf("%d,%d\n", id1+1, id2+1);
			}
			
			if (!people[id2].friends.contains(people[id1])) {
				for (Person iter : people[id2].friends) {
					iter.friends.add(people[id1]);
				}
				people[id2].friends.add(people[id1]);
			}
			
			
			else {
				System.out.printf("%d,%d\n", id1+1, id2+1);
			}
			//System.out.println(Arrays.toString(people));
			
		}
		
		
		br.close();
		
		//PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		//writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}
