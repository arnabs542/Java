package Medium;

//https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

/*
 * 	This is a binary search problem.
 * 
 * 	Given a array is rotated, we should find out where is the starting point of the original sorted array, which has the
 * 	smallest element
 * 
 * 	This can be done using binary search. For each selected middle element, if the middle element is greater than the
 * 	right element, then the pivot must be in between mid and right, excluding mid. Otherwise, it must be either mid itself
 * 	or to the left
 * 
 * 	Having the pivot point index, we have two choices:
 * 		>	Everything to left of pivot is sorted, same goes for to the right of pivot. Perform regular binary search
 * 			for two of those subarray, and merge results
 * 		>	Apply index shifting when doing the binary search. Careful of index out of bounds!
 */

public class Search_In_Rotated_Sorted_Array_I {
	
	public int search(int[] nums, int target) {
        int pivot = locatePivot(nums);
        return Math.max(
    		binarySearch(nums, 0, pivot - 1, target),
    		binarySearch(nums, pivot, nums.length - 1, target)
        );
    }
	
	private int locatePivot(int[] nums) {
		int left = 0, right = nums.length - 1;
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			
			if (nums[mid] > nums[right]) left = mid + 1;
			else right = mid;
		}
		
		return left;
	}
	
	private int binarySearch(int[] a, int left, int right, int target) {
		while (left < right) {
			int mid = left + (right - left) / 2;
			
			if ( a[mid] < target ) left = mid + 1;
			else right = mid;
		}
		return a[left] == target? left: -1;
	}
	
	private int binarySearch2(int[] a, int target, int pivot) {
		final int len = a.length;
		int left = 0, right = len - 1;
		
		while (left < right) {
			int mid = (left + (right - left) / 2);
			int real_mid = (mid + pivot ) % len;
			
			if ( a[real_mid] >= target ) right = mid;
			else left = mid + 1;
		}
		
		return a[ (left + pivot) % len ] == target? (left + pivot) % len: -1;
	}

}
