package Arrays;

import java.util.Arrays;

//https://leetcode.com/explore/featured/card/fun-with-arrays/523/conclusion/3228/

/*
 * 	The original solution is copy the array, sort it and count the discrepancies between the two arrays
 * 	
 * 	However we can 'simulate' to sort the array using counting sort algorithm without having to actually sort the array itself
 */

public class Height_Checker {
	
	//Original method to sort and compare the discrepancies
	public int heightChecker(int[] heights) {
        int[] sorted = Arrays.copyOf(heights, heights.length);
        Arrays.parallelSort(sorted);
        
        int move = 0;
        for (int i = 0; i < heights.length; i ++ ) {
        	if (sorted[i] != heights[i] )
        		move++;
        }
        return move;
    }
	
	//Since the bounds stated that each height must be between range [1,100] inclusive
	public int heightCheckerCountSort(int[] heights) {
		int[] bucket = new int[101];
		for (int i: heights) {
			//Since the lower bound is 1, but we still need to shift right by 1, i - 1 + 1 = i itself.
			bucket[i] ++;
		}
		
		for (int i = 1; i < 101; i ++ ) {
			bucket[i] += bucket[i-1];
		}
		
		int discrepancies = 0;
		for (int i = 0; i < heights.length; i ++ ) {
			//Remember that index 0 of bucket points to value of 1
			//Translate: If the index are NOT less than the next value's starting index AND greater than or equal to this value's supposed
			//		     starting index, then increment discrepancies
			if ( !(bucket[heights[i] - 1 ] <= i && i < bucket[heights[i] ] ) ) 
				discrepancies++;
		}
		return discrepancies;
	}
}
