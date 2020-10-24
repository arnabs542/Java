package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/bag-of-tokens/

/*
 * 	This is a greedy, sorting problem.
 * 
 * 	Given the actions that we could perform on a token:
 * 	>	Flip DOWN the token, so we gain 1 Point but lose token[i] Power
 * 	>	Flip UP the token, so we gain token[i] Power but lose 1 Point
 * 
 * 	If we are facing this choice, we would want to always flip DOWN the least power costly token first, and if needed,
 * 	flip UP only the most power costly token.
 * 
 * 	We will be sorting the tokens, and keep 2 pointers at both ends. On the start pointer is the token we'll most likely
 * 	flip DOWN, and on the end pointer is the token we'll most likely flip UP.
 * 	Keep flipping the token DOWN until we are out of POWER, save the state if it is greater than last score, then
 * 	attempt to flip up until we have enough power again, or run out of scores or token.
 * 	Check at the end of flipping UP, if we have enough power to keep playing. If it's not, then immediately return the
 * 	maximum score.
 */

public class Bag_Of_Tokens {
	
	public int bagOfTokensScore(int[] tokens, int P) {
		int len = tokens.length;
		int score = 0;
		int res = 0;
		int head = 0, tail = len - 1;
		
		Arrays.sort(tokens);
		
		//  While tail pointer is greater or equal to head pointer. We could flip DOWN the token pointed by tail 
		//	pointer as well
		while (head <= tail) {
			
			//	While we have sufficient power, flip the token 
			while ( head <= tail && P >= tokens[head] ) {
				P -= tokens[head];
				score ++;
				head ++;
			}
			res = Math.max( score , res);
			
			//	While the power is insufficient to flip, try to gain power by flipping largest token possible
			while ( head < tail && score > 0 && P < tokens[head] ) {
				P += tokens[tail];
				score --;
				tail --;
			}
			
			//	Check for inplayable game state. return immediately if the game is not playable anymore
			if ( P < tokens[head] ) 
				return res;
		}
		return res;
    }
}
