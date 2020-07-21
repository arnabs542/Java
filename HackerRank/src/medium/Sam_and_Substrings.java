package medium;

//https://www.hackerrank.com/challenges/sam-and-substrings/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This is a DP problem, which requires noticing the pattern here.
 * 
 * 	Example integer string is 1234. We would divide it into subproblems asking the sum of substrings which start from only including 1 (the
 * 		very first character) up to including all the substrings.
 * 	We further divide the subproblem into asking the sum of this substring which includes the newly added integer to the substring.
 * 
 * 	This require some pattern noticing:
 * 	1 : 1
 * 	2 : 2, 12
 * 	3 : 3, 23, 123
 * 	4 : 4, 34, 234, 1234
 * 
 * 	All of the above includes the latest added digit, which always be in the one's place! And also notice that all the previous digits
 * 	(like in 3: (2)3, (12),3 ) is actually the previous answer's multiplied by 10.
 * 	Knowing the above property we could now express it in terms:
 * 
 * 	S[i] = S[i-1] * 10 + (pos) * n
 * 
 * 	Eg: S[3] where latest digit included is 3: = (2 + 12) * 10 + (3) * 3 === 149.
 * 	
 * 	Now we know the latest sum involving 3 as one's digit, but this was not yet the full sum of substrings. We have to include the previous
 * 	sum of substrings, which is S[i] + sum[i-1]. 
 * 
 * 
 */

public class Sam_and_Substrings {

	static int substrings(String n) {
		long modulo = 1000000007l;
		long[] dp = new long[n.length() + 1 ];
		long[] sum = new long[n.length() + 1];
		
		for (int i = 1; i <= n.length(); i ++ ) {
			long val = Character.getNumericValue( n.charAt(i) );
			
			dp[i] = ( (dp[i-1] * 10l) + val * i) % modulo;
			sum[i] =(dp[i] + sum[i-1] ) % modulo;
		}
		
		return (int)(sum[n.length() ] );
		
	}
	
}
