package KMKProgChallenge;

import java.util.Arrays;
import java.util.Scanner;
public class ArraySeperator {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int caseNo = 1; caseNo <= numCases; caseNo ++ ) {
			
			int size = scan.nextInt();
			int[] array = new int[size];
			int zeroCount = 0;
			
			//Counts the occurrence of zeroes
			for (int index = 0; index < size; index ++ ) {
				array[index] = scan.nextInt();
				
				if (array[index] == 0)
					zeroCount ++;
			}
			
			//The new array must be of size zeroCount + 1
			int[] newArray = new int[zeroCount + 1];
			int sum = 0;
			int pos = 0;
			
			for (int index = 0; index < size; index ++ ) {
				//If had reached the end of array
				if (index == size - 1) {
					sum += array[index];
					newArray[pos] = sum;
				//Else if the array element is 0 (array separator)	
				}
				else if (array[index] == 0) {
					newArray[pos] = sum;
					pos ++;
					sum = 0;
				}
				else
					sum += array[index];
			}
			
			System.out.println("Case #" + caseNo + ": " + Arrays.toString(newArray));
			
		}

	}		//end of main()

}		//end of class
