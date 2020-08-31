package Medium;

import Binary_Tree.TreeNode;

//https://leetcode.com/problems/delete-node-in-a-bst/

//	For introduction to deleting nodes in BST, see TOPICS -> DATA_STRUCTURES -> BINARYSEARCHTREE

public class Delete_Node_In_BST {
	
	public TreeNode deleteNode(TreeNode root, int key) {
        
		if (root == null) return null;
		
		//	If the node to delete is possibly located in the left subtree, then connect the result node to the root's left
		if (root.val > key) root.left = deleteNode(root.left, key);
		//	If the node to delete is possibly located in the right subtree, then connect the result node to the root's right 
		else if (root.val < key) root.right = deleteNode(root.right, key);
		//	The node to delete is this node. In this case we need to return successor, thereby deleting this node
		else {
			
			//	If this node has no left subtree, return the right subtree. Note that if right subtree is also null,
			//	it will return null, which is also correct
			if (root.left == null) return root.right;
			//	If this node has no right subtree, return the left subtree.
			else if (root.right == null) return root.left;
			//	This node to delete has both left and right subtree. Find the successor and model the successor to be exactly same
			//	as the node to delete.
			else {
				
				TreeNode successor = root.right, parent = null;
				
				//	Find the leftmost node, while recording its parent
				while (successor.left != null) {
					parent = successor;
					successor = successor.left;
				}
				
				//	Here, Remember that the successor is the least value GREATER than the node to be deleted.
				//	>	Root's left shall be connected to the successor's left
				//	>	Root's right shall be connected to the successor's right, but don't form a cycle 
				//		(By disconnecting the parent's left)
				//	>	To disconnect parent's left, use the successor's right. Successor's right is greater than successor itself,
				//		but less than the parent. Therefore connection is valid
				
				
				//	If parent is null, this means that the root's right is the successor!
				//	>	Connect the root's left to the successor left
				//	>	Return it
				if (parent == null) {
					successor.left = root.left;
					return successor;
				}
				
				//	Else there is a parent. Do the above steps:
				parent.left = successor.right;
				successor.left = root.left;
				successor.right = root.right;
				return successor;
				
			}
			
		}
		
		return root;		//	This is for the case that the root is not the node to be deleted
    }
	
}
