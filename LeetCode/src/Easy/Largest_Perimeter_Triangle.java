package Easy;

//https://leetcode.com/problems/largest-perimeter-triangle/solution/

import java.util.Arrays;
/*
 * 	The properties to form a triangle is: the hypotenuse (longest) must less than (No equals) to the sum of 2 other shorter sides
 * 	h < a + b
 * 	
 * 	Therefore we just sort the array of lengths, and be greedy: take the longest 3 pairs first. If this one is not possible, just proceed to the
 * 	second largest three pairs
 */

public class Largest_Perimeter_Triangle {
	public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        
        for (int i = A.length - 1; i >= 2; i -- ) {
        	if (A[i] < A[i-1] + A[i-2] ) {
        		return A[i] + A[i-1] + A[i-2];
        	}
        }
        return 0;
    }
}
