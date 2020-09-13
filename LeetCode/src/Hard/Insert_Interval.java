package Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/insert-interval/

/*
 * 	The base idea of this whole question is split like this:
 * 		>	Copy all the intervals which doesn't intersect with the newInterval, which is located before (Less than) the newInterval
 * 			(Done by checking each interval's end with the newInterval's start. If the interval's end is less than newInterval's
 * 			 start, they don't intersect so add them to the result arr)
 * 		>	Merge those intervals which intersect with the newInterval into one single large interval.
 * 		>	Then, copy again all intervals which doesn't intersect with the newInterval, but is located after (More than) the
 * 			newInterval
 * 			(Done by checking each interval's start with the newInterval's end. If the interval's start is greater than newInterval's
 * 			 end, they don't intersect so add them to the result arr)
 * 
 * 	
 * 	A trick here is we can actually perform binary search here to skip over iterating through the intervals which intersect with
 * 	the newInterval. This proves effective if the interval set is large, and there are a lot of intervals intersecting.
 * 
 * 	The core idea of binary search is:
 * 		I will find the first interval (LEFT), which its start value is less than that of newInterval's start value, but its
 * 		next interval must has the start value greater or equal to target start. See:
 * 
 * 			___		|		______		|	    ___			|	___
 * 		___			|	______			|	___     ____	| __________ ___	
 * 		 ^				  ^					 ^					^
 * 
 *	 	This interval selected is the crucial one. It either means the newInterval must be inserted after the selected left interval
 *		(Case 1, 3), or merge the newInterval's start to be left interval's start (Case 2, 4)
 * 	
 * 		
 * 		Then I will find another first interval (RIGHT), which its end value is always greater than the newInterval's end,
 * 		but its previous interval must has its end value lesser or equal to the newInterval's end value
 * 
 * 		_________		|		______		|		_______		|		___
 * 		___ ________	| ____________ ___	|	__			__	| ______ ______
 * 				^						^					 ^				^
 * 
 * 		This interval selected is the crucial one. It either means the newInterval must be inserted before the selected right interval
 * 		(Case 2, 3), or merge the newInterval's end value to be right interval's end (Case 1, 4)
 * 
 * 
 * 		There are 2 edge cases we have to consider for:
 * 		>	All intervals are greater than the newInterval.
 * 			This means the left pointer and right pointer will all be index 0.
 * 			Then the toBeInserted interval start value must be kept as it is, and anything else will be dealt in the right Pointer
 * 			part
 * 		>	All intervals are lesser than the newInterval.
 * 			This means the left pointer and right pointer will all be index (len - 1)
 * 			Then the toBeInserted interval end value must be kept as it is, don't need to worry about the left Pointer part
 */


public class Insert_Interval {
	
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		
		int len = intervals.length;
        
        if (len == 0) return new int[][]{ newInterval };
		
		//	Binary search
		int left = 0;
		int right = len - 1;
		int leftPt, rightPt;
		
		while (left < right) {
			//	Biased to right to prevent infinite loop
			int mid = left + (right - left ) / 2 + 1;
			
			//	Interval's start is greater or equal to newInterval's start.
			if ( intervals[mid][0] >= newInterval[0] ) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		leftPt = left;
		
		left = 0;
		right = len - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			
			//	Interval's end is lesser or equal to newInterval's end
			if ( intervals[mid][1] <= newInterval[1] ) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		rightPt = right;
		
		// System.out.println("Left pt: " + leftPt + ", Right pt: " + rightPt);

		
		List<int[]> res = new ArrayList<>(len + 1);
		int[] leftInt = intervals[leftPt];
		int[] rightInt = intervals[rightPt];
		
		
		//	Copy all the intervals before the left Pointer's interval
		for(int i = 0; i < leftPt; i ++ ) {
			res.add( Arrays.copyOf( intervals[i], 2) );
		}
		
		
		//	If the left interval's is less than target's start, this interval does not intersect. Just add that
        if ( leftInt[1] < newInterval[0] ) {
			res.add( Arrays.copyOf( leftInt, 2) );
		} 
        //	Otherwise, there is intersection between left's interval and newInterval. Make the toBeInserted interval start
        //	to be left interval's start value
        //	Here Math.min is used to deal with edge case: All values are greater than newInterval
        else {
			newInterval[0] = Math.min(leftInt[0], newInterval[0]);
		}
		
		
		//	If the right interval's start is greater than target's start, this interval does not intersect. Just add that
        //	newInterval (Modified) into the result. Then add that non-intersecting right interval
        if ( rightInt[0] > newInterval[1] ) {
			res.add(newInterval);
			res.add( Arrays.copyOf(rightInt, 2) );
		}
        //	Otherwise, there is intersection between right's interval and newInterval. Make the toBeInserted interval end
        //	to be right interval's end value
        //	Here Math.max is used to deal with edge case: All values are lesser than newInterval
        else {
			newInterval[1] = Math.max( rightInt[1], newInterval[1] );
			res.add(newInterval);
		}
		
		
        //	Copy all the intervals after the right Pointer into the result
		for (int i = rightPt + 1; i < len; i ++ ) {
			res.add( Arrays.copyOf( intervals[i], 2) );
		}
		
	
		int[][] result = res.toArray( new int[res.size() ][2] );
		// System.out.println( Arrays.deepToString(result) );
		return result;
	}
	
}
