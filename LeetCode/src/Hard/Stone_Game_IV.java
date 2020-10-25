package Hard;

//https://leetcode.com/problems/stone-game-iv/

/*
 * 	This is a DP problem.
 * 
 * 	Given n stones, we have to think if we are able to remove any perfect square numbers of stone from the pile,
 * 	can we be the one to remove all stones and win the game?
 * 
 * 	In this case, we will think of how the previous subproblems would help us out. The previous subproblems are
 * 	the very same problem with smaller n. Let's think:
 * 
 * 	For a given n stones, I can take all the possible perfect square numbers. However, the problem is if I take this
 * 	much stone, will the enemy ended up in a losing state?
 * 
 * 	Let's think bottom up. If I had 1 stones, I will be the one to remove the 1 stone, thus winning. If I had 2 stones,
 * 	I can only remove 1 stone, thus leaving enemy with 1 stone. Since previously we already know that 1 stone will definitely
 * 	win the game, and the control now is on the enemy's side, we are going to lose the game if we remove 1 stone.
 * 	The same idea applies to N stones, we check the outcome if we remove all the possible square numbers. If there is at
 * 	least one square number such that when I remove that much stone, leaving the enemy in losing state, then I'll definitely
 * 	win the game.
 */

public class Stone_Game_IV {
	
	public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        for (int i = 1; i <= n; i ++ ) {
        	
        	boolean possible = false;
        	int limit = (int) Math.sqrt(i);
            int square;
            for (int sqrt = 1; sqrt <= limit; sqrt++ ) {
            	square = sqrt * sqrt;
            	if ( !dp[i - square] ) possible = true;
            }
            dp[i] = possible;
        }
        return dp[n];
    }
	
}
