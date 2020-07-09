package Linked_List;

//https://leetcode.com/problems/linked-list-cycle/

public class Linked_List_Cycle {
	class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
	
	
	public boolean hasCycle(ListNode head) {
		if (head == null) return false;
		
		ListNode fast = head;
		ListNode slow = head;
		
		do {
			slow = slow.next;
			for (int i = 0; i < 2; i ++ ) {
				fast = fast.next;
				if (fast == null ) return false;
			}
		} while (fast != slow);
		return true;
	}
}
