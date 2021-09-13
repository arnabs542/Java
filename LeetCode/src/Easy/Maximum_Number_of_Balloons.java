package Easy;

//https://leetcode.com/problems/maximum-number-of-balloons/
/*
 * 	This is a simply string / counting problem
 * 
 * 	Count frequencies of characters, especially 'b' 'a' 'l' 'o' 'n'
 * 	The max number of balloons can be formed are:
 * 
 * 	min(
 * 		freq(b),
 * 		freq(a),
 * 		freq(l) / 2,
 * 		freq(o) / 2,
 * 		freq(n)
 * 	)
 */

public class Maximum_Number_of_Balloons {
	public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        for (char c: text.toCharArray())
        	++freq[c - 'a'];
        int res = freq['b' - 'a'];
        res = Math.min(res, freq['a' - 'a']);
        res = Math.min(res, freq['l' - 'a'] / 2);
        res = Math.min(res, freq['o' - 'a'] / 2);
        res = Math.min(res, freq['n' - 'a']);
        return res;
    }
}
