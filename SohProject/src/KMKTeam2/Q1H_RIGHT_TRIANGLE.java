package KMKTeam2;

import java.util.Scanner;
public class Q1H_RIGHT_TRIANGLE {
	
	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Simply check if hypotenuse squared is equal to sum of square of both sides of triangle
		for (int i = 0; i < numCases; i ++ ) {
			
			int length = scan.nextInt();
			int width = scan.nextInt();
			int hypotenuse = scan.nextInt();
			
			if (hypotenuse * hypotenuse == (length * length) + (width * width))
				System.out.println("Case #" + (i + 1) + ": YES");
			else
				System.out.println("Case #" + (i + 1) + ": NO");
			
		}
		
		
	}		//end of main()
	
	
}		//end of class


