//package KMKTeam2;

import java.util.Scanner;
public class Q3B_N_TRIANGLE {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numTriangle = scan.nextInt();
		int size = scan.nextInt();
		
		//Each loop constructs one triangle
		for (int i = 0; i < numTriangle; i ++ ) {
			
			//Each loop constructs one line of triangle
			for (int line = 1; line <= size; line ++ ) {
				
				//Each loop adds a number element in one line of triangle
				for (int num = 1; num <= line; num ++ ) {
					System.out.print(num + " ");
				}
				System.out.println("");
				
			}
			
		}
		
	}		//end of main()

}		//end of class
