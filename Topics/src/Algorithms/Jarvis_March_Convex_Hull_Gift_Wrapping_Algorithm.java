package Algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 	The Jarvis March's gift wrapping / convex hull algorithm is a computational geometry algorithm used
 * 	to compute the convex hull for given set of points.
 * 
 * 	Given a set of points on 2D plane, you are going to use some points from it to enclose all the points,
 * 	such that the connected points are minimum in length.
 * 
 * 	Eg:
 * 				A
 * 			  B
 * 	C			D 		E
 * 			F
 * 
 * 	In this case, You would form a parameter from points A > C > F > E > A, thus enclosing all the points in
 * 	shortest distance possible.
 * 
 * 	If it is still hard to imagine, think of the points as thumbtacks on a table. Now you are going to take
 * 	a rubber band and stretch until covering all the thumbtacks. Once you release, the rubber band will 
 * 	retract and form the convex hull around the thumbtacks. You get the idea.
 * 
 * 	One example for the usage, is for robots to avoid obstacles in shortest path. Think of the points as
 * 	obstacles!
 * 
 * 	--------------------------------------------------------------------
 * 
 * 	The idea behind Jarvis March's algorithm is to:
 * 
 *	-	Start with the leftmost point in the plane. In this case, it is C
 *	-	From C, locate the leftmost / rightmost point to the leftmost point. This is done by an iteration
 *		through all the points. It may be A or F in this case, depending on the implementation
 *	-	Then, we would draw a line connecting C and A (or F). Then, the process repeats. From A, we would
 *		find the leftmost point as if viewed from the perspective of point A.
 *
 *	This may sound weird. How do we find a leftmost/rightmost point relative to a point? Turns out, we
 *	will have to use the cross product to find out.
 *
 *	First we will have a source point A that we want to find a point that is leftmost to it. Then, randomly
 *	select one of the other points B as the potential leftmost point to A.
 *	Now, iterate through other points C. We will be able to see we can form vectors AB and AC.
 *	Then, perform cross product of ABxAC. We will be able to easily know if point B is at the left of C depending
 *	on the result of cross product is negative or positive.
 *
 * 	By now, you should realize it doesn't matter if we find the leftmost or rightmost point, as long as we are
 * 	consistent in finding leftmost/rightmost point throughout the algorithm.
 * 
 * 	However, there will be cases of collinear. If some points are collinear, the cross product would be 0,
 *  yet they should be added to result too. After the search on the leftmost point, we would run one more
 *  iteration to check for any collinear points, and adding it to results if exist.
 */

public class Jarvis_March_Convex_Hull_Gift_Wrapping_Algorithm {
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
	
	public int[][] jarvis_march_algorithm(int[][] points) {
        final int len = points.length;
		Set<int[]> res = new HashSet<>();
        
        // Base case: If less than 4 points, then all points must be involved
        if (points.length < 4) {
        	res.addAll( Arrays.asList(points) );
        	return res.toArray( new int[ res.size() ][] );
        }
        
        // Locate the leftmost point first
        int leftmost_index = 0;
        for (int i = 0; i < len; ++i) {
        	if (points[i][0] < points[leftmost_index][0])
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
        		if (crossProduct(points[current_index], points[i], points[potential_index]) < 0)
        			potential_index = i;
        	
        	// Check for any collinear points
        	for (int i = 0; i < len; ++i) {
        		if (i != current_index && i != potential_index &&
	        			crossProduct(points[current_index], points[i], points[potential_index]) == 0 &&
	        			isBetween(points[current_index], points[i], points[potential_index]) )
        			res.add(points[i]);
        	}
        	
        	// Officially include the potential rightmost point into our list
        	res.add( points[potential_index]);
        	// This rightmost point become our current pivot point
        	current_index = potential_index;
        	
        } while (current_index != leftmost_index);
        
        return res.toArray( new int[ res.size() ][] );
    }
	
}
