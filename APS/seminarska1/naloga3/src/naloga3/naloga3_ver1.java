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
		int len;
		
		public Blok(int id, int start, int end, int len) {
			this.id = id;
			this.start = start;
			this.end = end;
			this.len = len;
		}
		
		public String toString() {
			return "[" + id + ", " + start + ", " + end + ", " + len + "]";
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
		Blok novBlok;
		int oldEnd = -1;
		while ((readLine = br.readLine()) != null) {
			String[] line = readLine.split(",");
			//System.out.println(Arrays.toString(line));
			
			int id = Integer.parseInt(line[0]); 
			int start = Integer.parseInt(line[1]);
			int end = Integer.parseInt(line[2]);
			int len = end - start + 1;
			
			//create empty blocks
			/*if (oldEnd+1 != start) {
				novBlok = new Blok(0, oldEnd+1, start-1, start - oldEnd - 1);
				list.addLast(novBlok);
			}*/
			
			//create full blocks
			novBlok = new Blok(id, start, end, len);
			
			list.addLast(novBlok);
			oldEnd = end;
		}
		
		list.write();
		
			
		int idealStart = 0;
		int frej = 0;
		
		
		LinkedListElement prev = list.first;
		for (LinkedListElement i = list.first.next; i != null; i = i.next) {
			Blok atmI = (Blok)i.element;
			if (idealStart == atmI.start) {
				//je kul, so eden za drugim
			}
			//System.out.println("Ideal: " + idealStart + " Actual: " + atmI.start);
			if (idealStart < atmI.start) {
				Blok best = (Blok)list.first.element;
				int bestId = -1;
				int max = 0;
				frej = atmI.start - idealStart;
				for (LinkedListElement j = i; j != null; j = j.next) {
					Blok atmJ = (Blok)j.element;
					if (atmJ.len == frej) {
						bestId = atmJ.id;
						best = atmJ;
						max = atmJ.len;
						

					}
					else if (atmJ.len < frej && atmJ.len > max) {
						bestId = atmJ.id;
						max = atmJ.len;
						best = atmJ;
					}
					
					
				}
				best.start = idealStart;
				best.end = idealStart + best.len;
				System.out.println("Id: " + bestId + " Max: " + max);
			}
			idealStart += atmI.len;
			
			prev = i;
		}
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
	}

}
