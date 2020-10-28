package Medium;


import Linked_List.ListNode;

//https://leetcode.com/problems/linked-list-cycle-ii/

/*
This is a linked list, cycle, Floyd Tortoise and Hare algorithm problem.

To detect a cycle in linked list is simple using two pointers. One goes 2 step at a time while another goes one step at a time.
If they collide at some point, that means there definitely is a cycle in the linked list. Otherwise the fast pointer would've
ended up at the tail of linked list which is NULL.

Now, how to find the beginning of the cycle? THis needs some visualization

Let the beginning node of cycle be A
Let the node where 2 pointer meet be B
Let the distance from head node to A be x
Let the distance from A to B be y
Let the distance from B to A back (not reverse direction) be z

Look:

			(B)---( )
			/		\
		[z]( )		( ) [y]
	[x]		\		/
( )---( )---(A)---( )

Now. Relate those information:
At point B when they meet, slow pointer moved x + y distance
						   fast pointer moved (x+y+z) + y distance = x + 2y + z
Since fast pointer move at twice speed, the distance fast pointer covered must be 2x that of slow pointer's
Therefore,
							x + y = 2( x + 2y + z )
Simplify,
							x = z
The distance from beginning to A is equal to the distance from B to A! 
Therefore put one of pointers back to head, so that
	One pointer will move x distance
	One pointer will move z distance
When they collide again, that is the point of entry of cycle!
*/


public class Linked_List_Cycle_II {
	static ListNode detectCycle(ListNode root) {
		if (root == null) return null;
		
		ListNode slow = root;
		ListNode fast = root;
		
		do {
			slow = slow.next;
			fast = fast.next;
			if (fast == null ) return null;
			fast = fast.next;
		} while (fast != null && slow != fast);
		
		slow = root;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return fast;
	}
}
