package Easy;

//https://leetcode.com/problems/add-strings/
/*
 *	This is a string problem.
 *
 *	We simulate the addition: From behind, we add the digits, and take only the last digit (if >= 10). The carry need to be
 *	evaluated for the next iteration, based on condition (sum >= 10).
 *
 *	Don't forget after the iteration, that if carry is true, add the last '1'.
 *
 *	Reverse the result because we add digits left to right.
 */

public class Add_Strings {
	
	public String addStrings(String num1, String num2) {
		final int l1 = num1.length();
		final int l2 = num2.length();
		StringBuilder res = new StringBuilder( Math.max(l1, l2) + 1 );
		boolean carry = false;
		
		// Two pointers to two strings
		for (int i1 = l1-1, i2 = l2-1; (i1 >= 0 || i2 >= 0); --i1, --i2) {
			char c1 = (i1 >= 0)? num1.charAt(i1): '0';
			char c2 = (i2 >= 0)? num2.charAt(i2): '0';
			
			int digit = c1 + c2 - '0' - '0' + (carry? 1: 0);
			carry = digit >= 10;
			res.append( (digit % 10) );
		}
		
		// Don't forget if there is last carry, like '5' + '5'
		if (carry) res.append('1');
		
		return res.reverse().toString();
    }
	
}
