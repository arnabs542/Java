package medium;

import java.util.List;

//https://www.hackerrank.com/challenges/coin-change/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This is a Dynamic programming problem which is said to be similar to the 01 Knapsack problem. We are going to generate all the possible
 * 	number of combinations from coin value 0 up to n, and from empty set of coins, and slowly include one coin at a time, until we've ended
 * 	up with original set of coins, and the goal of n.
 * 
 * 	The intuition behind the problem is, when I just introduced a new coin into the set, I will be making choice to either use this coin
 * 	or not. If I don't want to use this coin, I will have to look above the number of ways to form the same goal value without the coin.
 * 	If I do use the coin, then I will have to look at the number of ways to form the goal with deducted coin value.
 * 
 * 	Initially with empty set of coins / no coins, the number of ways to form any goal is 0, because we don't have any coins at all, except
 * 	if the goal is 0 itself, then there will always be 1 ways, regardless of any coins we have.
 * 
 * 	Therefore at any grid, If we do choose to include the coin, we have to add up the ways: Not including the coin and including the coin.
 * 		Not including the coin: Look at one row above, which is the coin set without this current coin, with same goal.
 * 		Including the coin	  : Look at the column of thisGoal - coinVal. This is asking how many ways to reach goal if i used the coin.
 * 
 * 	Since the problem only uses row above and columns behind, the space complexity can actually be optimized to only use up 2 row or even
 * 	1 row.
 */

public class The_Coin_Change_Problem {
	
	public static long getWays(int n, List<Long> c) {
		int row = c.size();
		int col = n + 1;
		long[][] dp = new long[row][col];
		
		for (int i = 0; i < row; i ++ )
			dp[i][0] = 1;
		
		for (int i = 1; i < col; i ++ ) {
			int pos = (int) (i - c.get(0));
			System.out.println("pos " + pos);
			dp[0][i] = (pos >= 0)? dp[0][pos] : 0; 
		}
		
		for (int r = 1; r < row; r ++ ) {
			for (int vert = 1; vert < col; vert ++ ) {
				int useCoinPos = (int) (vert - c.get(r));
				dp[r][vert] = dp[r-1][vert] + ( (useCoinPos >= 0)? dp[r][useCoinPos] : 0);
			}
		}
		
		for (int r = 0; r < row; r ++ ) {
			for (int vert = 0; vert < col; vert ++ ) {
				System.out.print(dp[r][vert] + " ");
			}
			System.out.println();
		}
		
		return dp[row - 1][n];
    }
}
