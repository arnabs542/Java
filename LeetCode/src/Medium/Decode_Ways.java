package Medium;

//https://leetcode.com/problems/decode-ways/
/*
 *	This is actually a Dynamic Programming problem, which is harder to observe
 *	PS: I observed it when 
 *			>	Case "1": 1
 *			>	Case "11": 2
 *			>	Case "111": 3
 *			>	Case "1111": 5
 *			>	Case "11111": 8
 *	This is FIBONACCI SEQUENCE ------- Dynamic Programming
 *
 *	-------------------------------------------------------
 *
 *	For each character, we can have choice to make it:
 *		>	Alone
 *		>	Pair with previous character
 *
 *	The number of ways to pair characters should be (alone + pair with previous character).
 *	The DP array should represent for the index, corresponds to the substring until index i, the number of ways to decode
 *	the string. Index -1 and 0 is initialized to 1, since one digit can only have 1 way to decode (Except digit 0)
 *
 *	Then,
 *
 *	For making the character alone, we just have to obtain the PREVIOUS CHARACTER'S WAYS OF DECODE ( DP[i-1] )
 *	However, we cannot make character alone when the character is '0'
 *
 *	For making the character pair with previous, then we obtain the PREVIOUS TWO CHARACTER'S WAY OF DECODE (DP[i-2] )
 *	However, we cannot make character pair when previous character is '0' or they sum up exceed 26.
 *
 *	At the end, the last element of DP array is the answer
 *
 *	-----------------------------------------------------------
 *
 *	Note that we only use DP[i-2] and DP[i-1]. Thus solution can optimize to only use array of size 2, making O(1) space
 *	complexity
 */

public class Decode_Ways {
	
	public int numDecodings(String s) {
		final int len = s.length();
		if (len == 0 || s.charAt(0) == '0') return 0;
		
		// Index 0 to put 1. Index 1 is no of ways to decode for substring until 1st char inclusiv, which is 1
		// Think this DP as 1-indexed
		int[] dp = new int[len + 1];
		dp[0] = 1; dp[1] = 1;
		
		for (int i = 1; i < len; ++i) {
			int v0 = (s.charAt(i-1) - '0') * 10;
			int v1 = (s.charAt(i) - '0');
			
			if (v0 + v1 == 0) return 0;
			
			int alone = (v1 == 0)? 0: dp[i];
			int pair = (v0 + v1 > 26 || v0 == 0)? 0: dp[i-1];
			
			dp[i+1] = alone + pair;
		}
		
		return dp[len];
	}
	
	
	public int numDecodings2(String s) {
		final int len = s.length();
		if (len == 0 || s.charAt(0) == '0') return 0;
		
		// Index 0 to put 1. Index 1 is no of ways to decode for substring until 1st char inclusiv, which is 1
		// Think this DP as 1-indexed
		int[] dp = new int[2];
		dp[0] = 1; dp[1] = 1;
		
		for (int i = 1; i < len; ++i) {
			int v0 = (s.charAt(i-1) - '0') * 10;
			int v1 = (s.charAt(i) - '0');
			
			if (v0 + v1 == 0) return 0;
			
			int alone = (v1 == 0)? 0: dp[1];
			int pair = (v0 + v1 > 26 || v0 == 0)? 0: dp[0];
			
			dp[0] = dp[1];
			dp[1] = alone + pair;
		}
		
		return dp[1];
	}
}
