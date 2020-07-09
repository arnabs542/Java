package medium;

import java.util.HashMap;

//https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	Note that an anagram is a string that has the same amount of character as another string. In other words, those 2 strings are the shuffled version of each other.
 * 	Therefore, main goal is to check whether for every possible length of substring, how many pair of anagrams are there?
 * 
 * 	One way to faster check for same number of characters is using custom hashing. This hashing will not be dependent on the position of the characters, but the
 * 	character themselves does. We will add the hash value to the map once found and it will point to the number of occurrences for this length.
 * 	Therefore every possible substring of certain length will have at least occurred once in the hash value.
 * 
 * 	How many pairs of anagram can it form? We can use the combinatorics formula n! / (r!)(n-r)!. Since r is always 2 (From n occurrences, choose 2), We can deduce a
 * 	formula of (n)(n-1) / 2. Further, we can also just sum it up every time where the number of occurrence is n, it will always add +(n-1) occurrences, as following pattern:
 * 		1 -> 0
 * 		2 -> 1
 * 		3 -> 3
 * 		4 -> 6
 * 		5 -> 10
 * 		6 -> 15	(Notice each time n increase, the pairs increase by n - 1)
 * 
 * 
 */

public class Sherlock_And_Anagrams {
	
	static final long pwconst = 4L;
	static final long max = 2147483647L;
	
	static int sherlockAndAnagrams(String s) {
		HashMap<Long, Integer> map;
		int count = 0;
		
		long prev = 0L;
		//Winlen at 0 is length of 1. Winlen at 1 is length of 2
		for (int winlen = 0; winlen < s.length() - 1; winlen ++ ) {
			map = new HashMap<>();
			
			prev = ( 1L * prev + getCharHash(s.charAt(winlen) ) ) % max;
			long window = 0L + prev;
			map.put(window, 1);
			
			for (int j = winlen + 1; j < s.length(); j ++ ) {
				window = 1L * window - getCharHash(s.charAt(j - winlen - 1) );
				window = ( 1L * window + getCharHash(s.charAt(j) ) ) % max;
				System.out.println((long)(window) );
				
				map.putIfAbsent(window, 0);
				map.put(window, map.get(window) + 1);
				count += map.get(window) - 1;
			}
			System.out.println("----------------------");
		}
		
		return count;
	}
	
	private static long getCharHash(char c) {
		long code = c - 'a' + 1;
		long res = code;
		for (int i = 1; i <= pwconst; i ++ ) {
			res = 1L * res * code % max;
		}
		return res;
	}

	
	private static long getStrHash(String s) {
		long res = 0L;
		for (char c: s.toCharArray() ) {
			res = (res + getCharHash(c) ) % Integer.MAX_VALUE;
		}
		return res;
	}
	
	public static void main(String[]args) {
		String str = "qwertyuiopasdfghjklzxcadwqdqwdwqdadadasdasdasdsaddadaddeadefdafdfdfaadasdadasdsadsadasdavbnm";
		System.out.println(sherlockAndAnagrams(str) );
	}
}
