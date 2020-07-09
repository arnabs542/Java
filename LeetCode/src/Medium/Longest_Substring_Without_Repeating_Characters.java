package Medium;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/

public class Longest_Substring_Without_Repeating_Characters {

	public static int lengthOfLongestSubstring(String s) {
		if (s.length() == 0 || s.length() == 1) return s.length();
		if (s.length() == 2) return (s.charAt(0) == s.charAt(1) )? 1: 2;
		
		int max = 0;
		int start = 0, end = 0;
		int len = s.length();
		HashSet<Character> set = new HashSet<>();
		while (len - start > max && start < len && end < len) {
			if (!set.contains(s.charAt(end) ) ) {
				set.add(s.charAt(end) );
				end ++;
			}
			else {
				max = Math.max(end - start, max);
				start++;
				end = start;
				set.clear();
			}
		}
		return Math.max(end - start, max);
	}
	 
	
	public static void main(String[]args) {
		 String s = "aab";
		 long time1 = System.currentTimeMillis();
		 System.out.println( lengthOfLongestSubstring(s) );
		 
		 
	}
	
}
