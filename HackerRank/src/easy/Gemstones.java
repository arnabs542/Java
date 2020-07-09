package easy;


//https://www.hackerrank.com/challenges/gem-stones/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	The concept is for each stone string, which may have duplicate characters, we need to obtain a set of it. We can use set or some similar structure
 * 	to achieve this. Then, add those into the counting bucket. Lastly, iterate over the bucket and check if any of the characters frequency is equal
 * 	to the array's length, then it means this character occurs in every single string and thus it is the gemstone
 */

public class Gemstones {
	static int gemstones(String[] arr) {
		int[] minerals = new int[26];
		for (String stone: arr) {
			boolean[] occurred = new boolean[26];
			for (char c: stone.toCharArray() ) 
				if (!occurred[c - 'a'] ) {
					occurred[c - 'a'] = true;
					minerals[c - 'a'] ++;
				}
		}
		int count = 0;
		for (int i: minerals) {
			if (i == arr.length)
				count ++;
		}
		return count;
	}
}
