package Algorithms;

import java.util.Arrays;
import java.util.Stack;

// This is continuation of Jarvis March's Algorithm. Make sure to check that out before proceeding to current algorithm
/*
 * 	In Jarvis's algorithm, for each point we have to iterate through the set of points to find the 'leftmost'/'rightmost'
 * 	point to proceed to.
 * 
 * 	The idea behind the Graham's Scan algorithm:
 * 	>	First, find the bottom most point - btm. If there is several candidates, take the leftmost one.
 * 		This is the point that's 100% included in the convex hull
 * 	>	Sort the set of points based on polar angle from the point btm, counterclockwise (or clockwise).
 * 		Imagine drawing a vertical line down from point btm. The polar angle of point p is the angle
 * 		between the vertical line and the line connecting btm to p.
 * 		(We may use the same function for cross product in Jarvis's algorithm)
 * 	>	Maintain a Stack. The Stack will record the points that are included in convex hull. This is how
 * 		it works:
 * 		-	Intiially, the point btm will be pushed
 * 		-	The second point in sorted set of points will be pushed too.
 * 		-	Now, from third point onwards:
 * 
 * 			Consider current point as p3, point at stack top as p2, and point at second top is p1.
 * 			Check if connecting p1, p2 and p3 makes a right turn. 
 * 
 * 			If it makes a right turn, then it is not possible for p2 to be in the convex hull, since
 * 			p1 > p3 will have covered p2! Therefore, we would discard p2 forever from the stack.
 * 
 * 			If it makes a left turn, so far it is a valid convex hull. Thus, push p3 onto the stack.
 * 
 * 	At the end, the stack will contain all the points of the convex hull.
 * 
 * 	The detailed explaination can be found at leetcode problem or wikipedia:
 * 			https://leetcode.com/problems/erect-the-fence/solution/	
 * 
 */

public class Graham_Scan_Algorithm {
	// Obtains the bottom left point among a set of points.
	// Returns the most bottom point. If there are several candidates, return the leftmost one
	private int[] getBottomLeftPoint(int[][] points) {
		int[] res = points[0];
		
		for (int[] p: points) {
			if (p[0] < res[0] || p[0] == res[0] && p[1] < res[1])
				res = p;
		}
		return res;
	}
	
	// Calculates the cross product of PQ x QR
	// Same as in Jarvis March's Algorithm
	private int crossProduct(int[] p, int[] q, int[]r) {
		return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
	}
	
	// Calculates the distance of two points
	private int calcDistance(int[] p1, int[] p2) {
		return (p1[0]-p2[0])*(p1[0]-p2[0]) + (p1[1]-p2[1])*(p1[1]-p2[1]);
	}
	
	// Swaps two points in points array
	private void swap(int[][] points, int l, int r) {
		int[] temp = points[l];
		points[l] = points[r];
		points[r] = temp;
	}
	
	// Sorts the array based on polar angle referenced from bottom leftmost point provided
	private void sortByAngle(int[][] points, int[] bottomleft_point) {
		// Sort by angle. If angle is same, sort by increasing distance (Closer to bottomleft, is in front)
		Arrays.sort(points, (x, y)-> {
			int angle = crossProduct(x, bottomleft_point, y);
			return angle == 0? calcDistance(x, bottomleft_point) - calcDistance(y, bottomleft_point): angle;
		});
		
		// However, for points that are collinear with bottomleft point, should be sorted such that
		// the furthest distance comes first
		// First, obtain the first collinear point in the sorted list.
		int[] last = points[ points.length - 1];
		int pointer = points.length - 2;
		
		while (pointer >= 0 && crossProduct( points[pointer], bottomleft_point, last) == 0)
			--pointer;
		
		// Reverse sort collinear points using two pointer
		for (int l = pointer+1, r=points.length-1; l < r; ++l, --r)
			swap(points, l, r);
	}
	
	
	// Computes the Convex Hull.
	public int[][] graham_scan(int[][] points) {
		final int len = points.length;
		if (len <= 1) return points;
		
		// Sort by the angle of bottom leftmost point
		sortByAngle(points, getBottomLeftPoint(points));
		
		// Stack to store the hull points
		Stack<int[]> stack = new Stack<>();
		stack.push(points[0]);
		stack.push(points[1]);
		
		// Graham's scan
		for (int i = 2; i < len; ++i) {
			int[] top = stack.pop();
			
			// While the previous point makes a 'right' turn to reach current point, pop it
			while (crossProduct(stack.peek(), top, points[i]) < 0)
				top = stack.pop();

			stack.push(top);
			stack.push( points[i] );
		}
		
		// Extract and return
		return stack.toArray( new int[stack.size()][] );
	}
	
	
}
