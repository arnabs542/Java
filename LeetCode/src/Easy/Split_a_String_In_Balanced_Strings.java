package Easy;

//https://leetcode.com/problems/split-a-string-in-balanced-strings/

/*
 * 	The String must be split fully, no skippings
 * 	This question utilizes sliding window approach
 */

public class Split_a_String_In_Balanced_Strings {
	
	public static int balancedStringSplit(String s) {
		if (s.length() <= 1) return 0;
		
		int numL = (s.charAt(0) == 'L')? 1:0;
		int numR = (numL == 1)? 0: 1;
		int numBalanced = 0;
		
		for (int i = 1; i < s.length(); i ++ ) {
			if (s.charAt(i) == 'L' )
				numL ++;
			else 
				numR ++;
			
			if (numL == numR) {
				numBalanced ++;
				numL = 0; numR = 0;
			}
		}
		return numBalanced;
	}
	

}
