package KMKTeam2;

import java.util.Scanner;
public class Q5I_PIG_LATIN {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		scan.nextLine();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++ ) {
			
			String input = scan.nextLine();
			String encodedStr = "";
			int currentPos = 0;
			boolean isLastWord = false;
			
			//Each loop takes a word from the string input, then encodes it and concatenate it to encodedStr
			//Then finds the next " " index and retrieve the word, until there is no longer next space in the string
			for (int nextSpace = input.indexOf(" "); nextSpace != -1 || isLastWord; nextSpace = input.indexOf(" ", nextSpace + 1) ) {
				
				String word;
				//If there is next word (there is a next space), then the word has to be extracted from currentPos until the space
				if (nextSpace != -1)
					word = input.substring( currentPos, nextSpace);
				//If nextSpace is -1, meaning that it is the last word. We take whatever is left in the string starting from currentPos
				else
					word = input.substring(currentPos);
				
				String modifiedWord;
				
				//If the first character of the extracted word is a vowel
				if ( (word.toLowerCase().charAt(0) ) == 'a' ||
					 (word.toLowerCase().charAt(0) ) == 'e'	||
					 (word.toLowerCase().charAt(0) ) == 'i' ||
					 (word.toLowerCase().charAt(0) ) == 'o' ||
					 (word.toLowerCase().charAt(0) ) == 'u'  	)
					modifiedWord = word + "ay";
				//Else the first character is not vowel
				else
					modifiedWord = word.substring(1) + word.charAt(0) + "ay";
				
				encodedStr = encodedStr.concat(modifiedWord + " ");
				
				//Updating the current position to the next index after the space
				currentPos = nextSpace + 1;
				
				//If the above code is running for the last word, break out of the loop immediately
				if (isLastWord)
					break;
				
				//Else if its not the last word yet, but the next index of space is -1 (does not exist), then next word is the last word
				if (!isLastWord && input.indexOf(" ", nextSpace + 1) == -1)
					isLastWord = true;
				
			}		//end of modifying and adding to encoded String loop
			
			//Run this only if the encodedStr is not empty, meaning that the input is not a single word but a sentence
			if ( !encodedStr.isEmpty() )
				System.out.println("Case #" + caseNum + ": " + encodedStr);
			
			//If the string consists of only 1 word
			if ( input.indexOf(" ") == -1) {
				String word = input;
				
				String modifiedWord = "";
				if ( (word.toLowerCase().charAt(0) ) == 'a' ||
					 (word.toLowerCase().charAt(0) ) == 'e'	||
					 (word.toLowerCase().charAt(0) ) == 'i' ||
					 (word.toLowerCase().charAt(0) ) == 'o' ||
					 (word.toLowerCase().charAt(0) ) == 'u'  	)
					modifiedWord = word + "ay";
				else
					modifiedWord = word.substring(1) + word.charAt(0) + "ay";
				
				System.out.println("Case #" + caseNum + ": " + modifiedWord);
			}
				
			
		}		//end of case completing loop
		
		
		

	}		//end of main()

}		//end of class
