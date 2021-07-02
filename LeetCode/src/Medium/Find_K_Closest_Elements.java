package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/find-k-closest-elements/
/*
 * This is a binary search / Array / Sorting problem
 * 
 * First of all, my first solution is to use queue and perform a linear scan. For each of the element, we check if it gets
 * closer and closer to x or not. We will keep adding the element into the queue. The queue itself represents our sliding window.
 * 
 * When encounter with a new element, we need to make a decision if we slide the window to include this element, will it be better?
 * If it does not, then since array is sorted, we can early terminate and return result.
 * Otherwise, we need to include this element. Poll the leftmost element if the queue is already size k.
 * 
 * 
 * Because array is sorted, we can perform binary search on it. The binary search shall return the pointer to the closest element
 * to x (Due to how we perform binary search, it can be greatest element that is less than x. In that case, need to compare
 * pt and pt+1 element).
 * Then, starting from that index, we extend the window by comparing the leftmost and rightmost elements until size k is reached.
 * 
 * 	
 * The optimized solution is to perform modified binary search. The idea is, we search for the left index. The possible left indices
 * are in the range [0, len-k]. Start binary search from this range.
 * Each iteration, we obtain the guess index mid. From it, we also check the second guess index mid+k, which is element that directly
 * follows the first guesses' window.
 * Compare the two elements whether which one are more closer. If left index is closer, then the first guess is possible to be the 
 * starting index, thus right = mid. Otherwise, the first guess couldn't be possible because it does not include the second guess
 * that is closer to x. Thus, left = mid +1.
 */

public class Find_K_Closest_Elements {
	
	// O(N) with Queue solution
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		Queue<Integer> q = new ArrayDeque<>(k);
        List<Integer> res = new ArrayList<>(k);
        
        for (int i: arr) {
        	if (q.size() < k) q.offer(i);
        	else {
        		//	If the new element is further distance (Given they are not same elements, consider case k=1, arr=[1,1,1,1,1,2], x=2)
        		if (i != q.peek() && Math.abs(i - x) >= Math.abs(q.peek() - x)) break;
        		q.poll();
        		q.offer(i);
        	}
        }
        res.addAll(q);
        return res;
    }
	
	
	//	Binary Search and Two pointers(Sliding window) solution
	public List<Integer> findClosestElements2(int[] arr, int k, int x) {
		final int len = arr.length;
		List<Integer> res = new ArrayList<>(k);
		int l = 0, r = len - 1;
		
		//	Binary Search
		while (l < r) {
			int mid = l + (r - l) / 2 + 1;
			if (arr[mid] > x) r = mid - 1;
			else l = mid;
		}
        
        //	In case [1,1,1,10,10,10] and x = 9, our pt is at 3rd '1'. Thus we need to check which is better, '1' or '10'
		if (l + 1 < len && Math.abs(arr[l+1] - x) < Math.abs(arr[l] - x)) ++l;
		r = l;
		
		//	Two pointers, expanding as sliding window
		while (r - l + 1 < k) {
			// Choose element at left pointer when:
			//	> right pointer out of range
			//	> left pointer not out of range, and left distance is smaller
			if (r == len-1 || l > 0 && Math.abs(arr[l-1] - x) <= Math.abs(arr[r+1] - x) )
				--l;
			else
				++r;
		}

		while (l <= r) res.add(arr[l++]);
		return res;
	}
	
	
	//	Binary search on left index solution
	public List<Integer> findClosestElements3(int[] arr, int k, int x) {
		final int len = arr.length;
		int l = 0, r = len - k;
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (x - arr[mid] > arr[mid + k] - x) l = mid+1;
			else r = mid;
		}
		
		List<Integer> res = new ArrayList<>(k);
		for (int i = 0; i < k; ++i) res.add(arr[l++]);
		return res;
	}
}
