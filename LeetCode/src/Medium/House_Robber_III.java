package Medium;
import Binary_Tree.TreeNode;

//https://leetcode.com/problems/house-robber-iii/

/*
 * 	This is a Binary Tree, Depth First Search Problem with a little Dynamic Programming element in it.
 * 
 * 	Binary Tree itself is a recursive data structure. We should be using recursion here.
 * 
 * 	Now, at each node, we have 2 choices:
 * 		>	Rob the node.
 * 		>	Not rob the current node.
 * 	And we essentially have to return the maximum profit that will gained by robbing this tree.
 * 
 *  If we dont want to rob this node, then we can rob the children nodes. Children node recursion will return the maximum
 *  profit that can be gained by robbing them and its children. Therefore we'll just add the 2 profits and return.
 *  
 * 	If we want to rob this node, that means the children node are locked. We must only at most rob the grandchildren
 *  nodes. We cannot rob the direct children node. Since the children nodes know about the maximum profit when robbing
 *  grandchildren, perhaps we can retrieve that maximum profit information from child node recursion itself?
 *  
 *  Therefore, a recursion call should return 2 values: 
 *  	>	Max profit with current node robbed		-	Node.val + children's NoRob value
 *  	>	Max profit without current node robbed	-	children's Rob value OR children's NoRob value
 *  
 *  Remember that in max profit without current node robbed, we can choose to rob the direct children, or NOT rob
 *  the direct children. See case:
 *  
 *  			(4)
 *  		  (1)
 *  		(2)
 *  	  (3)
 *  
 *  In this tree, max profit is 7 obtained by (4) and (3). See that in node (1),
 *  	>	It should return rob current node value of 4
 *  	>	However for NOT rob curent node value, it should return 3 as well, not direct children of 2. Therefore it
 *  		should be MAX( children rob, children not rob )
 *  
 */

public class House_Robber_III {
	
	public int rob(TreeNode root) {
		int[] res = recurse(root);
		
		return Math.max( res[0] , res[1] );
	}
	
	/*
		THe recursive algorithm
		Will return an array, where
			>	First element is the max profit with robbing the child node
			>	Second element is the max profit without robbing the child node, which is max of robbing and not robbing
	 */
	private int[] recurse(TreeNode node) {
		if (node == null) return new int[] {0,0};
		
		int[] left = recurse(node.left);
		int[] right = recurse(node.right);
		
		return new int[] {
			node.val + left[1] + right[1],
			Math.max( left[0], left[1] ) + Math.max( right[0] , right[1] )
		};
	}
	
}
