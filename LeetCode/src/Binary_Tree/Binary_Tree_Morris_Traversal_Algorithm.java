package Binary_Tree;

import java.util.LinkedList;
import java.util.List;

/*
 * 	Talking about Binary tree traversal methods, there are a few types of it and can be implemented in either recursively or iteratively
 * 		>	Pre order traversal 	(Root - Left - Right)
 * 		>	In order traversal		(Left - Root - Right)
 * 		>	Post order traversal	(Left - Right - Root)
 * 	
 * 	All of which can be implemented recursively. For iterative method, we might need some help with data structures like Queue or Stack
 * 
 * 	However, Morris Traversal Algorithm is able to help us do pre order traversal and in order traversal without using stack memory (No recursion), and
 * 	taking up only O(1) space complexity.
 * 
 * 	The algorithm is as follows:	(Applies only to in order and pre order only)
 * 		>	For a given node, if it has no left subtree, then after recording the node value, the node can be completely forgotten (Because we are not going
 * 			to come back later)
 * 		>	If it however has a left subtree, then the left subtree must be visited first than the right subtree, and we need a way to come back to this node later
 * 			(To get the right subtree)
 * 		>	So the question is, how are we going to return to this node without having to record it in data structure? The key is to realize WHEN we will be
 * 			needed to come back to this node
 * 					The answer is within the left subtree of this node, after visiting the rightmost node
 * 
 * 		>	Therefore we could create a function to obtain the rightmost node (Call predecessor) of the left subtree of this node, and link that node's right back 
 * 			to this node. That way when we finished doing stuff with that last node on the left subtree, we would end up back in this current node again.
 * 		> 	To detect whether it is the first time we visited this node, or we are directed back to this node through the predecessor, we could modify the function
 * 			to find predecessor itself, to stop searching if it will loop back to the parent node (Also prevents infinite loop).
 * 			Now, if we find the predecessor, and found out that the predecessor's right is not null (In fact, it points back to the same node we're on), then
 * 			we know that we've fully explore the left subtree, and so we destroy the link, and continue explore the right subtree.
 * 
 * 
 */

//--------------------------------//

public class Binary_Tree_Morris_Traversal_Algorithm {

	public static void morrisInorderTraversal(TreeNode root) {
		TreeNode curr = root;
		
		while (curr != null) {
			
			//If the current node's left is null, then we can just record this node and completely forget about it. We wont come back again
			if (curr.left == null) {
				System.out.print(curr.val + " ");
				curr = curr.right;
			}
			//Else we must have a way to come back to this node again after reach the rightmost node
			else {
				//Find the current node's left's rightmost node
				TreeNode predessor = findPredecessor(curr);
				
				//If the link has yet to be established (Means we are first time reaching this current node
				if (predessor.right == null) {
					predessor.right = curr;
					curr = curr.left;
				}
				//This is not the first time we reach at this node. This means we had traversed the left subtree completely, and came back.
				//Therefore destroy the link, and record the node then traverse the right subtree.
				else {
					predessor.right = null;
					System.out.print(curr.val + " ");
					curr = curr.right;
				}
			}
			
		}
		System.out.println();
	}
	
	public static void morrisPreorderTraversal(TreeNode root) {
		TreeNode curr = root;
		
		while (curr != null ) {
			
			if (curr.left == null) {
				System.out.print(curr.val + " ");
				curr = curr.right;
			}
			else {
				TreeNode predessor = findPredecessor(curr);
				if (predessor.right == null) {
					System.out.print(curr.val + " ");
					predessor.right = curr;
					curr = curr.left;
				}
				else {
					predessor.right = null;
					curr = curr.right;
				}
			}
			
		}
		System.out.println();
	}
	
	public static List<Integer> morrisPostorderTraversal(TreeNode root) {
		List<Integer> li = new LinkedList<>();
		
		TreeNode curr = root;
		
		while (curr != null) {
			
			if (curr.right != null) {
				TreeNode predecessor = findRightPredecessor(curr);
				
				if (predecessor.left != null) {
					predecessor.left = null;
					curr = curr.left;
				}
				else {
					predecessor.left = curr;
					li.add(0, curr.val);
					curr = curr.right;
				}
			}
			else {
				li.add(0, curr.val);
				curr = curr.left;
			}
			
		}
		
		System.out.println(li);
		return li;
	}
	
	
	private static TreeNode findRightPredecessor(TreeNode node) {
		TreeNode curr = node.right;
		
		while (curr.left != null && curr.left != node) {
			curr = curr.left;
		}
		
		return curr;
	}
	
	private static TreeNode findPredecessor(TreeNode node) {
		//When this function is called, it must mean the node has a left node. We start from the node's left, and keep looking right node
		TreeNode curr = node.left;
		
		//While the node's right is not null, or it doesn't point back to the origin node (Which means the link back to node has been found before)
		while (curr.right != null && curr.right != node) {
			curr = curr.right;
		}
		
		//Return the predecessor, which is the rightmost node of the left subtree of parent. It may already have the link established, which requires checking
		//in the calling function
		return curr;
	}
	
	
	
	
	
	
}
