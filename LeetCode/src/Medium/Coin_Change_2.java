package Medium;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3353/

/*
 * 	A dynamic programming problem. In the dp table,
 * 		- Across horizontal column represents the goal of coin value we're reaching for, from 0 to n
 * 		- Across vertical row represents the coins we will be including, from { } to the set including all coins
 * 		- Each table cell represents the number of ways to form to the goal coin value from the current set of coins
 * 
 * 	To determine the value to put in each cell, we consider the following:
 * 		The ways to form to the goal value, is to not include the last coin, or to include the last coin.
 * 		-	Not including the last coin, it's essentially just the same number of ways to form the same goal without the last coin (value above)
 * 		-	Including the last coin, it's essentially asking number of ways to form the goal of (goal - coin value) using the same set of coins
 * 			(Same row, but (goal - coin value) column
 * 	
 * 		Since we think of both, we have to plus them together.
 * 
 * 	The initialization step is that, given an empty set of coins, the ways to form the goal is always 0, except if the goal itself is 0, then
 * 	it is 1
 * 	For the goal value itself is 0, the ways to form it is always 1
 */

public class Coin_Change_2 {

//	//Recursive approach with memoization implementation
//	public static int change(int amount, int[] coins) {
//		if (coins.length == 0) return 0;
//		
//		int[][] memoir = new int[coins.length][amount+1];
//		return recursion(0, coins, 0, amount, memoir);
//    }
//	public static int recursion(int current, int[] coins, int index, int amt, int[][] memoir) {
//		if (current > amt) return -1;
//		if (current == amt) return 1;
//		if (memoir[index][current] != 0) return memoir[index][current];
//		
//		for (int i = index; i < coins.length; i ++ ) {
//			int returnV = recursion(current + coins[i], coins, i, amt, memoir);
//			memoir[index][current] += (returnV == -1)? 0: returnV;
//		}
//		return memoir[index][current];
//	}
	
	public static int change(int amount, int[] coins) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		
		//Initialization
		for (int row = 0; row < dp.length; row ++ ) {
			dp[row][0] = 1;
		}
		
		//Here each row represents the coins included up to the value of row
		//Thus row 1 means 1 coin included, row 2 means 2 included etc...
		for (int row = 1; row < dp.length; row ++ ) {
			int coinVal = coins[row-1];
			//Here each col represents the goal value, from 1 up to the final amount
			for (int col = 1; col < dp[0].length; col ++ ) {
				//The goal is smaller than the single coin val here. It's impossible to include the coin here, therefore we just
				//take the number of ways to form this goal without this coin
				if (coinVal > col)
					dp[row][col] = dp[row-1][col];
				//Else we have to consider both taking in the coin and not taking in the coin. Add both ways together
				else
					dp[row][col] = dp[row-1][col] + dp[row][col - coinVal];
			}
		}
		
		//Return the rightmost bottom value of the table, since it is the goal value column, and includes all coins in the set.
		return dp[dp.length - 1][dp[0].length - 1];
	}
	
	
	
	public static void main(String[]args) {
		int[] coins = {1,2,5};
		int amount = 5;
		System.out.println(change(amount, coins) );
	}
}
