package Arrays;

import java.util.HashSet;
import java.util.PriorityQueue;

//https://leetcode.com/problems/third-maximum-number/

public class Third_Maximum_Number {
	//Or use a TreeSet, which is like PriorityQueue and Set combined
	public int thirdMax(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for (int i: nums) {
			if (!set.contains(i) ) {
				queue.offer(i);
				set.add(i);
				
				if (queue.size() > 3) {
					queue.poll();
				}
			}
		}
		if (queue.size() < 3) {
			while (queue.size() != 1) queue.poll();
			return queue.poll();
		}
		return queue.peek();
	}
}
