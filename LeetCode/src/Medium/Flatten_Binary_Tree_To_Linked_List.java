package Medium;
import java.util.ArrayDeque;
import java.util.Deque;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
/*
 * 	This is a tree, Depth first search - Preorder traversal problem
 * 
 * 	Multiple solutions available. See below for a bit of explaination
 */

public class Flatten_Binary_Tree_To_Linked_List {
	
	//	Initial attempt - Top down, flatten left then right idea
	//	Recursive function takes in parent and child and
	//	returns the lastly connected node
	//	will connect parent with child in right subtree
	public void flatten(TreeNode root) {
		recurse( new TreeNode(), root);
    }
	
	private TreeNode recurse(TreeNode parent, TreeNode child) {
		if (child == null) return parent;
		TreeNode right = child.right;
		
		parent.right = child;
		TreeNode last = recurse(child, child.left);
		child.left = null;
		return recurse(last, right);
	}
	
	
	
	//	Recursive solution - Right - Left- Root reversed postorder
	//	Idea is that the recursion will flatten right subtree first, returning
	//	the head of flattened subtree. Then, pass in the head of right flattened to flatten
	//	left subtree, then finally connect with the root node.
	public void flatten2(TreeNode root) {
		recurse2(root, null);
	}
	public TreeNode recurse2(TreeNode root, TreeNode prev) {
		if (root == null) return prev;
		prev = recurse2(root.right, prev);
		prev = recurse2(root.left, prev);
		root.right = prev;
		root.left = null;
		return root;
	}
	
	
	//	Iterative method using Stack. This one is less intuitive.
	//	For each node on stack, push right node, then left node. Now
	//	the top of stack should be left node if exists, else right node.
	//	If none, it will be previous node's right or left node
	public void flatten3(TreeNode root) {
		if (root == null) return;
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			TreeNode top = stack.pop();
			if (top.right != null) stack.push(top.right);
			if (top.left != null) stack.push(top.left);
			if (!stack.isEmpty()) top.right = stack.peek();
			top.left = null;
		}
	}
	
	
	//	Iterative method in O(1) space using Morris Traversal algorithm inspired idea.
	//	The idea is, say a node doesn't have left subtree. This means it is already nicely
	//	linked list!
	//	But if it has a left subtree, grab the last node called predecessor which is the last
	//	if I perform preorder traversal, and connect it to the current node's right, which is
	//	what it is supposed to connect.
	//	Then move current node's left to right. Set left to null and you're done.
	public void flatten4(TreeNode root) {
		while (root != null) {
			if (root.left != null) {
				TreeNode predecessor = root.left;
				while (predecessor.right != null)
					predecessor = predecessor.right;
				predecessor.right = root.right;
				root.right = root.left;
				root.left = null;
			}
			root = root.right;
		}
	}
}
