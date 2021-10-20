package Medium;

//https://leetcode.com/problems/reverse-words-in-a-string/
/*
* 	To reverse words in a string, The most easiest direct solution is to using split with white spaces as delimiter (using regex is better
* 	since it matches multiple white spaces), then reverse the array and joining with seperated spaces.
*
*  The problem is still easier if we are allowed to utilize O(N) extra space. What we will do is to construct a new result string, and in the
*  original string, we locate the boundaries of each word FROM BEHIND (Thus reversing), and appending it to the result string.
*
*  ---------------------------------------------------------------
* 
*  The hardest part is to do it in-place in languages like C++. To do it in place, first idea that you need to get is:
*      >   Reverse the whole string, then reverse individual words to achieve what the problem originally want.
*  Seems easy, but consider multiple spaces to be eliminated. Then it becomes quite more complicated
*  Example:
*      "      quick    brown fox jumps     over "
* 
*  >   Reverse whole string
*  >   Keep an index: startIndex, indicating where we will insert our next word, in-place next. The reason for this is because of multiple spaces:
*      "      a " -> startIndex = 0 means when we encouter the 'a', we will put at index 0.
*  >   Iterate each character. Once encounter a character:
*      - Shift it to startIndex.
*      - Meanwhile, also locate the right boundary
*  >   Then we essentially have the shifted word at range [startIndex, startIndex+wordLen]. Reverse it.
* 
*  >   At the end, discard leftover characters.
*/

public class Reverse_Words_In_A_String {
	
	
	// Direct solution using Split and Joining using StringBuilder
	public String reverseWordsNaive(String s) {
		StringBuilder sb = new StringBuilder();
		String[] tokens = s.split(" +");
		
		for (int i = tokens.length - 1; i >= 0; --i) {
			if (tokens[i].length() == 0) continue;
			if (sb.length() != 0) sb.append(' ');	// Contains previous words. Append a space
			sb.append( tokens[i] );
		}
		return sb.toString();
	}
	
	// Two pointer from backwards. Eliminates split() function
	public static String reverseWords(String s) {
		final int len = s.length();
		StringBuilder sb = new StringBuilder();
		
		for (int r = len-1; r >= 0; --r ) {
			if ( s.charAt(r) == ' ') continue;
			
			int l = r;
			while ( l >= 0 && s.charAt(l) != ' ') --l;
			
			// Append space if sb contains previous words
			if (sb.length() != 0) sb.append(' ');
			
			// Append the word to sb.
			for (int i = l+1; i <= r; ++i) sb.append( s.charAt(i) );
			
			r = l;
		}
		return sb.toString();
	}
	
}
