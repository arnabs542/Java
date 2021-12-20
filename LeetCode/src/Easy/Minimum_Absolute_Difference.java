package Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/minimum-absolute-difference/
/*
 *  This is a Sorting Array problem.
 * 
 *  If the array is already sorted, then the neighboring elements will yield the local minimum absolute difference.
 * 
 *  That is, if we have elements in the sorted array [a,b,c,d,e...], for the element c, the minimum absolute difference
 *  it can produce with one other element, is either abs(b-c) or abs(d-c), which is both its neighbors.
 * 
 *  Therefore, sort the array, and compare each adjacent elements for their absolute difference, and take only those who
 *  are globally minimum absolute difference
 */


public class Minimum_Absolute_Difference {
	
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		Arrays.sort(arr);

        // First pass - Determine minimum absolute difference (To correctly allocate capacity)
        int minAbs = Integer.MAX_VALUE;
        int count = 0;

        for (int i = 1; i < arr.length; ++i) {
            int abs = Math.abs(arr[i] - arr[i-1]);
            // Update abs and reset counter
            if ( abs < minAbs ) {
                minAbs = abs;
                count = 0;
            }

            if (abs == minAbs) ++count;
        }

        List<List<Integer>> res = new ArrayList<>(count);

        // Second pass - Populate the result array
        for (int i = 1; i < arr.length; ++i) {
            int abs = Math.abs(arr[i] - arr[i-1]);

            if (abs == minAbs)
                res.add( Arrays.asList(arr[i-1], arr[i]) );
        }

        return res;
    }
	
}
