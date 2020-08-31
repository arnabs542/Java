package Data_Structures;

/*
 * 	A Binary search tree is a tree with each parent having no more than 2 children (at most 2, thats why called binary)
 * 	and ordered in a manner such that the left child is always less than the parent, and the right child is always greater than 
 * 	parent
 * 			(Kind of like ascending order from left to right) -------> greater
 * 
 * 	The basis of implementing a binary search tree is with a node that possess the attributes:
 * 		-left child
 * 		-right child
 * 		-data that it stores
 * 
 * 	Watch out for unbalanced binary search tree: Eg: root is 1, which any data would end up on the right of the tree.
 * 
 * 	Notes: To handle duplicates, either set a default position, like All duplicates goes to the right/left, OR:
 * 		   implement a counter in the node which counts the number of duplicates in the tree
 * 		   In the below implementation, I set it so it doesn't allow duplicates
 * 
 * 	The most complex operation on the binary search tree is deletion. It will be split into 3 cases:
 * 		>	Deletion node has no subtree
 * 		>	Deletion node has either left or right subtree
 * 		>	Deletion node has both left or right subtree (Most complex)
 * 
 * 	FOR MORE ACCURATE ALGORITHM WHICH ADJUST POINTER INSTEAD OF COPY DATA, SEE LEETCODE -> MEDIUM -> DELETE_NODE_IN_BST
 * 
 * 		For case 1: Deletion node has no subtree
 * 			This is easiest. Since it has no subtree, we can just disconnect it from the parent node
 * 
 * 		For case 2: Deletion node has either left or right subtree
 * 			We need to connect the deletion node's left or right subtree directly to its parent. This won't effect the order of the
 * 			BST.
 * 
 * 		For case 3: Deletion node has both left and right subtree
 * 			We can't just straight connect any of subtree to the parent. It disrupts the natural order property of BST.
 * 			We have to find a 'successor'. For this, we can choose either of the two values:
 * 
 * 				> The closest, larger value compared to deletion node (Smallest difference between values)
 * 				  This is found at the right subtree's leftmost node
 * 				> The closest, smaller value compared to deletion node (Smallest difference between values)
 * 				  This is found at the left subtree's rightmost node
 * 
 * 			We call this node 'replacement node'. Then, we would have to copy the contents of the replacement node into the
 * 			deletion node itself, and delete the original replacement node from left or right subtree
 * 			By selecting the closest value to the deletion node, we will not disrupt the natural property of BST itself
 * 			
 * 			Notice that since the replacement node is the leftmost or rightmost node in the tree, deleting it will just end up in
 * 			Case 1 or case 2, that is - No subtree or only 1 subtree. Deleting it will be easy
 * 
 *	
 */

public class BinarySearchTree {
	
	private TreeNode root = null;
	
	private class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	
	//------------------------------
	//		INSERTION
	//--------------------------------
	public boolean add(int val) {
		if (root == null) {
			root = new TreeNode(val);
			return true;
		}
		else return add(root, val);
	}
	
	private boolean add(TreeNode node, int val) {
		if (node.val == val) return false; 		//This BST does not allow duplicates
		//If the value should go into the left subtree (node val greater than val)
		if (node.val > val) {
			if (node.left == null) {
				node.left = new TreeNode(val);
				return true;
			}
			return add(node.left, val);
		}
		//If the value should go into the right subtree (node val lesser than val)
		else {
			if (node.right == null) {
				node.right = new TreeNode(val);
				return true;
			}
			return add(node.right, val);
		}
	}
	
	//------------------------------
	//		SEARCH
	//--------------------------------
	public boolean search(int val) {
		return search(root, val);
	}
	
	private boolean search(TreeNode node, int val) {
		if (node == null) return false;
		if (node.val == val) return true;
		if (node.val > val) return search(node.left, val);
		return search(node.right, val);
	}
	
	
	//------------------------------
	//		DELETION
	//--------------------------------
	public boolean delete(int val) {
		//Edge case: If the tree is empty
		if (root == null) return false;
		
		//Edge case: If the value to remove is the root node itself, then the root itself has to be reassigned
		if (root.val == val) {
			//If root is the only element
			if (root.left == null && root.right == null) {
				root = null;
			}
			//Root only has right subtree
			else if (root.left == null) root = root.right;
			//Root only has left subtree
			else if (root.right == null) root = root.left;
			//Else the root has left and right subtree. Root value has to be replaced
			else {
				int replace = getRightMinimum(root);
				delete(root, replace);
				root.val = replace;
			}
			return true;
		}
		//Otherwise execute normal deletion algorithm
		return delete(root, val);
	}
	
	private boolean delete(TreeNode node, int val) {
		//Recording parent is necessary to disconnect the link to the deleted nodes
		TreeNode parent = null;
		TreeNode curr = node;
		
		//While the node is yet to be found, search down the tree
		while (curr != null && curr.val != val) {
			parent = curr;
			if (curr.val > val) curr = curr.left;
			else curr = curr.right;
		}
		
		//Edge case: The node to be deleted is not present
		if (curr == null) return false;
		
		//Case 1: Deletion node is leaf node, then just disconnect it from parent
		if (curr.left == null && curr.right == null) {
			if (parent.val > val) parent.left = null;
			else parent.right = null;
		}
		//Case 2: Deletion node has either left or right subtree. Just connect parent to that subtree
		else if (curr.left == null) {
			if (parent.val > val) parent.left = curr.right;
			else parent.right = curr.right;
		}
		else if (curr.right == null) {
			if (parent.val > val) parent.left = curr.left;
			else parent.right = curr.left;
		}
		//Case 3: Deletion node has both left and right subtree. Find the next successor, and replace the deletion node value
		//		  with it, while delete the duplicated node itself
		else {
			int toReplace = getRightMinimum(curr);
			delete(curr, toReplace);
			curr.val = toReplace;
		}
		return true;
	}
	
	
	//Given a node, this will return the minimum value of this node's right subtree (Next successor)
	private int getRightMinimum(TreeNode node) {
		TreeNode curr = node.right;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr.val;
	}
	
	
	
	public static void main(String[]args) {
		BinarySearchTree tree = new BinarySearchTree();
		
		for (int i = 10; i < 20; i += 3 ) {
			tree.add(i);
		}
		for (int i = 0; i < 10; i += 2 ) {
			tree.add(i);
		}
		for (int i = 1; i < 10; i += 2) {
			tree.add(i);
		}
		
		
		for (int i = -5; i < 0; i ++ ) {
			System.out.println(i + " " + tree.delete(i) );
		}
		
		for (int i = 0; i < 20; i ++ ) {
			System.out.println(i + " " + tree.search(i) );
		}
		
	}
	
	
}
