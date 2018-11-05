abstract class ListElement implements Comparable
{
	public abstract void print();
}

public class ListNode 
{
	ListElement element;
	ListNode next;
	
	public ListNode(ListElement obj, ListNode nextNode) 
	{
		element = obj;
		next = nextNode;
	}
}
