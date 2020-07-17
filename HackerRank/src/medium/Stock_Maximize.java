package medium;

import java.util.List;

//https://www.hackerrank.com/challenges/stockmax/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This is a Dynamic Programming question.
 * 	We will be doing a bottom-up approach where we will start from day 1's stock opening and determine the maximum profit for that day.
 * 	Each day, we are able to make 2 choices:
 * 		-Either try to sell the previous day's stock, or
 * 		-Do nothing that day. No sell, no buy. Nothing.
 * 		
 * 		-Notice we don't have the choice to buy in 1 unit of stock. That will only create loss because the stock bought are unable to sell
 * 
 * 	By doing nothing at the day, it's easy to determine the maximum profit: Just take the previous day's maximum profit
 * 	The harder part is to sell the previous day's stock. How we know from the previous days, when is the starting point to start buying
 * 	stock and sell it on this day?
 * 
 * 	Notice for the stock pattern. We should always buy the stock of previous day if the previous day opening price is lesser than the current
 * 	days. Buy low sell high. Therefore starting from current day's previous day, loop until the point where the price is higher than
 * 	current day, then stop. Calculate the profit earned if we had buy all the previous day's stock and sell it on current day.
 * 
 * 	Lastly, just take the maximum between the 2 choices of selling or do nothing
 * 	
 * 	
 */

public class Stock_Maximize {
	
	public static long stockmax(List<Integer> prices) {
		long len = prices.size();
		//Initialized with day 0 included, which maximum profit is always 0.
		long[] dp = new long[ (int)(len + 1) ];
		
		for (int i = 0; i < len; i ++ ) {
			long currSell = prices.get(i);
			long cum = 0;
			
			//Loop pointer j until the point where the price is higher than the current day's price
			int j = i - 1;
			while (j >= 0 && prices.get(j) < currSell) {
				//Keep adding the prices to cumulative sum variable (So we know how much to spend to buy stocks)
				cum += prices.get(j);
				j--;
			}
			//Calculate the profit. Take the stopping point of j pointer's DP result (maximum profit up to that day),
			//Minus the price to buy in stock, added with gross earning which is number of days times the current sell price.
			long sellMax = dp[j + 1] - cum + (i - j - 1) * currSell;
			
			//Optimization choice. Choose to do nothing or sell the stocks
			dp[i + 1] = Math.max( dp[i], sellMax);
		}
		
		//Return the maximum profit on the last day
		return dp[(int) len];
		
	}
	
}
