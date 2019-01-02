
class BSTTree {
	@SuppressWarnings("rawtypes")
	
	class Node {
		
		Comparable val;
    	Node right;
    	Node left;
    	
		public Node(Comparable val) {
    		this.val = val;
    		this.right = null;
    		this.left = null;
    	}
    }
    
    class Tree {
    	Node root;
    	
    	public Tree() {
    		makenull();
    	}
    	
    	void makenull() {
    		root = null;
    	}
    	
		private Node insert(Node node, Comparable v)
    	{
    		if (node == null)
    			node = new Node(v);
    		else if (v.compareTo(node.val) < 0)
    			node.left = insert(node.left, v);
    		else if (v.compareTo(node.val) > 0)
    			node.right = insert(node.right, v);
    		else
    			;//element je ze v drevesu, ne naredimo nicesar
    		return node;
    	}
    	
    	public void insert(Comparable v) {
    		root = insert(root, v);
    	}
    }
}