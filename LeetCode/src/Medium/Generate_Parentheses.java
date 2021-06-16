package Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/generate-parentheses/submissions/
/*
 * 	This is a Backtracking problem / DP problem
 * 
 * 	My initial solution is that, given N parentheses, and know that a valid string must start with a opening
 * 	bracket, we could break it down in terms of subproblems. See:
 * 		
 * 	At the first enclosing bracket, inside the bracket could be a parentheses string consisting from 0 to N-1
 * 	parentheses. Also, after the first enclosing bracket also can be parentheses string, but since some parentheses
 * 	are used up in enclosing bracket, the number of parentheses outside is determined by number of parentheses inside
 * 	the first enclosing bracket.
 * 
 *  If confused, see example N=3
 *  Start by opening the first enclosing bracket
 *  	>	(    )
 *  
 *  Inside the bracket could be parentheses string of size 0 to N-1. Let P(x) be parentheses string consisting of x parentheses
 *  	>	( P(0) ) P(2)
 *  	>	( P(1) ) P(1)
 *  	>	( P(2) ) P(0)
 *  
 *  Therefore in my solution, I just start bottom up, generating all P(x) up until P(N).
 *  
 *  ===================================
 *  
 *  The official solution is, for my parentheses string, I always had to make a decision to put a opening bracket or a closing
 *  bracket (given after placement, the string will not end up invalid).
 *  
 *  This is ok to be done via recursion. Record the number of opening bracket and closing bracket placed so far, and the parentheses
 *  is done when the length of string is equal to 2*N.
 */

public class Generate_Parentheses {
	
	//	Dynamic Programming similar solution (Slow)
	public List<String> generateParenthesis(int n) {
        List<List<String>> res = new ArrayList<>();
        res.add( new ArrayList<>( Arrays.asList("") ) );
        
        //	To generate every N, it has a recurrence relationship where:
        //	( <from 0 to N-1 parentheses> ) <leftover parentheses>
        for (int p = 1; p <= n; ++p) {
        	List<String> parentheses = new ArrayList<>();
        	
        	for (int insideParentheses = 0; insideParentheses < p; ++insideParentheses) {
        		int outsideParentheses = p - 1 - insideParentheses;
        		
        		for (String inside: res.get(insideParentheses))
        			for (String outside: res.get(outsideParentheses))
        				parentheses.add("(" + inside + ")" + outside);
        		
        	}
        	res.add(parentheses);
        }
        return res.get(n);
    }
	
	
	
	
	//	Backtracking solution (Faster)
	public List<String> generateParenthesis2(int n) {
		List<String> res = new ArrayList<>();
		recurse(n, new StringBuilder(), 0, 0, res);
		return res;
	}
	
	private void recurse(int n, StringBuilder s, int opened, int closed, List<String> res) {
		if (s.length() == n * 2) {
			res.add(s.toString());
			return;
		}
		
		//	Tries to put a opening bracket
		if (opened < n) {
			s.append('(');
			recurse(n, s, opened+1, closed, res);
			s.deleteCharAt( s.length() - 1 );
		}
		//	Tries to put a closing bracket
		if (opened > closed) {
			s.append(')');
			recurse(n, s, opened, closed+1, res);
			s.deleteCharAt( s.length() - 1 );
		}
	}
}
