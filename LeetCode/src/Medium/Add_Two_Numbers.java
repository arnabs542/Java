package Medium;

//https://leetcode.com/problems/add-two-numbers/

public class Add_Two_Numbers {

	static class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }
	static class Solution {
	    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	       ListNode head = new ListNode(l1.val + l2.val , null);
	       ListNode current = head;
	       int pending = 0;
	       if (head.val >= 10) {
	    	   pending = head.val / 10;
	    	   head.val = head.val % 10;
	       }
	       l1 = l1.next;
	       l2 = l2.next;
	       
	       while (l1 != null && l2 != null) {
	    	   ListNode node = new ListNode(pending + l1.val + l2.val, null);
	    	   pending = 0;
	    	   if (node.val >= 10) {
		    	   pending = node.val / 10;
		    	   node.val = node.val % 10;
		       }
	    	   current.next = node;
	    	   current = node;
	    	   l1 = l1.next;
	    	   l2 = l2.next;
	       }
	       //List 2 exhausted
	       while (l1 != null) {
	    	   ListNode node = new ListNode(pending + l1.val, null);
	    	   pending = 0;
	    	   if (node.val >= 10) {
		    	   pending = node.val / 10;
		    	   node.val = node.val % 10;
		       }
	    	   current.next = node;
	    	   current = node;
	    	   l1 = l1.next;
	       }
	       
	     //List 1 exhausted
	       while (l2 != null) {
	    	   ListNode node = new ListNode(pending + l2.val, null);
	    	   pending = 0;
	    	   if (node.val >= 10) {
		    	   pending = node.val / 10;
		    	   node.val = node.val % 10;
		       }
	    	   current.next = node;
	    	   current = node;
	    	   l2 = l2.next;
	       }
	       
	       if (pending != 0) {
	    	   ListNode node = new ListNode(pending, null);
	    	   current.next = node;
	       }
	       
	       return head;
	    }
	}
	
	public static void main(String[]args) {
		ListNode n = new ListNode(1, new ListNode(2, new ListNode(3) ) );
		ListNode n2 = new ListNode(4, new ListNode(5, new ListNode(6) ) );
		ListNode sol = Solution.addTwoNumbers(n, n2);
		while (sol != null) {
			System.out.println(sol.val);
			sol = sol.next;
		}
		System.out.println( );
	}
	
}
