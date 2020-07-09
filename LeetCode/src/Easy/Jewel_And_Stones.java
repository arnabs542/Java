package Easy;

import java.util.HashSet;

//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3317/

public class Jewel_And_Stones {
	public int numJewelsInStones(String J, String S) {
		HashSet<Character> set = new HashSet<>();
		int counter = 0;
		
		for (char g: J.toCharArray() ) {
        	set.add(g);
        }
		
		for (char s: S.toCharArray() ) {
			if (set.contains(s) ) counter++;
		}
		return counter;
    }
}
