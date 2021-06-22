package Medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/number-of-matching-subsequences/
/*
 * 	This is a Array String problem.
 * 
 * 	One brute force we can think of is for every word in words[], keep two pointers that pointing to
 * 	index in s and word, and attempt to find subsequence for word by iterating through s.
 * 	This apporach takes O(N * S) time, and results in TLE in leetcode
 * 
 * 	Instead, we can preprocess the string S to store the index sequences of characters beforehand.
 * 	Example: s = "abcaaa", then we store for character a, indices = {0, 3, 4, 5}
 * 
 * 	Then, for each word, we simply keep our progress in the indices. For every character in word, we have to
 * 	look for the next character in indices, but be careful not to take smaller indices that we had already progressed!
 */

public class Number_of_Matching_Subsequences {
	
	//	Brute Force solution in O(N * S) time. S = length of s, N = number of string in words[]
	public int numMatchingSubseq(String s, String[] words) {
        final int len = s.length();
		int res = 0;
        for (String w: words) {
        	int wIndex = 0;
        	for (int sIndex = 0; sIndex < len && wIndex < w.length(); ++sIndex)
        		if (s.charAt(sIndex) == w.charAt(wIndex)) ++wIndex;
        	
        	res += (wIndex == w.length())? 1: 0;
        }
        return res;
    }
	
	
	//	Preprocess string solution. O(S + N*A) time. A = Average length of strings in words[]
	public int numMatchingSubseq2(String s, String[] words) {
		final int len = s.length();
		int res = 0;
		List<Integer>[] indices = new List[26];
		for (int i = 0; i < 26; ++i)
            indices[i] = new ArrayList<>();
		
		for (int i = 0; i < len; ++i)
			indices[ s.charAt(i) - 'a' ].add( i );
        
		for (String w: words)
            if (isSub(indices, w)) res += 1;
		return res;
	}
	private boolean isSub(List<Integer>[] indices, String w) {
		int[] its = new int[26];
		int curr = -1;
		
		for (char c: w.toCharArray() ) {
			int code = c - 'a';
			
			//	While the index of the next current character is already progressed by previous character
			while (its[code] < indices[code].size() && indices[code].get(its[code]) <= curr )
				++its[code];
			
			if (its[code] >= indices[code].size()) return false;
            curr = indices[code].get(its[code]);
			++its[code];
		}
		return true;
	}
}
