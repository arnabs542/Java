package Algorithms;

//https://www.hackerrank.com/challenges/nim-game-1/problem

//Best Explanation:
//https://www.youtube.com/watch?v=ORaGSyewF9Q

/*
 * 	A Nim Game is a strategical game where 2 players take turn to remove objects (stones, pebbles) etc from one of possibly many heaps of objects.
 * 	The player to be able to remove the last object (Last heap) wins; In other words, the player who couldn't make a move due to no objects left, loses
 * 
 * 	The Nim game has a way to determine (given players always make an optimal move) which of the player will win.
 * 			-In any finite combinatorics game (like Nim), from a losing position, all moves will lead to a winning position (of another player)
 * 			-From a winning position, there is at least one moves which leads to losing position (of another player), which is optimal move
 * 
 *  The pattern to realize is, we can represent the size of each heap in binary formats (like 7 is 111 in binary), and
 *  whoever is faced with the heap of all zeroes (0000...) is loser (no more moves available)
 *  By using the bitwise XOR, we can possibly tell that the state of the game is at the state of all zeroes or not.
 *  
 *  Realize that for any moves of player on the state of all zeroes, will always result in non-all zero state, and
 *  for a state of non-all zeroes, there is always a move to make it all result in zero
 *  
 *  For the player that is having non-all zeroes state and know what he is doing, he is certainly going to win, since he will always
 *  make the state of game to all zeroes, and pass on to the opponent. On the other hand, the opponent is always unable to make a move resulting
 *  in all zeroes and pass back to the winning state player
 *  			
 *  		Eg: (3,3), the XOR of the heaps (11 ^ 11) is 0. Whoever is the first to move, is already determined to lose
 *  
 */

public class NimGame {
	static String nimGame(int[] pile) {
		int xorSum = pile[0];
		for (int i = 1; i < pile.length; i ++ )
			xorSum = xorSum ^ pile[i];
		
		//We see if player one's state is initially at all zeroes, if true, then first player (first to move) is definitely going to lose
		return (xorSum == 0)? "Second":"First";
	}
	
}
