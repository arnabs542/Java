package Easy;

//https://leetcode.com/problems/bulls-and-cows/

/*
 * 	This is a Hash Table (HashMap) Problem.
 * 	We shall perform a one pass scan through both the secret and guess string.
 * 	If found the digits are of the same, then add that to the number of bulls counter
 * 
 * 	Otherwise, we can store the number of occurrences of the digit into a frequency table. One for each string.
 * 
 * 	At the end, both the frequency table will store the frequency of each digit which is not bulls.
 * 	Therefore, iterate from 0 to 9, find the intersection (Eg: if frequencies are 5 and 3, take 3. 3 of them are cows) 
 * 	and add that to cows counter
 * 
 * 	===========================================================================
 * 
 * 	The optimized solution models the problem into a supply and demand
 * 	We can think of each digit in the secret String is supply, and each digit in the guess String is demand
 * 
 * 	Create a table from 0 to 9. 
 * 	If it is negative, means we are short on certain digit (Demanded from guess).
 * 	If it is positive, means we have more supply than demand (Supplied more from the secret String)
 * 
 * 	Therefore one pass both the strings.
 * 	Same, if both digits are same, then add that to bulls.
 * 	Otherwise, check the table for the digit of secret. If it is negative (Supply required), add 1 to cows.
 * 		       check the table for the digit of guess. If it is positive (Demand required), add 1 to cows also
 * 	Then since digit is supply and demanded, update the values.
 * 			Digit of secret is added
 * 			Digit of guess is decremented	
 * 	
 * 	
 */

public class Bulls_And_Cows {
	
//	public String getHint(String secret, String guess) {
//		int len = secret.length();
//        int[] freqS = new int[10];
//        int[] freqG = new int[10];
//
//        int nBulls = 0;
//        int nCows = 0;
//        
//        for (int i = 0; i < len; i ++ ) {
//        	if (secret.charAt(i) == guess.charAt(i) ) {
//        		nBulls ++;
//        	} else {
//        		freqS[ secret.charAt(i) - '0'] ++;
//        		freqG[ guess.charAt(i) - '0'] ++;
//        	}
//        }
//        
//        for (int i = 0; i < 10; i ++ ) {
//        	nCows += Math.min(freqS[i], freqG[i] );
//        }
//        
//        return String.format("%dA%dB", nBulls, nCows);
//    }
	
	
	public String getHint(String secret, String guess) {
		int len = secret.length();
		
		int[] supplyDemand = new int[10];
		
		int nBulls = 0;
		int nCows = 0;
		
		for (int i = 0; i < len; i ++ ) {
			int s = secret.charAt(i) - '0';
			int g = guess.charAt(i) - '0';
			if (s == g ) nBulls ++;
			else {
				if ( supplyDemand[s] < 0) nCows ++;
				if ( supplyDemand[g] > 0) nCows ++;
				supplyDemand[s] ++;
				supplyDemand[g] --;
			}
		}
		
		return String.format("%dA%dB", nBulls, nCows);
	}
	
}
