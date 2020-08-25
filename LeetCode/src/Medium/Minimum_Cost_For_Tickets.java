package Medium;

//https://leetcode.com/problems/minimum-cost-for-tickets/solution/

/*
 * 	This is a Dynamic Programming problem, which we can determine the main problem itself based on the answer to the subproblem
 * 	itself
 * 
 * 	We will create a DP array based on the maximum number of days provided, which is the last element in the days array
 * 	The DP array will store the answer to the question:
 * 		What is the minimum cost to buy the tickets, such that from day 1 until this day there is always valid train to sit
 * 
 * 	Every day, We make choices based on	the condition: is there a train ticket that I must be using on this day?
 * 	
 * 	If on this day, we have no train to sit, then we will just take the previous day's minimum cost
 * 	If on this day however, we have to sit a train, we have 3 choices:
 * 
 * 		>	Either we will buy a 1 day ticket on this day, therefore the cost will be previous day's minimum added
 * 			with the 1 day ticket price
 * 		>	This day, we will have a 7 day ticket which expires on this very day. We have purchased the 7 day ticket
 * 			right at 6 days ago. The cost will be previous 7 day's minimum added with the 7 day ticket price
 * 		>	This day, we will have a 30 day ticket which expires on this very day. We have purchased the 30 day ticket
 * 			right at 29 days ago. The cost will be previous 30 day's minimum added with the 30 day ticket price
 * 
 * 	We take the minimum of the three and put it as the answer to this day's dp
 * 
 * 
 * 		Time Complexity is O(N) - We just iterate through the days
 * 		Space Complexity is O(N) - We keep a DP array to keep track of the previous day's minimum
 * 	
 */

public class Minimum_Cost_For_Tickets {
	
	public int mincostTickets(int[] days, int[] costs) {
        int lastday = days[ days.length - 1];
        
        int[] dp = new int[ lastday + 1];
        int pointer = 0;
        
        for (int i = 1; i <= lastday; i ++ ) {
        	if (i != days[pointer] ) {
        		dp[i] = dp[i - 1];
        	} else {
        		//	Buying a 1 day pass
        		int mincost = dp[i - 1] + costs[0];
        		
        		//	Buying a 7 day pass
        		mincost = Math.min( mincost, dp[ Math.max(0, i - 7) ] + costs[1] );
        		
        		//	Buying a 30 day pass
        		mincost = Math.min( mincost, dp[ Math.max(0, i - 30) ] + costs[2] );
        		
        		dp[i] = mincost;
        		pointer ++;
        	}
        }
        
        return dp[lastday];
    }

}
