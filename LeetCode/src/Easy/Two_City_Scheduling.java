package Easy;

import java.util.Arrays;

//https://leetcode.com/problems/two-city-scheduling/

/*
 * 	A really brute force solution would be checking every possible combination of people going to city A or B. This way would be very
 * 	inefficient and results in time	limit exceeded.
 * 
 * 	An O(n log n) -greedy sorting solution would be to sort every individual differences of going to city A compared to B. In other words, how
 * 	much would I save if I go to city A compared to B?
 * 	Now if the solution is sorted in descending, money saved if this person goes to city A instead of B, and we know exactly N person need to
 * 	go to city A, we would take the first half of the array to A, and the other half to B!
 */

public class Two_City_Scheduling {

//	public int twoCitySchedCostRecurse(int[][] costs) {
//		return recurse(costs, 0, 0, 0);
//    }
//	
//	public static int recurse(int[][] costs, int candidate, int cityA, int cityB) {
//		//Base case. All candidates dealt with
//		if (candidate >= costs.length)
//			return 0;
//		
//		//City A spaces is full
//		if (cityA >= costs.length / 2) {
//			return costs[candidate][1] + recurse(costs, candidate + 1, cityA, cityB + 1);
//		}
//		//City B spaces is full
//		if (cityB >= costs.length / 2) {
//			return costs[candidate][0] + recurse(costs, candidate + 1, cityA + 1, cityB);
//		}
//		
//		//Both haven't full. Try both cases
//		return Math.min( 
//				costs[candidate][0] + recurse(costs, candidate + 1, cityA + 1, cityB), 
//				costs[candidate][1] + recurse(costs, candidate + 1, cityA, cityB + 1) );
//	}
	//Really inefficient solution, but that's how recursion would work
	
	
	//My attempted sorting solution but is not optimal since i thought about finding absolute difference only
//	public int twoCitySchedCost(int[][] costs) {
//		Arrays.sort(costs, (x, y) -> {
//			return Math.abs(y[0] - y[1]) - Math.abs(x[0] - x[1]);
//		});
//		int budget = 0;
//		int cityA = 0;
//		int cityB = 0;
//		
//		for (int i = 0; i < costs.length / 2; i ++ ) {
//			if (costs[i][0] < costs[i][1] ) {
//				cityA ++;
//				budget += costs[i][0];
//			}
//			else {
//				cityB ++;
//				budget += costs[i][1];
//			}
//		}
//		for (int i = costs.length / 2; i < costs.length; i ++ ) {
//			if (cityA >= costs.length / 2) 
//				budget += costs[i][1];
//			else if (cityB >= costs.length / 2) 
//				budget += costs[i][0];
//			else {
//				if (costs[i][0] < costs[i][1] ) {
//					cityA ++;
//					budget += costs[i][0];
//				}
//				else {
//					cityB ++;
//					budget += costs[i][1];
//				}
//			}
//		}
//		return budget;
//    }
	
/*
 * 	As usual, this can be done with a dynamic programming solution (But sorting solution has better time complexity)
 * 	
 * 	In the dynamic programming solution, we use a 2D array, where the column will represent NO OF PPL THAT HAS GONE TO B
 * 																  the row represent NO OF PPL THAT HAS GONE TO A
 *  Therefore the array should be a square matrix of N+1 in size, where the rightmost corner represents nobody left to go A or B (N + N = 2N)
 *  
 *  Every move to the right means the candidate goes to city B, and moves downwards means candidate goes to city A.
 *  
 *  For the middle blocks, we would have to select either the candidate goes to A or B, whatever is cheaper
 */
	
	public static int twoCitySchedCostDP(int[][] costs) {
		int N = costs.length / 2;
		int[][] dp = new int[N+1][N+1];
		
		// dp[0][0] shall remain 0 as at this state, 0 people goes to A and 0 people goes to B
		
		//Remember that each move to the right (+col) represents one people go to city B
		for (int col = 1; col <= N; col++ ) {
			dp[0][col] = dp[0][col-1] + costs[col - 1][1];
		}
		//Remember that each move to downwards (+row) represents one people go to city A
		for (int row = 1; row <= N; row ++ ) {
			dp[row][0] = dp[row-1][0] + costs[row - 1][0];
		}
		
		//Now we begin filling the intermediates; We have to choose from the two options:
		//	dp[row-1][col] + costs[row+col-1][0] means from previous position which is above, move DOWNWARDS, which means the person going
		//		to city A.
		//	dp[row][col-1] + costs[row+col-1][1] means from previous position which is left, move RIGHT, which means the person going to
		//		to city B.
		
		//Notice that row + col - 1 represents which candidate is selected. Eg: row = 1, col = 1 shall be candidate selected is candidate 1 
		//	(from index 0)
		for (int row = 1; row <= N; row ++ ) {
			for (int col = 1; col <= N; col ++ ) {
				dp[row][col] = Math.min( dp[row-1][col] + costs[row + col - 1][0] , dp[row][col-1] + costs[row + col - 1][1] );
			}
		}
		return dp[N][N];
		
	}
	
	//Instead of doing the real sort, we actually only care that the first N people is among the highest budget saving out there. Therefore
	//we could do a quick select instead for faster execution time
	public static int twoCitySchedCost(int[][] costs) {
		Arrays.sort(costs, (x,y) -> {
			return (x[0] - x[1] ) - (y[0] - y[1] );
		});
		int budget = 0;
		for (int i = 0; i < costs.length / 2; i ++ ) {
			budget += costs[i][0];
		}
		for (int i = costs.length / 2; i < costs.length; i++ ) {
			budget += costs[i][1];
		}
		return budget;
	}
	
}		//end of class
