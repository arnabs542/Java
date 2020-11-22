package Easy;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/unique-morse-code-words/

/*
 * 	This is a Set problem. 
 * 
 * 	Given strings, we can parse them into the respective morse code. How do we know if similar morse code
 * 	had occurred before? We record it. Is there O(1) time of checking? using HashSet to hash the string
 * 	
 * 	========================================================================================
 * 
 * 	We can also try to perform the hashing ourselves. Since every length of word cannot exceed 12, and
 * 	each morse code length is maximum 4, and morse code has 2 states only: . and - . We can actually
 * 	represent them in binary form.
 * 		>	.	Represent 0
 * 		>	-	Represent 1
 * 
 * 	Using bitwise shifting, we can easily represent each character. The bits that we need are 48 bits.
 * 	A long will suffice.
 * 
 * 	Be careful not to start with 0 but with 1. Since when 0 gets left shifted it is still 0. This will cause
 * 	collision cases like
 * 		>	....-
 * 		>	..-
 * 	They will be same!
 * 
 * 	=======================================================================================
 * 
 * 	Also, we could use Trie to check if it exists before. If it does exist, there must be a corresponding
 * 	matching node in the Trie already. 
 * 	If it exists, continue
 * 	Else, add 1 to the unique words counter
 * 
 */

public class Unique_Morse_Code_Words {
	
	static final String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
	
	public int uniqueMorseRepresentations(String[] words) {
        Set<String> unique = new HashSet<>();
        
        for (String s: words) {
        	 StringBuilder sb = new StringBuilder();
             sb.ensureCapacity(48);
             
             for (char c: s.toCharArray() ) {
            	 sb.append( codes[c - 'a'] );
             }
             
        	unique.add(sb.toString() ); 
        }
        return unique.size();
    }
	
	
	public int uniqueMorseRepresentations2(String[] words) {
		Set<Long> unique = new HashSet<>();
		
		for (String s: words) {
			long l = 1l;
			
			for (char c: s.toCharArray() ) {
				String code = codes[c - 'a'];
				
				for (char codeC: code.toCharArray() ) {
					l = l << 1;
					l += codeC == '.'? 0: 1;
				}
			}
			unique.add( l );
		}
		return unique.size();
	}
	
	
	class TrieNode {
		TrieNode dot = null;
		TrieNode dash = null;
		boolean isWord = false;
	}
	private TrieNode createOrTraverse(TrieNode node, String charToInsert) {
		for (char c: charToInsert.toCharArray() ) {
			if (c == '.') {
				if (node.dot == null) node.dot = new TrieNode();
				node = node.dot;
			} else {
				if (node.dash == null) node.dash = new TrieNode();
				node = node.dash;
			}
		}
		return node;
	}
	public int uniqueMorseRepresentations3(String[] words) {
		TrieNode root = new TrieNode();
		int res = 0;
		
		for (String s: words) {
			TrieNode node = root;
			for (char c: s.toCharArray() ) {
				node = createOrTraverse(node, codes[c - 'a'] );
			}
			
			if (!node.isWord) res ++;
			node.isWord = true;
		}
		
		return res;
	}
}
