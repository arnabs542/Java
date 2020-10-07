package Medium;

//https://leetcode.com/problems/rotate-list/

/*
 * 	A Linked list problem. We are asked to rotate the linked list, as follows:
 * 	
 * 		A -> B -> C
 * 		C -> A -> B
 * 		B -> C -> A
 * 		A -> B -> C
 * 	Basically, we are rotating it to the right, so that each rotation, last element become the first
 * 	element (head), and last second element become the tail.
 * 
 * 	THe problem was originally solved in Linked_List problem. Do refer to that!
 */

public class Rotate_List {
	
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
	
	public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;		//	No list to rotate. Return
		
        //	Obtain the length of the linked list, along with the tail
        ListNode curr = head;
		int len = 1;
		
		while (curr.next != null) {
			len ++;
			curr = curr.next;
		}
		ListNode tail = curr;
		
		k = k % len;
		//==============================================================
		
		
		//	K = 0, there is no need for rotation. Return it
		if (k == 0) return head;
		
		//	Otherwise there must be a rotation occurring. Get the newtail (To break the cycle)
		ListNode newtail = head;
		for (int i = 0; i < k - 1; i ++ ) {
			newtail = newtail.next;
		}
		//	The node next to newtail will be the new head
		ListNode newhead = newtail.next;
		
		//	Break the cycle
		newtail.next = null;
		//	The original tail is no longer a tail. Set it to connect to original head
		tail.next = head;
		
		return newhead;
		
    }
}
