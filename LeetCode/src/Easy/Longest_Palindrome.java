package Easy;

import java.util.HashMap;

//https://leetcode.com/problems/longest-palindrome/
/*
 * 	A greedy problem with HashMap implementation. To build a palindrome with given string, we need to know the
 * 	frequency of each characters in the string. Then, we would build the palindrome with the identical character pairs.
 * 	
 * 	So, after finding out the frequency of characters, iterate through the characters. Add the nearest lower even number of the
 * 	frequency count to the result int (Eg: 59, we would add 58, 24, we would add 24 )
 * 
 *	Lastly, we could include a extra middle value in our palindrome.
 *	If the final count of the palindrome is lesser than the original string length, that means there are extra character we could
 *	place into the palindrome we've built (return res + 1), 
 *	else if palindrome length is equal to original string length, then no extra characters available, just return the full length
 */

public class Longest_Palindrome {

	public int longestPalindrome(String s) {
		
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray() ) {
        	map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        boolean lone = false;
        int len = 0;
        
        for (char c: map.keySet() ) {
        	int count = map.get(c);
        	len += (count % 2 == 0? count: count - 1);
        	if (!lone)
        		lone = count % 2 != 0;
        }
        
        return len + (lone? 1: 0);
    }
	
}
