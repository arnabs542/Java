package medium;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/play-game/problem

/*
 * 	This is a DP problem, but a little hard to find out the pattern.
 * 	The subproblem is the same as the base problem, to find out the maximum score that we can get, but need further details.
 * 	If we have a brick stack, and the problem stated that we will be always be the starting person. That means we are compulsory to take
 * 	at least 1 brick from the stack. Perhaps take 2, or 3
 * 
 * 	Let an example brick stack: [1, 2, 3, 4, 5] (where 1 is top of stack, 5 is bottom)
 * 
 * 	To exploit this property from bottom up approach, we have to see the brick stack from bottom to top, starting from only the lowest brick
 * 	and move our way to the top.
 * 	Therefore, the first question will be: What is maximum score that we get if the brick stack is [5], then subsequently [4, 5], [3, 4, 5]...
 * 
 * 	If we are the player, if the stack is only 3 size high, then of course we're gonna take it all. (Initialization).
 * 
 *  However, if the brick size are larger, then we have to think of a way to maximize the scoring, also considering the enemy player's optimal
 * 	move! <<< (The harder part to realize)
 * 
 * 	For each subproblem (The starting brick, bottom-up), we can find out the solution by considering the moves:
 * 		-Taking 1 brick, which is the top
 * 		-Taking 2 of them
 * 		-Taking 3 of them
 *  AND we need to think the consequences of each action.
 *  
 *  Assuming we are at brick N and we know all the solutions for all the brick below it. The solution here means we've calculated maximum
 *  score to be obtained for each moves, and recorded the best move to take (either take 1, 2 or 3).
 *  What will happen if i take 1, 2 or 3? The thing that will happen is:
 *  	-The enemy will start at N - T brick, where T is the number of brick that you took. The enemy being as smart as you are, will know the
 *  	 optimum action to take, either taking 1,2 or 3 brick. Then you will have your turn again at N - T - ET brick, where ET is the 
 *  	 number of bricks enemy had taken.
 *  
 *  With above situation in consideration, you are now able to calculate the maximum score that you'll get for each move!
 *  In each move, you know which brick enemy will start with, and you will predict how many bricks that enemy will take, then
 *  you will know which brick you'll land on again, which again, contains its own maximum score data!!!
 *  
 *  Therefore, each action's score is just (scores of brick you take this turn) + (maximum score of brick you land on again)
 *  From the 3 action scores, find the maximum of the 3, record it, and goes one level higher, until you've reached back at the original
 * 	problem.
 * 
 * --------------------------------------------------------------------------------------------------------------
 * 
 * 	Again, we can further compress the problem to be more easier.
 * 	For each brick, we're going to know how many brick the enemy is going to take and how much he is going to score. Therefore,
 * 	we would just create an array of cumulative sum table (Summing up the brick scores subsequently), and for each brick,
 * 	we will take the current cumulative sum of the brick, minus the enemy's maximum score. That will be our score if we made that action.
 * 	
 */

public class Bricks_Game {
	
//	static long bricksGame(int[] arr) {
//		int len = arr.length;
//		if (arr.length <= 3) return Arrays.stream(arr).sum();
//		
//		for (int i = 0; i < len / 2; i ++ ) {
//			int temp = arr[len - i - 1];
//			arr[len - i - 1] = arr[i];
//			arr[i] = temp;
//		}
//		
//		long[][] optChoices = new long[len][4];
//		
//		
//		for (int i = 0; i < 3; i ++ ) {
//			optChoices[i][3] = i;
//			optChoices[i][i] = arr[i] + ( (i == 0)? 0: optChoices[i-1][i-1] );
//		}
//		
//		for (int i = 3; i < len; i ++ ) {
//			
//			long cum = 0;
//			long max = Integer.MIN_VALUE;
//			
//			for (int c = 0; c < 3; c ++ ) {
//				cum += arr[i - c];
//				int enemytake = (int)(optChoices[i-c-1][3] + 1 );
//				int myNextTake = (int)(i - c - 1 - enemytake );
//				if (myNextTake < 0) 
//					optChoices[i][c] = cum;
//				else {
//					int nextOpChoice = (int)(optChoices[myNextTake][3] );
//					optChoices[i][c] = cum + optChoices[myNextTake][nextOpChoice];
//				}
//				max = Math.max(max, optChoices[i][c]);
//			}
//			
//			optChoices[i][3] = ( max == optChoices[i][2] )? 2:
//								( max == optChoices[i][1] )? 1: 0;
//		}
//		
////		printArr(optChoices);
//		return optChoices[len - 1][ (int)(optChoices[len - 1][3]) ];
//		
//	}
	
	public static long bricksGame(int[] arr) {
		int len = arr.length;
		long[] cum = new long[len];
		
		cum[0] = arr[len - 1];
		for (int i = 1; i < len; i ++ ) {
			cum[i] = cum[i-1] + arr[len - i - 1];
		}
		
		long[] dp = new long[len];
		dp[0] = cum[0]; dp[1] = cum[1]; dp[2] = cum[2];
		
		for (int i = 3; i < len; i ++ ) {
			long max = Integer.MIN_VALUE;
			
			for (int c = 1; c <= 3; c ++ ) {
				max = Math.max( max, cum[i] - dp[i-c] );
			}
			dp[i] = max;
		}
		
		return dp[len - 1];
		
	}
	
	private static void printArr(int[][] arr) {
		for (int i = arr.length - 1; i >= 0; i --) {
			System.out.println(Arrays.toString( arr[i]) );
		}
	}
	
	
	public static void main(String[]args) {
		int[] arr = {1,1,10,1,999,1,1,1,999};
		System.out.println( brickGame(arr) );
	}
	
}
