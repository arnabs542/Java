//package KMKTeam2;

import java.util.Scanner;
public class Q3F_TWO_SYMMETRIC_TRIANGLES {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int dimension = scan.nextInt();
			//maxStars is the maximum numbers of stars at the bottom of the triangle
			int maxStars = dimension * 2;
			
			System.out.println("Case #" + (i + 1) + ": ");
			
			//Each loop builds a line in the triangle
			for (int line = 1; line <= dimension; line ++ ) {
				
				//number of spaces between the stars of the current line
				int spaces = maxStars - (line * 2);
				
				//Each loop determines whether to print * or space in the current line
				for (int pos = 1; pos <= maxStars; pos ++ ) {
					if ( pos <= line || pos > (maxStars - line) )
						System.out.print("* ");
					else
						System.out.print("  ");
					
				}
				
				//Current line is completed. Open a new line
				System.out.println("");
				
			}		//end of line builder loop
			
		}		//end of case builder loop
		
		
	}		//end of main()
	
}		//end of class
