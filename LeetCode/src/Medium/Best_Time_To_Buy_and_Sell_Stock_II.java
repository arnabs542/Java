package Medium;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
/*
 * This is a DP, state machine problem.
 * 
 * In this stock problem, we essentially have to use DP, but how to use it?
 * Everyday, we have the choice of selling the holding stock, or buy the stock, or do nothing.
 * 
 * If we were to sell the holded stock, we need to know when is the last time we buy in the stock at cheapest price.
 * Similarly, when we sell, we need to optimally sell the stock that is bought low, sell high.
 * 
 * The way DP works here is, we will have 2 DP array, both representing 2 states:
 * 	1. At dp1[i], it records maximum money available at end of day i, but we MUST NOT be holding any stock
 * 	2. At dp2[i], it records maximum money available at end of day i, but we MUST BE holding a stock.
 * 
 * Then, everyday, we have choices to update dp array:
 * 
 * 		1. For dp1 (Not holding stock), we can reach this state via:
 * 			- Selling stock. Consider dp2[i-1] + prices[i]
 * 			- Do nothing. Consider dp1[i-1]
 * 
 * 		2. For dp2 (Holding stock), we can reach this state via:
 * 			- Buying stock at current day. Consider dp1[i-1] - prices[i]
 * 			- Do nothing. Consider dp2[i-1]
 * 
 * At the end of day, the maximum profit available is at dp1[i-1], since we shall not hold any stock at the end of day if we
 * want max profit.
 */

public class Best_Time_To_Buy_and_Sell_Stock_II {
	
	public int maxProfit(int[] prices) {
		final int len = prices.length;
		int[] noHold = new int[len];
		int[] gotHold = new int[len];
		gotHold[0] = -prices[0];
		
		for (int i = 1; i < prices.length; ++i) {
	        noHold[i] = Math.max(noHold[i-1], gotHold[i-1] + prices[i]);
	        gotHold[i] = Math.max(gotHold[i-1], noHold[i-1] - prices[i]);
	    }
		
		return noHold[len-1];
    }
	
	
	// Space optimized DP
	public int maxProfit2(int[] prices) {
		int noHold = 0, gotHold = -prices[0];
	    
	    for (int i = 1; i < prices.length; ++i) {
	        int newNoHold = Math.max(noHold, gotHold + prices[i]);
	        int newGotHold = Math.max(gotHold, noHold - prices[i]);
	        noHold = newNoHold; gotHold = newGotHold;
	    }
	    
	    return noHold;
	}
	
}
