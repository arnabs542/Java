//package KMKTeam2;

import java.util.Scanner;
public class Q1D_TIME_IN_SECONDS {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++) {
			
			int rawSeconds = scan.nextInt();
			
			int seconds = rawSeconds % 60;
			int minutes = (rawSeconds / 60) % 60;
			int hours = (rawSeconds / 3600);
			
			System.out.println("Case #" + (i + 1) + ": " + hours + ":" + minutes + ":" + seconds);
		}
		
	}		//end of main()

}		//end of class
