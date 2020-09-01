package Easy;

import java.util.Arrays;

//https://leetcode.com/problems/largest-time-for-given-digits/

/*
 * 	This is a Backtracking / Permutation problem.
 * 
 * 	Given 4 digits, we would generate all the 24 permutations of it, then validate each one if it satisfies a condition of 
 *  a valid time
 *  This is done easily if we create a function taking in a 4 digit integer. We get the first 2 digit by dividing 100,
 *  while the rest is modulo 100. If the first 2 is below 24 and the last 2 is below 60, it is a valid time
 *  
 *  We will also approach this in greedy way. Sort the digits in descending order, so when we generated the first available
 *  24 hour time, it is the most largest one
 *  
 *  =================================================================================================================
 * 
 * 	It can be done through some elimination
 * 
 * 	Let's see the restriction on the time format itself:
 * 
 * 	It can be split into 2 cases:
 * 		1.	[2] [0-3] : [0-5] [0-9]			(If the first digit is 2, the second digit is limited from 0 to 3)
 * 		2.	[0-1] [0-9] : [0-5] [0-9]		(Otherwise, first digit is 0 or 1, then second digit is 0 to 9)
 * 
 * 	We can make some conclusion here:
 * 		>	A valid time must have AT LEAST 1 DIGIT WHICH IS <= 2 (FOR FIRST DIGIT)
 * 		>	A valid time must have AT LEAST 2 DIGIT WHICH IS <= 5 (1 INCLUDED IN FIRST DIGIT, 1 FOR THIRD DIGIT)
 * 
 * 	We make further deductions:
 * 		>	To form time in format 2X:XX, it must have
 * 			-	AT LEAST 1 '2'						(AS FIRST DIGIT)
 * 			-	AT LEAST 2 DIGIT WHICH IS <= 3 		(ONE AS '2', ONE FOR SECOND DIGIT)
 * 			-	AT LEAST 3 DIGIT WHICH IS <= 5 		(ONE AS '2', ONE INCLUDED AS SECOND DIGIT, ONE AS THIRD DIGIT)
 * 
 * 		With above conditions satisfied, the time in format 2X:XX can be constructed
 * 
 * 		>	Otherwise, to form a valid time, Let's see edge case: 19:59
 * 			-	AT LEAST 1 DIGIT WHICH IS <= 1 			(FOR FIRST DIGIT)
 * 			-	AT LEAST 2 DIGITS WHICH IS <= 5			(ONE INCLUDED ABOVE AS FIRST DIGIT, OTHER FOR THIRD DIGIT)
 * 
 * 		With above conditions satisfied, the time in format 1X:XX or 0X:XX can be constructed
 * 
 * 	Since digit only from 0-9, we easily make a counter array, and fill in the 4 spaces individually.
 * 
 */

public class Largest_Time_For_Given_Digits {

//	public String largestTimeFromDigits(int[] A) {
//		
//		//	Sort Array in descending order for greedy method
//		Arrays.sort(A);
//		for (int i = 0; i <= 1; i ++ ) {
//			int temp = A[i];
//			A[i] = A[3 - i];
//			A[3 - i] = temp;
//		}
//		// ==================================================
//		
//		
//		int multiplier = 1000;
//		int time = 0;
//		boolean bitmap[] = new boolean[4];
//		
//		return largestTimeFromDigits(A, bitmap, time, multiplier);
//        
//    }
//	
//	private static String largestTimeFromDigits(int[] A, boolean[] bitmap, int time, int multiplier) {
//		if (multiplier == 0) {
//			if (validateTime(time) )
//				return String.format("%0d:%0d", time / 100, time % 100);
//			else
//				return "";
//		}
//		
//		for (int i = 0; i < 4; i ++ ) {
//			if ( !bitmap[i] ) {
//				bitmap[i] = true;
//				time += multiplier * A[i];
//				
//				String res = largestTimeFromDigits(A, bitmap, time, multiplier / 10 );
//				if ( !res.isEmpty() ) return res;
//				
//				time -= multiplier * A[i];
//				bitmap[i] = false;
//			}
//		}
//		
//		return "";
//	}
//	
//	
//	private static boolean validateTime(int n) {
//		int hours = n / 100;
//		int mins = n % 100;
//		
//		if (hours >= 24) return false;
//		if (mins >= 60) return false;
//		
//		return true;
//	}
	
	
	
	
	
	public String largestTimeFromDigits(int[] A) {
		
		Arrays.sort(A);
		
		// Index 0: Frequency of less than or equal to 1
		// Index 1: Frequency of less than or equal to 3
		// Index 2: Frequency of less than or equal to 5
		byte[] cum = new byte[3];
		byte[] freq = new byte[10];
		
		for (int i: A) {
			freq[i] ++;
			
			if (i <= 5) cum[2] ++;
			if (i <= 3) cum[1] ++;
			if (i <= 1) cum[0] ++;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.setLength(5);
		sb.setCharAt(2, ':');
		
		//Able to form 2X:XX
		if (freq[2] >= 1 && cum[1] >= 2 && cum[2] >= 3) {			
			sb.setCharAt(0, '2');
			freq[2] --;
			
			for (int i = 3; i >= 0; i -- ) {
				if (freq[i] > 0) {
					sb.setCharAt(1, (char)(i + '0') );
					freq[i] --;
					break;
				}
			}
			
			for (int i = 5; i >= 0; i --) {
				if (freq[i] > 0) {
					sb.setCharAt(3, (char)(i + '0' ) );
					freq[i] --;
					break;
				}
			}
			
			for (int i = 9; i >= 0; i -- ) {
				if (freq[i] > 0) {
					sb.setCharAt(4, (char)(i + '0') );
					freq[i] --;
					return sb.toString();
				}
			}
			
		}
		
		//Able to form 1X:XX or 0X:XX
		if (cum[0] >= 1 && cum[2] >= 2) {
			
			for (int i = 1; i >= 0; i -- ) {
				if (freq[i] > 0) {
					sb.setCharAt(0, (char)(i + '0') );
					freq[i] --;
					break;
				}
			}
			
			for (int i = 9; i >= 0; i --) {
				if (freq[i] > 0) {
					sb.setCharAt(1, (char)(i + '0' ) );
					freq[i] --;
					break;
				}
			}
			
			for (int i = 5; i >= 0; i -- ) {
				if (freq[i] > 0) {
					sb.setCharAt(3, (char)(i + '0') );
					freq[i] --;
					break;
				}
			}
			

			for (int i = 9; i >= 0; i --) {
				if (freq[i] > 0) {
					sb.setCharAt(4, (char)(i + '0' ) );
					freq[i] --;
					return sb.toString();
				}
			}
			
		}
		
		
		return ""; 		//Still Impossible case
		
	}
	
}
