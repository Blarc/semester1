class MapNode 
{
	public MapNode(Object key, Object value) 
	{
		this.key = key;
		this.value = value;
	}

	public Object getKey() 
	{
		return key;
	}

	public Object getValue() 
	{
		return value;
	}

	public void setKey(Object key) 
	{
		this.key = key;
	}

	public void setValue(Object value) 
	{
		this.value = value;
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof MapNode)
		{
			MapNode node = (MapNode)obj;
			return key.equals(node.key);
		}
		
		return false;
	}
	
	public String toString()
	{
		return "[" + key + ", " + value + "]";
	}
	
	private Object key;
	private Object value;
}



public class Map
{
	public static final int DEFAULT_SIZE = 7;

	Set[] table;
	
	public Map() 
	{
		makenull(DEFAULT_SIZE);
	}
	
	public Map(int size) 
	{
		makenull(size);
	}

	public void makenull() 
	{
		makenull(DEFAULT_SIZE);
	}
	
	public void makenull(int size) 
	{
		// create an empty table and initialize the linked lists
		table = new Set[size];
		
		for (int i = 0; i < table.length; i++) 
		{
			table[i] = new Set();
		}
	}

	private int hash(Object d) 
	{
		return Math.abs(d.hashCode()) % table.length;
	}

	public void assign(Object d, Object r) 
	{
		Set l = table[hash(d)];
		MapNode node = new MapNode(d, r);
		
		Object pos = l.locate(node);

		if (pos != null)
			((MapNode) l.retrieve((SetElement)pos)).setValue(r);
		else
			l.insert(node);
	}

	public Object compute(Object d) 
	{
		Set l = table[hash(d)];
		
		SetElement pos = l.locate(new MapNode(d, null));
		
		if (pos != null)
			return ((MapNode) l.retrieve(pos)).getValue();		

		return null;
	}

	public void delete(Object d) 
	{
		Set l = table[hash(d)];
		Object pos = l.locate(new MapNode(d, null));

		if (pos != null)
			l.delete((SetElement)pos);
	}

	public void rehash(int size)
	{
		Map m = new Map(size);
		
		for(int i = 0; i < table.length; i++)
			for(SetElement j = table[i].first(); !table[i].overEnd(j); j = table[i].next(j)) 
			{
				MapNode node = (MapNode)table[i].retrieve(j);
				m.assign(node.getKey(), node.getValue());
			}
		
		table = m.table;
	}
	
	public void print()
	{
		for (int i = 0; i < table.length; i++)
			table[i].print();
	}
}
