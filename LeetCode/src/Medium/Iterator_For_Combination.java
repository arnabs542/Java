package Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/problems/iterator-for-combination/

/*
 * 	Using backtracking, we can generate the combinations for a string.
 * 	We can pick the characters from first one until the length() - charleft one as first letter. Then, the problem becomes
 * 	subproblem where character pick must be one of the characters to the right, with charleft - 1. Repeat
 */

public class Iterator_For_Combination {
	
	class CombinationIterator {
		
		PriorityQueue<String> queue;
		String str;

	    public CombinationIterator(String characters, int combinationLength) {
	        queue = new PriorityQueue<>();
	        str = characters;
	        
	        recursion(new StringBuilder(), 0, combinationLength);
	    }
	    
	    private void recursion(StringBuilder sb, int idx, int charleft ) {
	    	if (charleft <= 0 ) {
	    		queue.offer( sb.toString() );
	    		return;
	    	}
	    	for (int i = idx; i <= str.length() - charleft; i ++ ) {
	    		sb.append( str.charAt(i) );
	    		recursion( sb, i + 1, charleft - 1);
	    		sb.deleteCharAt( sb.length() - 1);
	    	}
	    }
	    
	    public String next() {
	        return queue.poll();
	    }
	    
	    public boolean hasNext() {
	        return !queue.isEmpty();
	    }
	}
	

}
