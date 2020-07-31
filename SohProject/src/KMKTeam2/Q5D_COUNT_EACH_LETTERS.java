package KMKTeam2;

import java.util.Scanner;

public class Q5D_COUNT_EACH_LETTERS {

	public static void main(String[] args) {
		
		//The numeric representation of characters from a to z is 97 (a) to 122 (z), by using (int)charVariable
		//Therefore we can use numeric value - 10 to represent the index in array
		//Since capital cases are no difference than small letters, change the string to lowerCase first
		
		Scanner scan = new Scanner(System.in);
		
		//Each loop completes a case
		for (int caseNum = 1; true; caseNum ++ ) {
			
			String input = scan.nextLine();
			
			if (input.equals("#") )
				break;
			
			//An array to count the number of occurrences of alphabet in the String. 0 represents 'a' and 25 represents 'z'
			int[] letterCounter = new int[26];
			input = input.toLowerCase();
			
			
			//Each loop checks for a specific character in the String whether it is a alphabet
			//If yes, the respective alphabet counter is incremented
			for (int i = 0; i < input.length(); i ++ ) {
				
				char character = input.charAt(i);
				
				if ( Character.isAlphabetic(character) )
					//Since character a represents 97 in int value, simply -97 to make it a respective array index
					letterCounter[ character - 97 ] ++;
				
			}		//end of character is alphabet checker loop
			
			System.out.print("Case #" + caseNum + ": ");
			
			//Each loop prints the alphabet and its respective frequency in the String
			for (int i = 0; i < 26; i ++ ) {
				
				//If a alphabet frequency is not 0
				if ( letterCounter[i] != 0 ) {
					char character = (char) (i + 97);
					System.out.print( character + "(" + letterCounter[i] + ") ");
				}
				
			}		//end of alphabet frequency printing loop
			
			//Printing is done. Open a new line
			System.out.println();
			
		}		//end of case building loop
		
		
		
	}		//end of main()

}		//end of class
