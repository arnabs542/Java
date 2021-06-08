package Medium;
import java.util.HashMap;
import java.util.Map;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
/*
 * 	This is a Tree, DFS problem.
 * 
 * 	Problems that goes with tree, are often better solved with recursion. Similar case applies here.
 * 	First, remind that
 * 		>	Preorder: Root > Left > Right
 * 		>	Inorder: Left > Root > Right
 * 
 * 	When we first receive both arrays, we must realize the fact that:
 * 		>	In preorder, first element must be the root of whatever tree we are building.
 * 		>	Then, knowing the root node, we can check where the current root node is located in the
 * 			inorder traversal.
 * 
 * 			Once located, new information will be exposed to us by looking in Inorder traversal:
 * 
 * 					Left subtree nodes			 Right subtree nodes
 * 					<------------------- ( ROOT ) -------------------->
 * 
 * 	Therefore, we have successfully divided the problem into smaller subproblems to be solved by our
 * 	imiginary recursion function!
 * 	
 * 	In my solution, I pass the left and right limit of both inorder and preorder traversal arrays to
 * 	indicate the range of nodes that are included in the subtree.
 * 
 * 	However, turns out we actually don't need to impose the right limit on preorder array, due to
 * 	the recursive function only checks for the first element and nothing else. Also we can simply
 * 	check whether it is invalid left and right limit for inorder traversal to determine base case. 
 */

public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		final int len = preorder.length;
		//	Construct mapper for nodeVal -> inorder index
		Map<Integer, Integer> lookup = new HashMap<>();
		for (int i = 0; i < len; ++i)
			lookup.put(inorder[i], i);
		
		return buildTreeNode(preorder, inorder, 0, len-1, 0, len-1, lookup);
    }
	
	
	//	Recursive function to build a Treenode
	private TreeNode buildTreeNode(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight, Map<Integer, Integer> lookup) {
		//	Base case goes here.
		if (inLeft > inRight) return null;
		
		//	We know that the first element to the preLeft in preorder[] is the root node.
		TreeNode root = new TreeNode( preorder[preLeft] );
		int rootIdxInInorder = lookup.get( preorder[preLeft] );
		int leftSubtreeSize = rootIdxInInorder - inLeft;
		
		//	Left subtree. Preorder spans from current idx +1, until the end of left subtree size.
		//	Inorder spans from left limit until the index of current node in inorder traversal, minus 1
		root.left = buildTreeNode(preorder, inorder, preLeft+1, preLeft+leftSubtreeSize, inLeft, rootIdxInInorder-1, lookup);
		//	Right subtree. Preorder spans from the end of left subtree size, until right limit
		//	Inorder spans from index of current node in inorder traversal +1, until right limit
		root.right = buildTreeNode(preorder, inorder, preLeft+leftSubtreeSize+1, preRight, rootIdxInInorder+1, inRight, lookup);
		
		return root;
	}
	
}
