package KMKTeam2;

import java.util.Scanner;
public class Q5B_SECRET_MESSAGE {

	public static void main(String[] args) {
		
		//Strategy: 1. Check each character in the String one by one, if it is a alphabet or a digit or not
		//			2. If false: just add the respective character into the new String untouched
		//			3. If true: Check if the alphabet is 9, z, or Z
		//					YES: Make it 0, a, or A
		//					NO : Add 1 to the char
		//				Then, add modified character into the new String
		
		Scanner scan = new Scanner(System.in);
		
		//Each loop modifies a single String inputed 
		for (int caseNum = 1; true; caseNum ++) {
			
			String secretMsg = scan.nextLine();
			String newMsg = "";
			
			//If the input was "#", terminate the loop immediately
			if ( secretMsg.equals("#") )
				break;
			
			//Each loop checks for a character in the message and adds the processed character into new, modified String
			for (int i = 0; i < secretMsg.length(); i ++ ) {
				
				char character = secretMsg.charAt(i);
				
				//If the checked character is not a alphabet nor a digit
				if ( !Character.isLetterOrDigit( character ) )
					newMsg = newMsg.concat(character + "");
				else {
					//Check whether the character is 'z', 'Z' or '9' which requires special processing
					switch (character) {
						case 'z': character = 'a';
								  break;
						case 'Z': character = 'A';
								  break;
						case '9': character = '0';
								  break;
						default : character += 1;
								  break;
					}
					newMsg = newMsg.concat(character + "");
				}		
				
			}		//end of character checking loop
			
			System.out.println("Case #" + (caseNum) + ": " + newMsg);
			
		}		//end of message modifying loop
		

	}		//end of main()

}		//end of class
