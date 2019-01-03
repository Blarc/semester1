import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Naloga9 {
	
	public static class Person {
		int id;
		Set<Integer> friends;
		
		public Person(int id) {
			this.id = id;
			friends = new HashSet<Integer>();
			friends.add(id);
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
		HashMap<Integer, Person> people;
		LinkedList<Set> sets;
		boolean bool = false;
		
		//int test = 10;
		BufferedReader br = new BufferedReader(new FileReader(args[0]));		
		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		
		readLine = br.readLine();
		n = Integer.parseInt(readLine);
		people = new HashMap<Integer, Person>(n);
		sets = new LinkedList<Set>();
		sets.add(new HashSet<Integer>());
		
		for (int i = 0; i < n; i++) {
			readLine = br.readLine();
			line = readLine.split(",");
			int id1 = Integer.parseInt(line[0])-1;
			int id2 = Integer.parseInt(line[1])-1;
			
			if (people.get(id1) == null) {
				people.put(id1, new Person(id1));
			}
			
			if (people.get(id2) == null) {
				people.put(id2, new Person(id2));
			}
			
			Iterator<Set> iterator = sets.iterator();
			while (iterator.hasNext()) {
				Set<Integer> iter = iterator.next();
				
				if (iter.contains(id1) && !iter.contains(id2)) {
					bool = true;
					iter.add(id2);
					for (Set<Integer> iter2 : sets) {
						if (iter != iter2) {
							if (iter2.contains(id2)) {
								iter.addAll(iter2);
								//sets.remove(iter2);
							}
						}
					}
					
					break;
				}
				else if (iter.contains(id2) && !iter.contains(id1)) {
					bool = true;
					iter.add(id1);
					for (Set<Integer> iter2 : sets) {
						if (iter != iter2) {
							if (iter2.contains(id1)) {
								iter.addAll(iter2);
								//sets.remove(iter2);
							}	
						}
					}
					break;
				}
				else if (iter.contains(id1) && iter.contains(id2)) {
					bool = true;
					//System.out.println("!!"+(id1+1)+","+(id2+1));
					writer.println((id1+1)+","+(id2+1));
					break;
				}
			}
			
			if (!bool) {
				Set<Integer> temp = new HashSet<Integer>();
				temp.add(id1);
				temp.add(id2);
				sets.add(temp);
			}
			bool = false;
			
		}
		
		
		br.close();
		writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}

}
