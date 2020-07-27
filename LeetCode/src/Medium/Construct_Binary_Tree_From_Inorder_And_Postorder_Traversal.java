package Medium;


import java.util.HashMap;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

/*
 * 	This is a challenging, array, tree and DFS question.
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
 *	Now, If we found out the ROOT does not has left or right subtree (By checking the index of ROOT in In Order Traversal and the left or right limit of the array)
 *	Then we surely can easily limit both the In Order Traversal and the Post Order Traversal.
 *	
 *	If the ROOT index is at left limit, that means it has right subtree only. For the Post order traversal, since the first element is the ROOT itself, the
 *	other elements must be at right side of it, and the next ROOT is the next one in Post order traversal (Remember We reversed Post order to have ROOT in front?)
 *
 *	If the ROOT index is at right limit, that means it has left subtree only. For the Post order traversal, since the first element is the ROOT itself, the
 *	other elements must be at right side of it as well, and the next ROOT is the next in Post order traversal 
 *
 *	If the ROOT index is in between, then we have to find among the left elements, which of it is the left subtree's ROOT? Iterate through the left elements,
 *	search for the index in Post order traversal, and choose the one with the lowest index (Leftmost index, since Post order reversed prioritize ROOT at left)
 *	We call this index LEFT ROOT INDEX	
 *
 *	Knowing this, for the left subtree, we limit the In Order to the left side of the ROOT index, and the Post Order from the LEFT ROOT INDEX to right limit
 *				  for the right subtree, we limit the In Order to the right side of the ROOT index, and the Post Order from the left limit until LEFT ROOT INDEX - 1
 */

public class Construct_Binary_Tree_From_Inorder_And_Postorder_Traversal {

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder.length <= 0) return null;
		
    	int len = postorder.length;
        int[] revpost = new int[len];

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        
        for (int i = 0; i < len ; i ++ ) {
        	revpost[i] = postorder[ len - i - 1];
        	
        	map1.put( inorder[i] , i );
        	map2.put( revpost[i], i );
        }
    
        return create(inorder, 0, len - 1, revpost, 0, len - 1, map1, map2);
    }
    
    
    private static TreeNode create(int[] inorder, int l1, int r1, 
    		int[] postOrdR, int l2, int r2, 
    		HashMap<Integer, Integer> map1, HashMap<Integer,Integer> map2) 
    {
    	int rootVal = postOrdR[l2];
    	TreeNode root = new TreeNode(rootVal) ;
    	
    	if (l1 == r1 ) {
    		return root;
    	}
    	
    	int rootIdx = map1.get(rootVal);
    	
    	//The root Index in array 1 is at left limit. No left subtree for this node.
    	if (rootIdx == l1 ) {
    		root.right = create(inorder, l1+1, r1, postOrdR, l2+1, r2, map1, map2);
    	}
    	//The root Index in array 1 is at right limit. No right subtree for this node
    	else if (rootIdx == r1) {
    		root.left = create(inorder, l1, r1-1, postOrdR, l2+1, r2, map1, map2);
    	}
    	//Else it has a left subtree and right subtree
    	else {
    		int leftLim = Integer.MAX_VALUE;
    		for (int i = l1; i < rootIdx; i ++ ) {
    			int val = inorder[i];
    			leftLim = Math.min( map2.get(val) , leftLim);
    		}
    		
    		root.left = create(inorder, l1, rootIdx-1, postOrdR, leftLim, r2, map1, map2);
    		root.right = create(inorder, rootIdx+1, r1, postOrdR, l2+1, leftLim - 1, map1, map2);
    	}
    	
    	return root;
    }
	
}
