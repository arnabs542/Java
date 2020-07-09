package KMKProgChallenge;

import java.util.Scanner;
public class VotingTime {

	public static void main(String[] args) {

		//In case of same voters doing 2 votes which the first vote is a troll, then even if the second vote is valid (but isVoted)
		//it will not count
		//For same voters doing 2 votes which the first vote is valid, the first vote is COUNTED. The second vote, no matter is a troll
		//or valid, is not counted
		//For votes that consist of characters other than A or B, it is not counted, and the voter is consider voted and is
		//not eligible to vote again
		
		//Ahmad: vote 'A'
		//Beng: vote 'B'
		
		Scanner scan = new Scanner(System.in);
		
		int voters = scan.nextInt();
		
		for (int caseNo = 0; voters != 0 ; caseNo ++) {
			
			//Voter's code must be in INT (Since string.equals() not in syllabus)
			int[] voterCode = new int[voters];
			char[] choice = new char[voters];
			//Since like this, we have to mention that voter's code is in range 0 - 99 inclusive
			boolean[] hasVoted = new boolean[20];
			
			for (int i = 0; i < voters; i ++ ) {
				voterCode[i] = scan.nextInt();
				choice[i] = scan.next().charAt(0);
			}
			
			int ahmadCount = 0, bengCount = 0;
			
			//Counting vote loop
			for (int i = 0; i < voters; i ++) {
				int currentCode = voterCode[i];
				//If this guy haven't voted before
				if (!hasVoted[currentCode]) {
					hasVoted[currentCode] = true;
					
					if (choice[i] == 'A')
						ahmadCount ++;
					else if (choice[i] == 'B')
						bengCount ++;
				}	//end of if case, checking if the current voter has voted before and count the vote process
				
			} 		//end of counting vote loop
			
			System.out.println("Case #" + (caseNo + 1) + ": " + ahmadCount + " " + bengCount);
			
			voters = scan.nextInt();
			
		}		//end of case completing loop
		
	}		//end of main()

}		//end of class
