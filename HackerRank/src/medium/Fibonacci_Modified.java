package medium;

//https://www.hackerrank.com/challenges/fibonacci-modified/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

import java.math.BigInteger;

/*
 * 	Typical Dynamic Programming problem combined with handling of big integer, just use the built in big integer class
 */

public class Fibonacci_Modified {
	
	static int fibonacciModified(int t1, int t2, int n) {
		
		BigInteger[] dp = new BigInteger[n];
		dp[0] = BigInteger.valueOf(t1);
		dp[1] = BigInteger.valueOf(t2);
		
		for (int i = 2; i < n; i ++ ) {
			BigInteger curr = dp[i-2].add( dp[i-1].pow(2) );
			dp[i] = curr;
			System.out.println( dp[i].toString() );
		}
		
		return dp[n-1].intValue();
		
	}
}
