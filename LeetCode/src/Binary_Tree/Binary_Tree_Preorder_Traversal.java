package Binary_Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/928/

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
