package Easy;

//https://leetcode.com/problems/to-lower-case/

/*
 * 	ASCII: lower case a-z = 97 - 122
 * 		   upper case A-Z = 65 - 90
 *  To convert upper case to lower case, use ASCII of upper case + 32
 *  
 * 	Notes for readability: instead of c>= 65 && c<= 90, we can put (c >= 'A' && c <= 'Z')
 */

public class To_Lower_Case {
	
	static final int DIFF = 'a' - 'A';
	public String toLowerCase(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c: str.toCharArray() ) {
			if (c >= 65 && c <= 90)
				sb.append((char)(c + DIFF));
			else
				sb.append(c);
		}
		return sb.toString();
    }
	
}
