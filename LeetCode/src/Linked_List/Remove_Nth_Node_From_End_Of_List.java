package Linked_List;

//https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1296/

/*
 * 	Linked list uses a lot of two pointers technique
 * 
 *	To do this in a single pass, the basic idea is to keep two pointers where the fast pointer is always n steps ahead of the slow pointer. Once we see that
 *	the fast pointer reached the end, we know that slow pointer is about n steps away from the end. Therefore we could perform the deletion of node at the slow pointer
 *	
 *	The edge cases is deletion of the first node, which when fast pointer is initialized, will point directly to null. Therefore fix is to directly return the
 *	head's next node.
 *
 */
public class Remove_Nth_Node_From_End_Of_List {
	class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if (head.next == null) return null;
    	
    	ListNode fast = head;
    	ListNode slow = head;
    	for (int i = 0; i < n; i ++ ) {
    		fast = fast.next;
    	}
    	
    	while (fast != null && fast.next != null) {
    		fast = fast.next;
    		slow = slow.next;
    	}
    	
    	if (fast == null) return slow.next;
    	slow.next = slow.next.next;
    	
    	return head;
    }
}
