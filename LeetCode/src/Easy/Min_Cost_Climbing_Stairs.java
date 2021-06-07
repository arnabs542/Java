package Easy;

//https://leetcode.com/problems/min-cost-climbing-stairs/
/*
 * 	This is a classical Dynamic Programming Question.
 * 
 * 	At a particular step, I can choose to go to step i+1, or step i+2
 * 
 * 	Either case, if I know in advance the cost for step i+1 and step i+2 (subproblems)
 * 	to reach the end, then the problem can be solved in O(N) time, because surely, the
 * 	min cost to reach the end from current step is:
 * 
 * 		cost[i] + min( dp[i+1], dp[i+2] )
 */

public class Min_Cost_Climbing_Stairs {
	
	//	DP solution, manipulating array in place
	public int minCostClimbingStairs(int[] cost) {
		for (int i = cost.length - 3; i >= 0; --i)
			cost[i] = Math.min(cost[i] + cost[i+1], cost[i] + cost[i+2]);
		return Math.min(cost[0], cost[1]);
    }
	
	//	DP solution, but only keep track of latest 2 results.
	public int minCostClimbingStairs2(int[] cost) {
		int latest1 = cost[ cost.length - 1], latest2 = cost[ cost.length - 2 ];
		for (int i = cost.length - 3; i >= 0; --i) {
			int curr = cost[i] + Math.min(latest1, latest2);
			latest1 = latest2;
			latest2 = curr;
		}
		return Math.min(latest1, latest2);
	}
	
}
