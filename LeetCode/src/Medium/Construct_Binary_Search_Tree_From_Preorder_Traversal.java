package Medium;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
/*
 * 	This is a BST recursive problem. 
 * 
 *	The brute force solution would be to simply construct a root node, and perform BST insertion for each element. This takes O(N^2) in the worst
 *	case.
 * 
 *	The advantage of preorder traversal is, we always know the first element we get is a root node: The element at index 0 is the root of tree.
 *	
 *	Now, we constructed the root node. We have to take care of its left subtree and right subtree, but how?
 *	The nodes in left subtree must be strictly have values lesser than the root node, while the right subtree must be strictly have values greater
 *	than the root node, so we can use recursion to help us in constructing these child nodes.
 * 
 *	But, once the left subtree is constructed, how do we know if some element in the array is already used to construct left subtree when its time
 *	to construct right subtree? For this, we could use some global variable 'index' to keep track of our progress in the array. 
 *	Every recursion, we pass in the bounds to perform check if the node could fit and should be constructed or not.
 *
 * 
 *	If you want to do this iteratively, the idea is to use a Stack, which when used in this problem, is MONOTONIC. When you try to insert a new node
 *	and the latest node at the top of stack doesn't fit, you can pop the stack to go back to the upper level - parent node.
 */

public class Construct_Binary_Search_Tree_From_Preorder_Traversal {
	
	// Recursive solution with global variable index and arr.
	int index;
	int[] preorder;

	TreeNode bstFromPreorder(int[] preorder) {
		index = 0;
		this.preorder = preorder;

		return constructTreeNode(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private TreeNode constructTreeNode(int leftbound, int rightbound) {
		if (index >= preorder.length) return null;
		int n = preorder[index];
		if (n < leftbound || n > rightbound) return null;

		TreeNode curr = new TreeNode(n);
		++index;

		curr.left = constructTreeNode(leftbound, n - 1);
		curr.right = constructTreeNode(n + 1, rightbound);
		return curr;
	}
}
