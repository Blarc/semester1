import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;



public class Naloga3_NOT_WORKING {
	
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
		boolean poravnan;
		
		public Blok(int id, int start, int end) {
			this.id = id;
			this.start = start;
			this.end = end;
			this.len = end - start + 1;
			this.poravnan = false;
		}
		
		public String toString() {
			return "[" + id + ", " + start + ", " + end + ", " + len + "]";
		}
	}
	

	
	public static int[][] best;
	public static int min = Integer.MAX_VALUE;
	
	public static void fri(LinkedList list, LinkedListElement atm, int idealStart, int razdalja, int stevec, int[][] tab, int index) {
		
		
		if (stevec == 0) {
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println(Arrays.deepToString(tab));
			if (razdalja < min) {
				min = razdalja;
				for (int i = 0; i < tab.length; i++) {
					best[i][0] = tab[i][0];
					best[i][1] = tab[i][1];
				}
			}
			System.out.println("Razdalja: " + razdalja);
			return;
		}
		
			
		Blok atmBlok = (Blok)atm.next.element;
		
		/*if(razdalja >= min) {
			return;
		}*/
		
		if (atmBlok.poravnan) {
			fri(list, atm.next, idealStart, razdalja, stevec, tab, index);
		}
		
		//ce je PRVI blok ze poravnan
		if (atmBlok.start == idealStart) {
			atmBlok.poravnan = true;
			fri(list, atm.next, idealStart + atmBlok.len, razdalja, stevec-1, tab, index);
			return;
		}
		
		//ce PRVI blok ni poravnan, pogledamo koliko je zamika
		int frej = atmBlok.start - idealStart;
		
		
		//preverimo vse bloke ki jih lahko poravnamo
		for (LinkedListElement i = atm; i.next != null; i = i.next) {
			Blok novBlok = (Blok)i.next.element;
			
			//ce blok ni poravnan
			if (!novBlok.poravnan) {
				//ce je atm
				if (novBlok.id == atmBlok.id) {
					novBlok.poravnan = true;
					tab[index][0] = novBlok.id;
					tab[index][1] = idealStart;
					fri(list, atm.next, idealStart + novBlok.len, razdalja + novBlok.len, stevec-1, tab, index+1);
					tab[index][0] = 0;
					tab[index][1] = 0;
					novBlok.poravnan = false;
					
				}
				//ce je blok dovolj majhen da ga poravnamo v prazen prostor
				else if (novBlok.len <= frej) {
					//ga poravnamo
					novBlok.poravnan = true;
					tab[index][0] = novBlok.id;
					tab[index][1] = idealStart;
					//ce smo poravnali kak drug blok
					fri(list, atm, idealStart + novBlok.len, razdalja + novBlok.len, stevec-1, tab, index+1);	
					tab[index][0] = 0;
					tab[index][1] = 0;
					novBlok.poravnan = false;
				}
			}
		}
		
		return;
	}

	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		
		LinkedList list = new LinkedList();
		
		int numOfBlocks = 0;
		String readLine;
		Blok novBlok;
		while ((readLine = br.readLine()) != null) {
			String[] line = readLine.split(",");
			//System.out.println(Arrays.toString(line));
			
			int id = Integer.parseInt(line[0]); 
			int start = Integer.parseInt(line[1]);
			int end = Integer.parseInt(line[2]);
			novBlok = new Blok(id, start, end);
			
			list.addLast(novBlok);
			numOfBlocks++;
		}
		
		best = new int[numOfBlocks][2];
		int[][] tab = new int[numOfBlocks][2];
		
		fri(list, list.first, 0, 0, numOfBlocks, tab, 0);
		
		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		System.out.println("------------------------------------- OUTPUT ---------------------------------------------");
		list.write();
		System.out.println("Number of blocks: " + numOfBlocks);
		System.out.println(Arrays.deepToString(best));
		System.out.println(min);
		for (int i = 0; i < numOfBlocks; i++) {
			if (best[i][0] == 0) {
				break;
			}
			writer.println(best[i][0] + "," + best[i][1]);
		}
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
		
		br.close();
		writer.close();
	}

}
