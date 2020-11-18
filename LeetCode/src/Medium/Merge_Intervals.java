package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/merge-intervals/

/*
 * 	This is a interval arrays problem, which mostly involves sorting
 * 
 * 	Given a list of intervals and we have to sort them. First of all, those intervals may be jumbled up, so we
 * 	have to sort them so that from left to right it is easier to see and process
 * 
 * 	Sort by the interval start point is enough. 
 * 
 * 	We will have a pivot interval at all times, which is the interval that has the lowest "local" start point.
 * 	The next intervals may be merged, so we have to check it.
 * 	While the next interval's start point is less than previous interval's end point, the interval may be merged
 * 	and we update the end pointer to be maximum of end pointer itself or the current intervals end point.
 * 
 * 	The time when we need to actually proceed to new pointer is when the next interval's start point is greater than
 * 	the recorded end point. Then, push the last recorded interval into result, and proceed to the new interval.
 */

public class Merge_Intervals {
	public int[][] merge(int[][] intervals) {
		final int size = intervals.length;
		
		Arrays.sort( intervals, (x,y)-> {
			return x[0] - y[0];
		});
		
		List< int[] > res = new ArrayList<>();
		
		int idx = 1;
		int start = intervals[0][0], end = intervals[0][1];
		
		while (idx < size) {
			if (end >= intervals[idx][0] )
				end = Math.max( intervals[idx][1] , end);
			else {
				int[] interval = new int[2];
				interval[0] = start;
				interval[1] = end;
				res.add( interval );
				
				start = intervals[idx][0];
				end = intervals[idx][1];
			}
			idx ++;
		}
		
		int[] interval = new int[2];
		interval[0] = start;
		interval[1] = end;
		res.add( interval );
		
		return res.toArray( new int[res.size()][] );
	}
}
