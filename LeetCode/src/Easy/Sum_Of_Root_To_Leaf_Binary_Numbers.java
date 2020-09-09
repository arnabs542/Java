package Easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import Binary_Tree.TreeNode;
import javafx.util.Pair;

/*
 * 	This is a DFS problem of Binary Trees
 * 
 * 	Remember, that given a tree, we could perform the following traversals:
 * 		>	Pre-order Traversal (root > left > right)
 * 		>	In-order Traversal	(left > root > right)
 * 		>	Post-order Traversal(left > right > root)
 * 
 * 	And since a tree is essentially a type of graph, we can perform the following search algorithm:
 * 		>	Depth first search (DFS)
 * 		>	Breadth first search (BFS)
 * 
 * 	Since the question requires us to form the numbers from the root to leaf, DFS is required as BFS can't let us directly know
 * 	what is the number extending from the root to leaf.
 * 
 * 	Note that on every node, we are essentially shifting the number to left by 1 bit, then adding the node's value.
 * 		>	Either we perform << Left shift operation of 1 bit, then add the node.val
 * 		>	Or we just multiply the integer value by 2, then add the node.val
 * 
 * 	The easiest way to implement is by using recursion DFS of pre-order traversal. On every node, perform left shifting, add node.val
 * 	and pass the value to child nodes. If it is a leaf node, directly return the value
 * 
 * 	To implement iteratively, we have to mimic the recursive method: keeping the values associated with each node stored in the Stack
 * 	Note that Java recommends using the class ArrayDeque (of Interface Deque) for Stack and Queue operations instead of 
 * 	Stack and LinkedList itself
 * 	Instead of using one stack for the TreeNode and one stack for the values, use the Java's convenient class Pair from javafx.
 * 	It allows us to store a pair of key value.
 * 
 * 	=========================================================================================================
 * 
 * 	The harder way to solve is to using Binary Tree Morris Traversal Algorithm. For every node encountered, we check to see if it
 * 	contains a left subtree. If it does, once we traverse into the left subtree, we need a way to travel back to the parent node
 * 	so that we could explore the right subtree.
 * 	
 * 	This is done by establishing a link between the parent node and the left subtree's rightmost node. Find the predecessor node
 * 	(Left subtree's rightmost node) and connect the predecessor node's right to the parent node. After the link is established,
 * 	safely explore the parent node's left subtree.
 * 	
 * 	Once we got back to the parent node, the same function is going to be called again. The function shall discover there is a cycle
 * 	in the left subtree that connects us back to the parent node. In that case, it knows that the left subtree had been explored.
 * 	Therefore, disconnect the link and go to the right subtree
 * 
 * 	If there is no left subtree however, we shall go to right subtree directly, forgetting the parent node essentially.
 * 
 * 	To make the function suit the problem, the function to find the predecessor shall return some information:
 * 		>	The depth of the subtree, for backtracking purposes
 * 		>	Is the predecessor a leaf node? To determine if the value so far shall be added to result
 * 
 * 	Note that we have to use backtracking here. Once we traversed into the left subtree, when we go back to the parent node,
 * 	the value is already including the subtree binary bits. To backtrack, we need to perform right shift by the depth
 * 	
 * 	When we travel back to parent node, we may or may not be travelling back from a leaf node. A leaf node will have the property
 * 	of having no left subtree, and right subtree connects back to parent node (The morris temporary link).
 * 
 * 	Lastly, when travelling to the right subtree, we need to check also if the right subtree is a leaf node. If yes, add value to 
 * 	result
 * 	
 * 
 */

//https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

public class Sum_Of_Root_To_Leaf_Binary_Numbers {
	
	public int sumRootToLeaf(TreeNode root) {
//		return recursive(root, 0);
//      return iterative(root);
		return morris( root);
    }
	
	private static int recursive(TreeNode node, int val) {
		if (node == null) return 0;
		
		val = val * 2 + node.val;
		
		if (node.left == null && node.right == null) return val;
		else {
			return recursive(node.left, val) + recursive(node.right, val);
		}
	}
	
	
	//	Use ArrayDeque for queues and stack. It is said to be better / faster
	//	Instead of using two Deque (Stack), use Pair object in the ArrayDeque to store 2 values in one
	private static int iterative(TreeNode root) {
		
		Deque< Pair<TreeNode, Integer> > stack = new ArrayDeque<>();
		int res = 0;
		stack.push( new Pair<>(root, 0) );
		
		while ( !stack.isEmpty() ) {
			Pair<TreeNode, Integer> pair = stack.pop();
			TreeNode node = pair.getKey();
			int val = pair.getValue();
			
			val = val * 2 + node.val;
			
			if (node.left == null && node.right == null)
				res += val;
			else {
				if (node.left != null)
					stack.push( new Pair<>(node.left, val) );
				if (node.right != null)
					stack.push( new Pair<>(node.right, val) );
			}
		}
		
		return res;
	}
	
	
	
	
	
	private static int morris(TreeNode node) {
		int res = 0;
		int val = 0;
		
		while (node != null) {
			
			val <<= 1;
			val += node.val;
			
			if (node.left != null) {
				Pair<Boolean, Integer> link = morrisLink(node);
				if (link == null) {
					node = node.left;
				} else {
					val = (val - node.val) >> 1;
					
					if (link.getKey() ) {
						res += val;
					}
					
					val >>= link.getValue();
					node = node.right;
				}
			}
			else {
				if (node.right == null) {
					res += val;
				}
				node = node.right;
			}
			
		}
		
		return res;
		
	}
	
	
	//	Returns a pair. Boolean indicates if it is a leaf, and integer is the the depth of the tree, for backtrackng
	private static Pair<Boolean, Integer> morrisLink(TreeNode node) {
		TreeNode curr = node.left;
		int depth = 1;
		
		while (curr.right != null && curr.right != node) {
			curr = curr.right;
			depth++;
		}
		
		if (curr.right == node) {
			curr.right = null;
			return new Pair<>( curr.left == null, depth );
		}
		
		//	Otherwise just link it
		curr.right = node;
		return null;
	}

}
