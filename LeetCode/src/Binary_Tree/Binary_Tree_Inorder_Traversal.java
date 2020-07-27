package Binary_Tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/929/

/*
 * 	Binary Tree in order traversal is a traversal method which follows the order ( Left > Root > Right )
 * 	It can be implemented recursively, and also iteratively (which is a bit more challenging)
 * 
 * 	To implement iteratively, we have to keep a pointer, that always prefer to go left.
 * 	Whenever the pointer's node is not null, we push this root node into the stack, and then point the pointer to the node's left
 * 
 * 	When it finally reaches null, then we shall pop the parent node, record it, and point the pointer to the node's right
 * 	Notice that the pointer will only point to the parent node once, and never point back to it again after, to prevent infinite loop
 * 
 * 	Notice that implementation using Morris Algorithm is also plausible
 */

public class Binary_Tree_Inorder_Traversal {

//	public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new LinkedList<>();
//        recursive(root, res);
//        
//        return res;
//    }
//	private static void recursive(TreeNode node, List<Integer> res) {
//		if (node == null) return;
//		
//		recursive(node.left, res);
//		res.add( node.val);
//		recursive(node.right, res);
//	}
	
	
	public List<Integer> inorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		List<Integer> res = new LinkedList<>();
		
		TreeNode node = root;
		
		while (true ) {
			if (node != null) {
				stack.add(node);
				node = node.left;
			}
			else if (!stack.isEmpty() ) {
				TreeNode pop = stack.pop();
				res.add( pop.val );
				node = pop.right;
			}
			else break;
		}
		
		return res;
		
	}
	
}
