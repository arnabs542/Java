package Data_Structures;

import java.util.LinkedList;
import java.util.Queue;


/*
 * 	Home made simple binary tree Generator which takes in an Integer array (Using wrapper class to allow for null values)
 * 	The array is the binary tree read using level order traversal, from left to right. Null values cannot be skipped and must be included
 * 
 * 	Eg: 1,2,3,5,7,null,1 creates the binary tree (Notice the null value isn't included at 3)
 * 
 * 						1
 * 					  /   \
 * 					2	   3
 * 				   / \      \
 * 				  5  7   	 1
 */

public class Binary_Tree_Generator {

	public static TreeNode generate(Integer[] arr ) {
		if (arr.length == 0 ) return null;
		
		TreeNode root = new TreeNode(arr[0] );
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		
		for (int i = 1; i < arr.length; i += 2) {
			TreeNode node = queue.poll();
			
			if (arr[i] != null) {
				node.left = new TreeNode( arr[i] );
				queue.offer( node.left);
			}
			if (arr[i + 1] != null) {
				node.right = new TreeNode( arr[i+1] );
				queue.offer( node.right);
			}
			
		}
		
		return root;
	}
	
}
