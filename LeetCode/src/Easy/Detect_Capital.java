package Easy;

//https://leetcode.com/problems/detect-capital/

import java.util.regex.*;

/*
 * 	We could use library methods. Check if first character is uppercase. If it is, the rest of substring must be either all lowercase
 * 	or all uppercase
 * 	If first character is lowercase, then the rest must be all lowercase only.
 * 	Use str.toLowerCase(), str.toUpperCase() and Character.isUpperCase(char)
 * 
 * 	Similarly, we can count the frequency of uppercase and lowercase. Then determine if all is lowercase, all is uppercase, or
 * 	(IMPORTANT)FIRST char is upper and rest is lower
 */

public class Detect_Capital {

	public boolean detectCapitalUse(String word) {
		
		//LIBRARY METHOD (SLOWER)
//		char first = word.charAt(0);
//		String rest = word.substring(1);
//		
//		if (Character.isUpperCase(first) ) {
//			return rest.equals(rest.toLowerCase() ) || rest.equals(rest.toUpperCase() );
//		}
//		else {
//			return rest.equals( rest.toLowerCase() );
//		}
		
		
//		UPPERCASE LOWERCASE COUNTER METHOD (FASTER)
		int len = word.length();
		int uppers = 0;
		int lowers = 0;
		
		for (int i = 0; i < len; i ++ ) {
			char c = word.charAt(i);
			if (c <= 'z' && c >= 'a' ) lowers ++;
			else uppers ++;
		}
		
		return uppers == len || lowers == len || Character.isUpperCase( word.charAt(0) ) && lowers == len - 1;
		
		
		//REGEX METHOD	(VERY SLOW)
//		Pattern pattern = Pattern.compile("[a-z]*|[A-Z][a-z]*|[A-Z]*");
//		return pattern.matcher(word).matches();
	}
	
}
