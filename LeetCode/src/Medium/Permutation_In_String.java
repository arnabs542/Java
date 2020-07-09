package Medium;

//https://leetcode.com/problems/permutation-in-string/

/*
 * 	Key to realise: For a permutation of s1 to be exist in part of s2, it must has the same frequency of characters in that substring of s2
 * 					Therefore it is a combination of hash map and sliding window problem
 * 					Additionally, we keep a counter of difference to see the difference between the hash map and substring checked, that way
 * 					we don't have to keep checking the hashmap over and over
 */

public class Permutation_In_String {
	
	public boolean checkInclusion(String s1, String s2) {
		int[] bucket = new int[26];
		for (char c: s1.toCharArray() ) 
			bucket[c - 'a'] ++;
		
		int difference = s1.length();
		
		for (int i = 0; i < s1.length(); i ++ ) {
			int charIndex = s2.charAt(i) - 'a';
			bucket[charIndex] --;
			if (bucket[charIndex] >= 0) 
				difference --;
			else 
				difference ++;
		}
		if (difference == 0) return true;
		
		for (int add = s1.length(); add < s2.length(); add ++ ) {
			int addIndex = s2.charAt(add);
			bucket[addIndex] --;
			if (bucket[addIndex] >= 0)
				difference --;
			else
				difference ++;
			
			int removeIndex = s2.charAt(add - s1.length() );
			bucket[removeIndex] ++;
			if (bucket[removeIndex] > 0)
				difference ++;
			else
				difference --;
				
			if (difference == 0) return true;
		}
		
		return false;
		
    }
}
