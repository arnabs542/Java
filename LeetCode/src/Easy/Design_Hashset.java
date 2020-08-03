package Easy;


//https://leetcode.com/problems/design-hashset/

/*
 * 	HashSet can be implemented in various ways.
 */


class BinarySearchTree {
	
	private TreeNode root = null;
	
	private class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	
	//------------------------------
	//		INSERTION
	//--------------------------------
	public boolean add(int val) {
		if (root == null) {
			root = new TreeNode(val);
			return true;
		}
		else return add(root, val);
	}
	
	private boolean add(TreeNode node, int val) {
		if (node.val == val) return false; 		//This BST does not allow duplicates
		//If the value should go into the left subtree (node val greater than val)
		if (node.val > val) {
			if (node.left == null) {
				node.left = new TreeNode(val);
				return true;
			}
			return add(node.left, val);
		}
		//If the value should go into the right subtree (node val lesser than val)
		else {
			if (node.right == null) {
				node.right = new TreeNode(val);
				return true;
			}
			return add(node.right, val);
		}
	}
	
	//------------------------------
	//		SEARCH
	//--------------------------------
	public boolean search(int val) {
		return search(root, val);
	}
	
	private boolean search(TreeNode node, int val) {
		if (node == null) return false;
		if (node.val == val) return true;
		if (node.val > val) return search(node.left, val);
		return search(node.right, val);
	}
	
	
	//------------------------------
	//		DELETION
	//--------------------------------
	public boolean delete(int val) {
		//Edge case: If the tree is empty
		if (root == null) return false;
		
		//Edge case: If the value to remove is the root node itself, then the root itself has to be reassigned
		if (root.val == val) {
			//If root is the only element
			if (root.left == null && root.right == null) {
				root = null;
			}
			//Root only has right subtree
			else if (root.left == null) root = root.right;
			//Root only has left subtree
			else if (root.right == null) root = root.left;
			//Else the root has left and right subtree. Root value has to be replaced
			else {
				int replace = getRightMinimum(root);
				delete(root, replace);
				root.val = replace;
			}
			return true;
		}
		//Otherwise execute normal deletion algorithm
		return delete(root, val);
	}
	
	private boolean delete(TreeNode node, int val) {
		//Recording parent is necessary to disconnect the link to the deleted nodes
		TreeNode parent = null;
		TreeNode curr = node;
		
		//While the node is yet to be found, search down the tree
		while (curr != null && curr.val != val) {
			parent = curr;
			if (curr.val > val) curr = curr.left;
			else curr = curr.right;
		}
		
		//Edge case: The node to be deleted is not present
		if (curr == null) return false;
		
		//Case 1: Deletion node is leaf node, then just disconnect it from parent
		if (curr.left == null && curr.right == null) {
			if (parent.val > val) parent.left = null;
			else parent.right = null;
		}
		//Case 2: Deletion node has either left or right subtree. Just connect parent to that subtree
		else if (curr.left == null) {
			if (parent.val > val) parent.left = curr.right;
			else parent.right = curr.right;
		}
		else if (curr.right == null) {
			if (parent.val > val) parent.left = curr.left;
			else parent.right = curr.left;
		}
		//Case 3: Deletion node has both left and right subtree. Find the next successor, and replace the deletion node value
		//		  with it, while delete the duplicated node itself
		else {
			int toReplace = getRightMinimum(curr);
			delete(curr, toReplace);
			curr.val = toReplace;
		}
		return true;
	}
	
	
	//Given a node, this will return the minimum value of this node's right subtree (Next successor)
	private int getRightMinimum(TreeNode node) {
		TreeNode curr = node.right;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr.val;
	}
}

public class Design_Hashset {
	
	BinarySearchTree[] store;

	 /** Initialize your data structure here. */
    public Design_Hashset() {
        store = new BinarySearchTree[1000];
    }
    
    public void add(int key) {
        int idx = hashfunc(key);
        if (store[idx] == null ) store[idx] = new BinarySearchTree();
        
        store[idx].add(key);
    }
    
    public void remove(int key) {
        int idx = hashfunc(key);
        if (store[idx] == null) return;
        store[idx].delete(key);
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int idx = hashfunc(key);
        if (store[idx] == null) return false;
        return store[idx].search(key);
    }
    
    private int hashfunc(int val) {
    	return val % store.length;
    }

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
	
}
