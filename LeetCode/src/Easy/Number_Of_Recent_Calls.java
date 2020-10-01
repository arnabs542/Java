package Easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * 	This is a Queue problem.
 * 	Given a list of times which we will call ping. We need to know how many pings are made in past 3000 time unit, including the most
 * 	recent one.
 * 
 * 	We will need a way to record the past 3000 time unit ping calls. This is where queue shall be used. In each call to the ping,
 * 	discard all the calls (queue head) which are outdated ( < t - 3000 ). Then push the ping call, and return the size of queue.
 * 
 * 	The time units are in sorted order (increasing). We might be able to instead of popping from the head 1 by 1 (O(N)) time, we might be
 * 	able to perform binary search. However, binary search is not really applicable to linked lists. (Queue).
 * 	One way to get around is by using circular array, where we can actually access elements in O(1) time, and cut off elements also in
 * 	O(1) time. This way the array would be perfect for binary search which is done in O(log N) only.
 * 
 */

public class Number_Of_Recent_Calls {
	
	static class RecentCounter {
		
		Deque<Integer> calls;
		public RecentCounter() {
			calls = new ArrayDeque<>();
		}
		
		public int ping(int t) {
	        while (!calls.isEmpty() && calls.peek() < t - 3000 ) {
	        	calls.poll();
	        }
	        
	        calls.offer(t);
	        return calls.size();
	    }
	}
	
	
	/*
	 * 	This is a circular array queue, binary search solution, which is harder to implement
	 *	This utilizes the O(1) random access time for array (So binary search is log N), while keeping 2 pointers
	 *	which is similar to queue.
	 *	Since we know we only need to keep at most 3001 entries (Since t - 3000 is inclusive). We need to allocate array
	 *	of size 3001 beforehand
	 */
	static class RecentCounter2 {
		
		private int[] bucket;
		private int head, tail;
		private int interval;
		
		
		public RecentCounter2(int interval) {
			bucket = new int[interval + 1];
			head = 0;
			tail = 0;
			this.interval = interval;
		}
		public RecentCounter2() {
			bucket = new int[3002];
			head = 0;
			tail = 0;
			this.interval = 3001;
		}
		
		public int ping(int t) {
	        head = binarySearch(t);
	        
	        //	Tail pointer points to the place to insert the new element
	        bucket[tail] = t;
	        tail = (tail + 1) % (interval + 1);
	        //	Now the tail pointer points to the place next element shall go in.
	        
	        //	If tail is less than head, we imagine tail is ahead of head (outside array), then find their difference
	        if (tail <= head)
	        	return (interval + 1) + tail - head;
	        
	        //	Otherwise just reutrn their size difference
	        return tail - head;
	    }
		
		
		private int binarySearch(int t) {
			int left = head, right = tail;
			
			//	The lower bound to search for
			t = t - interval;
			while (left != right) {
				int mid;
				//	If the right is less than left, then we need to imagien the right is ahead of left by a whole array distance.
				//	This needs to be done in order to find the correct middle element
				if (right < left) {
					mid = (left + right + (interval + 1) ) / 2;
				} else {
					mid = left + (right - left) / 2;
				}
				
				if (bucket[mid] < t) {
					left = (mid + 1) % (interval + 1);		//Careful of out of bound!
				} else {
					right = mid;
				}
			}
			
			//	If the left pointer is pointing at invalid value, it needs to be incremented again
			//	Be careful that incrementing may end up out of bound. So perform modulo
			if (bucket[left] < t) return (left + 1) % (interval + 1);
			return left;
		}
		
	}
	
	
	
	public static void main(String[]args) {
		RecentCounter2 rc = new RecentCounter2();
		List<Integer> li = new ArrayList<>();
		
		for(int i = 0; i < 10; i ++ ) {
			li.add( rc.ping(i) );
		}
		
		System.out.println(li);
	}

}
