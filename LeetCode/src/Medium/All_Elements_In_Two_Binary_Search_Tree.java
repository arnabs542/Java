package Medium;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/all-elements-in-two-binary-search-trees/


/*
 * 	This is a traversal + sort problem
 * 
 * 	First of all, we know that the inorder traversal (Left -> Root -> Right) of an binary search tree is always in ascending order
 * 	(Sorted)
 * 	Knowing this property, if we perform an inorder traversal on each individual tree, we would get 2 sorted lists containing
 * 	all the elements in sorted order.
 * 	Now, we shall perform MERGE SORT to merge those 2 sorted arrays into one single sorted array.
 * 	Keep 2 pointers. Whoever value is smaller, gets added to the result array, and have its pointer move forward.
 * 	Note if one pointer reached the end, just add the remaining elements of another one
 * 
 * 	Time complexity is overall O(N + M), since we are traversing each element fixed number of times.
 * 	However, space complexity is O(N + M), since we will first obtain the inorder traversal result of both BST, which contains
 * 	all the elements in both BST.
 * 
 * 	---------------------------------------------------------------------------------------------------
 * 
 * 	Can we optimize the space?
 * 	The core idea to space optimization is that we should find a way to keep only the minimum element of both BST. Only when one
 * 	of the minimum element is appended into result array, we update it with the next minimum element. How?
 * 
 * 	Remember in a BST, the leftmost element is always the minimum one, then the parent node.
 * 	Therefore we could use a STACK to store the nodes of the left part of the tree (Meaning to ignore all the right subtrees)
 * 	
 * 	We would use a function which when passed in a node, will store the LEFT traversal of the tree starting from that node
 * 	into the stack.
 * 
 * 	Say we have tree 				(5)
 * 								(1)		(7)
 * 							(8)	  (2)	
 * 
 * 	Then we will push (5), go left, push (1), and go left, push (8). THat's it!
 * 
 * 	If we do this to both BST, we would have 2 Stacks, each having the LEFT traversal. Now, the top of stack contains the
 * 	minimum element of both the BST. Compare them, find the minimum one, and add it to the result.
 * 
 * 	Now, we have to update that one stack that gets popped, to have second minimum element at the top of stack.
 * 	This is done by calling the function of LEFT traversal once again, but pass the popped node's right child as the argument.
 * 	This shall update the whole stack to contain the second smallest node. (If exists).
 * 
 * 	This step needs to be done because the node's right contains nodes that are greater than the popped node, but SMALLER than
 * 	the PARENT node
 * 
 * 	With this, the space complexity shall be reduced down to O(log N + log M), whoever tree has the greatest height
 * 	(If we don't consider the result array into the space complexity)
 * 
 * 
 * 
 * 
 * 
 */

public class All_Elements_In_Two_Binary_Search_Tree {
	
	
	//	Inorder Traversal + Merge sort method. Not Space Optimized
	
//	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
//		List<Integer> tree1 = new LinkedList<>();
//		List<Integer> tree2 = new LinkedList<>();
//		
//		inorderTraversal(root1, tree1);
//		inorderTraversal(root2, tree2);
//		
//		System.out.println( tree1 );
//		System.out.println( tree2 );
//		
//		List<Integer> result = new LinkedList<>();
//		Iterator<Integer> it1 = tree1.iterator();
//		Iterator<Integer> it2 = tree2.iterator();
//		
//		int e1 = it1.hasNext()? it1.next(): Integer.MIN_VALUE;
//		int e2 = it2.hasNext()? it2.next(): Integer.MIN_VALUE;
//		
//		while (e1 != Integer.MIN_VALUE && e2 != Integer.MIN_VALUE ) {
//			if (e1 < e2) {
//				result.add(e1);
//				e1 = it1.hasNext()? it1.next(): Integer.MIN_VALUE;
//			} else {
//				result.add(e2);
//				e2 = it2.hasNext()? it2.next(): Integer.MIN_VALUE;
//			}
//		}
//		
//		while (e1 != Integer.MIN_VALUE) {
//			result.add(e1);
//			e1 = it1.hasNext()? it1.next(): Integer.MIN_VALUE;
//		}
//		while (e2 != Integer.MIN_VALUE) {
//			result.add(e2);
//			e2 = it2.hasNext()? it2.next(): Integer.MIN_VALUE;
//		}
//		return result;
//    }
//	
//	private static void inorderTraversal(TreeNode root, List<Integer> li) {
//		if (root == null) return;
//		
//		inorderTraversal(root.left, li);
//		li.add(root.val);
//		inorderTraversal(root.right, li);
//	}
	
	
	
	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stk1 = new Stack<>();
        Stack<TreeNode> stk2 = new Stack<>();
        
        List<Integer> res = new LinkedList<>();
        
        fillStackLeft(root1, stk1);
        fillStackLeft(root2, stk2);
        
        while ( !stk1.isEmpty() && !stk2.isEmpty() ) {
        	if (stk1.peek().val > stk2.peek().val ) {
        		TreeNode pop = stk2.pop();
        		res.add(pop.val);
        		fillStackLeft( pop.right , stk2 );
        	} else {
        		TreeNode pop = stk1.pop();
        		res.add(pop.val);
        		fillStackLeft( pop.right, stk1 );
        	}
        }
        
        while ( !stk1.isEmpty() ) {
        	TreeNode pop = stk1.pop();
    		res.add(pop.val);
    		fillStackLeft( pop.right, stk1 );
        }
        
        while ( !stk2.isEmpty() ) {
        	TreeNode pop = stk2.pop();
    		res.add(pop.val);
    		fillStackLeft( pop.right , stk2 );
        }
        
        return res;
        
    }
	
	private static void fillStackLeft(TreeNode node, Stack<TreeNode> stk) {
		while (node != null) {
			stk.push(node);
			node = node.left;
		}
	}
}
