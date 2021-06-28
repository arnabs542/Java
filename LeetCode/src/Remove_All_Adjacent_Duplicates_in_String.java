
//https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
/*
 *  This is a String - Stack problem.
 *  
 *  We remove identical characters in pairs. Once removed, we need to know the
 *  top character in the string after removal. Thus, Stack data structure is best fit
 *  for this situation
 */

public class Remove_All_Adjacent_Duplicates_in_String {
	
	public String removeDuplicates(String s) {
		StringBuilder sb = new StringBuilder();
		
		for (char c: s.toCharArray() ) {
			if (sb.length() == 0 || sb.charAt( sb.length() - 1 ) != c)
				sb.append(c);
			else
				sb.deleteCharAt( sb.length() - 1 );
		}
		return sb.toString();
    }
	
}
