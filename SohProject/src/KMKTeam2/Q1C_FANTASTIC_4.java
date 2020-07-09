//package KMKTeam2;

import java.util.Scanner;
public class Q1C_FANTASTIC_4 {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int divisibleBy4 = 0;
		int input = 0;
		
		while (input != -1) {
			input = scan.nextInt();
			
			if (input % 4 == 0)
				divisibleBy4 ++;
		}
			
		System.out.println(divisibleBy4);
		
		
	}		//end of main()
	
}		//end of class
