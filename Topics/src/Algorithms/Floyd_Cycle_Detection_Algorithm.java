package Algorithms;

/*
 * 	Floyd's Cycle Detection Algorithm, also known as Tortoise and Hare algorithm, is an algorithm to detect and if present, find out the	
 * 	starting node of a cycle in directed graph (Possibly linked list)
 * 
 * 	It uses the two pointer technique, where one is fast runner and the other is slow runner. The algorithm works where initially the two pointers
 * 	begin at the head of the graph. The fast pointer will always move faster than the slow pointer where it skips n steps
 * 	(A common setup is fast pointer skips 2 steps and slow pointer skips 1 steps each cycle)
 * 
 *	If there is indeed a cycle in the graph, then the two pointers will eventually meet at some node in the cycle. If there is none, then
 *	the fast pointer will hit the end of the graph where the node is null.
 *	After the two pointer has meet (indicating a cycle is present), we can further to find out the beginning of the cycle node by first resetting
 *	one of the pointers to the beginning of the graph, and let them continue iterating but this time going at the same pace (1 steps usually).
 *	They will meet again right at the beginning of the cycle node.
 *
 *	To prove the algorithm, we will need to illustrate it 
 *
 *							---	4 --- 5 ---
 *			1 ---- 2 ---- 3 	 			6
 *							---	8 --- 7 ---
 *
 *	s : the distance of the straight part, in this case it is from 1 to 3.
 *	k : the distance in the cycle from the beginning of cycle (cycle begins at 3), where the two pointers will meet. In this case the pointers
 *		will meet at node 7, therefore k will be distance from 3 to 7. 
 * 	l : the cycle's length, which is distance from 3 until back to itself, passing through 5 nodes
 *	
 *	Now, we consider the distances that both pointer covered until they meet each other.
 *
 *	Slow pointer moved (s + k) distance until it collides with fast pointer
 *	Fast pointer moved (s + k + l) distance (Since it moves faster, it at least must be cycled through the cycle at least once, therefore + l)
 *											(There may be cases where fast pointer cycled not only once but multiple times, in that case the proof
 *											 still stands so we can make assumptions first)
 *	
 *	Another interesting pattern to notice is that the distance fast pointer moved must be twice (Since it is 2 steps for fast pointer) of the
 *	slow pointers. 
 *	(This can be visualized as if 2 runners are running on straight track. A runs 1 steps and B run 2 steps. When A has run 5 steps, B must've
 *	 ran 10 steps!)
 *	
 *	Therefore the equation becomes as follows: 2(s + k) = (s + k + l) --- LHS is equivalent to the fast pointer distance covered, which is 2x of slow
 *																		pointer
 *	Simplifying the equation we will get (l = s + k)
 *	The length of the cycle is equal to the length of straight part PLUS the distance to meeting point from the beginning of cycle
 *
 *	Now the pointers are right at the k distance from beginning of cycle! If we make the pointer travel s distance more, it would have completed
 *	a full cycle, which means the pointer will get back to the beginning of cycle node.
 *	To achieve this, we put one pointer back to the beginning of graph, and another right at where it meets the other pointer. Let them
 *	traverse 1 step at a time. Due to the above property, when the left pointer reached the beginning of the cycle (s steps), the other pointer
 *	must be also covered s steps! They will meet right at the beginning of the cycle!
 *	
 *	
 *
 *
 */

public class Floyd_Cycle_Detection_Algorithm {
	static class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	}
	
	static ListNode createCycle(int n, int attach) {
		ListNode root = new ListNode(1);
		ListNode curr = root;
		for (int i = 2; i <= n; i ++ ) {
			curr.next = new ListNode(i);
			curr = curr.next;
		}
		if (attach >= 0 && attach < n) {
			ListNode begin = root;
			for (int i = 0; i < attach; i ++ )
				begin = begin.next;
			curr.next = begin;
		}
		
		return root;
	}
	
	public static ListNode beginning(ListNode root) {
		if (root == null) return null;
		
		ListNode slow = root;
		ListNode fast = root;
		
		do {
			slow = slow.next;
			fast = fast.next;
			if (fast == null || fast.next == null) return null;
			fast = fast.next;
		} while (slow != fast);
		
		slow = root;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return fast;
	}
	
	public static void main(String[]args) {
		ListNode root = createCycle(10, -1);
		System.out.println( beginning(root).val );
	}
	
}
