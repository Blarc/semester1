package naloga3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;



public class naloga3_ver1 {
	
	public static class LinkedListElement  
	{
		Object element;
		LinkedListElement next;
		
		LinkedListElement(Object obj)
		{
			element = obj;
			next = null;
		}
		
		LinkedListElement(Object obj, LinkedListElement nxt)
		{
			element = obj;
			next = nxt;
		}
	}
	

	public static class LinkedList
	{
		protected LinkedListElement first;
		protected LinkedListElement last;
		
		public LinkedList()
		{
			makenull();
		}
		
		public void makenull()
		{
			first = new LinkedListElement(null, null);
			last = null;
		}
		
		public void addLast(Object obj)
		{
			LinkedListElement nov = new LinkedListElement(obj);
			if (last != null) {
				last = last.next;
				last.next = nov;
			} else {
				first.next = nov;
				last = first;
			}
			
		}
		
		public void write()
		{
	
			LinkedListElement el;
			el = first.next;
			while (el != null) {
				System.out.print(el.element.toString()+", ");
				el = el.next;
			}
			System.out.println();
	
		}
		
		void addFirst(Object obj)
		{
			LinkedListElement nov = new LinkedListElement(obj);
			if (last == null) {
				last = first;
			} else if (first == last) {
				nov.next = first.next;
				last = nov;
			} else {
				nov.next = first.next;
			}
			first.next = nov;
		}
		
		int length()
		{
			int n = 0;
			LinkedListElement el;
			el = first.next;
			while (el != null) {
				n++;
				el = el.next;
			}
			
			return n;
		}
		
	
	}
	
	public static class Blok  {
		int id;
		int start;
		int end;
		
		public Blok(int id, int start, int end) {
			this.id = id;
			this.start = start;
			this.end = end;
		}
		
		public String toString() {
			return "[" + id + ", " + start + ", " + end + "]";
		}
	}


	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		
		LinkedList list = new LinkedList();
		
		String readLine;
		while ((readLine = br.readLine()) != null) {
			String[] line = readLine.split(",");
			//System.out.println(Arrays.toString(line));
			
			Blok novBlok = new Blok(Integer.parseInt(line[0]),
									Integer.parseInt(line[1]),
									Integer.parseInt(line[2]));
			
			list.addLast(novBlok);
		}
		
		list.write();
		
			
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
	}

}
