package Binary_Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/930/

/*
 * 	Binary tree Postorder Traversal is a traversal that follows the pattern ( Left > Right > Root )
 * 	
 * 	For iterative, if the topological order of the nodes being explored isn't important, then we would do a "reversed" pre order traversal.
 * 	Note that if we reverse Post order traversal, it becomes (Root > Right > Left). We could apply similar technique as in pre order traversal,
 * 	but reverse the order of nodes explored, and should prioritize right node first rather than left node
 * 
 * 	---------------------------------------------------------------------------------------------------------------------------------------
 * 
 * 	If the topological order of exploration must be considered, then we need to apply the pointer technique of In order traversal.
 * 	For a node current pointed, we should keep exploring the left subtree while keeping track of the parent node and the right node it contains.
 * 	
 * 	We do this by using a LIFO data structure (Stack). Add the right node to the stack first, then parent node, then only point to the node's left subtree.
 * 	So when we came back to the parent node, we can determine whether the right subtree is explored or not, by peeking at the stack and see if the head of stack
 * 	equals the parent node's right
 * 
 * 	If the right of the popped element from the stack does not match the stack's top, it either means two things:
 * 		-	The popped element does not have right subtree
 * 		-	We've already popped the right subtree before, and fully explored it
 * 	Either way, we can now explore the parent node, and assign the pointer to null, so that in next iteration it will pop from the stack again
 */

public class Binary_Tree_Postorder_Traversal {
	
//	public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> li = new LinkedList<>();
//        recurse(root, li);
//        
//        return li;
//    }
//	private static void recurse(TreeNode node, List<Integer> res) {
//		if (node == null) return;
//		
//		recurse(node.left, res);
//		recurse(node.right, res);
//		res.add( node.val);
//	}
	
	
//	public List<Integer> postorderTraversal(TreeNode root) {
//		List<Integer> li = new LinkedList<>();
//		Stack<TreeNode> stack = new Stack<>();
//		stack.push( root);
//		
//		while (!stack.isEmpty() ) {
//			TreeNode node = stack.pop();
//			if (node == null) continue;
//			
//			li.add(0, node.val);
//			stack.push( node.left );
//			stack.push( node.right );
//		}
//		
//		return li;
//	}
	
	
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> li = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		
		TreeNode curr = root;
		
		while (curr != null || !stack.isEmpty() ) {
			
			// While current is still not null, that means we could still travel to the left node. We should keep exploring the left node
			// while adding the parent node and the right node of parent node to the stack. Right node should be added to stack first so that parent node will
			// be popped first for verification
			while (curr != null) {
				if (curr.right != null) stack.push(curr.right);
				stack.push(curr);
				curr = curr.left;
			}
			//Now the curr is null. Pop the latest added node from the stack
			curr = stack.pop();
			
			// If this popped node is in fact, the parent node whose right node haven't been explored, then we shall pop the right node, put the parent node
			// in, and explore the right subtree by assigning the pointer to the right node
			if (!stack.isEmpty() && curr.right == stack.peek() ) {
				TreeNode right = stack.pop();
				stack.push( curr);
				curr = right;
			}
			//Otherwise, this node either has no right node, or has the right subtree explored already. Safely record it
			else {
				li.add(curr.val);
				curr = null;
			}
		}
		
		return li;
	}
	
	
	
	
}
