package Medium;
import Linked_List.ListNode;

//https://leetcode.com/problems/partition-list/
/*
 * 	This is a common linked list, two pointers problem
 * 
 * 	From the original linked list, we would like to construct
 * 	2 new linked lists. One with all nodes value lesser than x,
 * 	another with nodes value greater or equal to x.
 * 	At the end, simply connect the two together
 * 
 * 	Iterate through the original linked list, check the nodes value
 * 	and append to either of the two newly created linked list's tail
 */


public class Partition_List {
	
	public ListNode partition(ListNode head, int x) {
		ListNode lessHead = new ListNode();
		ListNode greaterHead = new ListNode();
		ListNode lessTail = lessHead;
		ListNode greaterTail = greaterHead;
		
		for (; head != null; head = head.next) {
			if (head.val >= x) {
				greaterTail.next = head;
				greaterTail = head;
			} else {
				lessTail.next = head;
				lessTail = head;
			}
		}
		
		//	Connect the lesser Linked List to Greater Linked list
		//	Remember that the dummy node exists!
		lessTail.next = greaterHead.next;
		greaterTail.next = null;
		return lessHead.next;
    }
	
}
