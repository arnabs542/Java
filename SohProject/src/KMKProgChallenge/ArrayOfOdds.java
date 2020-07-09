package KMKProgChallenge;

import java.util.Arrays;
import java.util.Scanner;
public class ArrayOfOdds {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int numCases = scan.nextInt();
		
		for (int caseNo = 1; caseNo <= numCases; caseNo ++ ) {
			
			int num = scan.nextInt();
			
			//Determines the size of array need to be created
			int size = (num / 2) + (num % 2);
			int[] array = new int[size];
			
			int digit = 1;
			
			for (int i = 0; i < size; i ++ ) {
				array[i] = digit;
				digit += 2;
			}
			
			System.out.println("Case #" + caseNo + ": " + Arrays.toString(array));
			
		}
		

	}		

}	
