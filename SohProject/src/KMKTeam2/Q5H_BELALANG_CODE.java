package KMKTeam2;

import java.util.Scanner;
public class Q5H_BELALANG_CODE {

	// 'a' is 97 in int according to java's character numbering
	// 'z' is 122 in int according to java's character numbering
	// ' ' is 32 in int according to java's character numbering
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		scan.nextLine();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++ ) {
			
			String input = scan.nextLine();
			String encodedStr = "";
			
			//Each loop encodes a character and adds it to the encodedStr string
			for (int pos = 0; pos < input.length(); pos ++ ) {
				
				int thisCharNum, encodedCharNum;
				
				//if the character is space, set the custom number to 27
				if (input.charAt(pos) == ' ')
					thisCharNum = 27;
				//else, convert the character from java's numbering to custom numbering (-96)
				else
					thisCharNum = input.charAt(pos) - 96;
				
				//The character custom numbering is converted to its respective custom numbering of encoded character
				//Eg: ' ' (27) is converted to a (1)
				encodedCharNum = 27 - (thisCharNum - 1);
				
				char encodedChar;
				//If the encoded character is a space, set the character to space ' '
				if (encodedCharNum == 27)
					encodedChar = ' ';
				//else, set the character to its respective character according to java's character numbering
				else
					encodedChar = (char)( encodedCharNum + 96);
				
				encodedStr = encodedStr.concat( encodedChar + "" );
			}		//end of character encoding and concatenate loop
			
			System.out.println("Case #" + caseNum + ": " + encodedStr);
			
		}		//end of case completing loop
		
		

	}		//end of main()

}		//end of class
