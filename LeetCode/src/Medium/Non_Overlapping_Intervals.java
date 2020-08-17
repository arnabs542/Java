package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/non-overlapping-intervals/

/*
 * 	This is a Greedy-based interval problem
 * 	
 * 	To fit the maximum possible interval, the best action to take is to always fill in the smallest interval first, then only
 * 	attempt to fill in larger ones. This is because if there is indeed a collision, it is better to just remove 1 large one than
 * 	removing multiple small ones.
 * 
 * 	Can we do it without having to keep an array of ranges to check or some sort? Yes, by imposing an alternative way of sorting.
 * 	We will sort by the following condition:
 * 		>	The interval end point should be sorted by ascending order (Small to Large), so the interval with smaller end point
 * 			should come first
 * 		>	In case of same end point, choose the one with larger start point (Large to small). This is to ensure the starting
 * 			point is as close to the end point, which is exactly our point from above. (Smaller intervals shall fit first)
 * 
 * 	With this way we just need to keep track of the end point covered so far, and will only take in intervals where 
 * 	the start point is larger or equal to the previous end point.
 * 		>	If we encounter an interval where the start point is smaller than the previous end point, that means this interval has
 * 			collided, and since we sorted from samller interval to larger interval, we shall remove the current one, which
 * 			is a larger interval (By removing, we add 1 to counter)
 * 		>	If we encounter an interval where the start point is larger than or equal to the previous end point, there is no collision,
 * 			and since we sorted the array from small interval to large ones, we should take in the current one which is optimal.
 * 			By taking in this one, the end point covered so far will become the current interval's end point
 * 
 * 	Time complexity is O(n log n), caused by sorting
 * 	Space complexity is O(1), we just keep track of a counter, and the previous end point
 */

public class Non_Overlapping_Intervals {
	
	public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.parallelSort( intervals, (x,y) -> {
        	if (x[1] == y[1] ) return y[0] - x[0]; 
        	return x[1] - y[1];
        });
        
        int count = 0;
        int ending = Integer.MIN_VALUE;
        for (int[] interval: intervals) {
        	if (interval[0] > ending)
        		ending = interval[1];
        	else
        		count ++;
        }
        return count;
    }

}
