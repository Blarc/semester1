//brisanje korena
//1. možnost, najmanjši v desnem delu, brišemo, napišemo namesto korena
//2. možnost, največjega v levem delu, brišemo, napišemo namesto korena

//height
// h = max(L, R) + 1;

//isBalanced
// |h(L) - h(R)| <= 1;
//visina levega in desnega poddreves se ne razlikuje za vec kot ena

//predecessor(Comparable v)
//največji izmed vseh ključev v drevesu ki so manjsi od podanega v-ja

//successor(Comparable v)
//najmanjsi izmed vseh ključev v drevesu ki so vecji od podanega v-ja



class BSTNode {
	Comparable value;
	BSTNode left;
	BSTNode right;
	
	public BSTNode(Comparable val) {
		value = val;
		left = null;
		right = null;
	}
}

class BSTree {
	BSTNode root;
	
	public BSTree() {
		makenull();
	}
	
	// Funkcija naredi prazno drevo
	void makenull() {
		root = null;
	}
	
	// Rekurzivna funkcija za vstavljanje elementa v list drevesa
	private BSTNode insertRek(BSTNode node, Comparable v)
	{
		if (node == null)
			node = new BSTNode(v);
		else if (v.compareTo(node.value) < 0)
			node.left = insertRek(node.left, v);
		else if (v.compareTo(node.value) > 0)
			node.right = insertRek(node.right, v);
		else
			;//element je ze v drevesu, ne naredimo nicesar
		return node;
	}
	
	// Rekurzivna funkcija za vstavljanje elementa v list drevesa
	public void insertRek(Comparable v) {
		root = insertRek(root, v);
	}
	
	// Rekurzivna funkcija za izpis poddrevesa s podanim korenom
	private void write(BSTNode node) {
		if (node != null) {
			System.out.print("(");
			write(node.left);
			System.out.print(", " + node.value + ", ");
			write(node.right);
			System.out.print(")");
		}
		else
			System.out.print("null");
	}
	
	// Funkcija za izpis drevesa
	public void write() {
		write(root);
		System.out.println();
	}
	
	// Rekurzivna funkcija, ki preveri, ali se podani element nahaja v podanem poddrevesu
	private boolean memberRek(BSTNode node, Comparable v) {
		if (node == null)
			return false;
		else if (v.compareTo(node.value) == 0)
			return true;
		else if (v.compareTo(node.value) < 0)
			return memberRek(node.left, v);
		else
			return memberRek(node.right, v);
	}
	
	// Funkcija preveri, ali se podani element nahaja v drevesu
	public boolean memberRek(Comparable v) {
		return memberRek(root, v);
	}
	
	
	//Samostojno delo

	protected BSTNode prune(BSTNode node) {
		if (node == null) return null;
		if ((node.left == null) && (node.right == null)) return null;
		node.left = prune(node.left);
		node.right = prune(node.right);
		return node;
	}
	
	// Funkcija, ki poreze liste drevesa
	public void prune()
	{
		root = prune(root);
	}
	
	// Funkcija, ki vrne visino drevesa
	//height
	// h = max(L, R) + 1;
	public int height(BSTNode node) {
		if (node == null) {
			return 0;
		}
		int l = height(node.left);
		int r = height(node.right);
		
		return ((l > r) ? l : r) + 1;
	}
	
	public int height() {
		return height(root);
	}
	
	public int abs(int a) {
		return (a > 0) ? a : -a;
	}
	
	// Funkcija, ki preveri, ali je drevo uravnoveseno
	public boolean isBalanced(BSTNode node) {
		if (node == null) {
			return true;
		}
		
		if (abs(height(node.left) - height(node.right)) <= 1) {
			return isBalanced(node.left) && isBalanced(node.right);
			 
		}
		else {
			return false;
		}
		
	}
	
	public boolean isBalanced() {
		// |h(L) - h(R)| <= 1;
		//visina levega in desnega poddreves se ne razlikuje za vec kot ena
		return isBalanced(root);
	}
			
	// Funkcija, ki vrne stevilo elementov v drevesu
	public int numOfElements(BSTNode node) {
		if (node == null) {
			return 0;
		}
		
		return numOfElements(node.left) + numOfElements(node.right) + 1; 
	}
	
	public int numOfElements() {
		// VSTAVITE SVOJO KODO
		
		return numOfElements(root);
	}
	
	// Iterativna funkcija za vstavljanje elementa v list drevesa
	public void insertIter(Comparable v) {
		// VSTAVITE SVOJO KODO
		if (root == null) {
			root = new BSTNode(v);
			return;
		}
		
		BSTNode iter = root;
		
		while (iter != null) {
			if (v.compareTo(iter.value) > 0) {
				if (iter.right != null) {
					iter = iter.right;
				}
				else {
					iter.right = new BSTNode(v);
					break;
				}
			}
			else {
				if (iter.left != null) {
					iter = iter.left;
				}
				else {
					iter.left = new BSTNode(v);
					break;
				}
			}
		}	
	}
	
	// Iterativna funkcija, ki preveri, ali se podani element nahaja v drevesu
	public boolean memberIter(Comparable v) {
		// VSTAVITE SVOJO KODO
		BSTNode iter = root;
		
		while (iter != null) {
			if (iter.value == v) {
				return true;
			}
			
			else if (v.compareTo(iter.value) > 0) {
				iter = iter.right;
			}
			else {
				iter = iter.left;
			}
		}
		return false;
	}
	
	// Funkcija, ki izpise elemente drevesa v padajocem vrstnem redu
	public void descending() {
		// VSTAVITE SVOJO KODO
	}
	
	// Funkcija, ki vrne kazalec na element drevesa s prvo manjso vrednostjo od podanega elementa
	public BSTNode predecessor(Comparable v) {
		// VSTAVITE SVOJO KODO
		
		return null;
	}
	
	// Funkcija, ki vrne kazalec na element drevesa s prvo vecjo vrednostjo od podanega elementa
	public BSTNode successor(Comparable v) {
		// VSTAVITE SVOJO KODO
		
		return null;
	}
	
	// Iterativna funkcija za brisanje elementa iz drevesa
	public void deleteIter(Comparable v) {
		// VSTAVITE SVOJO KODO
	}
}	

