package Easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//https://leetcode.com/problems/n-ary-tree-preorder-traversal/
/*
 * This is simply a Tree traversal problem
 * 
 * Instead of binary tree, now you faced with preorder traversal with
 * M-ary tree. However, the twist isn't big. Instead of checking left and
 * right, replce with a loop to check out the childrens
 * 
 * Note in iterative method, you should push to the stack from right to left,
 * so the leftmost children will get popped first
 */

class Node {
	public int val;
	public List<Node> children;
	public Node() {}
	public Node(int _val) {
		val = _val;
	}
	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
}

public class N_ary_Tree_Preorder_Traversal {
	
	//	Recursive method
	public List<Integer> preorder(Node root) {
		List<Integer> res = new ArrayList<>();
		recurse(root, res);
		return res;
	}
	private void recurse(Node root, List<Integer> res) {
		if (root == null) return;
		res.add(root.val);
		
		for (Node n: root.children)
			recurse(n, res);
	}
	

	
	//	Iterative method
	public List<Integer> preorder2(Node root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		Deque<Node> stk = new ArrayDeque<>();
		stk.push(root);
		
		while (!stk.isEmpty()) {
			Node node = stk.pop();
			res.add(node.val);
			
			//	Right to left!
			for (int i = node.children.size() - 1; i >= 0; --i) {
				Node n = node.children.get(i);
				if (n != null) stk.push(n);
			}
		}
		return res;
	}
}
