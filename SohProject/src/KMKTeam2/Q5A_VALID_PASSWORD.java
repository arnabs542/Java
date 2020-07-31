package KMKTeam2;

import java.util.Scanner;
public class Q5A_VALID_PASSWORD {

	public static void main(String[] args) {

		/* Things to be checked in the password:
		 * 1. At least 6 characters long
		 * 2. No capital letters
		 * 3. No characters other than digit 0-9 and small letters a-z
		 */
		Scanner scan = new Scanner(System.in);
		
		String password = scan.next();
		
		//Each loop checks a password
		for (int caseNum = 1; !password.equals("#"); caseNum ++) {
			
			//1. at least 6 characters long
			boolean isCaseOneValid = password.length() >= 6;
			
			//2. No capital letters
			boolean isCaseTwoValid = ( password.toLowerCase().equals(password) );
			
			//3. No characters other than digit 0-9 and small letters a-z
			boolean isCaseThreeValid = true;
			for (int i = 0; i < password.length(); i ++ ) {
				char toBeChecked = password.charAt(i);
				
				//If the character is not a letter nor a digit, then isCaseThreeValid will always be false
				if ( !Character.isLetterOrDigit(toBeChecked) )
					isCaseThreeValid = false;
			}
			
			//If one case fails, the password fails
			boolean isValid = isCaseOneValid && isCaseTwoValid && isCaseThreeValid;
			
			//Since we need to show status as "valid" or "invalid", just make a String
			String status;
			if (isValid)
				status = "valid";
			else
				status = "invalid";
			
			System.out.println("Case #" + (caseNum) + ": " + status);
			
			password = scan.next();
		}		//end of password checking loop
		
		
		
	}

}
