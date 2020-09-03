package Algorithms;

import java.util.Arrays;

/*
 * 	
 * 	Knuth Morris Pratt String Pattern Matching ALgorithm, also known as KMP algorithm, is an efficient algorithm to
 * 	search a particular pattern in the string in O(M+N) time, where M is length of string and N is length of pattern
 * 
 * 	Consider the following string:
 * 		A A A A A A A A B
 *	
 *	And we are searching for pattern:  "A A A B"
 *
 *	If we are using brute force / naive approach, if we find out the starting character matches that of the pattern's, we would attempt
 *	to match the next character and so on. 
 *	For example, in first iteration, we compare 1st char of string and 1st char of pattern. That matches, then we continue to 2nd of
 *	char, matches, and 3rd char, matches, but failing on the 4th char.
 *	This is going to repeat for the first 5 char in original string!
 *	
 *	In this case, each character that we encounter, it will match the following 3 A's, only to end up failing the matching 
 *	at the last B. In the first 5 iterations, it was a failed match and wasted some computations and time there
 *
 *	============================================================================================================================
 *
 *	Take the first iteration as example, we will have matched until the third A, only to fail at fourth character. In brute force,
 *	we would now backtrack to the second character and perform matching again.
 *	WHY BACKTRACK TO SECOND CHAR WHEN WE'VE ALREADY CHECKED UNTIL THE FOURTH CHAR?
 *
 *	This is the key to this algorithm. To iterate through the original string in one go, NO BACKTRACKING.
 *	This is achieved by first preprocessing the PATTERN STRING, building what is known as
 *		>	PI table
 *		>	LPS (Longest Prefix Suffix) table
 *		>	Prefix Array
 *	Whatever you want to call it.
 *
 *	=========================================================================================================================
 *
 *	PREFIX ARRAY / PI TABLE / LPS TABLE
 *	___________________________________
 *
 *	In the brute force approach, if a character is found to mismatch in the midst of pattern matching, the original string pointer
 *	would need to backtrack to original position + 1, and the pattern pointer would start all over again from 0.
 *	What if there actually was a prefix in the pattern string that we could check before backtracking?
 *
 *	See in this case:
 *		String: A B A B A B D
 *		Pattern: A B A B D
 *	
 *	Say we are using brute force. When we arrived at the fifth char, we see the A is not matching the D in pattern. Without algorithm,
 *	we would fallback to B (the second char), and pattern pointer back to initial.
 *	What if we had the information, telling us that even though the D is mismatched, we know that the previous section consists of
 *	'A B', which we can set the pattern pointer to the second character 'B' (Like we've just matched the last two 'A B'), then attempt
 *	to match the next character?
 *
 *	This is exactly what KMP algorithm does.
 *	The PREFIX ARRAY stores the information regarding the index of the pattern pointer shall fall back, according to the prefix in the
 *	pattern itself.
 *
 *	A Prefix array based on above example shall look like this
 *		A | B | A | B | D
 *		0 | 0 | 1 | 2 | 0
 *
 *	What this means is, when we've matched until 4th character B, if the next character D doesn't match, DONT FALL BACK IN ORIGINAL
 *	STRING! WE'VE MATCHED 'A B' SO FAR WHICH IS A VALID PREFIX OF THIS PATTERN.
 *
 *	Therefore in above case, the pointer would fall back to index 2, and tries to match the next character at index 3, which is A.
 *	If it fails as well, it will fall back at index 0, tries to match the next character at index 1, which is A also. If fails,
 *	the pattern matching fails and we shall continue with the next index in the original string
 *
 *	(Note that the prefix array shall be constructed in index 1 based. At index 0 shall be empty indicating the terminal point )
 *
 *	
 *
 *
 *	Therefore how would be build the prefix array? We can utilize Dynamic Programming here.
 *	
 *	For every new character introduced,	check the previous character's fallback index. Attempt to match the newly introduced character
 *	with the previous character's fallback index's next character.
 *
 *		IF SUCCESS: Put the fallback index + 1 into DP array and continue
 *		IF FAIL:	Fallback again to the fallback character's fallback index. Keep continue until a SUCCESS matching, or
 *					if it even fails at index 0, then put 0.
 *
 *	Here's a good example to construct a Prefix array:
 *
 *		A | C | A | C | A | B | A | C | A | C | A | B | A | C | A | C | A | C
 *
 *	The resulting prefix array shall be:
 *	
 *	 - | A | C | A | C | A | B | A | C | A | C | A | B | A | C | A | C | A | C
 *	INF| 0 | 0 | 1 | 2 | 3 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10| 11| 4
 *
 *	Once the pattern pointer had reached at the last index of prefix array (The last C character), where there is no longer
 *	any next character to match, we've found the matching pattern in the original string!
 *
 *
 *	==========================================================================================================
 *
 *	ACTUAL MATCHING
 *	_______________
 *
 *	During the actual matching phase, it is almost identical to the construction of the prefix array phase.
 *	We keep 2 pointers. One for original string, i, and one for prefix array and pattern string, j, initialized at index 0.
 *	We will match the pointer i character with the character at pointer j's next character
 *
 *	Every time the original string and pattern matches, move i and j forward.
 *	If no match, then begin falling back on the pattern pointer j.
 *	Upon each falling back, two things happen:
 *
 *		>	Now the fallback char's next character match. Move i and j forward.
 *		>	Fallback still don't match. Keep falling back until reached at index 0. NOTE THIS IS STILL NOT THE END, MATCH ONE LAST
 *			TIME TO SEE IF IT MATCH THE FIRST CHARACTER IN THE PATTERN. If it still don't match, it shall fall back again to some
 *			pre-set NEGATIVE VALUE or some sort, indicating no match. Reset j pointer to index 0 and move j forward
 *	
 *	This process continues until 2 cases:	
 *		>	Pointer j reached the end. Pattern found!
 *		>	Pointer i reached the end. No Pattern found!
 *
 * 
 */


