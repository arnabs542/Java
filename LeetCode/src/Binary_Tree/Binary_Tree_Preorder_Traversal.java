package Binary_Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/928/

/*
 * 	Binary tree pre order traversal is a traversal method which follows the order ( Root > Left > Right )
 *  Implementing in recursion is more easier than implementing in iteratively. Also, it can be implemented iteratively using Morris Algorithm
 *  
 *  To implement iteratively, we just need to pop the nodes out of the stack, and push the node right first, then only left. Since it is a LIFO structure,
 *  we can guarantee that the left will be always the first to be popped out
 */

public class Binary_Tree_Preorder_Traversal {
	
//	public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> res = new LinkedList<>();
//        recurse(root, res);
//        return res;
//    }
//	private static void recurse(TreeNode node, List<Integer> res) {
//		if (node == null) return;
//		
//		res.add(node.val);
//		recurse(node.left, res);
//		recurse(node.right, res);
//	}
	
	public List<Integer> preorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		
		List<Integer> res = new LinkedList<>();
		
		stack.push(root);
		
		while (!stack.isEmpty() ) {
			TreeNode pop = stack.pop();
			if (pop == null) continue;
			
			res.add(pop.val);
			stack.add(pop.right);
			stack.add(pop.left);
		}
		
		return res;
	}
	
}
