package KMKTeam2;

//package KMKTeam2;

import java.util.Scanner;
public class PCCB_FACTORING_CALCULATOR {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int input = 0;
		
		while (true) {
			input = scan.nextInt();
			
			//If the input is -1, terminate the loop immediately
			if (input == -1)
				break;
			
			//String which records all the factors of the given input
			String factors = "";
			
			//Each loop checks whether i is a factor of input, where i = integer from 1 up until input, inclusive.
			for (int i = 1; i <= input; i ++ ) {
				
				//if i is an factor of input, record the respective i into String factors, followed by a space
				if (input % i == 0)
					factors = factors.concat(i + " ");
				
			}		//end of factor checking loop
			
			System.out.println("The factors of " + input + " are " + factors);
			
		}		//end of case completing loop
		
		

	}		//end of main()

}		//end of class
