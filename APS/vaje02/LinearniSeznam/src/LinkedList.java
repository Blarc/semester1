
public class LinkedList
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
		//drzimo se implementacije iz knjige:
		//po dogovoru je na zacetku glava seznama (header)
		first = new LinkedListElement(null, null);
		last = null;
	}
	
	//Funkcija addLast doda nov element na konec seznama
	public void addLast(Object obj)
	{
		//najprej naredimo nov element
		LinkedListElement nov = new LinkedListElement(obj);
		//ustrezno ga povezemo s koncem verige obstojecih elementov
		if (last != null) {
			last = last.next;
			last.next = nov;
		} else {
			first.next = nov;
			last = first;
		}
		//po potrebi posodobimo kazalca "first" in "last"
		
	}
	
	//Funkcija write izpise elemente seznama
	public void write()
	{
		//zacnemo pri elementu za glavo seznama
		//sprehodimo se po elementih do konca seznama
		//in izpisemo vrednost vsakega elementa
		LinkedListElement el;
		el = first.next;
		while (el != null) {
			System.out.print(el.element + ", ");
			el = el.next;
		}
		System.out.println();
		
		
		//za kontrolo lahko izpisemo tudi vrednosti kazalcev "first" in "last"
	}
	
	//Funkcija addFirst doda nov element na prvo mesto v seznamu (takoj za glavo seznama)
	void addFirst(Object obj)
	{
		//najprej naredimo nov element
		LinkedListElement nov = new LinkedListElement(obj);
		//ustrezno ga povezemo z glavo seznama
		if (last == null) {
			last = first;
		} else if (first == last) {
			nov.next = first.next;
			last = nov;
		} else {
			nov.next = first.next;
		}
		first.next = nov;
		//po potrebi posodobimo kazalca "first" in "last"
	}
	
	//Funkcija length() vrne dolzino seznama (pri tem ne uposteva glave seznama)
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
	
	int lengthRek(LinkedListElement el) {
		if (el == null) {
			return 0;
		}
		return lengthRek(el.next) + 1;
	}
	//Funkcija lengthRek() klice rekurzivno funkcijo za izracun dolzine seznama
	int lengthRek()
	{
		// pomagajte si z dodatno funkcijo int lengthRek(LinkedListElement el), ki izracuna
		// dolzino seznama za opazovanim elementom ter pristeje 1
		return lengthRek(first.next);
	}
	
	//Funkcija insertNth vstavi element na n-to mesto v seznamu
	//(prvi element seznama, ki se nahaja takoj za glavo seznama, je na indeksu 0)
	boolean insertNth(Object obj, int n)
	{
		//zacnemo pri glavi seznama
		LinkedListElement el = first;
		//sprehodimo se po elementih dokler ne pridemo do zeljenega mesta
		for (int i = 0; i < n; i++) {
			if (el.next == null) {
				return false;
			}
			
			el = el.next;
		}
		
		
		LinkedListElement nov = new LinkedListElement(obj, el.next);
		el.next = nov;
		
		if(nov.next == null) {
			last = el;
		}
		return true;
		// ce je polozaj veljaven
		//   naredimo nov element
		//   ustrezno ga povezemo v verigo elementov
		//   po potrebi posodobimo kazalec "last"
		//   vrnemo true
		// sicer
		//   vrnemo false
		
	}
	
	//Funkcija deleteNth izbrise element na n-tem mestu v seznamu
	//(prvi element seznama, ki se nahaja takoj za glavo seznama, je na indeksu 0)
	boolean deleteNth(int n)
	{
		
		//zacnemo pri glavi seznama
		LinkedListElement el = first;
		LinkedListElement prev = null;
		//sprehodimo se po elementih dokler ne pridemo do zeljenega mesta
		for (int i = 0; i < n; i++) {
			if (el.next == null) {
				return false;
			}
			prev = el;
			el = el.next;
		}
		if (el.next == null) {
			return false;
		}
		if (el.next == last.next) {
			last = el;
		}
		if (el == last) {
			last = prev;
		}
		
		el.next = el.next.next;
		// ce je polozaj veljaven
		//   ustrezno prevezemo elemente seznama tako, da ciljni element izlocimo iz verige
		//   po potrebi posodobimo kazalec "last"
		//   vrnemo true
		// sicer
		//   vrnemo false
		
		
		return true;
	}
	
	//Funkcija reverse obrne vrstni red elementov v seznamu (pri tem ignorira glavo seznama)
	void reverse()
	{
		
		LinkedListElement a = null;
		LinkedListElement b = null;
		LinkedListElement c = null;
		
		if (first.next != null) {
			a = first.next;
			if (a.next != null) {
				b = a.next;
				if (b.next != null) {
					c = b.next;
					last = b;
					while (c.next != null) {
						b.next = a;
						a = b;
						b = c;
						c = c.next;
					}
					b.next = a;
					c.next = b;
					last.next.next = null;
					first.next = c;
					
				} else {
					last = b;
					a.next = null;
					last.next = a;
					first.next = last;
				}
			}
		}
		
		
		
		
		//ne pozabimo na posodobitev kazalca "last"!
	}
	
	void reverseRek(LinkedListElement el) {
		if(el.next.next == null) {
			first.next = el.next;
			el.next.next = el;
			return;
		}
		reverseRek(el.next);
		el.next.next = el;
	}
	//Funkcija reverseRek klice rekurzivno funkcijo, ki obrne vrstni red elementov v seznamu
	void reverseRek()
	{
		if (first.next != null) {
			if (first.next.next != null) {
				last = first.next.next;
				reverseRek(first.next);
				last.next.next = null;
			}
		}
		// pomagajte si z dodatno funkcijo void reverseRek(LinkedListElement el), ki
		// obrne del seznama za opazovanim elementom, nato ta element doda na konec (obrnjenega) seznama
	}
	
	//Funkcija removeDuplicates odstrani ponavljajoce se elemente v seznamu
	void removeDuplicates()
	{
		LinkedListElement e1 = first;
		LinkedListElement e2 = null;
		LinkedListElement temp = null;
		int length = length();
		for (int i = 0; i < length; i++) {
			e1 = e1.next;
			e2 = e1;
			for (int j = 1+i; j < length; j++) {
				temp = e2;
				e2 = e2.next;
				if (e2.element == e1.element) {
					if (e2 == last) {
						last = temp;
					}
					e2 = temp;
					deleteNth(j);
					length--;
					j--;
				}
			}
		}
		//ne pozabimo na posodobitev kazalca "last"!
	}
}
