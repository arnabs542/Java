package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/path-sum-iii/

/*
 * 	This is a Tree Traversal and recursion problem. We need to find number of paths that will sum up to a target value,
 * 	but not need the path to start with root; It can basically start anywhere!
 * 
 * 	The first brute force solution is both O(N^2) in time and space. For every recursion, we will obtain a list of prefixes,
 * 	with the inclusion of the node value itself. We will return the number of counts where in the list, value is equal to target
 * 
 * 	Eg:	1 -> 2 -> 3 -> 4, Upon reaching node 4, the list shall become [ 10, 9, 7, 4 ].
 * 		10 is the sum if the root is from (1) onwards
 * 		9 is the sum if the root is from (2) onwards
 * 		7 is the sum if the root is from (3) onwards
 * 		4 is the sum if the root is the current node (4) itself.
 * 	We will count the number of target in the list itself, and also call upon recursion on the child nodes left and right, add that
 * 	to the count, and return it.
 * 	
 * 	------------------------------------------------------------------------------------
 * 
 * 	Another brute force with better space complexity will be taking full form of recursion.
 * 	Notice for every node introduced, it can either take on the sum passed by its parent, or just start by itself
 * 	Therefore we will use 2 recursive functions:
 * 		>	One which will start a new path by the node itself, so will pass into the another recursive function which
 * 			finds the no of paths if it was started by this node. Also, it will tell the children to start a brand new path
 * 			by themselves too
 * 		>	Another one takes on the value passed by parent, and continue passing the sum into the childrens
 * 
 * 	
 * 	--------------------------------------------------------------------------------------
 * 
 * 	The best optimized solution of O(N) in both space and time is using HashMap to record the past prefixes.
 * 	The key of the HashMap is the sum of segments of the ancestor nodes. See:
 * 
 * 	1 -> 2 -> 3 -> 4
 * 
 * 	The hashmap upon reaching Node 4 shall be [1, 3, 6, 10]
 * 		
 * 		1	Means that one part of segment sums up to 1 (Which is root node, 1 itself)
 * 		3	Means that one part of segment sums up to 3 (Which is 1 -> 2 ) 
 * 		6	Means that one part of segment sums up to 6 (Which is 1 -> 2 -> 3 )
 * 		10 	Means that the sum of this paths is 10		(Including current node)
 * 
 * 	Every path formed by including the current node, but deleting one of above segments, will be a valid path (Although not to target)
 * 	
 * 	Eg:		At node (4), which we have a overall sum of 10 (4+3+2+1), we can delete:
 * 		>	Do nothing, it is the full path	
 * 		>	Delete 1, which forms path of 2 -> 3 -> 4 (Sum of 9)
 * 		>	Delete 3, which forms path of 3 -> 4	  (Sum of 7)
 * 		> 	Delete 6, which forms path of 4			  (Sum of 4)
 * 
 * 	Using this property, when we are introduced to a new node, while having the overall sum of the full path, we can
 * 	see that the equation:
 * 	(Assuming that the path DOES have a valid segment to delete!)
 * 
 * 		(OVERALL SUM INCLUDING THIS NODE VAL) - (SEGMENT TO DELETE) = target, 	Rearrange:
 * 		(OVERALL SUM) - target = (SEGMENT TO DELETE)
 * 
 * 	Therefore, by using the overall sum minus the target value, we check in the prefix sum hashmap if there is indeed a segment
 * 	that we can just delete to form a satisfying, valid path. (Remember that it also should keep track of HOW MANY segments
 * 	form this segment sum)
 * 
 * 	There is a backtracking element in this solution as we need to remove the prefix sum (Count - 1) once we are done with this node.
 * 
 * 
 * 
 */

import Binary_Tree.TreeNode;

public class Path_Sum_III {

//https://leetcode.com/problems/path-sum-iii/submissions/
/*
*	This is a DFS (and kinda backtracking) problem.
* 
*	On recursion of every node, we can have a 'queue' kinda data structure that keep track of every node we have encountered along the path.
*	Then, we can iterate through the path to perform a "Suffix sum" to determine if there is any path that sums up to target, that must end at
*	current node.
* 
*	However, the time complexity of this algorithm goes up to O(ND) where N = number of nodes, D = max depth of tree, worst case O(N^2) if is linear
* 
*	---------------------
* 
*	(Solution)
* 
*	Instead, if we keep a reference (map) to prefix sum -> frequency, what can it do?
*	A prefix sum keep track of available paths from root node to current node. On recursion, we know the sum from root node to current node.
*	Now, if we want to know if there is any intermediate paths, we know we have to eliminate some path that starts with root to somewhere, which
*	we'll look it up in the map in O(1) time.
* 
*	The time complexity is thus reduced to O(N).
*/

	public int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, 1);
		return recurse(root, 0, sum, prefix);
    }
	
	private int recurse(TreeNode node, int sum, int target, Map<Integer, Integer> prefix) {
		if (node == null ) return 0;
		
		sum += node.val;
		
		int res = (prefix.getOrDefault( sum - target, 0 ) );
		
		prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);
		res += recurse(node.left, sum, target, prefix) + recurse(node.right, sum, target, prefix);
		prefix.put(sum, prefix.get(sum) - 1);
		
		return res;
	}
	
	
}
