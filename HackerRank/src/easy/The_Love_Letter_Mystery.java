package easy;

//https://www.hackerrank.com/challenges/the-love-letter-mystery/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	As mentioned, the available actions to take is to reduce the ascii code of the characters. Therefore to create a palindrome string, lets say
 * 	only 2 characters, 'ac', then we would have to only compare the two characters. Since c is greater than a, we would have to reduce the c.
 * 	How many step? It should be 2 steps as c -> b -> a, which is also the subtraction of the two ascii codes 'c' - 'a'
 * 	
 * 	Therefore the algorithm is to compare the two characters at symmetric psitions, and subtract their ascii code to get their difference, aka
 * 	steps to make them same.
 */


public class The_Love_Letter_Mystery {

	static int theLoveLetterMystery(String s) {
		char[] arr = s.toCharArray();
		int result = 0;
		for (int i = 0; i < arr.length / 2; i ++ ) {
			result += Math.abs(arr[i] - arr[arr.length - i - 1] );
		}
		return result;
	}
}
