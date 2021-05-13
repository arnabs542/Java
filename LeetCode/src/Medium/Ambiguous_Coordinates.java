package Medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/ambiguous-coordinates/
/*
 * 	This is a Brute Force, String problem.
 * 
 * 	For initial string, remove parenthesis first. Then, try to partition the numbers into x and y value.
 * 
 * 	Then, for the x and y values, try to insert decimal points and check if it is valid or not. Turns out,
 * 	the condition was:
 * 		>	For the whole number to be valid, it cannot pad with 0
 * 		>	For the form 0.xxx.., it cannot trailing with 0
 * 		>	For other cases like xx.xxx.., it cannot pad with 0, and not trail with 0
 * 
 * 	Once both valid values are returned as a list, perform cartesian product (Combination).
 * 
 *	Time complexity are considered as O(N^4)
 */

public class Ambiguous_Coordinates {
	
	public List<String> ambiguousCoordinates(String s) {
        final int len = s.length();
        s = s.substring(1, len-1);
        List<String> res = new ArrayList<>();
        for (int l = 1; l < len-2; ++l) 
        	cartesianProduct(s.substring(0, l), s.substring(l), res);
        return res;
    }
	
	private void cartesianProduct(String l, String r, List<String> res) {
		List<String> lval = generatePossibleValues(l);
		List<String> rval = generatePossibleValues(r);
		for (String ls: lval) {
			for (String rs: rval)
				res.add("(" + ls + ", " + rs + ")");
		}
	}
	
	private List<String> generatePossibleValues(String s) {
		final int len = s.length();
		List<String> res = new ArrayList<>();
		//	Full, no decimal.
		if (len == 1 || !s.startsWith("0") ) res.add(s);
		//	x.xxx or 0.xxx. case. Must not trail with 0
		if (len >= 2 && !s.endsWith("0") ) res.add( s.substring(0,1) + '.' + s.substring(1) );
		//	Other cases: xx.xxx...
		//	Condition: Cannot start with '0'. Cannot trail with 0
		if (len >= 3 && !s.startsWith("0") && !s.endsWith("0") ) {
			for (int loc = 2; loc < len; ++loc)
				res.add(s.substring(0, loc) + '.' + s.substring(loc) );
		}
		return res;
	}
}
