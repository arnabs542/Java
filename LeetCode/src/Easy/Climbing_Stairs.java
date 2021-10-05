package Easy;

//https://leetcode.com/problems/climbing-stairs/
/*
 * This is a classical DP problem, which can also be good for introduction to recursion.
 * 
 * To solve number of ways to climb stairs up to step n, think: To reach step n, we could come here from 2 ways:
 * 	>	From step n-1, make 1 step forward to n
 * 	> 	From step n-2, make 2 step forward to n
 * 
 * Therefore the number of ways to reach step n, depends on previous 2 steps' answer. This introduces recurrence relation.
 * And due to recurrence relation, we could use DP here.
 * This DP is actually just fibonacci sequence, where next number depends on sum of previous 2 numbers, so for space complexity
 * we could simply use O(1) to store 2 variables.
 */

public class Climbing_Stairs {
	
//Classical DP Solution
//	public int climbStairs(int n) {
//        int[] dp = new int[n + 1];
//        dp[0] = 1;
//        dp[1] = 1;
//        
//        for (int i = 2; i <= n; i ++ ) {
//        	dp[i] = dp[i-1] + dp[i-2];
//        }
//        return dp[n];
//    }
	
	
//Noticing that the DP solution actually is the Fibonacci sequence, where the sequence starts with 1 and 1. We can reduce the space complexity down to
//O(1) due to the fact that fibonacci sequence just takes previous and previous second element
	
	public int climbStairs(int n) {
		int a = 1;
		int b = 1;
		
		for (int i = 2; i < n; i ++ ) {
			int temp = b;
			b = a + b;
			a = temp;
		}
		
		return b;
	}
	
	
	
	
}
