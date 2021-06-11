package Medium;

//https://leetcode.com/problems/stone-game-vii/
/*
 * 	This is a Dynamic Programming problem.
 * 
 * 	If I were to solve this brute force, every move, the player in turn can either pick to remove leftmost stone
 * 	or rightmost stone. Thus leading to a O(2^N) algorithm. Very slow.
 * 
 * 	Using Dynamic Programming, we eliminate repeated computations.
 * 	In my DP bottom-up approach, I start solving for the score differences with all possible stone subarray of length 1.
 * 	Since the player has only choice of removing the only stone, the score difference will still be 0.
 * 
 * 	First, knowing the subarray stone length, I can know whose turn it is currently. 
 * 	Let DP[i] containing the score difference if both players play optimally. If DP[i] is negative, it means
 * 	Alice > Bob. Vice versa, if positive DP[i], means Bob > Alice.
 * 	If it is Bob's turn, I will try to maximize the score difference, by attempting to remove both sides of stone.
 * 
 * 			max( sumOfStones after remove leftmost stone + DP[stoneArray without left stone],
 * 				 sumOfStones after remove rightmost stone + DP[stoneArray without right stone])
 * 
 * 	If it is Alice's turn, the score earned by Alice will be negative, and will try to minimize the score difference
 * 
 * 			min( -(sumOfStones after remove leftmost stone) + DP[stoneArray without left stone],
 * 				 -(sumOfStones after remove rightmost stone) + DP[stoneArray without right stone])
 * 
 * 	With this algorithm, the time complexity is O(N^2), with potential minimum space complexity is O(N).
 * 	At the end, simply check DP[full stone Array], which contains all stones, and Alice as first mover. That is the
 * 	final score outcome if both players play optimally.
 * 
 * 
 * 	*** There is superb solution that does not require checking whose turn it is in Leetcode solutions
 */

public class Stone_Game_VII {
	//	Negative means Alice > Bob
	//	Positive means Bob > Alice
	//	Scores earned by Alice is -, by Bob is +
	
	
	//	2D Dynamic Programming solution
	public int stoneGameVII(int[] stones) {
		final int len = stones.length;
		
		//	Obtain prefix sum array
		int[] sum = new int[len+1];
		for (int i = 0; i < len; ++i)
			sum[i+1] = stones[i] + sum[i]; 
		
		//	Row is starting index, col is ending index
		int[][] dp = new int[len][len];
		
		for (int size = 2; size <= len; ++size) {
			for (int startIdx = 0; startIdx <= len - size; ++startIdx) {
				//	If Bob's turn, as positive as possible. If Alice's turn, as negative as possible
				
				//	Alice turn
				if (len % 2 == size % 2) {
					dp[startIdx][startIdx+size-1] = Math.min(
							-(sum[startIdx+size]-sum[startIdx+1]) + dp[startIdx+1][startIdx+size-1],	//	Take leftmost stone
							-(sum[startIdx+size-1]-sum[startIdx]) + dp[startIdx][startIdx+size-2] 		//	Take rightmost stone
					);
				} 
				//	Bob turn
				else {
					dp[startIdx][startIdx+size-1] = Math.max(
							(sum[startIdx+size]-sum[startIdx+1]) + dp[startIdx+1][startIdx+size-1],	//	Take leftmost stone
							(sum[startIdx+size-1]-sum[startIdx]) + dp[startIdx][startIdx+size-2] 	//	Take rightmost stone
					);
				}
			}
		}
		return Math.abs( dp[0][len-1] ); 
	}
	
	
	
	//	Space Optimized 1D Dynamic Programming solution
	public int stoneGameVII2(int[] stones) {
		final int len = stones.length;
		
		//		Obtain prefix sum array
		int[] sum = new int[len+1];
		for (int i = 0; i < len; ++i)
			sum[i+1] = stones[i] + sum[i]; 
		
		int[] dp = new int[len];
		
		for (int size = 2; size <= len; ++size) {
			for (int startIdx = 0; startIdx <= len - size; ++startIdx) {
				//	If Bob's turn, as positive as possible. If Alice's turn, as negative as possible
				
				//	Alice turn
				if (len % 2 == size % 2) {
					dp[startIdx] = Math.min(
							-(sum[startIdx+size]-sum[startIdx+1]) + dp[startIdx+1],	//	Take leftmost stone
							-(sum[startIdx+size-1]-sum[startIdx]) + dp[startIdx] 	//	Take rightmost stone
					);
				} 
				//	Bob turn
				else {
					dp[startIdx] = Math.max(
							(sum[startIdx+size]-sum[startIdx+1]) + dp[startIdx+1],	//	Take leftmost stone
							(sum[startIdx+size-1]-sum[startIdx]) + dp[startIdx]		//	Take rightmost stone
					);
				}
			}
		}
		return Math.abs(dp[0]);
	}
}
