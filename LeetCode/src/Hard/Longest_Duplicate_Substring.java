package Hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3365/

public class Longest_Duplicate_Substring {
	
	static long modulo = Integer.MAX_VALUE;
	
	public static String longestDupSubstring(String S) {
		int left = 1;
		int right = S.length() - 1;
		
		String longest = "";
		while (left <= right) {
			int mid = left + (right - left) / 2;
			
			String result = rabin_karp(S, mid);
			if (result != null) {
				longest = result;
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		return (longest.length() <= 1)? "": longest;
	}
	
	private static String rabin_karp(String str, int length) {
		HashMap<Long, Integer> map = new HashMap<>();
		
		long hash = hash(str, length);
		map.put(hash, 0);
		long pow = (long)(Math.pow(26, length -1) );
		
		for (int i = 1; i <= str.length() - length; i ++ ) {
			hash = nextHash(pow, hash, str.charAt(i - 1), str.charAt(i + length - 1) );
			
			if (map.containsKey(hash) ) {
				String verifies = verify(str, i, map.get(hash), length );
				if (verifies != null)
					return verifies;
			}
			map.put(hash, i);
		}
		return null;
	}
	
	private static long hash(String str, int size) {
		long h = 0;
		long multi = 1;		//Initially at 31^0
		
		for (int i = size - 1; i >= 0; i -- ) {
			char ch = str.charAt(i);
			h = ( h + ( (ch - 'a' + 1) * multi ) % modulo) % modulo;
			multi = (multi * 26) % modulo;
		}
		return h;
	}
	
//	private static long nextHash(String str, long hash, int newPos, int length) {
//		char left = str.charAt(newPos - 1);
//		char right = str.charAt(newPos + length - 1);
//		long pow = (long)(Math.pow(31, length - 1) % modulo);
//		
//		return ( (hash - (left - 'a' + 1) * pow) * 31 + (right -'a' + 1) )%modulo;
//		
//	}
	  private static long nextHash(long pow,long hash,char left,char right){
	        return (hash - (left - 'a' + 1) * pow) * 26 + (right - 'a' + 1);
	    }
	
	 
	private static String verify(String str, int i, int j, int len) {
		for (int index = 0; index < len; index ++ ) {
			if (str.charAt(i + index) != str.charAt(j + index) )
				return null;
		}
		return str.substring(i, i + len);
	}
	
	public static void main(String[]args) {
		System.out.println(longestDupSubstring("banana"));
	}
}
