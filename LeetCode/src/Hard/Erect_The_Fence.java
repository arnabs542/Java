package Hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/erect-the-fence/
/*
 * 	This is a Computational Geometry - Convex Hull problem.
 * 
 * 	See Topics:
 * 		- Jarvis March COnvex Hull Gift Wrapping Algorithm
 * 		- Graham Scan Algorithm
 * 
 * 	for detailed explaination
 */

public class Erect_The_Fence {

	
	// Computes the cross product of PQ x QR
	private int crossProduct(int[] p, int[] q, int[] r) {
		return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
	}
	
	// Checks if point I is on line PQ
	private boolean isBetween(int[] p, int[] i, int[] q) {
		boolean isInX = i[0] >= p[0] && i[0] <= q[0] || i[0] <= p[0] && i[0] >= q[0];
        boolean isInY = i[1] >= p[1] && i[1] <= q[1] || i[1] <= p[1] && i[1] >= q[1];
        return isInX && isInY;
	}
	

	public int[][] outerTrees(int[][] trees) {
        final int len = trees.length;
		Set<int[]> res = new HashSet<>();
        
        // Locate the leftmost point first
        int leftmost_index = 0;
        for (int i = 0; i < len; ++i) {
        	if (trees[i][0] < trees[leftmost_index][0])
        		leftmost_index = i;
        }
        
        // Start running the algorithm.
        // current_index is the index of current pivot point
        int current_index = leftmost_index;
        do {
        	// Randomly select a point as potential first
        	int potential_index = (current_index + 1) % len;
        	
        	// Determine rightmost by cross product
        	for (int i = 0; i < len; ++i)
        		if (crossProduct(trees[current_index], trees[i], trees[potential_index]) < 0)
        			potential_index = i;
        	
        	// Check for any collinear points
        	for (int i = 0; i < len; ++i) {
        		if (i != current_index && i != potential_index &&
	        			crossProduct(trees[current_index], trees[i], trees[potential_index]) == 0 &&
	        			isBetween(trees[current_index], trees[i], trees[potential_index]) )
        			res.add(trees[i]);
        	}
        	
        	// Officially include the potential rightmost point into our list
        	res.add( trees[potential_index]);
        	// This rightmost point become our current pivot point
        	current_index = potential_index;
        	
        } while (current_index != leftmost_index);
        
        return res.toArray( new int[ res.size() ][] );
    }
	
	
	
	
	
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
	
	
	// Computes the Convex Hull using Graham Scan Algorithm
	public int[][] outerTrees2(int[][] trees) {
		final int len = trees.length;
		if (len <= 1) return trees;
		
		// Sort by the angle of bottom leftmost point
		sortByAngle(trees, getBottomLeftPoint(trees));
		
		// Stack to store the hull points
		Stack<int[]> stack = new Stack<>();
		stack.push(trees[0]);
		stack.push(trees[1]);
		
		// Graham's scan
		for (int i = 2; i < len; ++i) {
			int[] top = stack.pop();
			
			// While the previous point makes a 'right' turn to reach current point, pop it
			while (crossProduct(stack.peek(), top, trees[i]) < 0)
				top = stack.pop();

			stack.push(top);
			stack.push( trees[i] );
		}
		
		// Extract and return
		return stack.toArray( new int[stack.size()][] );
	}

}

