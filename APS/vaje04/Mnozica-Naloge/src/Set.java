class SetElement
{
	Object element;
	SetElement next;

	SetElement()
	{
		element = null;
		next = null;
	}
}

public class Set 
{
	private SetElement first;
	
	public Set() 
	{
		makenull();
	}
	
	public void makenull()
	{
		// kazalec first kaze na glavo seznama
		first = new SetElement();
	}
	
	public SetElement first()
	{
		return first;
	}
	
	public SetElement next(SetElement pos)
	{
		return pos.next;
	}
	
	public boolean overEnd(SetElement pos)
	{
		if (pos.next == null)
			return true;
		else
			return false;
	}
	
	public boolean empty()
	{
		return first.next == null;
	}
	
	public Object retrieve(SetElement pos)
	{
		return pos.next.element;
	}
	
	public void print()
	{
		System.out.print("{");
		for (SetElement iter = first(); !overEnd(iter); iter = next(iter))
		{
			System.out.print(retrieve(iter));
			if (!overEnd(next(iter)))
				System.out.print(", ");
		}
		System.out.println("}");
	}
	
	
	public void insert(Object obj) 
	{
		// nov element vstavimo samo, ce ga ni med obstojecimi elementi mnozice
		if (locate(obj) == null) {
			SetElement nov = new SetElement();
			nov.element = obj;
			nov.next = first.next;
			first.next = nov;
		}
		
	}
	
	public void delete(SetElement pos)
	{
		// odstranimo element na poziciji pos (pozor, zaradi glave seznama so polozaji zamaknjeni!)
		pos.next = pos.next.next;
	}
	
	public SetElement locate(Object obj)
	{
		// sprehodimo se cez seznam elementov in preverimo enakost (uporabimo metodo equals)
		//
		// ce element najdemo, vrnemo njegov polozaj (pozor, zaradi glave seznama so polozaji zamaknjeni)
		// sicer vrnemo null
		
		for (SetElement i = first(); !overEnd(i); i = next(i)) {
			if (obj.equals(retrieve(i))) {
				return i;
			}
		}
		
		return null;
	}
	
	public void union(Set a)
	{
		// brez podvajanja dodaj vse elemente iz mnozice a
		for (SetElement i = a.first(); !overEnd(i); i = next(i)) {
			insert(retrieve(i));
		}
	}
	
	public void intersection(Set a)
	{
		// odstrani vse elemente, ki se ne nahajajo tudi v mnozici a
		for (SetElement i = a.first(); !overEnd(i); i = next(i)) {
			if (locate(i) == null) {
				delete(locate(retrieve(i)));
			}
		}
	}
}
