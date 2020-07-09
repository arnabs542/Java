//package KMKTeam2;

import java.util.Scanner;
public class Q4A_COCONUT_KELAPA {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			int numDays = scan.nextInt();
			int [] numCoconut = new int[numDays];
			//Be careful with minCoco because if undetected, the minCoco will always be -1. Even if you set minCoco to
			//a very big number, there might be test cases where the smallest number is greater
			int maxCoco = 0, minCoco = -1, maxCounter = 1, minCounter = 1;
			
			//Each loop retrieves the number of coconut harvested for a day
			for (int j = 0; j < numDays; j ++ ) {
				numCoconut[j] = scan.nextInt();
				
				//Checks if the number of coconut on particular day is equal to the recorded maximum coconut amount
				if ( numCoconut[j] == maxCoco)
					maxCounter ++;
				
				//Checks if the number of coconut on particular day is equal to the recorded minimum coconut amount
				if ( numCoconut[j] == minCoco)
					minCounter ++;
				
				
				//Checks if the number of coconut on particular day is greater than the recorded maximum coconut amount
				if ( numCoconut[j] > maxCoco ) {
					maxCoco = numCoconut[j];
					maxCounter = 1;
				}
				
				//Checks if the number of coconut on particular day is lesser than the minimum number of coconut recorded
				//, or it is the first number of coconut recorded
				if ( numCoconut[j] < minCoco || minCoco == -1 ) {
					minCoco = numCoconut[j];
					minCounter = 1;
				}
				
				
			}		//end of coconut harvest amount retriever and processing loop
			
			System.out.println(minCoco + " " + minCounter + " " + maxCoco + " " + maxCounter);
			
		}		//end of case building loop
		
		
		
	}		//end of main()

}		//end of class
