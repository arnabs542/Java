package Medium;

//https://leetcode.com/problems/reverse-words-in-a-string/

/*
 * 	To reverse words in a string, The most easiest direct solution is to using split with white spaces as delimiter (using regex is better
 * 	since it matches multiple white spaces), then reverse the array returned with seperated spaces.
 * 
 * 	We could use a two pointer techinique. The right pointer and left pointer will wrap at individual words, where right pointer points
 * 	at last character of a word and left pointer points at the starting character of the word. This will utilize the loop where
 * 	right pointer keeps decreasing as long as it does not go out of bound and the right pointer char is white space.
 * 	The left pointer will keep decreasing starting from the right pointer's position as long as the left pointer - 1's character is not
 * 	white space (and of course not out of bound).
 * 	These two pointers will start working from the end of string, and when the pointers had wrapped around a word, it will append the
 * 	substring to the resulting string right at the end, followed by a space.
 * 
 * 	Assuming to do it in place, we will reverse the string (Better to trim it first) by character. Then, find the individual words and perform
 * 	the reversing like above.
 */

public class Reverse_Words_In_A_String {
	
	
	// Solution assuming that string is mutable and doing it in place O(1) (But actually it isn't)
	
//	public static String reverseWords(String s) {
//		//Use stringbuilder to simulate a mutable string
//        StringBuilder sb = new StringBuilder( s.trim() );
//        int len = sb.length();
//        
//        //Reverse the whole string
//        for (int i = 0; i < len / 2; i ++ ) {
//        	char temp = sb.charAt(i);
//        	sb.setCharAt(i, sb.charAt(len - i - 1) );
//        	sb.setCharAt(len - i - 1, temp);
//        }
//        
//        
//        int left = 0, right = 0;
//        
//        //Reverse each individual words.
//        while (left < len) {
//        	//Initialize the left to the first character of the individual word
//        	while (sb.charAt(left) == ' ' && left + 1 < len)
//        		left ++;
//        	right = left;
//        	//Initialize the right to the last character of the individual word
//        	while (right + 1 < len && sb.charAt(right + 1) != ' ')
//        		right ++;
//        	
//        	//To prevent losing data of left and right, we use another 2 pointers
//        	int swapl = left, swapr = right;
//        	//The limit position where the swapl pointer should stop at
//        	int limit = (right + left + 1) / 2;
//        	//Reverse the characters within the individual word
//        	while (swapl < limit) {
//        		char temp = sb.charAt(swapl);
//        		sb.setCharAt(swapl ++ , sb.charAt(swapr) );
//        		sb.setCharAt(swapr -- , temp);
//        	}
//        	//Set the left pointer to the position of last character of current individual word + 1, therefore it will search for the
//        	//next word
//        	left = right + 1;
//        }
//        
//        int cursor = 0;
//        //Reduce the spaces in between so that each word only separated by one space only
//        for (int i = 0; i < len; i ++ ) {
//        	if (sb.charAt(i) == ' ' && sb.charAt(cursor - 1) == ' ')
//        		continue;
//        	sb.setCharAt(cursor, sb.charAt(i) );
//        	cursor ++;
//        }
//        //Since all the words are trimmed of extra spaces, the rest right part of string shall be emptied
//        while (cursor < len) {
//        	sb.setCharAt(cursor ++ , ' ');
//        }
//        
//        //Trim the returned string so that it doesn't contain trailing spaces due to removal of inter spaces
//        return sb.toString().trim();
//        
//    }
	
	public static String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		
		int right = s.length() - 1, left = right;
		
		while (right >= 0) {
			while ( s.charAt(right) == ' ' && right - 1 >= 0 )
				right --;
			
			if (right < 0) break;
			left = right;
			
			while ( left - 1 >= 0 && s.charAt(left - 1) != ' ' )
				left --;
			
			sb.append( s.substring(left, right + 1) + " " );
			
			right = left - 1;
		}
		
		return sb.toString().trim();
	}
	
	public static void main(String[]args) {
		String str = "a good     example Nigga! Fly             ";
		System.out.println(reverseWords(str) );
	}
	
}
