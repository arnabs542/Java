//package KMKTeam2;

import java.util.Scanner;
public class Q6E_MODE_SODA {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numRespondent;
		
		//Each loop completes a case
		for (int caseNum = 1; true; caseNum ++ ) {
			
			numRespondent = scan.nextInt();
			
			//If the number of respondent input is 0, terminate the program immediately
			if (numRespondent == 0)
				System.exit(0);
			
			//Stores the netMark for 4 of the sodas. element at index 0 represents net mark for soda-1 and so on
			int[] netMark = new int [4];
			
			//Each loop takes the rating from one individual respondent and records their mark
			for (int respondentNo = 0; respondentNo < numRespondent; respondentNo ++ ) {
				netMark[0] += scan.nextInt();
				netMark[1] += scan.nextInt();
				netMark[2] += scan.nextInt();
				netMark[3] += scan.nextInt();
				scan.nextLine();
			}		//End of rating taking loop
			
			System.out.println("Case #" + caseNum + ": ");
			System.out.printf("Soda-%d: %.2f \n", 1, (netMark[0] * 1.0 / numRespondent ) );
			System.out.printf("Soda-%d: %.2f \n", 2, (netMark[1] * 1.0 / numRespondent ) );
			System.out.printf("Soda-%d: %.2f \n", 3, (netMark[2] * 1.0 / numRespondent ) );
			System.out.printf("Soda-%d: %.2f \n", 4, (netMark[3] * 1.0 / numRespondent ) );
			
		}		//end of case completing loop
		
		

	}		//end of main()

}		//end of class
