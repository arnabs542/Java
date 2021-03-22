package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/vowel-spellchecker/
/*
 * 	This is a HashMap and String problem, and can be solved without using more complex data structures like Trie
 * 	(Using Trie might end up having harder implementation, since there are 3 states for this one, although highly
 * 	workable)
 * 
 * 	For each string, consider the following cases:
 * 		>	Perfect match
 * 		>	Ignore case match
 * 		>	Vowel wildcard match
 * 
 * 	Therefore for each word in dictionary, at maximum we'll push 3 index into the HashMap. 
 * 		>	One perfect match, one
 * 		>	Lowercased version of the string, but remember to mark it as lowercased version. Otherwise it might not work
 * 			in cases like
 * 				['KiTe', 'kite'] and queries ['kite', 'Kite'], where supposed output is ['kite', 'KiTe']
 * 
 * 		>	Wildcard vowel version. Replace vowels to some character like * to mark wildcard.
 */

public class Vowel_Spellchecker {
	
	public String[] spellchecker(String[] wordlist, String[] queries) {
		Map<String, String> dicts = new HashMap<>();
		
		for (String s: wordlist) {
			dicts.put(s, s);
			dicts.putIfAbsent( "_" + s.toLowerCase(), s );
			dicts.putIfAbsent( encode(s) , s);
		}
		
		String[] res = new String[ queries.length ];
		for (int i = 0; i < res.length; ++i) {
			res[i] = dicts.getOrDefault(queries[i], "");
			
			if (res[i].isEmpty() )
				res[i] = dicts.getOrDefault("_" + queries[i].toLowerCase(), "");
			if (res[i].isEmpty() )
				res[i] = dicts.getOrDefault( encode(queries[i]), "");
		}
		return res;
    }
	
	private String encode(String s) {
		StringBuilder sb = new StringBuilder( s.toLowerCase() );
		for (int i = 0; i < sb.length(); ++i ) {
			char c = sb.charAt(i);
			if ( c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
				sb.setCharAt(i, '*');
		}
		return sb.toString();
	}
	
}
