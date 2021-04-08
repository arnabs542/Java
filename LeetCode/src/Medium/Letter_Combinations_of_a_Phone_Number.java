package Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/
/*
 * 	This is a Backtracking, DFS or BFS problem that involves generating permutation
 * 
 * 	First of all, we should have something to refer to when converting a digit to its alphabets. A
 * 	Array will do since the keys are small [2,9]
 * 
 * 	For Backtracking (DFS), we select one possible alphabet each time based on the digit, then go deep
 * 	one layer. We know a permutation is generated once the length of the generated string is equal to the
 * 	length of the original digits string. Therefore store it in the result array.
 * 
 * 	For BFS, we need Queue to store previous states, starting with an empty string in the list.
 * 	Every layer, we would pop everything from queue, representing incomplete alphabet strings, like "ab()()"
 * 	without the () part
 * 	Therefore knowing which index we are doing, simply append the possible characters and enqueue back into the queue
 * 	At the end, the queue will have complete strings and ready to be returned
 */

public class Letter_Combinations_of_a_Phone_Number {
	
	private static final String[] alphas = {
			"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
	};
	
	
	//	Backtracking with Recursion solution
	public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }
	private void backtrack(String digits, int index, StringBuilder sb, List<String> res) {
		if (index >= digits.length() ) {
			res.add( sb.toString() );
			return;
		}
		
		int digit = digits.charAt(index) - '0';
		for (char c: alphas[digit].toCharArray() ) {
			sb.append(c);
			backtrack(digits, index+1, sb, res);
			sb.deleteCharAt(index);
		}
	}
	
	
	//	Iteration solution, BFS via Queue
	//	Somehow slower due to immutability of strings.
	public List<String> letterCombinations2(String digits) {
		if (digits.length() == 0) return new ArrayList<>();
		
		LinkedList<String> res = new LinkedList<>();
		res.add("");
		
		for (char c: digits.toCharArray() ) {
			int d = c - '0';
			int size = res.size();
			
			while (size-- > 0) {
				String prev = res.removeFirst();
				for (char a: alphas[d].toCharArray() ) res.add(prev + a);
			}
		}
		
		return res;
	}
	
	
}
