package Easy;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
/*
 * 	This is a Array problem, with a little element of DP inside.
 * 
 * 	We want to find out the minimum and maximum element in the prices array, with the catch that the maximum
 * 	element must be after the minimum element. In other words, min cannot be after max.
 * 
 * 	We can keep a global result of profit, which represents the maximum profit so far, which will be returned
 * 	at the end of the problem. Initially, the value is set to 0 because at start we have no profit whatsoever
 * 
 * 	Also, keep a min variable. It will keep track of the minimum element met so far. It is initialized to INF
 * 	so it will be updated in the first iteration
 * 
 * 	
 * 	Now, run a single iteration through the prices array. If current element is smaller than min, update the min.
 * 	Then, update the profit whether if I select this day to sell the 'min' stock, will yield higher profit
 */


public class Best_Time_To_Buy_And_Sell_Stock {
	
	public int maxProfit(int[] prices) {
		int glob_res = 0;
		int min = Integer.MAX_VALUE;
		
		for (int i: prices) {
			min = Math.min(min, i);
			glob_res = Math.max(glob_res, i - min);
		}
		return glob_res;
    }
}
