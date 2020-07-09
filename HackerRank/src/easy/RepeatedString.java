package easy;

//https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup

/*
 * 	First, given the number of characters to be included in infinite string, we have to know 2 things:
 * 		-How many full loops are there? How many character 'a' in full string?
 * 		-How many leftover, partial string are there and how many character 'a' in the partial string?
 * 
 * 	These 2 informations can be found using only 1 iteration. We find number of full loops using division and number of leftover characters 
 * 	using modulo operation
 * 
 * 	When looping through the string, we check if the current string index is included in the partial string segment.
 * 	YES: Increment both partial count and full count
 * 	NO: Increment only full count
 * 	
 */

public class RepeatedString {
	static long repeatedString(String s, long n) {
		long fullLoop = n / s.length();
		long partialLen = n % s.length();
		
		int partialCnt = 0;
		int fullCnt = 0;
		
		for (int i = 0; i < s.length(); i ++ ) {
			if (s.charAt(i) == 'a') {
				if (i < partialLen) {
					partialCnt ++;
					fullCnt = partialCnt;
				}
				else
					fullCnt ++;
			}
		}
		return (fullCnt * fullLoop) + (partialCnt);
    }
}
