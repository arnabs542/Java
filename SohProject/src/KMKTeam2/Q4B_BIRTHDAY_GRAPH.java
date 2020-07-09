//package KMKTeam2;

import java.util.Scanner;
public class Q4B_BIRTHDAY_GRAPH {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		final String[] MONTH_NAMES = {
				"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"
		};

		//Each loop completes a case, will loop unless numCases input is 0
		for (int caseNum = 1; true; caseNum ++) {
			
			int numCases = scan.nextInt();
			int[] monthCount = {0,0,0,0,0,0,0,0,0,0,0,0};
			
			if (numCases == 0)
				break;
			
			//Each loop retrieves a birthday and add to the respective month counter
			for (int i = 0; i < numCases; i ++) {
				
				int dummyDay = scan.nextInt();
				int month = scan.nextInt();
				int dummyYear = scan.nextInt();
			
				monthCount[ month - 1 ] ++;
			
			}		//end of birthday date retrieving and processing loop
			
			System.out.println("Case #" + caseNum + ": ");
			
			//Each loop prints the respective month, with the * indicating how many people has birthday that month
			for (int month = 0; month < 12; month ++ ) {
				
				System.out.print( MONTH_NAMES[month] + ":" );
				
				//Each loop adds a * to the respective month
				for (int freqCount = 1; freqCount <= monthCount[month]; freqCount ++) 
					System.out.print("*");
				
				//The graph for particular month is done, opening a new line
				System.out.println("");
				
			}		//end of month printing loop
			
			
		}		//end of case builder loop
		
	}		//end of main()

}		//end of class
