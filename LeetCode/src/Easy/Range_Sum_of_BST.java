package Easy;

import java.util.LinkedList;
import java.util.Stack;

//https://leetcode.com/problems/range-sum-of-bst/

public class Range_Sum_of_BST {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) return 0;
		
		if (root.val >= R) {
			return (root.val == R)? root.val: 0 + rangeSumBST(root.left, L, R);
		}
		else if (root.val <= L) {
			return (root.val == L)? root.val: 0 + rangeSumBST(root.right, L, R);
		}
		else {
			return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
		}
	}
	
	public int rangeSumBSTStack(TreeNode root, int L, int R) {
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);
		int sum = 0;
		
		while (!stack.isEmpty() ) {
			TreeNode node = stack.pop();
			if (node == null) continue;
			
			if (node.val >= R) {
				sum += (node.val == R)? R: 0;
				stack.push(node.left);
			}
			else if (node.val <= L) {
				sum += (node.val == L)? L: 0;
				stack.push(node.right);
			}
			else {
				sum += node.val;
				stack.push(node.right);
				stack.push(node.left);
			}
		}
		return sum;
	}
	
	public int rangeSumBSTQueue(TreeNode root, int L, int R) {
		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		int sum = 0;
		
		while (!list.isEmpty() ) {
			TreeNode node = list.poll();
			if (node == null) continue;
			
			if (node.val >= R) {
				sum += (node.val == R)? R: 0;
				list.add(node.left);
			}
			else if (node.val <= L) {
				sum += (node.val == L)? L: 0;
				list.add(node.right);
			}
			else {
				sum += node.val;
				list.add(node.left);
				list.add(node.right);
			}
		}
		return sum;
	}
	
}
