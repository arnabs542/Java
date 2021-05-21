package Medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/find-and-replace-pattern/
/*
 * 	This is a String, HashMap problem
 * 	
 * 	For every string, one specific character from original string can only map to a character
 * 	in the pattern and vice versa. This means one to one relationship.
 *  To solve this, we build one by one:
 * 
 * 	From index 0 to N-1, obtain character from both strings.
 * 	To initiate mapping from original to another, conditions:
 * 		>	original char must not yet map to any pattern char
 * 		>	pattern char must not yet map to any original char
 * 	The invalid pattern matching occurs when:
 * 		>	original char already maps to a pattern char but is not pattern char
 * 		>	pattern char already maps to a original char but is not original char
 * 	
 * 	Time is O(NK) where N is number of words, K is length of words
 * 	Space is O(K) if you don't consider the result array, otherwise O(NK)
 * 
 * 	-----------------------
 * 
 * 	We could try to reduce each string into a normalized pattern string. Eg:
 * 	"abbccdea" => "01122340"
 * 	Explaination: Each char is represented into an int value which is the order they are first encountered
 * 
 *  First generate normalized pattern string of pattern. Then for each word, generate pattern string and check
 *  if equal or not
 */

public class Find_and_Replace_Pattern {
	
	//	Two maps solution. Must be in One to One Relationship
	public List<String> findAndReplacePattern(String[] words, String pattern) {
		List<String> res = new ArrayList<>();
		for (String s: words) {
			if (isMatchPattern(s, pattern))
				res.add(s);
		}
		return res;
    }
	private boolean isMatchPattern(String ori, String pat) {
		int[] oriToPat = new int[27];
		int[] patToOri = new int[27];
		for (int i = 0; i < ori.length(); ++i) {
			int o = ori.charAt(i) - 'a' + 1;
			int p = pat.charAt(i) - 'a' + 1;
			if (oriToPat[o] == 0 && patToOri[p] == 0) {
				oriToPat[o] = p;
				patToOri[p] = o;
			} else if (oriToPat[o] != p || patToOri[p] != o)
				return false;
		}
		return true;
	}
	
	
	
	//	Pattern matching solution
	public List<String> findAndReplacePattern2(String[] words, String pattern) {
		String pat = toPattern(pattern);
		List<String> res = new ArrayList<>();
		for (String s: words) {
			if (toPattern(s).equals(pat) ) 
				res.add(s);
		}
		return res;
	}
	private String toPattern(String s) {
		int[] charToIdx = new int[26];
		int counter = 1;
		String res = "";
		
		for (char c: s.toCharArray() ) {
			//	If the character is never seen before, assign a new value, and increment the value
			if (charToIdx[c - 'a'] == 0)
				charToIdx[c - 'a'] = counter++;
			res += charToIdx[c - 'a'];
		}
		return res;
	}
}
