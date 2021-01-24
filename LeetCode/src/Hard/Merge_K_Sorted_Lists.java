package Hard;
import java.util.PriorityQueue;
import Linked_List.ListNode;

//https://leetcode.com/problems/merge-k-sorted-lists/
/*
 * This question is highly related to the merging process in Merge Sort algorithm.
 * Do check it out if haven't
 * 
 * The core intuition is very similar to how we merge 2 linked lists in Merge Sort.
 * However this time we have K sorted lists instead of just 2! How?
 * 
 * One brute forcey way is:
 * 		While there is still lists that aren't finish yet, iterate through all the HEAD of
 * 		K lists. Find out the minimum one, to append to the new linked list.
 * 
 * 	The time complexity is approximately O(NK) because:
 * 		while there are elements still left, an iteration of K times will run to find minimum node.
 * 		There are total of N elements.
 * 
 *	------------------------------------------------------------------------------------
 *
 *	The expensive part of above algorithm is the iteration of K times. Can we search out the minimum
 *	node in shorter time complexity? O(Log N)? - Heaps!
 *
 *	Push every Head of the sorted linked list into the Heap. It will sort out automatically for us.
 *	Then pop out the smallest one, append to tail of new linked list, then check:
 *	If the popped linked list has next node, push the next node into Heap.
 *
 *	Time complexity reduced to O(N log K), but space O(K) is traded off
 */

public class Merge_K_Sorted_Lists {
	
	//	Brute-forcey way. Iterate K each time, leading
	//	to O(KN) time
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode dummy = new ListNode(Integer.MAX_VALUE);
		ListNode tail = dummy;
		
		while (true) {
			ListNode next = dummy;
			int index = -1;
			
			for (int i = 0; i < lists.length; ++i) {
				if (lists[i] != null && lists[i].val < next.val) {
					index = i;
					next = lists[i];
				}
			}
			
			if (next == dummy) break;
			
			tail.next = next;
			tail = tail.next;
			lists[index] = next.next;
		}
		return dummy.next;
	}
	
	
	
	//	Heap solution 
	public ListNode mergeKLists2(ListNode[] lists) {
		PriorityQueue<ListNode> heap = new PriorityQueue<>( (x,y)-> {
			return x.val - y.val;
		});
		for (ListNode l: lists) 
            if (l != null) heap.add(l);
		
		ListNode dummy = new ListNode();
		ListNode tail = dummy;
		
		while (!heap.isEmpty() ) {
			ListNode pop = heap.poll();
			tail.next = pop;
			tail = tail.next;
			
			if (pop.next != null) heap.add(pop.next);
		}
		
		return dummy.next;
	}

}
