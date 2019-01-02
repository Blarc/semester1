package treeDrawing;

public class Node {
	Comparable val;
	Node right;
	Node left;
	
	public Node(Comparable val) {
		this.val = val;
		this.right = null;
		this.left = null;
	}
}
