package Easy;
import java.util.ArrayDeque;
import java.util.Deque;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/increasing-order-search-tree/

/*
 * 	This is a Binary Search Tree DFS (Inorder traversal) Problem. Surprisingly, it seems to be easier implemented
 * 	iterative than recursively, using Stack.
 * 
 * 	Given Binary Search Tree, we want to reorder them so that root node is minimum node and the right node points to
 * 	next minimum larger node. Remember this is like finding sorted list in BST, thus inorder traversal algorithm comes
 * 	into mind.
 * 
 * 	Using Stack, we would place the nodes to be explored later. Why we would want to explore a node later? because there
 * 	is a smaller node at left subtree.
 * 	Therefore, when facing a node:
 * 	
 * 		If the node has left subtree:
 * 			Push current node into Stack
 * 			Push left node into Stack so it is explored first before current node
 * 			Remove current node's left pointer so it no longer points to left subtree to prevent revisiting
 * 
 * 		Now, otherwise the node has no left subtree:
 * 			Append current node to the tail of resulting 'list'
 * 			If the node has a right subtree, push right node into Stack
 * 
 * 	And that's it!
 * 
 * 	==============================================================================================
 * 
 * 	THe recursion algorithm is just like Stack implementation above. Each recursion call we pass in the tail node so the node
 * 	can be easily append to it.
 * 
 * 	Each recursion call should return the updated pointer so it can be easily updated in the parent call.
 * 
 * 	Therefore, in each recursion call,
 * 			If the node passed is null, return the tail pointer untouched
 * 			Otherwise, first recurse on the left subtree. Once returned the updated pointer, set the current call's
 * 			tail pointer to the updated one
 * 			Then, append the current node to the tail's right. Move the tail to point to current node.
 * 			Then, recurse on right subtree. Since it returns the updated pointer, return the recursion return value.
 */

public class Increasing_Order_Search_Tree {
	
	//	Iterative Stack solution
	public TreeNode increasingBST(TreeNode root) {
		TreeNode head = new TreeNode();
		TreeNode tail = head;
		
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        while (!stack.isEmpty() ) {
        	TreeNode pop = stack.pop();
        	
        	if (pop.left != null) {
        		stack.push(pop);
        		stack.push(pop.left);
        		pop.left = null;
        	} else {
        		tail.right = pop;
        		tail = pop;
        		
        		if (pop.right != null) stack.push(pop.right);
        	}
        }
        return head.right;
    }
	
	
	//	Recursive solution, passing tail reference all around
	public TreeNode increasingBST2(TreeNode root) {
		TreeNode head = new TreeNode();
		recurse(head, root);
		return head.right;
	}
	private TreeNode recurse(TreeNode tail, TreeNode node) {
		if (node == null) return tail;
		
        tail = recurse(tail, node.left);
        node.left = null;
		
		tail.right = node;
		tail = tail.right;
		
		TreeNode right = node.right;
        node.right = null;
		return recurse(tail, right);
	}

}
