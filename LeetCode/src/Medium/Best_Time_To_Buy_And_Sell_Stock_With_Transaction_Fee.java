package Medium;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
/*
 * 	This is a Dynamic Programming Problem, very, very similar to problem "Best Time to Buy and Sell Stock II".
 * 
 * 	Again, this problem is best represented with state diagram. The very same algorithm is used in the problem stated
 * 	above, except now it introduced the transaction fee.
 * 	However, don't let that scare you. LITERALLY THE VERY SAME ALGORITHM WORKS, just take into account the transaction
 * 	fee, and you are done with this problem.
 */

public class Best_Time_To_Buy_And_Sell_Stock_With_Transaction_Fee {
	
	public int maxProfit(int[] prices, int fee) {
		int prev_sold = 0, prev_hold = Integer.MIN_VALUE / 2;
		
		for (int p: prices) {
			int curr_sold = Math.max(prev_sold, prev_hold + p - fee);
			int curr_hold = Math.max(prev_hold, prev_sold - p);
			prev_sold = curr_sold;
			prev_hold = curr_hold;
		}
		return prev_sold;
    }
	
}
