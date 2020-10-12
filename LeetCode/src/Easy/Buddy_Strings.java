package Easy;

//https://leetcode.com/problems/buddy-strings/

/*
 * 	A String problem.
 * 
 * 	We have to check if there is a single swap in String A which can end up totally equal to String B.
 * 	We split that into cases:
 * 		>	If A length is not equal to B length, immediately there is no possible swap.
 * 		>	If A is already total equal to B already, check if there is duplicate characters to swap
 * 		>	Otherwise, the number of differences need to be EXACTLY 2 (NO MORE NO LESS), and those 2 characters when 
 * 			swapped must match
 */

public class Buddy_Strings {

	public boolean buddyStrings(String A, String B) {
		//	Different string length. Impossible to swap
        if (A.length() != B.length() ) return false;	
        
        //	Number of differences, and the index in which they differ
        int diff = 0;
        int[] diffIdx = new int[2];
        
        //	Used to check duplication of characters
        boolean[] dup = new boolean[26];
        boolean containDup = false;
        
        for (int i = 0; i < A.length(); i ++ ) {
        	char aChar = A.charAt(i);
        	
        	//	NOT MATCH CASE
        	if (aChar != B.charAt(i) ) {
        		//	Number of differences increase
        		diff ++;
        		
        		//	If the differences already exceed 2, immediately it's not possible for single swap to achieve equalness
        		if (diff > 2 ) return false;
        		
        		//	Record the index in which the characters mismatch
        		diffIdx[diff - 1] = i;
        	}
        	
        	//	If the duplicate indicator already is true, this character is seen previously. We know duplicate exist
        	if (dup[aChar - 'a'] ) containDup = true;
        	//  Mark this character as seen
        	dup[ aChar - 'a' ] = true;
        }
        
        
        //	Case: 2 Differences. See when they swap if it is equal
        if (diff == 2) {
        	return A.charAt( diffIdx[0] ) == B.charAt( diffIdx[1] ) && A.charAt( diffIdx[1] ) == B.charAt( diffIdx[0] );
        }
        
        //	Case: No differences. See if there is duplicate characters to swap to end up same
        if (diff == 0) return containDup;
        
        //	Other cases : simply not possible
        return false;
    }
	
	
}
