package KMKTeam2;

import java.util.Scanner;
public class Q3H_FACING_TRIANGLES {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			
			int dimension = scan.nextInt();
			//Maximum number of stars at the middle of the facing triangle 
			int maxStars = dimension * 2;
			
			System.out.println("Case #" + (i + 1) + ": ");
			
			//Indicates whether the triangle had reached the peak and will begin retracting or not
			boolean reachPeak = false;
			
			//Each loop completes a line in the facing triangle
			for (int line = 1; line > 0;  ) {
				
				//Each loop determines whether to put * or space in the current line
				for (int pos = 1; pos <= maxStars; pos ++ ) {
					
					if (pos <= line || pos > (maxStars - line) ) 
						System.out.print("* ");
					else
						System.out.print("  ");
					
				}		//end of * or space placing loop
				
				//The line is completed. Opening a new line
				System.out.println("");
				
				//If the line is right at the maximum and the second, duplicate line havent been printed yet
				if (line == dimension && !reachPeak) {
					reachPeak = true;
					//print the current line of maximum stars again
					continue;
				}
				
				//updating the line number. If peak havent been reached, +1, if reached peak, -1
				if (!reachPeak)
					line ++;
				else
					line --;
				
			}		//end of line building loop
			
		}		//end of case building loop
		
		
		
		
	}		//end of main()

}		//end of class
