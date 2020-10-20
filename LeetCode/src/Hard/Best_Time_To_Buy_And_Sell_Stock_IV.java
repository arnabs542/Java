package Hard;

import java.util.Arrays;

/*
 * 	This is a typical Dynamic Programming Question
 * 
 * 	Given maximum k transaction actions that we could perform, find out the maximum profit given stock prices.
 * 
 * 	For dynamic programming, we have to think in terms of how the solution to the subproblem can help me solve the larger
 * 	problem.
 * 	In this problem, the subproblems are: Given lower k, what is the maximum profit up to each day?
 * 
 * 	The most base problem is that k=1, find out the maximum profit from day 1, day 2, day 3 until day N.
 * 	Meaning, the DP array shall store in the columns, the maximum profit that I can reap in the range from day 0 until
 * 	the latest day up to day N.
 * 	
 * 	Now, if we know the solution to K=1 the maximum profit of each day spans, what we could do is to try and insert another
 * 	transaction into the transactions, so that number of transactions increase by 1!
 * 	
 * 	The basic algorithm looks like this:
 * 
 * 		For each transaction limits from 0 to N:
 * 			For each day:
 * 				Find out the maximum profit that I can reap with this transaction limit, from day 0 until this day.
 * 
 * 	How would we find out the maximum profit? We can try to loop through the previous days, and find the maximum such that
 * 
 * 				PROFIT FROM THIS TRADE						INHERIT THE PREVIOUS DAY'S MAX PROFIT
 * 		( price of current day - price of prev day ) + ( prev day's prev day Maximum Profit (DP)  )
 * 	is the largest.
 * 
 * 	The DP array would be a 2D array where rows are transaction counts from 0 to K, and columns are day 0 to N.
 * 	This would end up in time complexity of O(N^2 * K ), in addition to looping through the 2D array, each day we have to
 * 	iterate backward to see the maximum profit if I decided to sell on this day.
 * 
 * 	Let's say the day = 4. See those iterations:
 * 			price[4] - price[3] + dp[2]
 * 			price[4] - price[2] + dp[1]
 * 			price[4] - price[1] + dp[0]
 * 	Notice that for the maximum price to form, the dp[] - price[] part must be maximum! Therefore, we can remove the
 * 	necessity to iterate backwards by only keeping track of the maximum difference!
 * 
 * 	For more information: https://www.youtube.com/watch?v=oDhu5uGq_ic&t=697s&ab_channel=TusharRoy-CodingMadeSimple
 */

public class Best_Time_To_Buy_And_Sell_Stock_IV {
	
	/*
	 * 	2D array solution, time O(KN), space O(KN) or O(N^2)
	 */
	public int maxProfit(int k, int[] prices) {
		int len = prices.length;
		k = Math.min( k , len / 2 );
		
		//	The rows (y) are the transaction counts. The columns (x) are the days.
		//	The first row represent transaction = 0, which it shall be all 0 (No transaction could be done)
		//	The first column represent day 1 of trading
		int[][] dp = new int[k + 1][ len ];
		
		//	Each transaction loop
		for (int t = 1; t <= k; t ++ ) {
			
			int prevMax = dp[t - 1][0] - prices[0];
			
			//	Each trading day loop
			for (int d = 1; d < len; d ++ ) {
				dp[t][d] = Math.max(
						dp[t][d-1],				//	No transaction done today, so take previous result
						prices[d] + prevMax		//	Transaction is done with maximum value possible.
					);
				
				prevMax = Math.max( prevMax, dp[t-1][d-1] - prices[d] );	//	Update the maximum difference (profit + buy)
			}
		}
		
		return dp[k][len - 1];
    }	
	
	/*
	 * 	Compressed DP array solution
	 * 	Time O(KN), Space O(KN) or O(N^2)
	 */
	public int maxProfit2(int k, int[] prices) {
		int len = prices.length;
		if (len == 0) return 0;
		k = Math.min( k , len / 2 );
		
		//	primaryDP is the row that we shall be using as DP array in this turn
		//	storedDP is the LAST ROW of the DP array.
		//	At the end of one loop, they will be switched
		int[] primaryDP = new int[len];
		int[] storedDP = new int[len];
		
		//	Each transaction loop
		for (int t = 1; t <= k; t ++ ) {
			
			int prevMax = storedDP[0] - prices[0];
			
			//	Each trading day loop
			for (int d = 1; d < len; d ++ ) {
				primaryDP[d] = Math.max(
						primaryDP[d-1],				//	No transaction done today, so take previous result
						prices[d] + prevMax		//	Transaction is done with maximum value possible.
					);
				
				prevMax = Math.max( prevMax, storedDP[d-1] - prices[d] );	//	Update the maximum difference (profit + buy)
			}
			
			//	Exchange the references to array (Total 2 arrays there), so we can use one of arrays without affecting
			//	the DP result;
			int[] temp = primaryDP;
			primaryDP = storedDP;
			storedDP = temp;
		}
		
		return storedDP[len - 1];
    }	
	
}

