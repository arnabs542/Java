package Medium;


import java.util.HashMap;
import java.util.Map;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

/*
 * 	This is a array, tree and DFS question.
 * 	Given Post order and In order traversal of the binary tree, we have to recreate the binary tree itself.
 * 
 * 	We can take advantage of a property when we reverse the post order: Post order: ( Left > Right > Root )
 * 																		Post Reverse: ( Root > Right > Left )
 * 
 * 	Therefore, we can always assume that in Post order reversed, the first element is always going to be the Node to be created in the recursion call
 * 	We call the node to be created 'root'
 * 
 * 	Now, knowing the node to be created, we need to determine which other elements go into left subtree and which goes into right subtree
 * 	Using the In order traversal, this can be determined if we can determine the position of the root (Preferably using HashMap position querying?)
 * 		
 *	In order traversal: ( Left > Root > Right)
 *	Therefore:
 *						left subtree		right subtree
 *						<------------       ----------->
 *						[ e, e, e, e, ROOT, e, e, e, e ]
 *
 *
 *
 *	Therefore, the basic algorithm for each recursion call is basically:
 *
 *	buildTree(int inorderLeft, int inorderRight) {
 *		- Create new node based on current postorder ROOT pointer
 *		- Decrement postorder ROOT pointer
 *		- Obtain the index of currently created ROOT from inorder array
 *		- Further split the current inorder array range into left and right subtree range.
 *
 *		- Recurse to build right subtree
 *		- Recurse to build left subtree
 *
 *		- Return ROOT.
 *	}
 */

public class Construct_Binary_Tree_From_Inorder_And_Postorder_Traversal {

	int[] inorder, postorder;
	Map<Integer, Integer> inorderIndex;
	int postorderPointer;
	
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		this.inorder = inorder;
		this.postorder = postorder;
		inorderIndex = new HashMap<>();
		
		postorderPointer = postorder.length - 1;
		
		for (int i = 0; i < inorder.length; ++i)
			inorderIndex.put( inorder[i], i);
		
		return buildTree(0, inorder.length - 1);
    }
    
    
    private TreeNode buildTree(int inorderLeft, int inorderRight) {
    	if (inorderLeft > inorderRight) return null;

		// Create a new node based on current position in postorder array
		TreeNode node = new TreeNode(postorder[postorderPointer]);

		// Get position of the current element in the inorder array
		int split = inorderIndex.get(postorder[postorderPointer]);

		// Since we used this element already, decrement position
		--postorderPointer;
	
		// Build right subtree
		node.right = buildTree(split+1, inorderRight);
		// Build left subtree
		node.left = buildTree(inorderLeft, split-1);

		return node;
    }
	
}
