import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class naloga4_zadnik {
	public static void main(String[] args) throws IOException{
		long start = System.nanoTime();

		String vrstica;
		try {
			BufferedReader tok = new BufferedReader(
					new FileReader(args[0])
					);
			PodStrukt p = new PodStrukt();
			vrstica = tok.readLine();
			int stElementov = 0;
			int stVrstic = Integer.parseInt(vrstica);
			//System.out.println(stVrstic);
			while(stVrstic > 0) {
				vrstica = tok.readLine();
				System.out.println(vrstica);
				String[] ukaz = vrstica.split(",");
				if(ukaz[0].equals("s")){
					stElementov = Integer.parseInt(ukaz[1]);
					p.init(stElementov);
				}
				if(ukaz[0].equals("i")){
					p.insert(Integer.parseInt(ukaz[1]), Integer.parseInt(ukaz[2]));
				}
				if(ukaz[0].equals("r")){
					//p.free(Integer.parseInt(ukaz[1]));
				}
				stVrstic--;
			}
			tok.close();
			long end = System.nanoTime();
			long elapsedTime = end-start;
			double seconds = (double)elapsedTime / 1000000000.0;
			//p.izpis(args);	
			System.out.println(seconds);		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static class PodStrukt{
		private int stEl;
		LinkedList seznam;
		
		public PodStrukt() {
			init(5);
		}
		
		public void init(int n) {
			seznam = new LinkedList();
			seznam.deleteNth(0);
			//!!!!     polje[stEl] predstavlja stevilo elementov v tem polju    !!!!!!
			this.stEl = n;
			//seznam naj bo za 1 vecji, da si v zadnje mesto seznama zapisemo stevilo elementov v seznamu 
			seznam.addFirst(new int[n+1]);
		}
		
		public boolean insert(int v, int p) {
			//ce je zahtevan indeks previsok ali negativen, izstopimo
			if(p > stEl || p < 0) {
				return false;
			}
			LinkedListElement e = seznam.first.next;
			int[] polje = null;
			int vsota = 0;
			int pozicijaPolja = 0;
			//dokler je se kaj seznamov
			while(e != null) {
				
				polje = (int[])e.element;
				//sestevamo skupno stevilo elementov. ce presezemo zeljeno mesto, smo v pravem clenu
				vsota += polje[stEl];
				if(vsota > p) {
					int razlika = vsota - p;
					int vPolju = polje[stEl];
					//ce ima trenutno polje kaj prostora, lahko dodamo element brez problema
					if(vPolju < stEl) {
						//dodamo element takoj, ce je mesto prazno
						if(polje[razlika] == 0) {
							polje[razlika] = v;
							//se povecamo stevilo elementov v polju
							polje[stEl]++;
							return true;
						}
						//ce ne, zacnemo pomikati elemente
						for(int i=stEl-1; i > razlika; i--) {
							polje[i] = polje[i-1];
						}
						//ko so vsi elementi zamaknjeni, imamo prostor za novega
						polje[razlika] = v;
						//povecamo stevilo elementov v polju
						polje[stEl]++;
						return true;
					}
					//ce je polje polno, ga razdelimo na dva dela
					else {
						int polovica = stEl / 2;
						int[] novo = new int[stEl+1];
						for(int i=polovica; i < stEl; i++) {
						//ce je i nad polovico, prepisujemo elemente v novo polje ter jih brišemo iz starega
							novo[i-polovica] = polje[i];
							polje[i] = 0;
						}
						//popravimo stevilo elementov v obeh poljih
						polje[stEl] = polovica;
						novo[stEl] = stEl - polovica;
						//seznam.insertNth(novo, pozicijaPolja+1);
						//zdaj bo element zagotovo pristal v enem izmed polj
						if(razlika > polje[stEl]) {
							//odstejemo razliko, da dobimo indeks
							razlika -= polje[stEl];
							//ce je razlika vecja od stevila elementov v prvem seznamu, zacnemo zamikati elemente v drugem seznamu
							for(int i=novo[stEl]; i > razlika; i--) {
								novo[i] = novo[i-1];
							}
							//ko so vsi elementi zamaknjeni, vstavimo novega
							novo[razlika] = v;
							//povecamo stevilo elementov v polju
							novo[stEl]++;
						}
						else {
							for(int i=polje[stEl]; i > razlika; i--) {
								polje[i] = polje[i-1];
							}
							//ko so vsi elementi zamaknjeni, imamo prostor za novega
							polje[razlika] = v;
							//povecamo stevilo elementov v polju ter dodamo novo polje v list
							polje[stEl]++;
						}
						//dodamo na novo ustvarjeno polje na naslednjo pozicijo ter zakljucimo
						seznam.insertNth(novo, pozicijaPolja+1);
						return true;
					}
				}
				//povecamo indeks polja, da vemo kam vstaviti morebitnega novega
				pozicijaPolja++;
				e = e.next;
			}
			//ce do zdaj nismo koncali, vrnemo false
			return false;
		}
		
	}

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
		
		LinkedList()
		{
			makenull();
		}
		
		//Funkcija makenull inicializira seznam
		public void makenull()
		{
			//drzimo se implementacije iz ucbenika:
			//po dogovoru je na zacetku glava seznama (header)
			first = new LinkedListElement(null, null);
			last = null;
		}
		
		//Funkcija addLast doda nov element na konec seznama
		public void addLast(Object obj)
		{
			//najprej naredimo nov element
			LinkedListElement newEl = new LinkedListElement(obj, null);
			
			//ali je seznam prazen?
			// po dogovoru velja: ce je seznam prazen, potem kazalec "last" ne kaze nikamor
			if (last == null)
			{
				//ce seznam vsebuje samo en element, kazalca "first" in "last" kazeta na glavo seznama
				first.next = newEl;
				last = first;
			}
			else
			{
				last.next.next = newEl;
				last = last.next;
			}
		}
		
		//Funkcija write izpise elemente seznama
		public void write()
		{
			LinkedListElement el;
			
			//zacnemo pri elementu za glavo seznama
			el = first.next;
			while (el != null)
			{
				System.out.print(el.element + ", ");
				el = el.next;
			}
			
			System.out.println();
			
			/*
			//za kontrolo lahko izpisemo tudi vrednosti prvega in zadnjega elementa
			if (first.next != null)
				System.out.println("Prvi element: " + first.next.element);
			else
				System.out.println("Ni prvega elementa");
			
			if (last != null)
				System.out.println("Zadnji element: " + last.next.element);
			else
				System.out.println("Ni zadnjega elementa");
			*/
		}
		
		//Funkcija addFirst doda nov element na prvo mesto v seznamu (takoj za glavo seznama)
		void addFirst(Object obj)
		{
			//najprej naredimo nov element
			LinkedListElement newEl = new LinkedListElement(obj);
			
			//novi element postavimo za glavo seznama
			newEl.next = first.next;
			first.next = newEl;
			
			if (last == null)//preverimo, ali je to edini element v seznamu
				last = first;
			else if (last == first)//preverimo, ali je seznam vseboval en sam element
				last = newEl;
		}
		
		//Funkcija length() vrne dolzino seznama (pri tem ne uposteva glave seznama)
		int length()
		{
			int counter;
			LinkedListElement el;
			
			counter = 0;
			
			//zacnemo pri elementu za glavo seznama
			el = first.next;
			while (el != null)
			{
				counter++;
				el = el.next;
			}
			
			return counter;
		}
		
		//Funkcija insertNth vstavi element na n-to mesto v seznamu
		//(prvi element seznama, ki se nahaja takoj za glavo seznama, je na indeksu 0)
		boolean insertNth(Object obj, int n)
		{
			LinkedListElement el;
			
			//zacnemo pri glavi seznama
			el = first;
			
			//premaknemo se n-krat
			for (int i = 0; i < n; i++)
			{
				el = el.next;
				if (el == null)
					return false;
			}
			
			LinkedListElement newEl = new LinkedListElement(obj);
			newEl.next = el.next;
			el.next = newEl;
				
			if (last == null) //ce smo dodali edini element
				last = first;
			else if (last == el) //ce smo dodali predzadnji element
				last = last.next;
			else if (last.next == el) //ce smo dodali zadnji element
				last = el;
			//v ostalih primerih se kazalec "last" ne spreminja
				
			return true;
		}
		
		//Funkcija deleteNth izbrise element na n-tem mestu v seznamu
		//(prvi element seznama, ki se nahaja takoj za glavo seznama, je na indeksu 0)
		boolean deleteNth(int n)
		{
			LinkedListElement el, prev_el;
			
			//zacnemo pri glavi seznama
			prev_el = null;
			el = first;
			
			//premaknemo se n-krat
			for (int i = 0; i < n; i++)
			{
				prev_el = el;
				el = el.next;
				if (el == null)
					return false;
			}
			
			if (el.next != null)
			{
				//preden izlocimo element preverimo, ali je potrebno popraviti kazalec "last"
				if (last == el.next) //ce brisemo predzadnji element
					last = el;
				else if (last == el) //ce brišemo zadnji element
					last = prev_el;
					
				el.next = el.next.next;
				
				return true;
			}
			else
				return false;
		}
		
		LinkedListElement returnNth(int n) {
			LinkedListElement el, prev_el;
			
			//zacnemo pri glavi seznama
			prev_el = null;
			el = first;
			
			//premaknemo se n-krat
			for (int i = 0; i < n; i++)
			{
				prev_el = el;
				el = el.next;
				if (el == null)
					return null;
			}
			return el;
		}
		
		//Rekurzivna funkcija, ki obrne vrstni red elementov v seznamu
		void reverseRek(LinkedListElement el)
		{
			if (el == null)
				return;
			
			if (el.next == null)
			{
				first.next = el;
				last = first;
			}
			else
			{
				reverseRek(el.next);
				el.next = null;
				last = last.next;
				last.next = el;
			}
		}
		
		//Funkcija reverseRek klice rekurzivno funkcijo, ki obrne vrstni red elementov v seznamu 
		void reverseRek()
		{
			reverseRek(first.next);
		}
		
		//Funkcija removeDuplicates odstrani ponavljajoce se elemente v seznamu
		void removeDuplicates()
		{
			LinkedListElement curEl;
			
			curEl = first.next;
			while(curEl != null)
			{
				LinkedListElement tmpEl;
				
				//preveri ali se element curEl.next nahaja v seznamu 
				
				tmpEl = curEl;
				while (tmpEl.next != null)
				{
					if (tmpEl.next.element.equals(curEl.element)) 
					{
						//element je ze v seznamu, izlocimo ga
						tmpEl.next = tmpEl.next.next;	
					}
					else 
					{
						//element ni kopija, pustimo ga v seznamu
						tmpEl = tmpEl.next;
					}
				}
				
				curEl = curEl.next;
			}
			
			//ne pozabimo na kazalec "last"
			last = null;
			curEl = first;
			while(curEl.next != null)
			{
				if (curEl.next.next == null)
				{
					last = curEl;
					break;
				}
				else
					curEl = curEl.next;
			}
		}
	}

}
