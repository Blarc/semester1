import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Naloga5 {
	
	public static class Blok {
		int id;
		int size;
		Blok next;
		
		public Blok(int id, int size) {
			this.id = id;
			this.size = size;
		}
		
		public Blok() {
			
		}
	}
	
	public static class PodSeznam {
		int len;
		int prosto;
		Blok first;
		Blok last;
		
		public PodSeznam(int n) {
			this.len = n;
			this.prosto = n;
			this.first = new Blok();
		}
		
		public void addLast(Blok atm) {
			prosto -= atm.size;
			
			if (last != null) {
				last = last.next;
				last.next = atm;
			} else {
				first.next = atm;
				last = first;
			}
		}
		
		
		public void remove(int id) {
			Blok el, prev_el;
			
			prev_el = null;
			el = first;
			
			while (el.next.id != id) {
				prev_el = el;
				el = el.next;
				if (el == null) {
					System.out.println("NAPAKA V REMOVE");
					return;
				}
			}
			
			prosto += el.next.size;
			
			if (last == el.next) {
				last = el;
			}
			else if (last == el) {
				last = prev_el;
			}
			
			el.next = el.next.next;
		}
		
		public void toPrint() {
			Blok nov = first;
			while (nov.next != null) {
				System.out.printf("[%d, %d],", nov.next.id, nov.next.size);
				nov = nov.next;
			}
			System.out.println();
		}
		
	}
	
	public static class GlavniSeznam {
		PodSeznam[] seznam;
		int[] idSeznam;
		
		public GlavniSeznam() {
			init(16, 16);
		}
		
		public void init(int a, int b) {
			this.idSeznam = new int[a*b];
			this.seznam = new PodSeznam[a];
			for (int i = 0; i < a; i++) {
				seznam[i] = new PodSeznam(b);
			}
		}
		
		public boolean alloc(int size, int id) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			
			for (int i = 0; i < seznam.length; i++) {
				
				//ce pase direkt v PodSeznam
				if (seznam[i].prosto == size) {
					Blok nov = new Blok(id, size);
					seznam[i].addLast(nov);
					idSeznam[id] = i;
					return true;
				}
				
				else {
					if (seznam[i].prosto > size) {
						if (seznam[i].prosto - size < min) {
							min = seznam[i].prosto - size;
							minIndex = i;
						}
					}
				}
			}
			
			if (minIndex > -1) {
				Blok nov = new Blok(id, size);
				seznam[minIndex].addLast(nov);
				idSeznam[id] = minIndex;
				return true;
			}
			
			return false;
		}
		
		public int free (int id) {
			seznam[idSeznam[id]].remove(id);
			return 0;
		}
		
		public void toPrint() {
			for (int i = 0; i < seznam.length; i++) {
				System.out.print(i+": ");
				seznam[i].toPrint();
			}
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 2) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		String readLine = br.readLine();
		String[] line = readLine.split(" ");
		int n = Integer.parseInt(line[0]);
		
		GlavniSeznam bard = new GlavniSeznam();
		int[] rezultati = new int[10];
		
		for (int i = 0; i < n; i++) {
			line = br.readLine().split(",");
            String ukaz = line[0];
            int a = Integer.parseInt(line[1]);
            switch(ukaz) {
                case "i":
                	int b = Integer.parseInt(line[2]);
                	rezultati = new int[b+1];
                	bard.init(a, b);
                    break;
                case "a":
                	b = Integer.parseInt(line[2]);
                	bard.alloc(a, b);
                    break;
                case "f":
                	bard.free(a);
                    break;
                default: System.out.println("invalid");
            }
		}
		
		
		br.close();
		
		//bard.toPrint();
		
		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
		
		for (int i = 0; i < bard.seznam.length; i++) {
			rezultati[bard.seznam[i].prosto]++;
		}
		
		for (int i = rezultati.length-1; i > -1; i--) {
			writer.println(rezultati[i]);
		}
		
		writer.close();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	    
	}

}
