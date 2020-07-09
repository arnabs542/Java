package easy;

//https://www.hackerrank.com/challenges/palindrome-index/problem

/*
 * 	Check for palindrome as usual from two sides. Once an inequality was met, either this or another character at the other end must be removed. Check
 * 	for two cases:
 * 		-If i remove the character at left, will the string from left+1 to right be palindrome? If yes, return left index
 * 		-If i remove the character at right, will the string from left to right-1 be palindrome? If yes, return right index
 * 	If the two cases fail, there is no more way to create palindrome, so return -1.
 * 
 * 	If the loop iterates successfully without checking, return -1 as it is a palindrome
 */

public class Palindrome_Index {
	
	static int palindromeIndex(String s) {
		int len = s.length();
		for (int i = 0; i < len / 2; i ++ ) {
			if (s.charAt(i) != s.charAt(len - i - 1) ) {
				//Left char removal test
				if (checkPalin(i + 1, len - i - 1, s) )
					return i;
				
				if (checkPalin(i, len - i - 2, s) )
					return len - i - 1;
				
				return -1;
			}
		}
		return -1;
    }
	
	private static boolean checkPalin(int left, int right, String s) {
		String sub = s.substring(left, right + 1);
		for (int i = 0; i < sub.length() / 2; i ++ ) {
			if (sub.charAt(i) != sub.charAt(sub.length() - i - 1) )
				return false;
		}
		return true;
	}
}
