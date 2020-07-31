package KMKTeam2;

import java.util.Scanner;
public class Q5G_WORD_COUNTER {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		scan.nextLine();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++ ) {
			
			String input = scan.nextLine();
			int wordCount = 0;
			
			//Each loop checks whether the character is a space. If so, wordCount is +1, unless next character is #
			for (int pos = 0; pos < input.length(); pos ++ ) {
				
				char character = input.charAt(pos);
				
				if (character == ' ')
					wordCount ++;
				
				if (character == '#') {
					break;
				}
				
				
			}		//end of character checking loop
			
			System.out.println("Case #" + (caseNum) + ": " + wordCount);
			
		}		//end of case completing loop
		
		
		
		
		

	}		//end of main()

}		//end of class
