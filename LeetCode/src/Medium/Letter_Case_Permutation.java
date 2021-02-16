package Medium;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/letter-case-permutation/
/*
 * 	This is obviously a backtracking problem. No other way around it.
 * 
 * 	For each character, if it is digit, it only has 1 permutation so push it in stringbuilder, and continue recursion
 * 	into next character
 * 	Otherwise if it is a alphabet, it can be uppercase or lowercase. Thus, first append it uppercase, recurse into it,
 * 	then replace it with lowercase, recurse into it, and lastly, don't forget to delete the character since the upper
 * 	recursin call may also be alphabet and need to put uppercase and lowercase
 * 
 * 	------------------------------------------
 * 
 * 	Notice the repeated computations. We could actually generate permutations using more space. This way, each character
 * 	we need to iterate through the possible permutations, and push all current character's variation into it
 * 	A Linked List or Deque is perfect for this job: Removing from front, adding to back - Exactly queue!
 */

public class Letter_Case_Permutation {
	public List<String> letterCasePermutation(String S) {
        List<String> res = new LinkedList<>();
        generate(S, 0, new StringBuilder(S.length() ), res);
        return res;
    }
	
	private void generate(String s, int index, StringBuilder sb, List<String> res) {
		if (index >= s.length() ) res.add(sb.toString() );		//	Exceeded length means a permutation is generated
		else {
			char c = s.charAt(index);
			if ( Character.isAlphabetic( c ) ) {				//	Alphabet. Can be uppercase or lowercase
				sb.append( Character.toLowerCase( c ) );
				generate(s, index+1, sb, res);
				sb.setCharAt(index, Character.toUpperCase( c ) );
				generate(s, index+1, sb, res);
				sb.deleteCharAt(index);
			} else {											//	Otherwise it is a digit
				sb.append( c );
				generate(s, index+1, sb, res);	
				sb.deleteCharAt(index);
			}
		}
	}
	
	
	
	public List<String> letterCasePermutation2(String S) {
		LinkedList<String> queue = new LinkedList<>();
		if (S.length() == 0) return queue;
		queue.push("");
		
		for (char c: S.toCharArray() ) {
			if ( Character.isAlphabetic(c) ) {
				for (int i = queue.size(); i > 0; --i) {
					String s = queue.removeFirst();
					queue.addLast( s + Character.toLowerCase(c) );
					queue.addLast( s + Character.toUpperCase(c) );
				}
			} else {
				for (int i = queue.size(); i > 0; --i)
					queue.addLast( queue.removeFirst() + c );
			}
		}
		
		return queue;
	}
}
