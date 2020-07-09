package Medium;

import java.util.LinkedList;

//https://leetcode.com/problems/sum-root-to-leaf-numbers/

/*
 * 	A BFS/DFS problem. Given every time i travel down one layer into the tree, then I would shift the value before one place to the left,
 * 	and then add the value of the current node into the value. This is done by simply multiply by 10 because
 * 		Eg: 53, if i want to append 1, then i multiply 10,
 * 			53 = 5(10) + 3, ---------> 53(10) = 5(10)(10) + 3(10) = 5(100) = 3(10) = 530
 * 			then i add 1 to become 531
 * 
 * 	After that, check if the node is a leaf. If yes, then return value. Otherwise, continue the process into the search
 */

public class Sum_Root_To_Leaf_Numbers {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) {this.val = val;};
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	public int sumNumbers(TreeNode root) {
		if (root == null) return 0;
		return recurse(root, 0);
	}
	private int recurse(TreeNode node, int val) {
		val *= 10;
		val += node.val;
		
		if (node.left == null && node.right == null) return val;
		else {
			int sum = (node.left == null)? 0: recurse(node.left, val);
			sum += (node.right == null)? 0: recurse(node.right, val);
			return sum;
		}
	}
	
	
	public int sumNumbersBST(TreeNode root) {
		if (root == null) return 0;
		int sum = 0;
		
		LinkedList<Object[]> queue = new LinkedList<>();
		queue.add(new Object[] {root, 0});
		
		while (!queue.isEmpty() ) {
			Object[] pair = queue.poll();
			TreeNode node = (TreeNode) pair[0];
			int val = (Integer) pair[1];
			
			val *= 10;
			val += node.val;
			
			if (node.left == null && node.right == null) sum += val;
			else {
				if (node.left != null) queue.offer(new Object[] {node.left, val} );
				if (node.right != null) queue.offer(new Object[] {node.right, val} );
			}
		}
		return sum;
	}
}
