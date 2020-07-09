//package KMKTeam2;

import java.util.Scanner;
public class Q4G_ZERO_TO_HERO {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			int numInt = scan.nextInt();
			int[] listNum = new int[numInt];
			int maxOddNum = 0;
			
			//Each loop adds a element into the array listNum[], and also determines whether the number input is maximum so far
			for (int j = 0; j < numInt; j ++ ) {
				
				listNum[j] = scan.nextInt();
				
				//Determines whether the input is the maximum odd number compared to previous, if yes, replace it with current one
				if (listNum[j] > maxOddNum && listNum[j] % 2 != 0)
					maxOddNum = listNum[j];
			}//end of element adding loop
		
			String stringNum = "";
			for (int element : listNum) {
				//If stringNum is already has a element in it, add a comma before next element
				if (!stringNum.isEmpty())
					stringNum = stringNum.concat(", ");
				//If element of array is 0, replace it with maximum odd number in array
				if (element == 0 )
					stringNum = stringNum.concat(maxOddNum + "");
				//Otherwise just add the element into the string
				else
					stringNum = stringNum.concat(element + "");
			}
			
			System.out.println("Case #" + (i + 1) + ": [" + stringNum + "]" );
			
		}		//end of case building loop
			

	}		//end of main()

}		//end of class
