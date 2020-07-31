package KMKTeam2;

import java.util.Scanner;
import java.util.ArrayList;
public class Q5F_WORD_SCRAMBLE {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		scan.nextLine();
		
		//Each loop completes a case
		for (int caseNum = 1; caseNum <= numCases; caseNum ++ ) {
			
			String input = scan.nextLine();
			ArrayList<String> words = new ArrayList<String>();
			
			int endMarker = input.indexOf("#");
			int latestSpace = input.indexOf(" ");
			int afterSpace = 0;
			
			//Each loop records each individual word into the arrayList words, where each word separated by space
			while (latestSpace < endMarker) {
				
				if (latestSpace == -1)
					break;
				
				
				words.add( input.substring(afterSpace , latestSpace) );
				
				afterSpace = latestSpace + 1;
				latestSpace = input.indexOf(" ", latestSpace + 1);
			}		//end of recording each individual word loop
			
			String inverse = "";
			
			//Each loop adds an inverted word into the inverse String
			for (String beforeInvert: words) {
				
				String afterInvert = "";
				
				//Each loop adds a character to the afterInvert string, starting from behind the 
				for (int i = beforeInvert.length() - 1; i >= 0; i -- )
					afterInvert = afterInvert.concat( beforeInvert.charAt(i) + "" );
				//End of character adding loop
				
				inverse = inverse.concat(afterInvert + " ");
				
			}		//end of inverted word adding loop
			
			System.out.println("Case #" + caseNum + ": " + inverse);
			
			
		}		//end of case building loop
		
		
		

	}		//end of main()

}		//end of class
