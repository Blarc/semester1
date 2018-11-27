import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Naloga4 {
	
	public static class Blok {
		int len;
		int count;
		int[] seznam;
		Blok next;
		
		public Blok() {
			stBlokov = 1;
			init(5);
		}
		
		public Blok(Blok old) {
			this.len = old.len;
			this.count = old.count;
			this.seznam = old.seznam;
			this.next = old.next;
		}
		
		public Blok(int n, Blok next) {
			init(n);
			this.next = next;
		}
		
		/*public void toPrint() {
			for (Blok i = this; i != null; i = i.next) {
				System.out.print(Arrays.toString(i.seznam));
			}
			System.out.println();
		}*/
		
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
				}	
			}
		}
		
		public void moveLeft(int pos) {
			for (int i = pos; i < len-1 && seznam[i+1] != 0; i++) {
				seznam[i] = seznam[i+1];
				seznam[i+1] = 0;
			}
			if (count > 1) {
				seznam[count-1] = 0;
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
			for (int i = 0; i < next.count && seznam[i] != 0; i++) {
				seznam[count] = next.seznam[i];
				count++;
			}
			
		}
		
		public boolean insert(int id, int pos) {
			//ce je veljavno
			if (-1 < pos && pos <= all) {
				//ce spada v ta blok
				if (pos <= count && pos < len) {
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
						stBlokov++;
						
						if (next != null) {
							Blok tmp = new Blok(next);
							next = new Blok(len, tmp);
						}
						else {
							next = new Blok(len, null);
						}
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
			if (-1 < pos && pos < all) {
				//ce spada v ta blok
				if (pos < count && pos < len) {
					//ga zbrisemo
					moveLeft(pos);
					count--;
					//ce po brisanju elementov v tem clenu pod N/2
					int half = len/2;
					if (count < half && next != null) {
						//ce po brisanju elementov v naslednjem clenu pod N/2	
						if (next.count - 1 < half) {
							collapse();
							next = next.next;
							stBlokov--;
						}
						else {
							insert(next.seznam[0], half-1);
							next.remove(0);
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
	public static int stBlokov;
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
		
		PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
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
                    /*System.out.println("Insert: " + id + " " + pos);
                    for (Blok j = first; j != null; j = j.next) {
                    	System.out.println(Arrays.toString(j.seznam));
                    }*/
                    break;
                case "r":
                	if (first.remove(id)) {
                		all--;
                	}
                	/*System.out.println("Remove: " + id);
                	for (Blok j = first; j != null; j = j.next) {
                    	System.out.println(Arrays.toString(j.seznam));
                    }*/
                    break;
                default: System.out.println("invalid");
            }
        }
		
        br.close();
        
        
        writer.println(stBlokov);
        for (Blok i = first; i != null; i = i.next) {
        	for (int j = 0; j < i.len; j++) {
        		if (i.seznam[j] == 0) {
        			if (j == i.len - 1) {
        				writer.print("NULL");
        			} 
        			else {
        				writer.print("NULL,");
        			}	
        		} 
        		else {
        			if (j == i.len - 1) {
        				writer.print(i.seznam[j]+"");
        			}
        			else {
        				writer.print(i.seznam[j]+",");
        			}
        		}
        	}
        	writer.println();
        }
        /*System.out.println("--------------- OUTPUT -----------------");
        for (Blok j = first; j != null; j = j.next) {
        	System.out.println(Arrays.toString(j.seznam));
        }*/
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed time: " + elapsedTime + " ms");
	    
	    writer.close();
	}
}
