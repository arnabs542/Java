package Medium;

//https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

/*
 * 	I could simply start over inserting a new node from the root every time, but it would end up in O(n2) time
 * 
 */

public class ConstructBinarySearchTreeFromPreorderTransversal {
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode() {}
	      TreeNode(int val) { this.val = val; }
	      TreeNode(int val, TreeNode left, TreeNode right) {
	          this.val = val;
	          this.left = left;
	          this.right = right;
	      }
	}
	
//	public TreeNode bstFromPreorder(int[] preorder) {
//		if (preorder.length == 0) return null;
//		
//        TreeNode root = new TreeNode(preorder[0] );
//        for (int i = 1; i < preorder.length; i ++ ) {
//        	insert(root, preorder[i]);
//        }
//        return root;
//        
//    }
//	
//	void insert(TreeNode node, int val) {
//		if (val < node.val) {
//			if (node.left == null) node.left = new TreeNode(val);
//			else insert(node.left, val);
//		}
//		else {
//			if (node.right == null) node.right = new TreeNode(val);
//			else insert(node.right, val);
//		}
//	}
	
	// This approach is the most simplistic of insert function, but would take O(n2) if the tree is uneven, or skewed to one side only

	/*
	 * 	Instead, this approach keeps track of the current node it is processing. Everytime a new node is inserted, the current variable is set
	 * 	to point to the newly created node and see if the value can be fitted there.
	 * 	If the next value can't be inserted in either left or right branch, it simply returns to the parent to determine if the 
	 * 	new value fits there at the parent
	 * 
	 * 	This approach is better and has time complexity of O(n) instead
	 */
	 public TreeNode bstFromPreorder(int[] preorder) {
		 if (preorder.length <= 0) return null;
		 
		 TreeNode root = new TreeNode(preorder[0]);
		 if (preorder.length == 1) return root;
		 
		 recurse(root, preorder, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);		
		 
		 return root;
	 }
	 
	 public int recurse(TreeNode node, int[] preorder, int position, int left, int right) {
		 
		 //Base case check: If the position is already out of bounds, or if the value is not meant for this node
		 if (position >= preorder.length || preorder[position] < left || preorder[position] > right) 
			 return position;
		 
		 int toInsert = preorder[position];
		 
		 //Left tree check
		 if (toInsert < node.val && toInsert > left) {
			 node.left = new TreeNode(toInsert);
			 position = recurse(node.left, preorder, position+1, left, node.val);
		 }
		 
		 //Base case check again
		 if (position >= preorder.length || preorder[position] < left || preorder[position] > right) 
			 return position;
		 
		 toInsert = preorder[position];
		 
		 //Right tree check
		 if (toInsert > node.val && toInsert < right) {
			 node.right = new TreeNode(toInsert);
			 position = recurse(node.right, preorder, position+1, node.val, right);
		 }
		 
		 return position;
	 }
	
}
