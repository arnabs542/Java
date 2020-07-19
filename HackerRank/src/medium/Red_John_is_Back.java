package medium;

//https://www.hackerrank.com/challenges/red-john-is-back/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This question is Dynamic Programming, and mixed with finding prime number algorithm (Sieve of Eratosthenes)
 * 	
 * 	For prime number finding algo, find Topics/sec/Misc/Sieve_of_Eratosthenes
 * 
 * 	For a room where the height must always be 4 unit high, and n in width. To find the number of ways to arrange it, we can divide it
 * 	into subproblems, where the subproblem is built on the two choices we made initially when faced with a new room:
 * 		-Either i will decide to put the brick horizontally when starting, or
 * 		-I will put the brick vertically
 * 
 * 	Notice that if I put the brick horizontally, then the bricks below the horizontal brick must also be placed horizontally. 
 * 	Therefore placing brick horizontally would just have 1 ways of placing it (No permutations of how we place horizontal brick whatsoever)
 * 
 * 	These are the two ways to kick start filling the room with bricks. If decide to put horizontally, we would be left with room n - 4 to fill
 * 	the bricks. Otherwise, if decide to put vertically, we would be left with room n - 1 to fill the bricks. Both of which is what we've
 * 	already solved earlier, if using DP bottom up approach!
 * 
 * 	For room size = 0, there is 1 way to fill it. Then for subsequent sizes, there will be dp[n-4] + dp[n-1] ways to fill it. If n-4
 * 	falls below 0, then there is 0 way to fill the room! No way to fill negative sized room!
 */

public class Red_John_is_Back {

	static int redJohn(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 1;
		
		for (int i = 1; i <= n; i ++ ) {
			dp[i] = dp[i-1] + ( (i-4 < 0)? 0: dp[i-4] );
		}
		
		int ways = dp[n];
		System.out.println(ways);
		return findPrime(ways);
    }
	
	static int findPrime(int n) {
		boolean[] sieve = new boolean[n + 1];
		int count = 0;
		
		for (int i = 2; i <= n; i ++ ) {
			if (sieve[i] ) continue;
			else {
				count ++;
				for (long j = 1L * i * i; j >= 0 && j <= n; j += i ) {
					sieve[(int)j ] = true;
				}
			}
		}
		return count;
	}
	
	
	public static void main(String[]args) {
		System.out.println( (int)(217286 * 217286) );
		System.out.println(redJohn(40) );
	}

	
}
