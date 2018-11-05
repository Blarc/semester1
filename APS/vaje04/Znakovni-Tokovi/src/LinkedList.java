
public class LinkedList 
{
	protected ListNode first;
	
	public LinkedList() 
	{
		first = new ListNode(null, null);
	}
	
	public ListNode first()
	{
		return first;
	}
	
	public ListNode next(ListNode pos)
	{
		return pos.next;
	}
	
	public ListElement retrieve(ListNode pos)
	{
		return pos.next.element;
	}
	
	public boolean overEnd(ListNode pos)
	{
		if (pos.next == null)
			return true;
		else
			return false;
	}
	
	public void add(ListElement element) 
	{
		first.next = new ListNode(element, first.next);
	}
	
	public void print()
	{
		for (ListNode curNode = first(); !overEnd(curNode); curNode = next(curNode))
		{
			retrieve(curNode).print();
		}
	}
	
	public void update(ListNode pos, ListElement el)
	{
		pos.next.element = el;
	}
	
	public void sort()
	{
		//
		// NALOGA: implementirajte funkcijo "sort"
		//
		// Funkcija uredi elemente v seznamu.
		//
		// Upostevajte, da seznam vsebuje header element!
		// Za primerjavo med elementi seznama uporabljajte metodo compareTo.
		//
		
		for (ListNode i = first(); !overEnd(i); i = next(i)) {
			for (ListNode j = next(i); !overEnd(j); j = next(j)) {
				if (retrieve(i).compareTo(retrieve(j)) < 0) {
					ListElement tmp = retrieve(i);
					update(i, retrieve(j));
					update(j, tmp);
				}
			}
		}
	}
}
