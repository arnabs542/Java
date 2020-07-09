//package KMKTeam2;

import java.util.Scanner;
public class Q6B_MULTIPLE_CHOICE {

	public static void main(String[] args) {
			
		Scanner scan = new Scanner(System.in);
		
		int numStudent;
		
		//Each loop completes a case
		for (int caseNum = 1; true; caseNum ++ ) {
			
			numStudent = scan.nextInt();
			scan.nextLine();
			
			if (numStudent == 0)
				break;
			
			//Input for the answer (key)
			String keyInput = scan.nextLine();
			int ansLength = keyInput.length();
			
			char[] key = new char[ansLength];
			char[][] studentAns = new char[numStudent][ansLength];
			double[] studentMark = new double[numStudent];
			
			//Stores the answer (key) into the array key[]
			for (int pos = 0; pos < ansLength; pos ++ )
				key[pos] = keyInput.charAt(pos);
			
			//Each loop obtains the answer of a student, and determines its mark
			for (int studentNum = 0; studentNum < numStudent; studentNum ++ ) {
				
				String studAns = scan.nextLine();
				
				//Each loop determines whether an answer is correct or not, and updates the student's mark
				for (int ansPos = 0; ansPos < ansLength; ansPos ++ ) {
					studentAns[studentNum][ansPos] = studAns.charAt(ansPos);
					
					if ( studentAns[studentNum][ansPos] == key[ansPos])
						studentMark[studentNum] ++;
					else
						studentMark[studentNum] -= 0.25;
					
				}		//end of answer determining loop
				
			}		//end of answer obtaining and mark determining loop
			
			System.out.println("Case #" + caseNum + ": ");
			
			//Each loop prints a student's mark
			for (int studentNum = 0; studentNum < numStudent; studentNum ++ ) {
				System.out.printf("Student %d: %.2f", (studentNum + 1), studentMark[studentNum]);
				System.out.println();
			}		//end of student's mark printing loop
			
		}		//end of case completing loop
		
		
		
	}		//end of main()

}		//end of class
