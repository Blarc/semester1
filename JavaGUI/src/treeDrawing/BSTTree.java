package treeDrawing;

import treeDrawing.Node;

public class BSTTree {
	Node root;
	
	public BSTTree() {
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
