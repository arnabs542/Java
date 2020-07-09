//package KMKTeam2;

import java.util.Scanner;
public class Q5E_IS_IT_THE_SAME {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++ ) {
			
			//Careful: Capital letters and small letters doesn't matter. Thus, make the input to all lower case
			String input = scan.next();
			input = input.toLowerCase();
			String backwards = "";
			
			//Each loop adds a character to the String backwards. The added character start from the end of input String
			for (int pos = input.length() - 1; pos >= 0; pos -- ) {
				backwards = backwards.concat( input.charAt(pos) + "" );
			}		//end of character adding loop
			
			boolean isPalindrome = input.equals(backwards);
			
			if (isPalindrome)
				System.out.println("Case #" + (caseNum) + ": Yes");
			else
				System.out.println("Case #" + (caseNum) + ": No");
				
		}		//end of case building loop
		
		
		

	}		//end of main()

}		//end of class
