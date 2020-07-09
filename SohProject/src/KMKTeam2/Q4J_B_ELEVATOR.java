//package KMKTeam2;

import java.util.Scanner;
public class Q4J_B_ELEVATOR {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++ ) {
			
			int numStudents = scan.nextInt();
			int totalPound = 0;
			int allowCounter = 0;
			
			//Each loop retrieves a students pound, and determines whether the people in the elevator now exceeds the limit
			for (int i = 0; i < numStudents; i ++ ) {
				totalPound += scan.nextInt();
				
				if (totalPound < 500)
					allowCounter ++;
				
			}		//end of weight retrieving and limit determining loop
			scan.nextLine();
				
			System.out.println("Case #" + caseNum + ": " + allowCounter);
			
			
			
			
			
		}		//end of case completing loop
		
		
		
		
	}		//end of main()

}		//end of class
