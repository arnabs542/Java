package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/unique-binary-search-trees-ii/
/*
 * 	This is a Recursion, (Optional)- DP problem with memoization
 * 
 * 	This problem can be first solved using recursion because the input is small enough to contain O(N!)
 * 
 * 	Given initial range [1,N], we could attempt all the nodes from 1 to N as our root node. 
 * 	Say we picked an integer i as our root. Then, the left subtree will surely contain the nodes from
 * 	1 to i-1, and the right subtree will contain nodes from i+1 to N.
 * 
 * 	Notice the above process is not restricted for 1 to N only. It can be used in cases where we need to
 * 	construct the nodes from any range of integer A..B where A <= B. This is the recurrence relation we
 * 	are looking for!
 * 
 * 	In each recursion call where we need to find unique BST ranging from A...B:
 * 		>	For each i selected as root node from A...B
 * 			- Query all unique BST in left subtree, ranging A.. i-1
 * 			- Query all unique BST in right subtree, ranging i+1...B
 * 			- Construct root node and append all possible combinations of left and right subtree.
 * 		>	Return the result as a list to upper level of recursion.
 * 
 * 	---------------------------
 * 
 * 	Notice how the problem can easily be memoized. Say our case N=8, then for example the range 1..2 will be
 * 	queried many times. We can avoid repeated computations using Dynamic Programming's memoization approach
 */

public class Unique_Binary_Search_Trees_II {
	
	
	// Memoization solution
	public List<TreeNode> generateTrees(int n) {
		Map<Integer, List<TreeNode>> memoi = new HashMap<>();
		return generateTrees(1, n, memoi);
    }
	
	private List<TreeNode> generateTrees(int from, int to, Map<Integer, List<TreeNode>> memoi) {
		// Encode the range [from,to] as a two digit integer.
		int index = from * 10 + to;
		if (memoi.containsKey(index)) return memoi.get(index);
		
		List<TreeNode> result = new ArrayList<>();
		if (from > to) {
			result.add(null);
			return result;
		};
		
		// Select one node in range to be the root, then recurse for left and right subtree
		for (int i = from; i <= to; ++i) {
			List<TreeNode> leftSubtrees = generateTrees(from, i-1, memoi);
			List<TreeNode> rightSubtrees = generateTrees(i+1, to, memoi);
			
			for (TreeNode l: leftSubtrees) {
				for (TreeNode r: rightSubtrees) {
					TreeNode root = new TreeNode(i);
					root.left = l;
					root.right = r;
					result.add(root);
				}
			}
		}
		memoi.put(index, result);
		return result;
	}
	
}
