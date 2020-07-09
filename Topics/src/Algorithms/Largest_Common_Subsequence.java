package Algorithms;

/*
 * 	Largest Common subsequence is a dynamic programming question which asks us from 2 string, how many connections can be made between the
 * 	2 sequence of characters without the connecting lines intersecting each other
 * 
 * 	Eg: 1 4 2
 * 		1 2 4
 * 	The answer for the above should be 2, since 1 can connect to 1, and 2 can connect to 2, but if now i try to connect 4's, they intersect with
 * 	connection of 2's
 * 
 *	The most basis idea is to use recursion with time complexity of O(2^n), with the algorithm such that:
 *		** i represents index of array A, j represents index of array B
 *
 *	If A[i] == B[j], then i have to return 1 + recursionFunc(i+1, j+1)
 *	Basically what it does is if the connection can be made, this 2 numbers can't be use to make connection anymore. Proceed to the next number
 *	of the 2 arrays
 *
 *	Else, A[i] != B[j], then I will have to return max( recursionFunc(i, j+1) , recursionFunc(i+1, j) )
 *	This is because without matching, i could pick to proceed forward with array A, or proceed with array B. Who knows which one is a better
 *	approach? That's why we use maximum out of the two to decide
 *
 *	Lastly, if i and j is out of bounds, simply return 0. No connections to make here
 *
 *	The bad part of this algorithm is that it will go ahead and compute the same situation again (if it does before), resulting in unnecessary
 *	computations. What if we are able to store the information we've computed before?
 *
 *	------------------------------------------------------------------------------------------------------------------------------------
 *
 *	To store the information we've computed before is simply called Memoization
 *	We would create a grid/ table where x axis and y axis represents array A or B respectively
 *	The algorithm is similar to the above recursion approach but except that instead of instantly returning or perform computation immediately,
 *	we would do the following:
 *
 *		-If we are going to perform recursionFunc at i and j, check the table first if it already stores a value. If it does, we've computed it
 *		 before and we simply immediately return the content at the table without doing recursion again.
 *		-Every time we perform recursion and it returned something, store that "something" into the table before returning to the upper level
 *		 of the recursion stack
 *
 * -----------------------------------------------------------------------------------------------------------------------------------------
 * 
 * With Dynamic programming approach, we could achieve O(n^2) time complexity!
 * Similarly, we have a table but this time, we would have a table of 1 extra row and column, and we're going to utilize starting from row and
 * column 1 only. (row 0 and column 0 will have all 0's)
 * 
 * We simply do a nested for loop to go through the whole table. The algorithm is as follows:
 * 		** Let dpArr represents the dynamic programming array we use to store information
 * 
 * 		- If A[i] == B[j], we store the value of 1 + dpArr[i][j] (Essentially, the previous diagonal direction value) into dpArr[i+1][j+1]
 * 		  > Why i+1, j+1? Because we only utilize the table starting from index 1 for col and row. row 0 and col 0 will have default values of 0
 * 		  > Why 1 + dpArr[i][j]? 1 is the connection we've just made. dpArr[i][j] stores the information about previously done number of connections
 * 			and it's possible only from the previous of A[i] and B[j] (connections cannot overlap)
 * 
 * 		- Else A[i] != B[j], we store the value of max( dpArr[i][j+1], dpArr[i+1][j] ) into dpArr[i+1][j+1]
 * 		  > Why max? Although no connections were made, previously made connections must be carried forward, either from previous array A element
 * 			or previous array B element (That's why its values from above and behind the current block!) 
 */

public class Largest_Common_Subsequence {

	//------------------------Recursion Algorithm-------------------------------------------------------------------
	public static int recursionAlgo(int[] A, int[] B ) {
		return recurse(A,B, 0, 0);
	}
	
	public static int recurse(int[] A, int[] B, int i, int j) {
		//Base case: they've exceeded and no connection can be made here
		if (i >= A.length || j >= B.length ) return 0;
		
		//Connection is made here. Let's proceed with both A and B one step ahead
		else if (A[i] == B[j] )
			return 1 + recurse(A, B, i+1, j+1 );
		
		//No connection is made. Proceed with either A or B. Since we can't decide the optimal move, we have to try both
		else
			return Math.max(
					recurse(A,B, i+1, j),
					recurse(A,B, i, j+1 ) );
					
	}

	//------------------------Memoization Algorithm---------------------------------------------------------------------
	
	public static int recursionAlgoMEMO(int[] A, int[] B ) {
		//used to store the information of previously computed answer here
		int[][] memo = new int[A.length][B.length];
		return recurseMEMO(A,B, 0, 0, memo);
	}
	
	public static int recurseMEMO(int[] A, int[] B, int i, int j, int[][] memo) {
		//Base case: they've exceeded and no connection can be made here
		if (i >= A.length || j >= B.length ) return 0;
		
		//If there is record on the previously computed, simply return it
		if (memo[i][j] != 0) return memo[i][j];
		
		//Always store the information in the memoization table first, only then return it
		else if (A[i] == B[j] ) {
			memo[i][j] = 1 + recurseMEMO(A, B, i+1, j+1, memo);
			return memo[i][j];
		}
		
		else {
			memo[i][j] = Math.max(
					recurseMEMO(A,B, i+1, j, memo),
					recurseMEMO(A,B, i, j+1, memo ) );
			return memo[i][j];		
		}
	}
	
	//------------------------------------------ Dynamic Programming ----------------------------------------------------------------
	
	public static int dpLCS(int[] A, int[] B ) {
		
		//Remember that row 0 and col 0 is reserved for value 0 only. Utilize only from row 1 and column 1 onwards
		int[][] dpArr = new int[A.length+1][B.length+1];
		
		for (int i = 0; i < A.length; i ++ ) {
			for (int j = 0; j < B.length; j ++ ) {
				
				if (A[i] == B[j]) {
					//Stores 1 + the previous diagonal value
					dpArr[i+1][j+1] = 1 + dpArr[i][j];
				}
				else {
					//Stores the maximum of the above value or behind value
					dpArr[i+1][j+1] = Math.max(dpArr[i][j+1], dpArr[i+1][j] );
				}
				
			}
		}
		//Returns the maximum value met so far, which shall always be located in the rightmost below corner of table
		return dpArr[A.length][B.length];
	}
	
	
	
	
	
	public static void main(String[]args) {
		int[] A = {1,3,7,1,7,5};
		int[] B = {1,9,2,5,1};
		
		long time = System.nanoTime();
		System.out.println( recursionAlgo(A, B) );
		System.out.println("Time: " + (System.nanoTime() - time) );
		time = System.nanoTime();
		System.out.println( recursionAlgoMEMO(A, B) );
		System.out.println("Time: " + (System.nanoTime() - time) );
		time = System.nanoTime();
		System.out.println( dpLCS(A, B) );
		System.out.println("Time: " + (System.nanoTime() - time) );
		
	}
	
}
