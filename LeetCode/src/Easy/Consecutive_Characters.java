package Easy;

//https://leetcode.com/problems/consecutive-characters/

/*
*	A Linear scan of string problem.
* 
*	Given a non empty string, the result must always be greater than or equal to 1. 1 is minimum because any character
*	already counts.
*	Starting from second character, scan until last character. If the previous character is same as current character,
*	add 1 to power. Otherwise, reset the power.
*	After updating the power in each iteration, compare the power to the result, so that result stores the maximum power
*	met.
*/

public class Consecutive_Characters {
    public int maxPower(String s) {
    	if (s.isEmpty()) return 0;
    	int res = 1;
    	int count = 1;
    	for (int i = 1; i < s.length(); i ++ ) {
    		count = s.charAt(i) == s.charAt(i-1)? count + 1: 1;
    		res = Math.max(count, res);
    	}
    	return res;
    }
}
