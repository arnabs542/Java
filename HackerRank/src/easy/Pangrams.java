package easy;

//https://www.hackerrank.com/challenges/pangrams/problem

//Alternatively, you can use a set data structure and see if the length of the set is 26 or not, if there is no other characters present

public class Pangrams {

	static String pangrams(String s) {
		boolean[] isExist = new boolean[26];
		for (char a: s.toLowerCase().replace(" ", "").toCharArray() ) {
			isExist[(int)(a - 97) ] = true;
		}
		for (boolean b: isExist) {
			if (!b) return "not pangram";
		}
		return "pangram";
    }
	
}
