package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
/*
 * 	This is a Tree, Stack problem.
 * 
 * 	Firstly, we could use stack to store the nodes in preorder traversal. When storing the nodes which is 
 * 	in range [0,100], we could flip the sign to indicate the node's left subtree has been visited. However,
 * 	we need to +1 to avoid ambiguity of value 0.
 * 
 * 	>	For each node,
 * 		- If top of stack is negative, we pop the top of stack, indicate the node is fully occupied
 * 		- Otherwise flip the sign of top element
 * 	>	If stack is empty before push or pop, then it is invalid tree
 * 
 * 	Start by pushing a negative value onto the stack, so when first node comes, the initial negative value is
 * 	popped
 * 
 *  ----------------------
 *  
 *  An O(1) space solution is to count indegrees and outdegrees. In valid tree where we treat null as a node
 *  itself, a valid node provides 2 outdegree and consumes 1 indegree. A null node will consume 1 indegree
 *  and provides no outdegree. At the end, both should cancel each other
 */

public class Verify_Preorder_Serialization_of_a_Binary_Tree {
	
	// Stack solution
	public boolean isValidSerialization(String preorder) {
		String[] nodes = preorder.split(",");
		Deque<Integer> stack = new ArrayDeque<>();
		
		stack.push(-1);
		
		for (String n: nodes) {
			if (stack.isEmpty()) return false;
			
			if (stack.peek() > 0) stack.push( -stack.pop() );
			else stack.pop();
			
			if (!n.equals("#"))
				stack.push( Integer.parseInt(n) + 1 );
		}
		return stack.isEmpty();
    }
		
		
	// Counting Indegree and outdegree solution
	public boolean isValidSerialization2(String preorder) {
		String[] nodes = preorder.split(",");
		int deg = 1;
		
		for (String n: nodes) {
			deg -= 1;
			if (deg < 0) return false;		// If deg < 0, too much indegree. Never should happen
			if (!n.equals("#")) deg += 2;	// Non-null, add 2 outdegree
		}
		return deg == 0;
    }
	
}
