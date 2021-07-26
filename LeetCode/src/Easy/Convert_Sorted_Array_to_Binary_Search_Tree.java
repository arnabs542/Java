package Easy;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/submissions/
/*
 * This is a recursion, binary search tree, Divide and conquer problem.
 * 
 * Given a sorted array, if we want to make a balanced binary tree from it, we must pick the middle element as our root,
 * so that the left subarray and right subarray are almost the same sized (At most differ by size 1).
 * 
 * Then, we want the subtree at left and right subarray, which is the exact same subproblem as above
 */

public class Convert_Sorted_Array_to_Binary_Search_Tree {
	
	public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }
	private TreeNode sortedArrayToBST(int[] nums, int from, int to) {
		if (from == to)
			return new TreeNode(nums[from]);
		if (from > to) return null;
		
		int mid = from + (to - from) / 2;
		TreeNode root = new TreeNode(nums[mid]);
	
		root.left = sortedArrayToBST(nums, from, mid-1);
		root.right = sortedArrayToBST(nums, mid+1, to);
		return root;
	}
}
