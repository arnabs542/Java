package Hard;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/rectangle-area-ii/
//https://github.com/ryancheunggit/leetcode/blob/rise/code/850_solution.py
/*
 * 	This is a Hard line sweep problem.
 * 
 * 	The line sweep algorithm is a computational geometry algorithm which may simulate a sweep across of 
 * 	points in O(N^2 log N) time (at least, in this case)
 * 
 * 	We can simulate a vertical line of infinite length sweeping across the 2D plane, and event triggers
 * 	whenever we touch an opening or closing point of rectangle.
 * 
 * 	Each and every rectangle can be decomposed into 2 events:
 * 		>	Opening event ( At point x, an vertical interval of y1, y2 is opened by the rectangle)
 * 		>	Closing event ( At point x, an vertical interval of y1, y2 is closed by the rectangle)
 * 
 * 	Therefore, we can first preprocess the rectangles by iterating through each and every rectangle, converting
 * 	them into closing and opening Events.
 * 	Note that to account for overlapping, the events in the end, must be sorted so that position of x is 
 * 	non-decreasing.
 * 
 * 	Now, we will need a data structure to store currently active intervals (Rectangles) that are within range
 * 	of the rectangles. 
 * 	On every event, we will first calculate the area covered by the active intervals. Once again, this can be
 * 	thought as another line sweep, as we need to traverse from bottom to top of the intervals using an infinite
 * 	horizontal line. Therefore, we have to sort the active intervals before each line sweep.
 */

public class Rectangle_Area_II {
	
	// Line Sweep Solution in O(N^2 log N) time
	public int rectangleArea(int[][] rectangles) {
		final int MOD = 1000000007;
		final int OPEN = 0, CLOSE = 1;
		
		// Events stored in { x_pos, open/close, lower_y, upper_y }
		List<int[]> events = new ArrayList<>(rectangles.length * 2);
		for (int[] rect: rectangles) {
			events.add( new int[] { rect[0], OPEN, rect[1], rect[3]} );
			events.add( new int[] { rect[2], CLOSE, rect[1], rect[3]} );
		}
		
		// Sort the events
		events.sort( (x, y)-> Integer.compare(x[0], y[0]));
		
		// Active events
		List<int[]> activeIntervals = new ArrayList<>();
		long res = 0;
		int prev_x = events.get(0)[0];
		
		// Iterate through each event points
		// 	>	Calculate area of active events
		//	>	Check event type to see if add to active intervals or remove
		for (int[] e: events) {
			int x_pos = e[0];
			boolean isOpen = e[1] == OPEN;
			int lower_y = e[2];
			int upper_y = e[3];
            
			// Calculate area of active events
			res += calcActiveRectangles( x_pos - prev_x, activeIntervals);
            prev_x = x_pos;
			
			// Check event type
			if (isOpen) {
				activeIntervals.add( new int[] {lower_y, upper_y});
                activeIntervals.sort( (x, y)-> Integer.compare(x[0], y[0]));
			} else {
				// This part can be improved instead of linear scan
				for (int i = 0; i < activeIntervals.size(); ++i)
					if ( activeIntervals.get(i)[0] == lower_y && activeIntervals.get(i)[1] == upper_y) {
						activeIntervals.remove(i);
                        break;
                    }
			}
		}
		
		return (int)(res % MOD);
    }
	
	
	// Helper function to calculate active intervals area
	private long calcActiveRectangles(int x_dist, List<int[]> activeIntervals) {
		if (activeIntervals.isEmpty() ) return 0;
		
		long res = 0;
		int prev_y = Integer.MIN_VALUE;
		
		for (int[] interval: activeIntervals) {
			int begin_y = Math.max(prev_y, interval[0]);
			res += Math.max(0, interval[1] - begin_y);
			prev_y = Math.max(prev_y, interval[1]);
		}
		
		return res * x_dist;
	}
}
