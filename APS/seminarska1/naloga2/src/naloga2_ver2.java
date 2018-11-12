import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class naloga2_ver2 {
	
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
	
	public static class Blok {
		int size;
		int id;
		
		public Blok(int id, int size) {
			this.id = id;
			this.size = size;
		}
		
		public String toString() {
			return "[" + id + ", " + size +"]";
		}
	}
	
    public static class Malloc {
        int vseh = 0;
        LinkedList list;
        LinkedListElement[] idTab;
        int length;
        LinkedListElement atm;

        public void init(int size) {
            this.length = size;
            this.list = new LinkedList();
            this.idTab = new LinkedListElement[size+1];
            list.addLast(new Blok(0, size));
            atm = list.first;
        }
        
        public boolean alloc(int size, int id) {
        	if (idTab[id-1] == null) {
        		for (int i = 0; i < length && atm.next != null; i++) {
        			Blok tmp = (Blok)atm.next.element; ////////////////////////////////// wierd, but it works...
        			if (tmp.size >= size && tmp.id == 0) {
        				vseh++;
        				idTab[id-1] = atm;
    					LinkedListElement next2 = atm.next.next;
        				if (tmp.size - size > 0) {
        					if (next2 == null) {
        						atm.next = new LinkedListElement(new Blok(id, size));
        						list.addLast(new Blok(0, tmp.size-size));
        						atm = atm.next;
        					}
        					else {
        						atm.next = new LinkedListElement(new Blok(id, size));
        						next2 = new LinkedListElement(new Blok(0, tmp.size - size), next2.next);
        					}
        				}
        				else {
        					if (next2 == null) {
        						atm.next = new LinkedListElement(new Blok(id, size));
        						atm = atm.next;
        					}
        					else {
        						atm.next = new LinkedListElement(new Blok(id, size), next2);
        					}
        				}
        				
        				return true;
        			}
        		}
        	}
        	return false;
        }
        
        public int free(int id) {
        	
        	Blok next3 = null;
        	LinkedListElement temp = idTab[id-1];
        	Blok prejsni = (Blok)temp.element;
        	Blok zdejsni = (Blok)temp.next.element;
        	int size = zdejsni.size;
        	Blok nasledni = (Blok)temp.next.next.element;
        	if (temp.next.next.next != null) {
        		next3 = (Blok)temp.next.next.next.element;
        	}
        	
        	if (nasledni != null && prejsni != null && nasledni.id == 0 && prejsni.id == 0) {
        		size += nasledni.size + prejsni.size;
        		prejsni.size = size;
    			temp.next = temp.next.next.next;
    			if (next3 != null) {
    				idTab[next3.id - 1] = temp;
    			}
        	}
    		else if (nasledni != null && nasledni.id == 0) {
    			zdejsni.size += nasledni.size;
    			temp.next.next = temp.next.next.next;
    			if (next3 != null) {
    				idTab[next3.id - 1] = temp.next;
    			}
    			
        	}
    		else if (prejsni != null && prejsni.id == 0) {
    			prejsni.size += size;
    			temp.next = temp.next.next;
    			idTab[nasledni.id - 1] = temp;
    		}
        		
        	
        	zdejsni.id = 0;	
        	idTab[id-1] = null;
        	
        	return size;
        }
    }

    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis();
        if(args.length < 1) {
            System.out.println("Uporaba: java naloga2 <vhodna datoteka> <izhodna datoteka>");
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String[] line;
        line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        System.out.println("n: " + n);


        Malloc plswork = new Malloc();
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(",");
            String ukaz = line[0];
            int bard = Integer.parseInt(line[1]);
            switch(ukaz) {
                case "i":
                    //System.out.println(bard);
                    plswork.init(bard);
                    break;
                case "a":
                    int sion = Integer.parseInt(line[2]);
                    //System.out.println(bard + " " + sion);
                    plswork.alloc(bard, sion);
                    break;
                case "f":
                    //System.out.println(bard);
                    plswork.free(bard);
                    break;
                case "d":
                    //System.out.println(bard);
                    //plswork.defrag(bard);
                    break;
                    default: System.out.println("invalid");
            }
            if (i == n-1) {
                System.out.println(Arrays.toString(line));
            }
        }
        br.close();
        
   
        plswork.list.write();
        System.out.println(((Blok)plswork.atm.element).id);
        System.out.println(((Blok)plswork.atm.next.element).id);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed Time: " + elapsedTime + " ms");
    }
}
