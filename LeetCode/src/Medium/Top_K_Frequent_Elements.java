package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/top-k-frequent-elements/

/*
 * 	HEAP + HASH MAP
 * 
 * 	A simpler way to solve this question is to use Heap and Hash Map combination. Remember that heap has a property to be always available
 * 	to poll out the max/ min value of the data.
 * 	Therefore we would first count the frequencies of the numbers in the array first. Then, we would add them into the heap.
 * 	The better choice to be done here is to only maintain the size of heap to be k size. Then it won't consume up as many space as needed.
 * 	We create a heap with custom comparator which sorts by comparing the frequency count of both from frequency table. Then, when the 
 *	size of heap overflows when we had just added new integer into it, we would just poll out the number with smallest frequency from it.
 *	Lastly, just poll out all the numbers from heap and put it into result array.
 *
 *	-----------------------------------------------------------------------------------------------------------------------
 *
 *	QUICK SELECT / PARTITIONING
 *
 *	Another solution is to use the Partitioning technique from Quick Sort Algorithm, which can be quite efficient when dealing with
 *	'Find k largest/ top k...' Problems.
 *
 *	It basically finds a pivot, and scan through the range with one fast pointer and one slow pointer.
 *	From fast pointer, any integer that has smaller frequency than the pivot's shall be swapped with the slow pointer, which
 *	the slow pointer will also be move forward by one step.
 *
 *	To prevent the pivot from being swapped, set the pivot to be the end of the list, and loop until the previous element of the pivot.
 *
 *	At the end, the slow pointer will point at the place where the pivot integer is supposed to be. Swap the pivot and the integer where slow
 *	pointer is pointing to. 
 *	Now, the slow pointer is the point where every integer to the right is larger than the integer at pivot, and every integer to the left
 *	is smaller than the integer at pivot. Calculate the length of subsequence to the right, if it matches with the requested k large elements,
 *	then just return that subsequence (Since question noted that arrangement doesn't matter)
 *	If it doesn't, we would just use binary search technique, narrow down the range of either right pointer or left pointer, and
 *	repartition until we've found the exact subsequence length.
 *
 *	The worst case is if the pivot is always belong to the end of the array (The array is already sorted), resulting in
 *	worst case O(n^2) time complexity. However on average, it shall be O(N).
 *	To minimize the worst case probability, we could randomly select the pivot from the array and swap it with the last element before
 *	the partitioning process, which can further reduce the probability of selecting worst pivot again and again.
 *	
 */

public class Top_K_Frequent_Elements {
	
//	public int[] topKFrequent(int[] nums, int k) {
//		Map<Integer, Integer> map = new HashMap<>();
//		PriorityQueue<Integer> heap = new PriorityQueue<>( (x,y) -> {
//			return map.get(x) - map.get(y);
//		});
//		
//		for (int i: nums) {
//			map.put(i, map.getOrDefault(i, 0) + 1);
//		}
//		
//		for (int i: map.keySet() ) {
//			heap.add( i );
//			if (heap.size() > k) heap.poll();
//		}
//		
//		int[] res = new int[k];
//		for (int i = k - 1; i >= 0; i --) {
//			res[i] = heap.poll();
//		}
//		return res;
//	}
	
	
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int i: nums) 
			freq.put(i, freq.getOrDefault(i, 0) + 1);
		
		List<Integer> li = new ArrayList<>(freq.keySet() );
		int pivot = 0;
		
		int target = li.size() - k;
		int left = 0, right = li.size() - 1;
		while (pivot != target) {
			pivot = partition(li, left, right, freq);
			if (pivot > target)
				right = pivot - 1;
			else if (pivot < target)
				left = pivot + 1;
		}
		
		int[] res = new int[k];
		li = li.subList(target, li.size() );
		for (int i = 0; i < k ; i ++ ) {
			res[i] = li.get(i);
		}
		return res;
	}
	
	private static int partition(List<Integer> li, int left, int right, Map<Integer,Integer>freq) {
		int partfreq = freq.get(li.get(right) );
		
		int i = left, j = left;
		while (j < right) {
			int curr = li.get(j);
			if (freq.get( curr ) < partfreq) {
				int temp = li.set(i, curr );
				li.set(j, temp);
				i ++;
			}
			j++;
		}
		int temp = li.set(i, li.get(right) );
		li.set(right, temp);
		System.out.println(li + ", " + i);
		return i;
	}
	
}
