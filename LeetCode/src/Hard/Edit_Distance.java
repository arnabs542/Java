package Hard;

//https://leetcode.com/explore/featured/card/may-leetcoding-challenge/538/week-5-may-29th-may-31st/3346/

/*
 * 	This was a dynamic programming problem, solved using a 2 dimension array with extra column and row initially as record.
 * 	Each block space represents the steps to convert from the substring (from row) to the other one (from column)
 * 
 * 	For the initialization, it would be to have null string (Empty string) as both row and column as so:
 * 			(Eg: word1 = "abcde" word2 = "azced" )
 * 		
 * 			null	a	b	c	d	e
 * 		null
 * 		a
 * 		z
 * 		c
 * 		e
 * 		d
 * 
 * 	 The question would be, what to fill in those null row and column as initialization? THat would be equivalent to asking
 * 	 How many steps to convert from the null string to the other substring (vice versa)? That would meant to delete all characters! Therefore,
 * 	 the initialization would be
 * 
 * 			null	a	b	c	d	e
 * 		null 0		1	2	3	4	5			<< Remember that each step includes the previous character, Eg at column 'd' means the
 * 		a	 1									   substring is "abcd" against null. To convert "abcd" to null string requires 4 steps
 * 		z	 2
 * 		c	 3
 * 		e	 4
 * 		d	 5
 * 
 *	 Now, how would we go and fill up the table? We have to split into 2 cases:
 *		-Case where characters don't match
 *		-Case where characters match
 *
 *	If the characters match, then that means we no need to do anything to change the current character! We would fill the block with the value
 *	of steps to convert the string to another without this character included, which is the diagonal value!
 *
 *	If the characters doesn't match, then it means we have to convert this character, which requires an extra step (+1), but we'll take the
 *	minimum of the steps previously to add one step to. Example, at row 4 column 4, to convert "abcd" to "azce", I would consider to add 1 to
 *	either:
 *		-Steps to convert "abc" to "azc" (diagonal)
 *		-Steps to convert "abcd" to "azc" (above)
 *		-Steps to convert "abc" to "azce" (left)
 *	Whoever is minimum out of these would be taken in and incremented by 1
 *
 *	Then, the final answer would be located at the furthest rightmost corner of the whole array: Steps to convert "abcde" to "azced"
 */

public class Edit_Distance {

	public static int minDistance(String word1, String word2) {
		int[][] boxes = new int[word1.length() + 1][word2.length()+1];
		
		//Initialization of dp box
		for (int i = 0; i < word1.length() + 1; i ++ ) {
			boxes[i][0] = i;
		}
		for (int i = 0; i < word2.length() + 1; i ++ ) {
			boxes[0][i] = i;
		}
		//End of initialization
		
		
		for (int row = 0; row < word1.length(); row ++ ) {
			char wOne = word1.charAt(row);
			
			for (int col = 0; col < word2.length(); col ++ ) {
				char wTwo = word2.charAt(col);
				
				//If the characters match, take the diagonal value of them, as this character pair don't have to be matched
				if (wOne == wTwo)
					boxes[row+1][col+1] = boxes[row][col];
				//Else, take the minimum out of the 3 choices, and add 1 additional step, to convert this character to match
				else
					boxes[row+1][col+1] = Math.min( boxes[row][col], Math.min(boxes[row+1][col], boxes[row][col+1] ) ) + 1;
			}
		}
		//Return the rightmost corner answer, which is the steps to convert both strings to match
		return boxes[word1.length()][word2.length()];
    }
	
}
