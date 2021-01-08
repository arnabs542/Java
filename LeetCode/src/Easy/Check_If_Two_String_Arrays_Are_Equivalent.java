package Easy;

//https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
/*
 * 	This is a String, which is an Array problem, since strings are essentially character arrays
 * 
 * 	The Brute force but workable way is to simply append all the strings. If the end result are equal then yes.
 * 	
 * 	--------------------------------
 * 
 * 	However the above solution is space consuming, since 
 * 		>	Strings are immutable. Each string is a instance of its own. Appending causes new instance to be created
 * 		>	We have to store 2 new strings, which is already exist in the parameter passed in
 * 
 * 	To check whether two strings are equal or not, we just have to ensure each character equals, and is of same length
 * 	We can use 2 pointers technique in this one
 * 	Since it is an array of strings, think of it as 2D jagged array. Index i points to a string, index j points to a
 * 	character in the string.
 * 
 * 	In this sense, we need 4 pointers. 2 pointing to strings in word1 and word2 respectively, another 2 pointing to
 * 	character in the string pointed by the prior two pointers.
 * 
 * 	Each iteration, check if the character pointers are out of range. If yes, proceed the string pointer and reset char
 * 	pointer to 0.
 * 
 * 	Also check for string pointer. If out of range, check if other one does as well.
 * 	>	If Yes, it means all the characters matched and we reach the end of both strings. Return true
 * 	>	Otherwise one string ended earlier while other has not. Return false.
 * 
 * 	Check if the characters pointed are equal. If not, immediately return false.
 */

public class Check_If_Two_String_Arrays_Are_Equivalent {
	
	
	//	Brute force-y way: Using StringBuilder to append Strings and lastly compare
	public boolean arrayStringAreEqual2(String[] word1, String[] word2) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		
		for (String s: word1) sb1.append(s);
		for (String s: word2) sb2.append(s);
		
		return sb1.toString().equals(sb2.toString() );
	}
	
	
	//	Optimize way: Using position pointers to prevent O(N) space complexity
	public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        final int len1 = word1.length;
        final int len2 = word2.length;
        
        int str1 = 0;
        int char1 = 0;
        int str2 = 0;
        int char2 = 0;
        
        while (true) {
        	//	End of the current string in word1 array
        	if ( char1 >= word1[str1].length() ) {
        		++str1;
        		char1 = 0;
        	}
        	//	End of the current string in word2 array
        	if ( char2 >= word2[str2].length() ) {
        		++str2;
        		char2 = 0;
        	}
        	
        	//	If we are at end of word1 array, check if we are also in end of word2 array
        	if (str1 == len1) return str2 == len2;
        	//	Since the prior already false, then word1 hasn't finished if this is the case. Return false
        	if (str2 == len2) return false;
        	
        	//	Character not equal
        	if ( word1[str1].charAt(char1) != word2[str2].charAt(char2) ) return false;
        	
        	//	Proceed the pointers
        	++char1;
        	++char2;
        }
    }
	
}
