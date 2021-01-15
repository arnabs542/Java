package Easy;

//https://leetcode.com/problems/get-maximum-in-generated-array/
/*
 * 	This is an array problem.
 * 
 * 	One bad solution (But worth mentioning) is using recursion to find out the solution to n
 * 	Since the situation given fits the recursion pattern - Base cases and Formula
 * 
 * 	Given N, check if it is odd or even. If odd, then find out T(N/2) + T(N/2 + 1). Otherwise only T(N/2)
 * 
 * 	Since each time the term divides by 2, the time taken to find out one term N is, O(log N). We need to find out
 * 	the maximum so we attempt all possible values of N, taking O(N log N).
 * 
 * 	-----------------------------------
 * 
 * 	The above can be improved by recording the results of calculation so next time a repeated calculation can be done
 * 	in O(1).
 * 
 * 	Use either memoization, or use DP array. For DP array method, it is identical to generate the whole array already!
 * 
 * 	O(N) time and O(N) space.
 */

public class Get_Maximum_in_Generated_Array {
	
	//	Bad solution using recursion. Time complexity O(N log N) 
	public int getMaximumGenerated(int n) {
		int max = 0;
		while (n >= 1) max = Math.max( max, get(n--) );
		return max;
	}
	public int get(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		
		if (n % 2 == 0) return get(n/2);
		return get(n/2) + get(n/2 + 1);
	}
	
	
	//	DP solution / Simulation solution O(N) time and space
	public int getMaximumGenerated2(int n) {
		if (n == 0) return 0;
		
		int[] dp = new int[n+1];
		dp[1] = 1;
		int max = 1;
		
		for (int i = 2; i <= n; ++i) {
			if (i % 2 == 0) dp[i] = dp[i/2];
			else dp[i] = dp[i/2] + dp[i/2 + 1];
			
			max = Math.max(max, dp[i]);
		}
		return max;
	}

}
