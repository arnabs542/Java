package Hard;

import java.util.Arrays;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

/*
 * 	This is a DP problem.
 * 	We have a series of stock prices, and we are only limited to only just 2 transactions. How would we go about determining
 * 	the maximum profit we can take?
 * 
 * 	We can divide the stock prices into first, and second section. The first section and second section will be
 * 	the maximum profit that we'll reap on that timeframe. By combining the profit on those 2 different timeframes, 
 * 	we essentially had gotten ourselves maximum profit of 2 transactions
 * 
 * 	So we are going to generate 2 different DP arrays, one will indicate the maximum profit which timeframe starting from
 * 	first day and ending at index i of the array (variable ending day)
 * 	
 * 	The second DP array will indicate the maximum profit which timeframe ends at the last day and starting at index i of
 * 	the array (variable starting day)
 * 
 * 	To deal with those individual DP arrays are more simple.
 * 	For DP1, everyday we either have the choice of not selling (Taking max profit from prev day),
 * 			or sell the stocks on this day, which we bought on the minimum price day
 * 	So basically we have to keep track of the minimum price that had occurred in past days
 * 
 * 	For DP2, since we are going backwards from the end, we either has the choice of buying the stock this day, or do nothing
 * 			If we do nothing, then we will buy at some later day, and sell at the maximum price day
 * 			If we choose to buy, then we will profit for (maximum price in timeframe - thisdayprice)
 * 	So basically we have to keep track of the maximum price that had occurred in the timeframe
 * 
 * 	Since the two timeframes shall join together, we shall compare those DP arrays column by column,
 * 	We will return the maximum of DP1[i] + DP2[i]
 * 
 */

public class Best_Time_To_Buy_And_Sell_Stock_III {
	
	public int maxProfit(int[] prices) {
		if (prices.length == 0) return 0;
        int days = prices.length;
        
        //	The first half section DP. 
        int min = prices[0];
        int[] dp1 = new int[days + 1];
        
        for (int i = 1; i <= days; i ++ ) {
        	dp1[i] = Math.max( prices[i - 1] - min, dp1[i - 1] );
        	min = Math.min( min, prices[i - 1] );
        }

        //	The second half section DP.
        int max = prices[days - 1];
        int[] dp2 = new int[days + 1];
        
        for (int i = days - 1; i >= 0; i --) {
        	dp2[i] = Math.max( dp2[i + 1], max - prices[i] );
        	max = Math.max( max, prices[i] );
        }
        
        //	Finally find the maximum combo
        int res = 0;
        for (int i = 0; i < days - 1; i ++ ) {
        	res = Math.max( res, dp1[i+1] + dp2[i] );
        }
        return res;
    }

}
