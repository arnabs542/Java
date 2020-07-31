package Easy;

//https://leetcode.com/problems/climbing-stairs/

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
