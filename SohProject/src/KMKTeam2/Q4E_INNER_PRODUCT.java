package KMKTeam2;

import java.util.Scanner;
public class Q4E_INNER_PRODUCT {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			int setSize = scan.nextInt();
			int[] setA = new int[setSize], setB = new int[setSize];
			
			//Each loop adds an element into setA
			for (int a = 0; a < setSize; a ++) 
				setA[a] = scan.nextInt();
			//end of setA element adding loop
			
			for (int b = 0; b < setSize; b ++) 
				setB[b] = scan.nextInt();		
			//end of setA element adding loop
			
			int dotProduct = 0;
			
			//Each loop evaluates product of corresponding term, and add to dotProduct
			for (int j = 0; j < setSize; j ++) {
				dotProduct += ( setA[j] * setB[j] );
			}
			
			System.out.println("Case #" + (i + 1) + ": " + dotProduct);
			
			
		}		//end of case building loop
		

	}		//end of main()

}		//end of class
