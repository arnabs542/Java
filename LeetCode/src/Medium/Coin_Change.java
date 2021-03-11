package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/coin-change/
/*
 * 	This is a Dynamic Programming question.
 * 
 * 	From that given amount, we can make multiple choices from the available coins. Once we have selected one coin value,
 * 	the problem is reduced to a subproblem: Find the coinChange for amount-c, where c is the selected coin value.
 * 
 * 	Therefore, we could use DP to solve this. One way is to use bottom up approach, where the base case is amount = 0, which
 * 	requires no coin at all (ans = 0). Then, proceed to evaluate amount = 1, 2... and so on.
 * 	In each amount, we iterate over all coin values, and look back at the memoized amount-c solution, and we plus 1 to it.
 * 	At the end, the answer will be right at dp[amount];
 * 
 * 	------------------------------------------------
 * 	
 * 	Not only bottom-up, but top-down approach is of course plausible. Have a hashMap to help record the past evaluate amounts.
 * 	In each recursive call, if amount is seen before, return immediately, otherwise you have no choice but to evaluate, but 
 * 	remember to record it in HashMap after evaluating so that in the future you won't have to perform repeated calculations!
 */

public class Coin_Change {
	
	//	Dynamic Programming Bottom-up Solution
	public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount+1];
		
		for (int i = 1; i <= amount; ++i) {
			for (int c: coins) {
				int idx = i - c;
				if (idx < 0 || dp[idx] == -1) continue;
				if (dp[i] == 0 || dp[i] > dp[idx] + 1)
					dp[i] = dp[idx] + 1;
			}
			if (dp[i] == 0) dp[i] = -1;
		}
		return dp[amount];
    }
	
	
	//	Memoization Top Down solution
	public int coinChange2(int[] coins, int amount) {
		Map<Integer, Integer> memoi = new HashMap<>();
		memoi.put(0, 0);
		return coinChange2(coins, amount, memoi);
	}
	private int coinChange2(int[] coins, int amount, Map<Integer, Integer> memoi) {
		if (amount < 0) return -1;
		if (memoi.containsKey(amount) ) return memoi.get(amount);
		
		int res = -1;
		for (int c: coins) {
			int attempt = coinChange2(coins, amount-c, memoi);
			if (attempt != -1 && (res == -1 || attempt < res) )
				res = attempt+1;
		}
		memoi.put(amount, res);
		return res;
	}
	
}
