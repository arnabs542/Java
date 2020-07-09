package medium;

//https://www.hackerrank.com/challenges/encryption/problem?h_r=internal-search

/*
 * 	Although labeled a medium question, it can be solved by finding the pattern without actually building the array itself
 */

public class Encryption {
	
	static String encryption(String s) {
		s = s.replaceAll(" ", "");
		int cols = (int) Math.ceil( Math.sqrt(s.length() ) );
		
		StringBuilder sb = new StringBuilder();
		for (int start = 0; start < cols; start ++ ) {
			for (int pos = start; pos < s.length(); pos+= cols) {
				sb.append(s.charAt(pos) );
			}
			sb.append(" ");
		}
		return sb.toString();
    }

}
