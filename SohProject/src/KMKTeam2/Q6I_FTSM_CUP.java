package KMKTeam2;

import java.util.Scanner;
public class Q6I_FTSM_CUP {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++ ) {
			
			int[] teamScores = new int[4];
			int highestScore = 0, winningTeam = -1;
			
			//Each loop retrieves the number of wins, draw and loses for a team
			//Then, it calculates the net score for the team and checks whether the team has so far the highest score
			for (int teamNo = 0; teamNo < 4; teamNo ++ ) {
				
				teamScores[teamNo] += scan.nextInt() * 3;
				teamScores[teamNo] += scan.nextInt();
				scan.nextInt();
				scan.nextLine();
				
				if (teamScores[teamNo] > highestScore) {
					winningTeam = teamNo + 1;
					highestScore = teamScores[teamNo];
				}
				
			}		//end of win, draw and lose retrieving and calculation loop
			
			System.out.printf("Case #%d: %d %d %d %d %d", caseNum, teamScores[0], teamScores[1], teamScores[2], teamScores[3], winningTeam);
			System.out.println();
			
			
			
		}		//end of case completing loop
		
		
			
		
	}		//end of main()

}		//end of class
