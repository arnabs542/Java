package Medium;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/kth-largest-element-in-an-array/

/*
 * 	This is a Heap problem / Quick Partition (Divide and Conquer) problem
 * 
 * 	The easiest solution is just sort it and return the element at that position. O(N log N)
 * 
 * 	------------------------------------------------------------
 * 
 * 	We can also use the priority queue method which is a heap. Since it asks for the maximum kth element, what we
 * 	mostly don't want is small elements, therefore we would use a MIN HEAP, to allow us to discard any minimum elements
 * 	immediately once the heap exceed the size of K.
 * 	
 * 	The remaining elements in the heap will consists of first K maximum elements. When we are done iteration, the top of the
 * 	heap is immediately our answer.
 * 
 * 	Heap's insertion and heapify is O(log N) therefore taking overall time complexity O(N log N) and space O(K)
 * 
 * 	--------------------------------------------------------------
 * 
 * 	One ingenious solution is to using the partition algorithm in quick sort method. The partition algorithm allows us to
 * 	find out the position of one single pivot element as if it is in a sorted array.
 * 	
 * 	Since we are finding the Kth maximum element, if it is found that the index of pivot is exactly match, immediately return
 * 	the pivot as we had found the answer
 * 
 * 	If it is not, it's nevermind as we can immediately narrow down the range. Since hopefully the pivot element is exactly in
 * 	middle of the array, then we can cut half the range that we are searching.
 * 
 * 	Let I be the index of pivot. If the supposed index is greater than I, then just recurse on the right side of the array.
 * 	Otherwise, just recurse on the left side.
 * 
 * 	On worst case where pivot element is always at end, it should be O(N^2) time. Otherwise it can go to O(N) time in best case with
 * 	proven (Find online)
 */

public class Kth_Largest_Element_In_an_Array {
	
	public int findKthLargest(int[] nums, int k) {
		Arrays.parallelSort(nums);
		
		return nums[nums.length - k];
	}
	
	
	public int findKthLargest2(int[] nums, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		for (int n: nums) {
			heap.add(n);
			if (heap.size() > k) heap.poll();
		}
		return heap.poll();
	}
	
	
	
	public int findKthLargest3(int[]nums, int k) {
		return recurse(nums, 0, nums.length - 1, k);
	}
	private int recurse(int[] nums, int l, int r, int k) {
		int supposedIndex = nums.length - k;
		int pivot = nums[r];
		int i = l;
		
		for (int j = i; j < r; j ++ ) {
			if (nums[j] < pivot) {
				swap(nums, i, j);
				i++;
			}
		}
		//	Partition
		swap(nums, r, i);
		
		//	The index is exactly what we want
		if (i == supposedIndex) return pivot;
		
		//	If the partitioned index is less (The one we want to find at right side)
		if (i < supposedIndex) return recurse(nums, i+1, r, k);
		//	Else the partitioned index is greater (The one we want to find at left side)
		return recurse(nums, l, i-1, k);
	}
	
	
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
