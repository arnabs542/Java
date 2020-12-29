package Medium;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/

/*
 * 	This is a Tree, Depth first search problem (With possible mix of bit manipulation)
 * 
 * 	My idea is that, first we have to realize how a possible palindrome is made:
 * 	
 * 	Since a palindrome is split into two equal parts (with possibly one extra character in middle), where frequency of one
 *  character in left side must equal to its frequency in right side, Therefore each character must be of even frequency,
 *  with exception we can contain AT MOST one character with odd frequency, where the character is put at exact middle
 *  
 *  --------------------------------
 *  
 *  Therefore, while traversing the tree using DFS, the frequency of the node's value has to be increased, so it switches
 *  from being odd to even and vice versa. We record that into the boolean array.
 *  
 *  Then, if this node is a leaf node, check if the path from root node until current leaf node can form a valid
 *  palindrome or not.
 *  
 *  Recurse on the left and right subtree. The returned value has to be added to the result
 *  
 *  Then we revert the record of this current node, similar to backtracking. Then we can safely return to upper level
 *  
 *  --------------------------------
 *  
 *  Instead of using a boolean array, notice that we only need to use 9 bits to represent odd or even. Therefore,
 *  we can use bit manipulation instead of boolean array here.
 */

public class Pseudo_Palindromic_Paths_in_a_Binary_Tree {
	
	public int pseudoPalindromicPaths (TreeNode root) {
		boolean[] isOdd = new boolean[10];
		return recurse(root, isOdd);
	}
	
	private int recurse(TreeNode node, boolean[] isOdd ) {
		if (node == null) return 0;
        
		//	Include the current node into counting
		isOdd[node.val] = !isOdd[node.val];
        
		//	This is a leaf node. Check if the path from root to current node forms palindrome
		int res = 0;
		if (node.left == null && node.right == null) 
			res = checkPalindrome(isOdd)? 1: 0;
		
		//	Recurse on left subtree and right subtree
		res += recurse(node.left, isOdd) + recurse(node.right, isOdd);
		
		//	Undo this path before returning to upper level of the tree.
		isOdd[node.val] = !isOdd[node.val];
		
		return res;
	}
	
	private boolean checkPalindrome(boolean[] isOdd) {
		int sum = 0;
		for (int i = 1; i <= 9; ++i) sum += isOdd[i]? 1: 0;
		return sum <= 1;
	}
	
	
	
	
	
	
	public int pseudoPalindromicPaths2 (TreeNode root) {
		int isOddMask = 0;
		return recurse2(root, isOddMask);
	}
	private int recurse2(TreeNode node, int isOddMask ) {
		
		if (node == null) return 0;
        
		int res = 0;
		//	Include the current node into counting
		isOddMask ^= (1 << node.val);
        
		//	This is a leaf node. Check for possibly palindrome path
		if (node.left == null && node.right == null) 
			res = checkPalindrome2(isOddMask)? 1: 0;
		
		//	Recurse on left subtree and right subtree
		res += recurse2(node.left, isOddMask) + recurse2(node.right, isOddMask);
		
		//	Revert the path
		isOddMask ^= (1 << node.val);
		return res;
	}
	
	private boolean checkPalindrome2(int isOddMask) {
		// While isOddMask is not 0, and the Least Significant Bit is not 1
		while (isOddMask != 0 && (isOddMask & 1) != 1)
			isOddMask >>= 1;
		//Return if isOddMask, after shifted one place to right, is 0 or not
		return isOddMask >> 1 == 0;
	}
}
