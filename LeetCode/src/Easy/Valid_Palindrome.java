package Easy;

//https://leetcode.com/problems/valid-palindrome/

/*
 * 	Use two pointers.
 * 	Notice: No alphanumeric characters at all (Which also count as palindrome)
 * 
 * 	If the characters are not alphanumeric, then move the pointer 1 step ahead. Continue until you meet one. Same applies to
 * 	the right pointer but move backwards instead
 * 
 * 	Remember to convert to lowercase first
 * 	If at one occurrence the two pointers character does not match, then it is not palindrome
 * 	Once the two pointers meet, then it is palindrome
 */

public class Valid_Palindrome {
	
	public boolean isPalindrome(String s) {

        int len = s.length();
        
        if (len <= 1) return true;
        
        char[] sArr = s.toLowerCase().toCharArray();
        int leftP = 0, rightP = len - 1;
        
        while ( leftP < len && !Character.isLetterOrDigit( sArr[leftP] ) )
        	leftP ++;
        while ( rightP >= 0 && !Character.isLetterOrDigit( sArr[rightP] ) )
        	rightP --;
        
        while (leftP < rightP ) {
        	if (sArr[leftP] != sArr[rightP] ) return false;
        	leftP ++;
        	rightP --;
        	
        	while ( leftP < len && !Character.isLetterOrDigit( sArr[leftP] ) )
            	leftP ++;
            while ( rightP >= 0 && !Character.isLetterOrDigit( sArr[rightP] ) )
            	rightP --;
        }
        return true;
    }
	
}
