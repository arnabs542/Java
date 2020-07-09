package Data_Structures;

/*
 * 	A Binary search tree is a tree with each parent having no more than 2 children (at most 2, thats why called binary)
 * 	and ordered in a manner such that the left child is always less than the parent, and the right child is always greater than the parent
 * 			(Kind of like ascending order from left to right) -------> greater
 * 
 * 	The basis of implementing a binary search tree is starting with a node that possess the attributes:
 * 		-left child
 * 		-right child
 * 		-data that it stores
 * 
 * 	Watch out for unbalanced binary search tree: Eg: root is 1, which any data would end up on the right of the tree.
 * 
 * 	Notes: To handle duplicates, either set a default position, like All duplicates goes to the right/left, OR:
 * 		   implement a counter in the node which counts the number of duplicates in the tree
 * 
 *	
 */

public class Binary_Search_Tree<D extends Comparable<D> > {
	
	public Node<D> root;
	
	//Constructor//
	public Binary_Search_Tree(D data) {
		root = new Node<D>(data);
	}
	
	public Binary_Search_Tree() {
		root = null;
	}
	//End of Constructor//
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public int cardinality() {
		if (root == null) return 0;
		return root.cardinality();
	}
	
	public boolean isMember(D data) {
		if (root == null) return false;
		return root.isMember(data);
	}
	
	public int height() {
		if (root == null) return 0;
		return root.height();
	}
	
	public void add(D data) {
		if (root == null) {
			root = new Node<D>(data);
			return;
		}
		root.add(data);
	}
	
	public void addAll(D... data) {
		for (D d: data)
			add(d);
	}
	
	public boolean contains(D data) {
		if (root == null) return false;
		return root.contains(data);
	}
	
	public void printInOrder() {
		if (root == null) return;
		root.printInOrder();
		System.out.println();
	}
	
	
	//Private class of one node. Implements most of the functionality inside//
	public static class Node<D extends Comparable<D>> {
		public D data;
		public Node<D> left;
		public Node<D> right;
		
		public Node(D data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public int cardinality() {
			return 1 + ( (left == null)? 0: left.cardinality() ) + ( (right == null)? 0: right.cardinality() );
		}
		
		public boolean isMember(D data) {
			return (this.data.equals(data) ) 
					|| (left == null)? false: left.isMember(data) 
					|| (right == null)? false: right.isMember(data);
		}
		
		public int height() {
			if (left == null && right == null) return 0;
			else if (left == null) return 1 + right.height();
			else if (right == null) return 1 + left.height();
			else return 1 + Math.max( left.height(), right.height() );
		}
		
		public void add(D data) {
			if (data == null)
				return;
			if (data.compareTo(this.data) > 0) {
				if (right == null)
					right = new Node<D>(data);
				else
					right.add(data);
			}
			else if (data.compareTo(this.data) < 0) {
				if (left == null)
					left = new Node<D>(data);
				else 
					left.add(data);
			}
		}
		
		public boolean contains(D data) {
			if (left == null && right == null) return this.data.equals(data);
			else if (left == null) return this.data.equals(data) || right.contains(data);
			else if (right == null) return this.data.equals(data) || left.contains(data);
			else return this.data.equals(data) || right.contains(data) || left.contains(data);
		}
		
		public void printInOrder() {
			if (left != null) left.printInOrder();
			System.out.print(data + " ");
			if (right != null) right.printInOrder();
		}
		
		
		
	}
	//End of private Node class//
	
	
	public static void main(String[]args) {
		Binary_Search_Tree<Integer> bst = new Binary_Search_Tree<>();
		System.out.println(bst.isEmpty() );
		System.out.println(bst.height() );
		bst.addAll(20,16,30,12,18,72,40);
		bst.printInOrder();
		System.out.println(bst.height() );
		System.out.println(bst.cardinality() );
		System.out.println(bst.contains(40) );
		
	}
	
	
}
