package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/house-robber-ii/

/*
 * 	A Dynamic programming problem, sequel to the question House Robber I
 * 
 * 	Since the house is now circular, robbing the house N will result in us unable to rob the first house, house 1.
 *  Conversely, robbing the house 1 will result in us unable to rob the last house, house N
 *  
 *  In this case, we can split the problem into 2 problems. Since we are unable to rob both the house 1 and house N
 *  at same time, how about we try to rob as follows:
 *  	From House 1 to House N-1
 *  	From House 2 to House N
 *  and see whoever results in maximum profit. That solves the problem.
 *  
 *  
 *  Trying to solve it without dividing into 2 subproblems are hard. In DP we will lose information of which DP result
 *  involves robbing house 1 or not. Even if we do know, the DP before the last house may end up in all robbing house 1,
 *  only to see that actually reaping the last house with different pattern reaps the most profit.
 */

public class House_Robber_II {
	
	public int rob(int[] nums) {
		int[] dp1 = new int[2];		//	House 1 to House N-1
		int[] dp2 = new int[2];		//	House 2 to House N
		int len = nums.length;
		if (len == 1) return nums[0];	//	Edge case: Only 1 house
		
		//	Rob house 1 in DP1
		dp1[1] = nums[0];
		int prev1, prev2;
		
		//	Rob intermediate houses
		for (int i = 1; i < len - 1; i ++ ) {
			prev1 = dp1[0];
			prev2 = dp2[0];
			dp1[0] = dp1[1];
			dp2[0] = dp2[1];
			
			dp1[1] = Math.max( prev1 + nums[i], dp1[0] );
			dp2[1] = Math.max( prev2 + nums[i], dp2[0] );
		}
		
		//	Rob last house
		prev2 = dp2[0];
		dp2[0] = dp2[1];
		dp2[1] = Math.max( prev2 + nums[len - 1] , dp2[0] );
		
		return Math.max( dp1[1], dp2[1] );
	}
}
