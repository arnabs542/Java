package Medium;

//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/536/week-3-may-15th-may-21st/3335/

/*
 * 
 * 	A question based on binary search tree, with requirements of Depth First Search
 * 	
 * 	Key to realise is that binary search tree can be seen in ascending order if we do In-order DFS (left -> parent -> right)
 * 	therefore the smallest element must always be in the leftmost (and lower-most) node, followed by its parent, followed by the right node etc...
 * 
 * 	We may not need to build an separate array or similar structure to find the kth smallest element, just simple counting down of k will do.
 * 	However, if need to build a system where it optimally knows where the kth element is when insert and delete operation is done, we have
 * 	to use a seperate data structure for that, like doubly linked list
 */

public class KstSmallestElementInABST {
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
	
	int kthMin = Integer.MIN_VALUE;
	
	public int kthSmallest(TreeNode root, int k) {
		search(root, k);
		return kthMin;
    }
	
	public int search(TreeNode node, int k) {
		if (node == null || k <= 0) return k;
		
		k = search(node.left, k);
		if (k > 0 && node.val > kthMin) {
			k--;
			kthMin = node.val;
		}
		k = search(node.right, k);
		
		return k;
	}
}
