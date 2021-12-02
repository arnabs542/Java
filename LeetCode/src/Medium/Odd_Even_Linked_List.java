package Medium;


//https://leetcode.com/problems/odd-even-linked-list/
/*
*	This is a linked list problem.
* 
*	With my idea, simply construct two linked list from existing nodes: Oddlist and Evenlist. Then at the end, connect
*	them two and return the combined linked list.
* 
*	Using the concept of dummy node, everything can be done much more easier and readable way.
* 
*	Keep track of 4 items: oddHead, oddTail, evenHead, evenTail. Iterate through the original linked list and append them
*	to oddTail or evenTail accordingly. 
*	At the end, connect oddTail to evenHead (Not the dummy node), and point evenTail to null. Return the oddHead (Not the dummy node)
* 
*/


public class Odd_Even_Linked_List {

	class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) {
			this.val = val;
		}
		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
	

	public ListNode oddEvenList(ListNode head) {
		ListNode oddHead = new ListNode();
		ListNode evenHead = new ListNode();
		ListNode oddTail = oddHead;
		ListNode evenTail = evenHead;

		for (int i = 1; head != null; ++i, head = head.next) {
			// Odd
			if ( (i & 1) == 1 ) {
				oddTail.next = head;
				oddTail = oddTail.next;
			}
			// Even
			else {
				evenTail.next = head;
				evenTail = evenTail.next;
			}
		}

		evenTail.next = null;
		oddTail.next = evenHead.next;
		return oddHead.next;
	}
	
}
