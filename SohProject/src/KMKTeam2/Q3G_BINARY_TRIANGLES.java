//package KMKTeam2;

import java.util.Scanner;
public class Q3G_BINARY_TRIANGLES {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			int dimension = scan.nextInt();
			
			System.out.println("Case #" + (i + 1) + ": ");
			
			//Each loop completes a line of the binary triangle
			for (int line = 1; line <= dimension; line ++ ) {
				
				boolean isOne;
				
				//If the line number is odd, the line starts with 1
				//Conversely, if the line number is even, the line starts with 0
				if (line % 2 == 0)
					isOne = false;
				else
					isOne = true;
				
				//Each loop determines whether to put 0 or 1 in the current line
				for (int pos = 1; pos <= line; pos ++ ) {
					
					if (isOne) {
						System.out.print(1);
						isOne = false;
					}
					else {
						System.out.print(0);
						isOne = true;
					}
					
				}		//end of 0 or 1 placing loop
				
				//Open a new line as the current line is completed
				System.out.println("");
				
			}		//end of line building loop
			
		}		//end of case building loop
		
		

	}		//end of main()

}		//end of class
