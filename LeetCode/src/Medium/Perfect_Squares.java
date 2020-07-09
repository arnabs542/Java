package Medium;

import java.util.HashSet;
import java.util.Iterator;

//https://leetcode.com/problems/perfect-squares/

/*
 * 	The 2 main ways to solve this question is to use Dynamic programming or Breath First Search. Although it can be solved using mathematics,
 * 	(Lagrange's Four Square Theorem)
 * 
 * 	For dynamic programming, We must first realize for any integer n, the only plausible perfect squares that can go into it are only in the
 * 	range 1 to sqrt(n). What happens to n if we choose each possible combinations of perfect squares from 1 to sqrt(n)?
 * 
 * 	THerefore we could use bottom up approach for this. We know that for n = 0, the least number of perfsqr to add up is 0. For 1, it will
 * 	find out the ranges 1 to sqrt(n), which is 1 itself. After using the 1 as one of perfsqr, the remaining is 0. THerefore the solution
 * 	is 1 + perfsqr(0), which is 1. This continues until n and we'll get the answer.
 * 
 * 	---------------------------------------------------
 * 
 * 	For BFS approach, we start out with the root node as n itself. Then similarly, we find out the ranges from 1 to sqrt(n). We'll create
 * 	the next depth of nodes which is every possible subtraction of the n with the perfsqr. This continues until we have met the first subtraction
 * 	to 0, which we shall return the layer depth as the answer. Note the layer depth at root node is 0 (So if n = 0, returns 0)
 */

public class Perfect_Squares {
	
	public static int numSquares(int n) {
		//We make the array which store the minimum number of steps to use perfect square number to add up to the index number itself.
		//Index number 1 represents n = 1, which there is 1 step to add up
		int[] perfsqr = new int[n + 1];
		
		//The number we'll be processing, starting from index 2 which is number 2
		for (int i = 1; i <= n; i ++ ) {
			int range = (int)Math.floor( Math.sqrt(i) );
			int minstep = Integer.MAX_VALUE;
			
			for (int j = 1; j <= range; j ++ ) {
				int result = 1 + perfsqr[i - j * j];
				minstep = Math.min(minstep, result);
			}
			perfsqr[i] = minstep;
		}
		
		return perfsqr[n];
	}
	
	public static int numSquaresBST(int n) {
		HashSet<Integer> set = new HashSet<>();
		set.add(n);
		int layer = 0;
		
		Iterator<Integer> it;
		while ( !set.isEmpty() ) {
			HashSet<Integer> newSet = new HashSet<>();
			it = set.iterator();
			layer ++;
			
			while (it.hasNext() ) {
				int next = it.next();
				int range = (int)Math.sqrt(next);
				
				for (int i = 1; i <= range; i ++ ) {
					int append = next - i * i;
					if (append == 0) return layer;
					newSet.add(append);
				}
			}
			set = newSet;
		}
		return -1;
	}
	
	public static void main(String[]args) {
		for (int i = 1; i < 100; i ++ ) {
			System.out.println(i + " is " + numSquares(i) );
		}
	}
}
