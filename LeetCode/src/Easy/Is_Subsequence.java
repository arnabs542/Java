package Easy;

import java.util.HashMap;
import java.util.LinkedList;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3355/

public class Is_Subsequence {
	//First attempt at this question, storing the indexes of each character in a map using character as a key.
	public boolean isSubsequenceMap(String s, String t) {
		HashMap <Character, LinkedList<Integer> > count = new HashMap<>();
		for (int i = 0; i < t.length(); i ++ ) {
			char c = s.charAt(i);
			count.putIfAbsent(c , new LinkedList<>() );
			count.get(s.charAt(i) ).add(i);
		}
		
		int min = -1;
		for (char c: s.toCharArray() ) {
			if (!count.containsKey(c)) return false;
			LinkedList<Integer> li = count.get(c);
			while (!li.isEmpty() && li.peek() < min) {
				li.poll();
			}
			if (li.isEmpty() ) return false;
			min = li.poll();
		}
		return true;
    }
	
	//Since question specified that to check if s is subsequence of t only, and not vice versa, we can just do a linear scan
	public boolean isSubsequenceScan(String s, String t) {
		int index = 0;
		for (char c: s.toCharArray() ) {
			while (index < t.length() && t.charAt(index) != c )
				index ++;
			if (index++ >= t.length() ) return false;
			continue;
		}
		return true;
	}
	
	//We can also use the dynamic programming approach of O(n^2) time of the Longest Common Subsequence. Since the rightmost bottom corner
	//represents the longest length of the common subsequence, we just have to check if that value is equal to s.length()
	public boolean isSubsequenceDP(String s, String t) {
		//We will make vertical column s, and horizontal column t
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		
		for (int i = 0; i < s.length(); i ++ ) {
			for (int j = 0; j < t.length(); j ++ ) {
				//If this character matches, then take diagonal and increment one
				if (s.charAt(i) == t.charAt(j) )
					dp[i+1][j+1] = dp[i][j] + 1;
				//Else take the maximum of either row above or column behind
				else
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j] );
			}
		}
		
		return dp[s.length()][t.length()] == s.length();
	}
	
	
}
