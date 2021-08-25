package Medium;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/sum-of-square-numbers/
/*
 * This is a Math, Two pointers problem with a lot of possible solutions.
 * 
 * C will go up to INT32_MAX, which is inefficient to even do O(C) solution. Instead, let's think:
 * 
 * 	> For a^2 + b^2 = c, the possible value of a and b couldn't be possibly be larger than sqrt(c).
 * 	
 * Therefore, first is that we can use a HashSet to record past seen squares. Iterating from 0 to sqrt(c),
 * say i, we compute i*i, add to the set, and check if [c - i*i] exists in the set. If yes, then immediately
 * it is true.
 * 
 * ------------
 * 	
 * Using two pointers, set l and r to be both ends of 0 and sqrt(c). Compute the square of l*l + r*r.
 * If value is large, decrease r. Else if value is small, increase l. Loop until found, or invalid case when
 * l > r
 */

public class Sum_of_Square_Numbers {
	
	// Set solution in O(sqrt(N)) time and space
	public boolean judgeSquareSum(int c) {
		Set<Integer> squares = new HashSet<>();
		int root = (int)Math.sqrt(c);
		
		for (int i = 0; i <= root; ++i) {
			int sq = i * i;
			squares.add(sq);
			
			if (squares.contains(c - sq))
				return true;
		}
		return false;
    }
	
	// Two pointers solution
	public boolean judgeSquareSum2(int c) {
		int root = (int)Math.sqrt(c);
		
		for (int l = 0, r = root; l <= r; ) {
			int sqr = l * l + r * r;
			if (sqr == c) return true;
			else if (sqr > c) --r;
			else ++l;
		}
		return false;
    }
	
}
