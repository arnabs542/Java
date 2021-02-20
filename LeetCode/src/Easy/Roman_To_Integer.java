package Easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/roman-to-integer/
/*
 * 	This is a HashMap, String problem.
 * 
 * 	A roman numeral convert to integer is quite an easy task. For each of the roman characters,
 * 	simply get its respective value and add to our result variable. It can be done via HashMap
 * 
 * 	One challenges faced is those special '4' and '9's like IV, IX, XL, XC, CD and CM, which are
 * 	4,9,40,90,400,900 respectively. How do we face this?
 * 
 * 	We can actually check two characters at once to see if there is said pair existing in the string.
 * 	However, it can be quite tedious to implement.
 * 
 * 	Instead, observe: Those IV, IX..., the front character is smaller than that of the one behind. Take
 * 	advantage of this characteristic!
 * 	
 * 	Keep a previous value variable, which tracks the value of the roman character in last iteration. Now,
 * 	if current value is larger than the previous one, that means it must be one of special characters!
 * 	Since previous is already added to result in previous iteration, now we subtract two times of that!
 * 	That's it! Solved
 */

public class Roman_To_Integer {
	static Map<Character, Integer> val;
	static {
		val = new HashMap<>();
		char[] romans = {'I', 'V', 'X','L','C','D','M'};
		int[] ints = {1,5,10,50,100,500,1000};
		for (int i = 0; i < romans.length; ++i)
			val.put( romans[i] , ints[i] );
	}
	
	
	public int romanToInt(String s) {
		int prev = Integer.MAX_VALUE;
		int sum = 0;
		
		for (char c: s.toCharArray() ) {
			int v = val.get(c);
			sum += v;
			if (prev < v) sum -= 2 * prev;
			prev = v;
		}
		return sum;
    }
	
}
