package KMKTeam2;

import java.util.Scanner;
public class Q2B_WHICH_QUADRANT {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int xCoor = scan.nextInt();
			int yCoor = scan.nextInt();
			
			if (xCoor == 0 || yCoor == 0)
				System.out.println("Case #" + (i + 1) + ": AXIS" );
			else if (xCoor > 0 && yCoor > 0)
				System.out.println("Case #" + (i + 1) + ": Q1" );
			else if (xCoor < 0 && yCoor < 0)
				System.out.println("Case #" + (i + 1) + ": Q3" );
			else if (xCoor > 0 && yCoor < 0)
				System.out.println("Case #" + (i + 1) + ": Q4" );
			else
				System.out.println("Case #" + (i + 1) + ": Q2" );
			
		}
		
		
		
	}		//end of main()
	
	
	
}		//end of class
