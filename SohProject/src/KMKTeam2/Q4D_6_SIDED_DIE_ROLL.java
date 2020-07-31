package KMKTeam2;

import java.util.*;
public class Q4D_6_SIDED_DIE_ROLL {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			int input = scan.nextInt();
			//faceCount represents the number of occurrences of each die face of {1,2,3,4,5,6}
			int[] faceCount = {0, 0, 0, 0, 0, 0};
			
			//Each loop takes an input of dice roll and count the faces occurrences
			for (int j = 0; input != -1; j ++) {
				faceCount[input - 1] ++;
				input = scan.nextInt();
			}		//end of inputting dice roll and counting loop
			
			System.out.println("Case #" + (i + 1) + ": ");
			
			//Each loop display a line which tells the number of occurrences of each face of die
			for (int k = 0; k < 6; k ++ ) {
				System.out.println("Face " + (k + 1) + ":" + faceCount[k]);
			}		//end of occurrences of each face of die
			
		}		//end of case building loop
		
		
		
		
	}		//end of main()

}		//end of class
