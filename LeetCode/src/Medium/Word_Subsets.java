package Medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/word-subsets/
/*
 * 	This is a String problem, a not so direct question
 * 
 * 	If we were to do brute force, for every word in A, we compare with B. If say b in B is a subset of A, then the frequency
 * 	of characters in a must at least equal to that in b. We are comparing frequency of characters here.
 * 
 * 	However, brute force will result in time limit exceeded due to each array can reach length 10000. Is there better way?
 * 
 * 	--------------------------------------------------------
 * 
 * 	Notice that we want every pattern in B to be subset in A. Therefore, since there is only 26 lowercase letters, we can combine
 * 	the entire strings in B to reduce to only one universal pattern string, such that:
 * 		
 * 		If string a in A matches the universal pattern string, then all b in B must be subset of A.
 * 
 * 	Combining them is easy. Iterate over all b, count frequency, and have one frequency table which represents the universal
 * 	string, always take the maximum for each frequency.
 */

public class Word_Subsets {
	
	public List<String> wordSubsets(String[] A, String[] B) {
		byte[] combined = new byte[26];
		List<String> res = new ArrayList<>();
		
		//	Process and combine all of B
		for (String b: B) {
			byte[] f = new byte[26];
			for (char c: b.toCharArray())
				++f[c - 'a'];
			
			for (int i = 0; i < 26; ++i)
				combined[i] = (byte)Math.max(combined[i], f[i]);
		}
		
		//	Now search for universal
		for (String a: A) {
			byte[] f = new byte[26];
			for (char c: a.toCharArray())
				++f[c - 'a'];
			
			boolean match = true;
			for (int i = 0; i < 26 && match; ++i)
				match = combined[i] <= f[i];
			
			if (match) res.add(a);
		}
		return res;
        
    }
}
