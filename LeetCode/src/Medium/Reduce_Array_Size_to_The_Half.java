package Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/reduce-array-size-to-the-half/
/*
 * This is a Hash Table (HashMap), PriorityQueue / Sorting problem which revolves around idea of Greedy Algorithm
 * 
 * 	If I want to remove an integer, of course I want to remove the one with largest frequency first. This is greedy
 * 	To achieve this, I must obtain the frequencies of all elements in the array beforehand.
 * 	Then, from the frequencies, sort them in decreasing order, then always remove the largest element first. That's it
 * 
 * 	Overall time complexity is O(N log N)
 */

public class Reduce_Array_Size_to_The_Half {
	
	public int minSetSize(int[] arr) {
		int len = arr.length;
		final int target = len / 2;
		int res = 0;
		
		//	Frequency table in O(N)
		Map<Integer, Integer> freq = new HashMap<>();
		for (int i: arr)
			freq.compute(i, (k,v)-> v == null? 1: v+1);
		
		// Sort the frequencies, we can use Heap in this, giving us maximum element at first
		PriorityQueue<Integer> heap = new PriorityQueue<>((x,y)-> y-x);
		for (int i: freq.values())
			heap.add(i);
		
		while (len > target) {
			len -= heap.poll();
			++res;
		}
		return res;
    }
	
}
