package Medium;

//https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/

/*
 * 	Given a set of possible pair of coordinates available from the rectangles, we shall choose one from those possible coordinates
 * 	randomly, such that each coordinates has equal chance to being choosen
 * 
 * 	Now if we were to choose a rectangle first, then only choose a point from the rectangle, then each point wouldn't have the
 * 	equal chance to be selected. Since each rectangle has equal chance to be selected, regardless of their size,
 * 	each individual point in the larger rectangle actually has smaller chance to being selected
 * 
 * 	To solve this problem, larger rectangle deserves larger chance of being selected than the smaller rectangle.
 * 	This is done by setting up a cumulative area array, Eg if we have rectangles of area 10, 20, 30:
 * 
 * 		Cumulative area array:		10, 30, 60
 * 
 * 	Then we will generate a random int from 1 to 60 inclusive.
 * 		1 - 10:	Rect 1
 * 		11 - 30: Rect 2
 * 		31 - 60: Rect 3
 * 
 * 	With this, we will do binary search to find the respective rectangle. We will find the least element which has value larger than
 * 	or equal to the target value. Eg:	15 will belong to Rect 2, and 60 will belong to Rect 3
 * 
 * 	Therefore If the mid value is smaller than the target, then left = mid + 1 (mid couldn't be the value)
 * 			If the mid value is larger than or equal to target, then right = mid (mid could be the value)
 * 
 * 	With that, we can choose a rectangle with bias, where larger triangle will have larger chance of being selected
 * 
 */

import java.util.Random;
import java.util.TreeMap;

public class Random_Point_In_Non_Overlapping_Triangles {
	
	class Solution {
		
		int[][] rects;
		
		int[] cum;
		
		int sum;
		Random rand;

		
	    public Solution(int[][] rects) {
	    	this.rects = rects;
	    	
	    	cum = new int[rects.length];
	    	
	    	int cum = 0;
	    	for (int i = 0; i < rects.length; i ++ ) {
	    		int[] rect = rects[i];
	    		cum += ( Math.abs(rect[0] - rect[2]) + 1) * (Math.abs(rect[1] - rect[3]) + 1);
	    		this.cum[i] = cum;
	    	}
	    	
	    	this.sum = cum;
	    	this.rand = new Random();
	    }
	    
	    public int[] pick() {
	        int chose = rand.nextInt(sum) + 1;
	        
	        int left = 0, right = cum.length - 1;
	        
	        while (left < right) {
	        	int mid = left + (right - left ) / 2;
	        	
	        	if (cum[mid] < chose) {
	        		left = mid + 1;
	        	} else {
	        		right = mid;
	        	}
	        }
	        
	        return pickFromRect( rects[left] );
	    }
	    
	    
	    private int[] pickFromRect( int[] rect) {
	    	int leftx = rect[0], rightx = rect[2], lefty = rect[1], righty = rect[3];
	    	
	    	int randx = rand.nextInt( Math.abs(leftx - rightx) + 1) + leftx;
	    	int randy = rand.nextInt( Math.abs(lefty - righty) + 1) + lefty;
	    	return new int[] {randx, randy};
	    }
	}

}
