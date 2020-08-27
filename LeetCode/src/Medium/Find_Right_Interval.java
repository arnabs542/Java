package Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/find-right-interval/

/*
 * 	This is a Binary Search problem with usage of HashMap.
 * 
 * 	We are going to sort the interval array, but the solution require us to put the answer in the correct index. Therefore
 * 	we first create the HashMap, mapping from each array object to the original index
 * 	
 * 	Then, we have to sort the interval arrays by their interval start point in order to allow us to use the binary 
 * 	search technique itself.
 * 
 * 	For each interval, since we have to find the interval where the starting position is greater than or equal to this interval's
 * 	end point, we have to search for it. Luckily, the array is sorted by starting position so we can just binary ssearch on the
 * 	right side of the current interval to find it in log(n) time
 * 
 * 	Notice the property of binary search:
 * 
 * 		>	If we are on the last interval in the array, the left will wound up at non-existent position (Overflow), while right
 * 			points to the last element in array, which is the interval itself
 * 		>	If there are no valid interval, both left pointer and right pointer will be at the interval element next to the 
 * 			selected interval. However, that interval is not the answer
 * 
 * 		Therefore we can conclude that:
 * 		>	By using Right pointer instead of left pointer, we avoid ourselves of array out of bound error (When last element is selected)
 * 		>	Check each time the answer selected is indeed a valid answer. By checking the answer interval's starting point 
 * 
 * 	Then, using HashMap, find the original index of the answer interval back and put in the selected interval's index
 * 
 */

public class Find_Right_Interval {

	public int[] findRightInterval(int[][] intervals) {
		
		int len = intervals.length;
		Map<int[], Integer> map = new HashMap<>();
		
		for (int i = 0; i < len; i ++ ) {
			map.put(intervals[i], i );
		}
		
        Arrays.parallelSort( intervals, (x,y) -> {
        	return x[0] - y[0];
        });
        
        int[] res = new int[len];
        
        for (int i = 0; i < len; i ++ ) {
        	int idx = map.get(intervals[i] );
        	int end = intervals[i][1];
        	
        	int left = i + 1, right = len - 1;
        	
        	while (left < right) {
        		int mid = left + (right - left ) / 2;
        		
        		if (intervals[mid][0] >= end) {
        			right = mid;
        		} else {
        			left = mid + 1;
        		}
        	}
        	
        	res[idx] = ( right == i || intervals[right][0] < end )? -1: map.get(intervals[right] );
        }
        
        return res;
        
    }	
	
}
