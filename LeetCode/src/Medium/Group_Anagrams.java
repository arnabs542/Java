package Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/group-anagrams/
/*
 *	This is a Sorting, String, HashMap problem
 *
 * 	First of all, be informed that anagrams are made of same set and frequency of characters. That means, if the anagrams are
 *  sorted by character, they represent the same form:
 *  
 *  		"bbcca", "cacbb"   ----Sorting---> "aabbc"
 *  
 *  Thus, we can sort the anagrams in O(N) time, by bucket sort technique where we count frequency of 'a','b','c' and later assemble
 *  them back.
 *  
 *	Then, use a HashMap to store group of anagrams
 */

public class Group_Anagrams {
	
	// Sorting character and HashMap solution
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();
		
		for (String s: strs) {
			String key = sortString(s);
			
			// A new group of anagrams
			if (!map.containsKey(key)) {
				List<String> group = new ArrayList<>();
				map.put(key, group);
				res.add(group);
			}
			map.get(key).add(s);
		}
		
		return res;
    }
	
	// Sort characters in O(N) time
	private String sortString(String s) {
		int[] freq = new int[26];
		StringBuilder sb = new StringBuilder(s.length());
		
		//Count frequency of alphabets
		for (char c: s.toCharArray())
			++freq[c - 'a'];
		
		for (int i = 0; i < 26; ++i)
			while (freq[i]-- > 0)
				sb.append( (char)(i + 'a') );
		return sb.toString();
	}
}
