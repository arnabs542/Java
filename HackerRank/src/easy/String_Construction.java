package easy;

//https://www.hackerrank.com/challenges/string-construction/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class String_Construction {
	static int stringConstruction(String s) {
		int cost = 0;
		boolean[] occured = new boolean[26];
		for (char c: s.toCharArray() ) {
			if (!occured[c - 'a'] ) {
				occured[c - 'a'] = true;
				cost ++;
			}
		}
		return cost;
	}
}
