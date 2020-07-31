package KMKTeam2;

import java.util.Scanner;
public class Q5C_RELOAD_AIRTIME {

	public static void main(String[] args) {
		
		/* Strategy: 1. Check whether the code is *, if yes, terminate the program
		 * 			 2. Check whether the code is 14 characters in length
		 * 			 2. Check whether the front 5 characters are *122*
		 * 			 3. Check whether the following 8 characters are 8 digits and not other characters
		 * 			 4. Check whether the ending character is '#'
		 */
		
		Scanner scan = new Scanner(System.in);
		
		String input;
		
		//Each loop completes a case
		for (int caseNum = 1; true; caseNum ++ ) {
			
			input = scan.next();
			boolean isValid = false;
			
			//If the input is '*', terminate the loop immediately
			if (input.equals("*") )
				break;
			
			//If the input is not 14 characters long
			if (input.length() != 14)
				isValid = false;
			//Else if the input first 5 characters is not *122*
			else if ( !input.substring(0, 5).equals("*122*") )
				isValid = false;
			//Else if the input last character is not #
			else if ( input.charAt(13) != '#')
				isValid = false;
			//Else
			else {
				//The loop checks whether the 5th character to 13th character are digits
				for (int i = 5; i <= 12; i ++ ) {
					isValid = Character.isDigit( input.charAt(i) );
				}
			}
			
			String status;
			if (isValid)
				status = "valid";
			else
				status = "Invalid";
			
			System.out.println("Case #" + caseNum + ": " + status);
			
		}		//end of case building loop
		
		
		
	}		//end of main()

}		//end of class
