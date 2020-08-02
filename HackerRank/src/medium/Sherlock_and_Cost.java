package medium;

//https://www.hackerrank.com/challenges/sherlock-and-cost/problem

/*
 * 		THis is a DP problem.
 * 		Problem asks to always select a number from Array B to Array 1 such that the number is always in range [1, B[i] ]
 * 		and the sum of absolute difference of pairs in array A should be maximized.
 * 
 * 		First point to realize: Since we are finding the greatest absolute difference, then each element should be in either one of
 * 								2 values: B[i] (Bounded by array B), or 1 (Minimum)
 * 
 * 		So when dealing with bottom up approach, we should add each value one by one, slowly stacking up back into original problem.
 * 		Each subproblem should be asking what is the maximum absolute difference with this value as last number.
 * 
 * 		Notice that we have to make choices: At the end, we could select it as 1, or B[i]. Let's think of its implications:
 * 
 * 			>	If we select it as B[i] (max), then we need to consider if in array A, the value behind is 1, OR also MAX
 * 			>	If we select it as 1 (min), then we need to consider if in array A, the value behind is 1, OR also MAX as well
 * 
 * 		Therefore since we need to use previously computed results:	
 * 			>	If previous end in MAX in array A
 * 			>	If previous end in 1   in array A
 * 
 * 		So, we should kept two DP arrays:
 * 			1.	The DP where each grid represents maximum absolute difference of pairs, but last value in 
 * 				Array A must end up in highest value B[i]
 * 			2.	The DP where each grid represents maximum absolute difference of pairs, but last value in
 * 				Array A must end up in smallest value 1.
 * 
 * 		Then, when new value was introduced, we need to compute and update for the two arrays.
 * 		Let the latest included value be B[i], last full value be B[i-1], MAX dp array be max and MIN dp array be min
 * 	
 * 			For MAX dp array: (The latest value must be MAX)
 * 				-	If the previous value selected is 1, the formula is
 * 						abs( B[i] - 1 ) + min[i-1]
 * 				-	If the previous value selected is MAX, the formula is
 * 						abs( B[i] - B[i-1] ) + max[i-1]
 * 
 * 			For MIN dp array: (The latest value must be 1)
 * 				-	If the previous value selected is MAX, the formula is
 * 						abs( 1 - B[i-1] ) + max[i-1]
 * 				-	If the previous value selected is MIN, the formula is
 * 						abs( 1 - 1) + min[i-1]
 * 					which is just min[i-1]
 * 
 * 		For each value introduced, compute all 4 of above, and always put the maximum into the respective 2 DP arrays.
 * 		At the end, return the maximum out of the two DP arrays in the last value
 * 
 * 		
 * 		Now notice in DP, we are just using the last value, therefore we could optimize the space complexity from O(n) to O(1)
 * 		by just keeping the previous value
 */

public class Sherlock_and_Cost {
	
//	static int cost(int[] B) {
//		int len = B.length;
//		int[] max = new int[len];
//		int[] min = new int[len];
//		
//		for (int i = 1; i < len; i ++ ) {
//			int maxback1 = Math.abs(B[i] - 1) + min[i-1];
//			int maxbackmax = Math.abs(B[i] - B[i-1] ) + max[i-1];
//			
//			max[i] = Math.max(maxback1, maxbackmax);
//			
//			int minbackmax = Math.abs( 1 - B[i-1] ) + max[i-1];
//			int minback1 = min[i-1];
//			
//			min[i] = Math.max(minbackmax, minback1);
//		}
//		
//		return Math.max( max[len - 1] , min[len - 1] );
//    }
	
	static int cost(int[] B ) {
		int len = B.length;
		int prevmax = 0;
		int prevmin = 0;
		
		for (int i = 1; i < len; i ++ ) {
			int maxback1 = Math.abs( B[i] - 1 ) + prevmin;
			int maxbackmax = Math.abs( B[i] - B[i-1] ) + prevmax;
			
			int minbackmax = Math.abs( 1 - B[i-1] ) + prevmax;
			
			prevmax = Math.max( maxback1, maxbackmax);
			prevmin = Math.max( minbackmax, prevmin );
		}
		
		return Math.max( prevmax, prevmin);
	}
	
}
