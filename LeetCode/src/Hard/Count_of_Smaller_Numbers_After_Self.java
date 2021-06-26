package Hard;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/count-of-smaller-numbers-after-self/
/*
 *  This turns out to be a Merge sort / BST problem
 * 	However, only merge sort solution is discussed here as it is guaranteed O(N log N) time.
 * 
 * 	Merge sort, uses divide and conquer strategy with recursion to sort an array. For given range in an array [l,r],
 * 	it splits the range into two to sort. Once returned, it will use two pointers to finally MERGE the two separate
 * 	sorted array into one.
 * 
 *  Notice here, during splitting, original order of the array is not yet lost:
 *  		[1,2,3,4,5,6,7]
 *  	[1,2,3,4]   [5,6,7]
 *   [1,2] [3,4]	[5,6]  [7]
 *  [1] [2] [3] [4] [5] [6]
 *  
 *  In all the levels, the right subarray elements must be to the right of all left subarray elements in the original unsorted
 *  array. Does that ring a bell?
 *  
 *  Let's start from bottom. Once the subarray of size 1 (base case) is returned, we will combine two subarrays.
 *  Let's say left side is [5], and right size is [1]. 
 *  We'll notice that right side's [1] has to be go to the left of [5]! This means in original unsorted array, 5 comes before
 *  1, which means a smaller number after 5!
 *  
 *  Therefore, the idea is to count the number of elements in right subarray that has to go before elements in left subarray.
 *  When left element is selected, I have to account for how many right subarray elements had been choosen before this left
 *  subarray element is chosen (How many smaller numbers are to the right of the element).
 *  
 *  This means I have to track the original indices of each element as well, because I need to update the result array as well.
 *  This can be done by having a pair of {val, idx}
 */

public class Count_of_Smaller_Numbers_After_Self {
	
	public List<Integer> countSmaller(int[] nums) {
        final int len = nums.length;
        List<Integer> res = new ArrayList<>(len);
        
        //	Create the array of [val, idx] for sorting
        int[][] indexedNums = new int[len][2];
        for (int i = 0; i < len; ++i) {
            indexedNums[i] = new int[]{ nums[i], i };
            res.add(0);
        }
        
        //	Merge sort the indices while filling count in the process
        mergeSort(indexedNums, 0, len-1, res);
        
        return res;
    }
	
	private void mergeSort(int[][] indexedNums, int left, int right, List<Integer> res) {
		if (left == right) return;
		
		int mid = left + (right - left) / 2;
		mergeSort(indexedNums, left, mid, res);
		mergeSort(indexedNums, mid+1, right, res);
		
		List<int[]> temp = new ArrayList<>(right - left + 1);
		int countRight = 0;
		int l = left, r = mid+1;
		
		while (l <= mid || r <= right) {
			//	Have to take the left element
			if ( l <= mid && (r > right || indexedNums[l][0] <= indexedNums[r][0]) ) {
				temp.add(indexedNums[l]);
				res.set( indexedNums[l][1], res.get(indexedNums[l][1]) + countRight);
				++l;
			}
			//	Add the right element. We have to track number of right section elements coming to left side
			else {
				temp.add(indexedNums[r]);
				++countRight;
				++r;
			}
		}
		
		int p = left;
		for (int[] t: temp)
			indexedNums[p++] = t;
	}
	
}
