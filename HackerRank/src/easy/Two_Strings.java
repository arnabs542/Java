package easy;

//https://www.hackerrank.com/challenges/two-strings/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Two_Strings {
	
	static String twoStrings(String s1, String s2) {
		boolean[] contains = new boolean[26];
		for (char c: s1.toCharArray() )
			contains[c - 'a'] = true;
		
		for (char c: s2.toCharArray() )
			if (contains[c - 'a'] ) return "YES";
		
		return "NO";
	}
}
