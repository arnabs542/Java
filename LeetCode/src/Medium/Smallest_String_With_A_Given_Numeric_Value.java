package Medium;

//https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
/*
 * 	This is a greedy problem. 
 * 
 * 	Think of it as array of characters. Starting from front, if we are able to put 'a' in it, of course we would do it!
 * 	Otherwise if cannot, try 'b', 'c' and so on..
 * 
 * 	So for each space from front, calculate the total value if we were to fill all behind ones with 'z'. Now,
 * 	subtract the currently required value left with the calculated.
 * 	This will give us a picture of what happens if we fill all behind characters with 'z'
 * 
 * 	>	If the answer is negative, it means filling all behind values with 'z' is overkill. Safely insert 'a'
 * 	>	Otherwise, the answer is positive. Fill it in with the character as indicated. If 1, fill 'a', if 2, fill 'b'
 * 		and so on.
 */

public class Smallest_String_With_A_Given_Numeric_Value {
	
	public String getSmallestString(int n, int k) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= n; ++i) {
			int diff = Math.max( k - (n - i) * 26, 1 );
			sb.append( (char)(diff + 'a' - 1) );
			k -= diff;
		}
		
		return sb.toString();
	}
	
}
