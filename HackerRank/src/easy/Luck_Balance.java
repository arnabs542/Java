package easy;

import java.util.ArrayList;
import java.util.Collections;

//https://www.hackerrank.com/challenges/luck-balance/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*	1. Identify the important contest (add them to an data structure) while just lose (increment) the non-important contests
 * 	2. Sort the important contest marks
 * 	3. Win the least lucky point costly important contest k times, and lose the rest (more lucky costly)
 * 
 */

public class Luck_Balance {

	static int luckBalance(int k, int[][] contests) {
		int luckBal = 0;
		ArrayList<Integer> important = new ArrayList<>();
		
		for (int[] contest: contests) {
			if (contest[1] == 0) 
				luckBal += contest[0];
			else {
				important.add(contest[0]);
			}
		}
		
		Collections.sort(important);
		
		int mustWin = important.size() - k;
		for (Integer i: important) {
			if (mustWin > 0) {
				luckBal -= i;
				mustWin --;
			}
			else {
				luckBal += i;
			}
		}
		return luckBal;
    }
	
	
}
