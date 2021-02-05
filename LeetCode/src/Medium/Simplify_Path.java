package Medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//https://leetcode.com/problems/simplify-path/
/*
 *	Let's observe what the problem wants:
 *		>	Starting with a slash /, it shall be followed by periods '.' or filename.
 *		>	If we have a filename, we do deep one level into the file itself. 
 *		>	If we have '..', simply return to the upper level.
 *		>	Multiple slashes treated as single slash.
 *
 *	Therefore we need a data structure which allow us to go back upper one level, while easy for us to
 *	add data into it! It is like recording path histories. Does that ring a bell?
 *
 *	Stack
 *
 *	Firstly, split the string based on delimiter '/'. Then, check each split tokens from front to back
 *
 *	If '.', safely ignore
 *	If '..', pop from stack if its not empty
 *	If filename, push to stack
 *	If empty string, means we have '//' or more slashes. Simply ignore.
 */

public class Simplify_Path {
	
	public String simplifyPath(String path) {
		Deque<String> deque = new ArrayDeque<>();
		String[] splitted = path.split("/");
		
		for (String s: splitted) {
			if (s.equals("") || s.equals(".") ) continue;
			if (s.equals("..") ) {
				if (!deque.isEmpty() ) deque.pollLast(); 
			}
			else
				deque.addLast(s);
		}
		
		StringBuilder sb = new StringBuilder("/");
		sb.append( String.join("/", deque) );
		return sb.toString();
	}

}
