package Easy;

//	https://leetcode.com/problems/fibonacci-number/submissions/
/*
 * 	This is a classical recursion, dynamic programming problem
 * 
 * 	Although this can be solved iteratively for those who don't have algorithm and data structures background,
 * 	fibonacci numbers itself is recursive in nature. Since to evaluate fib(n), we have to look up previous terms
 * 	until we hit the base case
 * 
 * 	And since there are overlapping subproblems, it can be solved via dynamic programming.
 */


public class Fibonacci_Numbers {
	
	//	Iterative solution
	public int fib(int n) {
		if (n < 2) return n;
		int n1 = 0, n2 = 1;
		for (--n; n > 0; --n) {
			int next = n1 + n2;
			n1 = n2;
			n2 = next;
		}
		return n2;
    }
	
	//	Recursive solution
	public int fib2(int n) {
		if (n < 2) return n;
		return fib2(n-1) + fib2(n-2);
	}

	//	Cached Dynamic Programming Solution
	static int[] dp = new int[31];
	static {
		dp[1] = 1;
		for (int i = 2; i < 31; ++i)
			dp[i] = dp[i-1] + dp[i-2];
	}
	public int fib3(int n) {
		return dp[n];
	}
	
}
