package Medium;

import java.util.LinkedList;
import java.util.List;

/*
 * 	This is a DFS / BFS problem with some kind of backtracking element in it.
 * 	
 * 	Before any computation, let's add the base case:
 * 		>	If the N is 1, then 0 will be needed to be included in the answer
 * 
 * 	Now, as the first digit, it can be any digit from 1 to 9. So we iterate through 1 to 9 and attempt to put each one
 * 	as the first one
 * 	Then, we will call upon the recursive function. The recursive function will know that we've selected that digit from
 * 	previous 'seat' and will attempt to find the next possible digit to place, that will give absolute difference to K, that is:
 * 		The next digit, x is greater than the selected digit: X - sd = K		(sd is the selected digit)
 * 															  X = K + sd
 * 		The next digit, x is lesser than the selected digit:  sd - X = K
 * 															  X = sd - K
 * 	Note that if the X is computed to be less than 0 (0 is a valid digit, so is included), or greater than 9, then
 * 	it will be invalid for the case
 * 
 * 	Then, upon selected the digit, we will attempt to use that digit, and call the recursive funciton agian on the next digit to
 * 	be placed
 * 
 * 	Upon reaching the index which is the end of string, then we know we've formed a valid number, thus add it to the result list
 * 
 */



public class Numbers_With_Same_Consecutive_Differences {
	
	//	To map the int to character, to put into StringBuilder
	static final char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	public static int[] numsSameConsecDiff(int N, int K) {
		
		StringBuilder sb = new StringBuilder();
		sb.setLength(N);
		
		List<Integer> res = new LinkedList<>();
		
		//	Base case: N is 1, 0 as the answer will need to be included
		if (N == 1) res.add(0);
		
		//	Attempt to select every digit from 1 to 9 as first number
		for (int i = 1; i <= 9; i ++ ) {
			recursion(i, 0, sb, K, res);
		}
		
		//	Since we need to return int[] array, use Stream and mapToInt (TO convert from wrapper Integer to primitive int)
		return res.parallelStream().mapToInt(x -> x).toArray();
    }
	
	private static void recursion(int val, int idx, StringBuilder sb, int K, List<Integer> res) {
		//	If it is not a valid val to put
		if (val > 9 || val < 0 ) return;
		
		//	Put the digit at the index
		sb.setCharAt(idx, num[val] );
		
		//	If we've reached the end of the StringBuilder, a valid number is formed. Just add to te result list
		if (idx == sb.length() - 1 ) {
			res.add( Integer.parseInt(sb.toString() ) );
			return;
		}
		
		//	Find the next digit x which is smaller than current selected digit
		recursion( val - K, idx + 1, sb, K, res);
		
		//	If K is 0, this would cause repetition if not wrapped in if clause
		if (K != 0)
			//	Find the next digit x which is larger then current selected digit
			recursion( val + K, idx + 1, sb, K, res);
		
	}
	
}
