package KMKTeam2;

import java.util.Scanner;
public class Q4C_ABOVE_AVERAGE {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			int[] marks = new int[100];
			int numStudent = 0;
			int input = 0;
			int sum = 0;
			
			//Each loop reads an instance of mark from the line. If it reads -1, the loop is broken
			for ( ; numStudent < 100 ; numStudent ++ ) {
				input = scan.nextInt();
				if (input == -1)
					break;
				else {
					marks[ numStudent ] = input;
					sum += input;
				}
			}
			
			//Be careful of division involving 2 integer values. Otherwise it may give incorrect result as in Example case 2
			int average = (int)( Math.round( (double)sum / numStudent ) );
			//Number of students above average
			int numAbvAverage = 0;
			
			//Each loop checks if a particular student is above average, and decides to increment numAbvAverage or not
			for (int j = 0; j < numStudent; j ++) {
				if ( marks[j] > average )
					numAbvAverage ++;
			}
			
			//As above, be careful of division involving 2 integers, as incorrect result may be gotten as in Example case 2
			int abvAvgPercent = (int)( Math.round( numAbvAverage * 100.0 / numStudent) );
			
			System.out.println("Case #" + (i + 1) + ": " + average + " " + abvAvgPercent + "%");
			
		}		//end of case building loop

	}		//end of main()

}		//end of class
