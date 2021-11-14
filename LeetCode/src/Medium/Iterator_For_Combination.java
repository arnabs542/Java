package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/iterator-for-combination/
/*
 * 	Using backtracking, we can generate the combinations for a string.
 * 	We can pick the characters from first one until the length() - charleft one as first letter. Then, the problem becomes
 * 	subproblem where character pick must be one of the characters to the right, with charleft - 1. Repeat the recursion
 * 
 * 	========================================================================================================
 * 
 * 	However, this directly ignores the purpose of iterator. If I initialized the iterator and only call the next() once before
 * 	disposing, all the computations are wasted!
 * 
 * 	Instead, the optimal way is to only compute the next lexicographical string once on every next() call. This would potentially
 * 	save up a lot of computations in the case where the iterator is rarely called.
 * 
 * 	I done this by keeping a K length int array of 'pointers', where K is the combinationLength.
 * 	For example, if characters = 'abcde' and combinationLength = 2,
 * 
 * 	Then pointers = [1,3] means currently, the result string is "bd", which is at index 1 and 3 of base string respectively.
 * 	On each next() call, the pointers will be incremented, due to the nature of combination.
 */

public class Iterator_For_Combination {}


// Backtracking solution - Generates everything on init, but ignores the purpose of iterator.
class CombinationIterator {
	Queue<String> queue;
	String str;

    public CombinationIterator(String characters, int combinationLength) {
        queue = new ArrayDeque<>();
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




// Iterator based solution - Compute only when necessary
class CombinationIterator2 {
	String base;
	String next;
	int[] pointers;	// pointers[i] stores at the index i of result string, what is the index in the base string? 

	public CombinationIterator2(String characters, int combinationLength) {
		base = characters;
		pointers = new int[ combinationLength ];
		
		// Initialize pointers to [0,1,2,3,4...]
		for (int i = 0; i < pointers.length; ++i) pointers[i] = i;
		next = getStringFromPointers();
		
	}
	
	public String next() {
		String res = next;
		generateNext();
		return res;
	}
 
	public boolean hasNext() {
		return next == null;
	}
	
	private String getStringFromPointers() {
		StringBuilder sb = new StringBuilder();
		for (int i: pointers)
			sb.append( base.charAt(i) );
		return sb.toString();
	}
	
	// Updates the next String, if possible. Otherwise set next to null.
	private void generateNext() {
		// 1 - Obtain the position to update
		int i = pointers.length - 1;
		while (i >= 0 && pointers[i] == i + (base.length() - pointers.length) ) --i;
		
		// Case - Exhausted next combination.
		if (i == -1) {
			next = null;
			return;
		}
		
		// 2 - Increment pointers
		++pointers[i];
		for (i = i+1; i < pointers.length; ++i) pointers[i] = pointers[i-1] + 1;
		
		// 3 - Update the next
		next = getStringFromPointers();
	}
 
}
