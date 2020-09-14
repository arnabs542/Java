package Easy;

//https://leetcode.com/problems/house-robber/

/*
 * 	Once you see find MAXIMUM amount that we can rob, we shall notice that it possibly is a DP problem
 * 
 * 	Since of the rules set: We can't rob adjacent houses. That means on every new house introduced, we can only do two actions:
 * 		>	Rob this new house. This means we can't rob the previous house. If we find total profit, it would be this house
 * 			value + previous previous day's maximum DP value
 * 		>	Not Rob this new house. This means we will take the previous day's maximum DP value
 * 
 * 	Therefore, find out whatever action is most profitable, and record it into DP table. THen continue on to next day
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
