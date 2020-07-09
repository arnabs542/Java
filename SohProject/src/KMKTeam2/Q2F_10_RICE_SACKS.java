//package KMKTeam2;

import java.util.Scanner;
public class Q2F_10_RICE_SACKS {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int heaviest = 0;
			for (int j = 0; j < 10; j ++ ) {
				int currentWeight = scan.nextInt();
				if (currentWeight > heaviest)
					heaviest = currentWeight;
			}
			
			System.out.println("Case #" + (i + 1) + ": " + heaviest);
		}
		
		
	}		//end of main()

}		//end of class
