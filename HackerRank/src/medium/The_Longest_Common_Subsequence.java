package medium;

//https://www.hackerrank.com/challenges/dynamic-programming-classics-the-longest-common-subsequence/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 		LCS is a classical dynamic programming question. This question not only asks for the longest Common subsequence, and also ask us to backtrack
 * 		to find out the answer generated.
 * 
 * 		Using the technique in Topics/Algorithm/Largest_Common_Subsequence, we can generate the dp table for the question (Here we cannot optimize space from
 * 		o(N^2) because we need to backtrack later).
 * 		Once we got the largest length at the rightmost bottom corner, we have to keep checking:
 * 		
 * 			-	If at this row and column the respective elements in array are equal, then we include it into the result array
 * 			-	If it is not, check if upper row is equal in dp value with this dp value. If yes, then go up, else, go left
 * 
 * 		This is because if the respective elements at the row and column are equal, the longest common subsequence must include this element as well.
 * 		Otherwise, we need to track from where the grid's LSC coming from, either from upper or left
 */

public class The_Longest_Common_Subsequence {

	static int[] longestCommonSubsequence(int[] a, int[] b) {
		int row = a.length;
		int col = b.length;
		
		int[][] dp = new int[row+1][col+1];
		
		for (int r = 1; r <= row; r ++ ) {
			for (int c = 1; c <= col; c ++ ) {
				dp[r][c] = Math.max( (a[r-1] == b[c-1]? dp[r-1][c-1] + 1: Integer.MIN_VALUE)
						, Math.max( dp[r-1][c] , dp[r][c-1] ) );
				
				System.out.print(dp[r][c]);
			}
			
			System.out.println();
		}

		int[] res = new int[ dp[row][col] ];

		int r = row, c = col;
		for (int i = res.length - 1; i >= 0; i -- ) {
			
			while ( a[r-1] != b[c-1] ) {
				if ( dp[r][c] == dp[r-1][c] )
					r -= 1;
				else
					c -= 1;
			}
			
			res[i] = a[r-1];
			r --; c--;
			System.out.println( res[i] + " " + r + " " + c );
		}
		return res;
	}
	
	
	public static void main(String[]args) {
		int[] a = {1,2,3,4,1};
		int[] b = {3,4,1,2,1,3};
		longestCommonSubsequence(a, b);
	}
	
}
