package Algorithms;

/*
 * 	Rabin-Karp Algorithm is a pattern matching algorithm to quickly find if a substring occurs in a string or not. It utilizes the hashing
 * 	of the substring and searches for matching hash value of the sliding window in the main string to find matches. If a match in hash value is
 * 	found, only then it will verify if the substring matches using the traditional method: comparing character by character
 * 
 * 	The key of this algorithm is effective hashing, and how we perform rehashing when the window slides, so that we are able to minimize the 
 * 	frequency of collision (Hash value match but string doesn't match).
 * 
 * 	The formula for evaluating the hash value. Let's take the simple example where we assume only digits, 0 to 9.
 * 	The pattern was '192'
 * 	The prime number we can take is Integer.MAX_VALUE, which is a prime number
 * 
 * 	Formula: hash = (hash + (characterValue * numberOfCharsPossible ^ positionFromBehind ) ) % primeNumber
 * 
 * 		1	------> hash += (1 * 10^2) % prime = 100
 * 		9	------> hash += (9 * 10^1) % prime = 90
 * 		2	------> hash += (2 * 10^0) % prime = 2
 * 
 * 	We found out the hash value is 192, which is actual representation of the number itself! With hashing, we can convert string into some sort
 * 	of numerical value which computer can easily compare. Simply use 26 (Assuming small case letters only, and perhaps map them into starting with
 * 	1,2,3... using -'a' method )
 * 	
 * 
 */

//STILL, SOMETHING IS WRONG WHEN THE SIZE OF PATTERN STRING IS BIG. PERHAPS SOMETHING WRONG WITH THE OVERFLOWING?

public class Rabin_Karp_String_Matching {
	
	static long prime = 1073741827;

	public static void contains(String str, String pattern) {
		long patternHash = hash(pattern, pattern.length() );
		System.out.println("pattern's hash: " + patternHash);
		
		long window = hash(str, pattern.length() );
		if (window == patternHash) System.out.println("Matches at index 0");
		
		for (int i = 1; i <= str.length() - pattern.length(); i ++ ) {
			window = roll(str, i, window, pattern.length() );
			System.out.println("substring search " + str.substring(i, i + pattern.length() ) + " of hash " + window);
			if (patternHash == window) System.out.println("Matches at index " + i);
		}
		
	}
	
	private static long charCode(String str, int idx) {
		return str.charAt(idx) - 'a' + 1;
	}
	
	private static long hash(String str, int length) {
		long hash = 0;
		for (int i = 0; i < length; i ++ ) {
			hash += (long)( (charCode(str,i) * Math.pow(26, i) ) % prime);
		}
		return hash;
	}
	
	private static long roll(String str, int idx, long oldHash, int length) {
		oldHash = oldHash - charCode(str, idx - 1);
		oldHash = oldHash / 26;
		oldHash = (long)( oldHash + (charCode(str, idx + length - 1) * Math.pow(26, length - 1) % prime) );
		return oldHash;
	}
	
	
	public static void main(String[]args) {
		contains("fehdhidhahdaehdheadieaidheihduiaedieaixxxxxxxxxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxxxxxxx");
	}
}
