package Easy;

//https://leetcode.com/problems/house-robber/

/*
 * 	Once you see find MAXIMUM amount that we can rob, we shall notice that it possibly is a DP problem
 * 
 * 	DP Problem all starts from a recursive relation. See:
 * 
 * 	Since of the rules set: We can't rob adjacent houses. That means on every new house introduced, we can only do two actions:
 * 		>	Rob this new house. This means we can't rob the previous house. If we find total profit, it would be this house
 * 			value + previous previous day's maximum DP value
 * 		>	Not Rob this new house. This means we will take the previous day's maximum DP value
 * 
 * 	Therefore a simple recursive solution is to start from the end of house, branch into 2 of above actions. If I rob this house,
 * 	I will find max profit of previous 2 house. If I don't, I will find max profit of previous house, which further branches into
 * 	subproblems. Base case is if this is the last house, the max profit is to rob this house.
 * 
 * 	Recursive solution is always repeated computing, leading to O(2^N) time complexity
 * 	
 * 	
 * 	Therefore, use Dynamic Programming which is bottom up approach. Find out whatever action is most profitable by using Math.max,
 *  and record it into DP table. THen continue on to next day
 * 
 * 	Notice we only need to keep track of previous day's DP value and previous previous day's DP value. So we can simplify the whole
 * 	DP table into just 2 variables storing prev day DP and prev prev day DP.
 */

public class House_Robber {
	
	public int rob(int[] nums) {
		int len = nums.length;
		if (len == 0) return 0;
		
        int[] dp = new int[2];
        
        dp[1] = nums[0];
        
        for (int i = 1; i < len; i ++ ) {
        	int choice = Math.max( dp[0] + nums[i], dp[1] );
        	dp[0] = dp[1];
        	dp[1] = choice;
        }
        
        return dp[1];
    }

}
