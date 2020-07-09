package KMKTeam2;

import java.util.Scanner;
public class Q3D_EQUILATERAL_PATTERN {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case; A triangle is formed
		for (int i = 1; i <= numCases; i ++ ) {
			
			int numRows = scan.nextInt();
			
			System.out.println("Case #" + i + ":");
			
			//Each loop completes a row of triangle
			for (int row = 1; row <= numRows; row ++) {
				
				//At the beginning of the row, print the spaces required first
				for (int pos = 0; pos < numRows - row; pos ++)
					System.out.print(" ");
				
				//Then, print the number of stars according to the row we are currently in
				//Row 1: 1 star		Row 2: 3 star		Row 3: 5 star		Row 4: 7 star 		and so on...(Odd number sequence)
				for (int star = 0; star < (row * 2) - 1; star ++) {
					System.out.print("*");
				}
				
				//Open new line		or use ...print("\n");
				System.out.println();
				
			}
			
		}	
		
	}		//end of main()

}		//end of class