public class Knuth_Morris_Pratt_String_Pattern_Searching_Algorithm_KMP {
	
	
	private static int[] createPrefixArray(String s) {
		int len = s.length();
		int[] dp = new int[len + 1];
		
		dp[0] = Integer.MIN_VALUE;
		
		//	Here I use indexing 0. In the dp array shall map to index 1
		for (int i = 0; i < len; i ++ ) {
			int idx = dp[i];	//	The previous character's fallback index
			
			while (idx != Integer.MIN_VALUE ) {
				if ( s.charAt(i) == s.charAt(idx) ) {	// s.charAt(i) is the newly introduced character
														// s.charAt(idx) is the next character of the fallback index's character
					dp[i + 1] = idx + 1;	//	If match, set current dp to the fallback index + 1
					break;
				}
				else idx = dp[idx];			//	Else keep falling back
			}
			if (idx == Integer.MIN_VALUE) dp[i + 1] = 0;	//	If there is no match, then set 0.
		}
		
		return dp;
	}
	
	
	public static boolean KMPMatch(String s, String pattern) {
		int len = s.length();
		int plen = pattern.length();
		int[] prefixArray = createPrefixArray( pattern );
		
		int jPointer = 0;	
		for (int iPointer = 0; iPointer < len; iPointer ++ ) {
			if (jPointer >= plen ) return true;	//	If jPointer had exceeded or equal to pattern length, then return true
			
			//	While the characters dont match
			while ( s.charAt(iPointer) != pattern.charAt(jPointer) ) {
				jPointer = prefixArray[jPointer];		//Set the jPointer to the fallback index.
				//	Matching at the beginning of pattern also failed. Set to 0 and continue
				if (jPointer == Integer.MIN_VALUE) {
					jPointer = 0;
					break;
				}
			}
			
			//	If the character is equal, then increment the pattern Pointer. iPointer is incremented in for loop
			//	If not equal, the pointer is probably at 0. Dont do anything to that
			if (s.charAt(iPointer) == pattern.charAt(jPointer) ) {
				jPointer ++;
			}
		}
		
		//	At the end of loop, either jPointer reach the end same time with the iPointer, or iPointer reached the end alone.
		return jPointer >= plen;
	}
	
	
	
	public static void main(String[]args) {
		String s = "abababababababababd";
		String p = "ababd";
		
		System.out.println( KMPMatch(s, p) );
	}

}
