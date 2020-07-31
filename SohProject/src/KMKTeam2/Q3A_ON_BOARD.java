package KMKTeam2;

import java.util.Scanner;
public class Q3A_ON_BOARD {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Drawing the board for each cases
		for (int i = 0; i < numCases; i++ ) {
			
			int row = scan.nextInt();
			int column = scan.nextInt();
			System.out.println("Board #" + (i + 1) + ": " + row + "*" + column);
			
			for (int vertical = 0; vertical <= row; vertical ++) {
				
				//check whether it is first line or not, thus if drawing | | | is needed
				if (vertical != 0) {
					for (int verticalSign = 0; verticalSign <= column; verticalSign ++) {
							System.out.print("| ");
					}
					//after drawing | | | a new line is opened
					System.out.println("");
				}
				
				//Drawing the +-+-+-+ based on the number of column. The plus sign drawn always is (column + 1)
				//Note that the - sign must be always in between of 2 + sign, EXCEPT IF IT IS FIRST CHARACTER IN THE ROW
				for (int plusSign = 0; plusSign <= column; plusSign ++) {
					if (plusSign != 0)
						System.out.print('-');
					System.out.print('+');
				}
				//after drawing +-+-+ a new line is opened
				System.out.println("");
				
			}		//end of for loop for drawing board
		
		}		//end of for loop for each test cases
		
		
	}		//end of main
	
	
}		//end of class
