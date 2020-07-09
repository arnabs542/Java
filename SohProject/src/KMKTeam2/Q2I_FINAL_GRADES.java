//package KMKTeam2;

import java.util.Scanner;
public class Q2I_FINAL_GRADES {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int sum = 0;
			for (int j = 0; j < 3; j ++)
				sum += scan.nextInt();
			
			char grade = ' ';
			if (sum < 50)
				grade = 'F';
			else if (sum <= 59)
				grade = 'D';
			else if (sum <= 79)
				grade = 'C';
			else if (sum <= 89)
				grade = 'B';
			else
				grade = 'A';
			
			System.out.println("Student #" + (i + 1) + ": " + sum + " " + grade);
		}
		
		
		
	}		//end of main()
	
	
	
}		//end of class
