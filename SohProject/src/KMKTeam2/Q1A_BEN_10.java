//package KMKTeam2;

import java.util.Scanner;
public class Q1A_BEN_10 {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		int counter = 0;
		
		//receive input for 15 times, if input is greater than 10, increment counter
		for (int i = 0; i < 15; i++) {
			if ( scan.nextInt() > 10 )
				counter ++;
		}
		
		System.out.println(counter);
		
	}		//end of main()
	
}		//end of class
