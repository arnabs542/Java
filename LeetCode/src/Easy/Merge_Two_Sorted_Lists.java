package Easy;

//https://leetcode.com/problems/merge-two-sorted-lists/

/*
 * 	This is a Linked List, Recursion problem.
 * 
 * 	One approach is to reorder the whole linked list using two pointers.
 * 	At a certain point in our program, the two pointers will point to certain nodes in both linked list respectively.
 * 	The pointers initially will be at the head of both linked lists.
 * 	
 * 	The job is to select the lesser one, append it to the tail of newly constructed linked list, and increment the
 * 	pointer. Repeat the process until all nodes are exhausted. Then, we would have a nicely merged sorted list
 * 
 * 	-----------------------------------------------------------------------
 * 
 * 	Another approach is to do insertion from one of linked list into another linked list. Still, minimum of two pointers
 * 	has to be used to achieve this algorithm.
 * 
 * 	Select the one linked list which starts with lesser value as primary list to iterate, so that we won't need to
 * 	insert nodes from secondary list before the first node.
 * 	The other linked list will become secondary linked list
 * 	
 * 	>	If the pointers to primary and secondary list are not null, 
 * 			-	Primary node is lesser - Proceed the primary pointer
 * 			-	Primary node is greater - Insert secondary node behind the primary node (prev pointer might be needed)
 * 
 * 	>	If primary list is exhausted but secondary list still have nodes yet to insert, It means all remaining secondary nodes are greater than
 * 		primary nodes. Append to the tail of primary list
 * 
 * 	>	Other cases, we can return the head of primary list immediately.
 * 
 * 	--------------------------------------------------------------------------
 * 
 * 	This problem also could be solved with recursion. 
 * 	Each recursion call will receive 2 head of linked list. Its job is to find out whoever node is lesser to be returned
 * 	to upper recursion level. However, before doing that, it must ensure it returns a sorted list.
 * 	Therefore,
 * 		>	Select the lesser node out of the two
 * 		>	Call the recursion with 2 of the linked list passed in, but excluding the selected, lesser node.
 * 		>	Append the lesser node to the returned, sorted linked list
 * 		>	Return lesser node
 * 
 */

public class Merge_Two_Sorted_Lists {
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (l1 != null || l2 != null) {
        	//	List 2 is exhausted, or List 1's value is less than List 2's value
        	if ( l2 == null || l1 != null && l1.val < l2.val) {
        		tail.next = l1;
        		tail = tail.next;
        		l1 = l1.next;
        	} else {
        		tail.next = l2;
        		tail = tail.next;
        		l2 = l2.next;
        	}
        }
        
        return dummy.next;
    }
	
	
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		ListNode primary = (l2 == null || l1 != null && l1.val < l2.val)? l1: l2;	//	The list that has the smallest starting value
		ListNode secondary = (primary == l1)? l2: l1;		//	The list with greater starting value
		ListNode curr = primary;							//	Current pointer in the primary list
		ListNode prev = null;								//	The node previous to the current pointer
		
		while ( curr != null && secondary != null ) {
			//	The node in primary List is greater than the node in secondary list. Insert the secondary list node behind the primary list's node
			if ( curr.val > secondary.val ) {
				ListNode nextSecondary = secondary.next;
				if (prev != null) prev.next = secondary;
				prev = secondary;
				secondary.next = curr;
				secondary = nextSecondary;
			} 
			//	Otherwise primary List Node is lesser than secondary list node. Proceed in primary list
			else {
				prev = curr;
				curr = curr.next;
			}
		}
		
		//	Secondary list haven't exhausted means all remaining nodes in secondary is greater than
		//	all nodes in primary. So append to primary list's tail
		if (secondary != null)
			prev.next = secondary;
		
		return primary;
	}
	
	
	
	
	
	public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
		//	Base cases. Either node is null, just return the other one
		if (l1 == null) return l2;
		if (l2 == null) return l1;
		
		//	Otherwise we have to pick the lesser node, and recurse to obtain the next node supposed to connect
		//	Then return the lesser node
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists3(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists3(l1, l2.next);
			return l2;
		}
	}
	
}
