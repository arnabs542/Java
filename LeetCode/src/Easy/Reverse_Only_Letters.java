package Easy;

//https://leetcode.com/problems/reverse-only-letters/
/*
 * This is a String, Two pointer problem.
 * 
 * Reversing a string is like swapping the characters at the front & back. Once a swap is done, we move forward
 * the left pointer and move backward the right pointer and repeat.
 * 
 * Now, if we have non-alphabetic characters, we will simply proceed the said pointer without swapping.
 */

public class Reverse_Only_Letters {
	public String reverseOnlyLetters(String s) {
        StringBuilder res = new StringBuilder(s);
        int l = 0, r = s.length() - 1;
        while (l < r) {
        	if ( !Character.isAlphabetic(res.charAt(l))) ++l;
        	else if ( !Character.isAlphabetic(res.charAt(r))) --r;
        	else {
        		char temp = res.charAt(l);
        		res.setCharAt(l, res.charAt(r));
        		res.setCharAt(r, temp);
        		++l; --r;
        	}
        }
        
        return res.toString();
    }
}
