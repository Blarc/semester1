import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class properly_done_Naloga4 {
	
	public static class Blok {
		int len;
		int count;
		int[] seznam;
		Blok next;
		Blok previous;
		
		public Blok() {
			init(5);
			previous = null;
		}
		
		public Blok(int n, Blok prev) {
			init(n);
			previous = prev;
		}
		
		public void toPrint() {
			for (Blok i = this; i != null; i = i.next) {
				System.out.print(Arrays.toString(i.seznam));
			}
			System.out.println();
		}
		
		public void init(int n) {
			this.count = 0;
			this.len = n;
			this.seznam = new int[n];
			this.next = null;
		}
		
		public void moveRight(int pos) {
			int tmp1 = seznam[pos];
			int tmp2 = 0;
			for (int i = pos; i < len && seznam[i] != 0; i++) {
				if (i < len-1) {
					tmp2 = seznam[i+1];
					seznam[i+1] = tmp1;
					tmp1 = tmp2;
					
					/*if (seznam[i+1] == 0) {
						break;
					}*/
				}	
			}
		}
		
		public void moveLeft(int pos) {
			for (int i = pos; i < len && seznam[i+1] != 0; i++) {
				seznam[i] = seznam[i+1];
				seznam[i+1] = 0;
			}
		}
		
		public void split() {
			int half = len / 2;
			for (int i = half; i < len; i++) {
				next.seznam[i-half] = seznam[i];
				seznam[i] = 0;
					
				count--;
				next.count++;
			}
		}
		
		public void collapse() {
			for (int i = 0; i < len && seznam[i] != 0; i++) {
				previous.seznam[previous.count+i] = seznam[i];
			}
		}
		
		public boolean insert(int id, int pos) {
			//ce je veljavno
			if (-1 < pos && pos <= all) {
				//ce spada v ta blok
				if (pos <= count) {
					//ce je se kak prazen plac v seznamu
					if (count < len) {
						//ce je mesto prazno
						if (seznam[pos] == 0) {
							seznam[pos] = id;
						}
						//ce mesto ni prazno
						else {
							this.moveRight(pos);
							seznam[pos] = id;
						}
						count++;
					}
					//ce je seznam poln, naredimo nov blok in vstavimo element
					else {
						next = new Blok(len, this);
						this.split();
						insert(id, pos);
					}
				}
				//pogledamo ce spada v naslednji blok
				else {
					next.insert(id, pos-count);
				}
				
				return true;
			}
			return false;
		}
		
		public boolean remove(int pos) {
			if (-1 < pos && pos <= all) {
				//ce spada v ta blok
				if (pos < count) {
					//ga zbrisemo
					seznam[pos] = 0;
					moveLeft(pos);
					count--;
					//ce po brisanju število vstavljenih elementov v tem clenu pade pod N/2
					int half = len/2;
					if (count < half) {
						//ce obstaja naslednji blok
						if (next != null) {
							insert(next.seznam[0], half-1);
							next.remove(0);
						}
						//ce ne obstaja naslednji blok
						else {
							collapse();
							previous.next = null;
						}
					}
				}
				//pogledamo ce spada v naslednji blok
				else {
					next.remove(pos-count);
				}
				return true;
			}
			return false;
		}
	}
	
	public static int all = 0;
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		if(args.length < 1) {
			System.out.println("Uporaba: java naloga1 <podatki> <resitev>");
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		
		String readLine = br.readLine();
		String[] line = readLine.split(" ");
		int n = Integer.parseInt(line[0]);
		
		
		Blok first = new Blok();
		
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(",");
            String ukaz = line[0];
            int id = Integer.parseInt(line[1]);
            switch(ukaz) {
                case "s":
                	first.init(id);
                    break;
                case "i":
                	//insert
                    int pos = Integer.parseInt(line[2]);
                    if (first.insert(id, pos)) {
                    	all++;
                    }
                    break;
                case "r":
                	if (first.remove(id)) {
                		all--;
                	}
                    break;
                default: System.out.println("invalid");
            }
        }
		
		
        first.toPrint();
        
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	}
}
