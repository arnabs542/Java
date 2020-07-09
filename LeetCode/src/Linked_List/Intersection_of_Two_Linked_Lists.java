package Linked_List;

//https://leetcode.com/problems/intersection-of-two-linked-lists/

/*
 * 	If only the two pointers would meet each other, then we know the intersection occurs. The problem is time
 * 	Since when iterating, the two linked list would have different lengths. If they both iterate at the same pace, one of them would still be
 * 	faster than the another to reach the intersection. Therefore the problem is how do we make them in phase?
 * 
 * 	Notice the length for the part of linked list AFTER the intersection is same for both of linked lists. The trouble causing the pointers
 * 	to not meet at the intersection is due to the part BEFORE the intersection which may be different lengths. Therefore, we shall shift the
 * 	longer linked list's pointer so that the pointers will meet at the intersection
 * 
 * 	We can do this by first determining the length of the 2 linked list themselves. A simple linear iteration will do. Upon reaching the end,
 * 	we shall check if the two linked list end up in the same last node or not. If not, they are separate linked lists and no intersection to
 * 	consider at all.
 * 	
 * 	Then we shall found the difference of the lengths of the linked list. Whoever linked list is longer will get their starting point shifted
 * 	by difference d times so that both linked list will reach the end at the same time! Now iterate again and find their meeting point. That's
 * 	their intersection.
 * 
 * ----------------------------------------
 * 
 * 	Another solution with same concept but more genius is that when the node has reached null, move the pointer to another linked list's head.
 * 	That way we don't have to find out the lengths of the linked list and no need to check if the linked list does have intersection or not.
 * 	Due to switching of pointer A / B to another linked list, the faster pointer will start again in longer linked list and the slower pointer
 * 	will start at shorter linked list. That way the difference is compensated and they will reach the end at the same time
 * 
 */

public class Intersection_of_Two_Linked_Lists {
	static class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	}
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;
		
		ListNode currA = headA;
		ListNode currB = headB;
		//The distances from head of linked list to the end of linked list
		int distA = 1, distB = 1;
		
		while (currA.next != null) {
			currA = currA.next;
			distA ++;
		}
		while (currB.next != null) {
			currB = currB.next;
			distB ++;
		}
		
		//If they don't end up in the same last node, then no intersection is occurring
		if (currA != currB) return null;
		//Reset the heads back to their respective place.
		currA = headA; currB = headB;
		
		//Linked list A is longer, therefore we have to shift it n times where n is the difference in length of linked lists
		if (distA > distB) {
			for (int i = distA - distB; i > 0; i -- )
				currA = currA.next;
		}
		//Linked list B is longer
		if (distB > distA) {
			for (int i = distB - distA; i > 0; i -- )
				currB = currB.next;
		}
		
		//Now they will reach the end at the same time. See when do they intersect?
		while (currA != currB) {
			currA = currA.next;
			currB = currB.next;
		}
		return currA;
	}
	
	//--------------------------------------------------------------------------
	
	public ListNode getGenius(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;
		
		ListNode currA = headA;
		ListNode currB = headB;
		
		while (currA != currB) {
			currA = (currA == null)? headB: currA.next;
			currB = (currB == null)? headA: currB.next;
		}
		
		return currA;
	}
	
}
