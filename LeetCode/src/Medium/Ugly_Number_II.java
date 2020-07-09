package Medium;


import java.util.TreeSet;

//https://leetcode.com/problems/ugly-number-ii/

/*
 * 	This problem is a dynamic programming question.
 * 	The key to realize first is that ugly numbers must be an ugly number multiplied by 2,3 or 5. See example:
 * 	1,2,3,4,5,6,8,9,10,12...
 * 	2 is 1 (Ugly) multiply 2
 * 	3 is 1 (Ugly) multiply 3
 * 	4 is 2 (Ugly) multiply 2
 * 	5 is 1 (Ugly) multiply 5
 * 	6 is 2 (Ugly) multiply 3 or 3 (Ugly) multiply 2
 * 	8 is 4 (Ugly) multiply 2
 * 	9 is 3 (Ugly) multiply 3
 * 	10 is 5(Ugly) multiply 2 or 2 (Ugly) multiply 5
 * 	12 is 6(Ugly) multiply 2 or 4 (Ugly) multiply 3
 * 
 * 	Notice 7 does not have any ugly number multiplied to 2,3,5, nor does 11.
 * 	14 is multiple of 2, but since it is 7 (NOT UGLY) times 2, it does not count as an ugly number
 * 
 * 	Therefore, instead of going through each possible ugly number to identify if it is an ugly number, try to generate ugly numbers instead, as most of the possible
 * 	numbers are not ugly, brute force solution would almost iterate until Integer.MAX times!
 * 
 * 	Make an array of n sizes where n is the argument to find nth ugly number, and initialize the array[0] to 1
 * 
 * 	Make three variables representing the multiples of 2, 3 and 5. Initialize them to 2,3 and 5 respectively. This means that they store the values of
 * 	1 (AN UGLY NUMBER) multiplied to 2, 3 and 5.
 * 
 * 	Make three pointers (just int variable) representing the indexes of where the ugly number multiplied come from. Initialize them to index 0, since
 * 	initially, the three multiplies of 2 3 and 5 are 2, 3 and 5 themselves, which came from the ugly number 1, at index 0 of ugly numbers
 * 
 * 	Now we have to iterate n times to fill the array. The array will get filled with ugly numbers, where each loop, we will determine the ugly number to be
 * 	chosen by using Math.min() on the three variables.
 * 	After filling in the ugly number, we have to update the variables. Check if the filled ugly number is of the same with each of the variables.
 * 	If yes, that means the ugly number is already been filled in, so we need to find the next ugly number and multiply by 2,3 or 5 respectively, depending on which
 * 	ugly number is filled.
 * 	Therefore, increment the index value by 1 (Meaning pointer has moved to next ugly number), and update the variable to have the next ugly number multiplied by
 * 	2, 3 or 5.
 */

public class Ugly_Number_II {
	
	public static int nthUglyNumber(int n) {
		TreeSet<Long> set = new TreeSet<>();
		set.add(1L);
		
		long curr = 1;
		for (int i = 0; i < n; i ++ ) {
			curr = set.pollFirst();
			set.add(curr * 2L);
			set.add(curr * 3L);
			set.add(curr * 5L);
		}
		return (int)curr;
	}
	
	
	public static int nthUglyNumberDP(int n ) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		
		long min = 1L;
		int idx2 = 0, idx3 = 0, idx5 = 0;
		long multi2 = 2, multi3 = 3, multi5 = 5;
		
		for (int i = 1; i < n; i ++ ) {
			min = Math.min( Math.min(multi2, multi3), multi5);
			ugly[i] = (int)min;
			
			//Notice those conditions are not mutually exclusive (Meaning two or more if statements may be executed). This means the ugly number filled may be contained
			//in more than one variables. Therefore we'll update them both or all.
			if (min == multi2) {
				idx2 ++;
				multi2 = ugly[idx2] * 2L;
			}
			if (min == multi3) {
				idx3 ++;
				multi3 = ugly[idx3] * 3L;
			}
			if (min == multi5) {
				idx5 ++;
				multi5 = ugly[idx5] * 5L;
			}
		}
		return (int)min;
	}
	
	public static void main(String[]args) {
		
	}
}
