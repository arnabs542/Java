package KMKTeam2;

import java.util.Scanner;
public class Q6D_SELF_STUDY {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numStudent;
		
		//Each loop completes a case
		for (int caseNum = 1; true; caseNum ++ ) {
			
			numStudent = scan.nextInt();
			
			//If the number of student inputted is 0, terminate the program immediately
			if (numStudent == 0)
				System.exit(0);
			
			//Records the study Hours for each individual student
			int[] studyHours = new int[numStudent];
			//Counts the number of student studying over 25 hours
			int over25Count = 0;
			
			//Each loop takes a student and request for its study hours in a week
			for (int studentNo = 0; studentNo < numStudent; studentNo ++ ) {
				
				//Each loop takes a day in a week and request for study hour of that day
				for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek ++ )
					studyHours[studentNo] += scan.nextInt();
				//End of daily study hour loop
				
				//If this student studies over 25 hours, increment over25Count
				if (studyHours[studentNo] > 25)
					over25Count ++;
				
				scan.nextLine();
			}		//end of student study hour loop
			
			System.out.printf("Case #%d: %.2f%%", caseNum, (over25Count * 100.0 / numStudent) );
			System.out.println();
			//Each loop prints out a student along with its total study hours
			for (int studentNo = 0; studentNo < numStudent; studentNo ++ ) {
				System.out.println("Student " + (studentNo + 1) + ": " + studyHours[studentNo]);
			}		//end of student study hour printing loop
			
			
		}		//end of case completing loop
		
		
		

	}		//end of main()

}		//end of class
