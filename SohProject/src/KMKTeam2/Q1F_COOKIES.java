//package KMKTeam2;

import java.util.Scanner;
public class Q1F_COOKIES {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Logic: Every customer will request different amount of cookies in each boxes, and different amount of boxes
		//		 in each container. See how many can be packaged and how many leftover
		for (int i = 0; i < numCases; i ++ ) {
			
			int numCookies = scan.nextInt();
			int cookiesInBox = scan.nextInt();
			int boxInContainer = scan.nextInt();
			
			//Find how many boxes can be packaged with all the cookies, and the leftover that cannot fill fully a box
			int packagedBoxes = numCookies / cookiesInBox;
			int leftCookies = numCookies % cookiesInBox;
			
			//Find how many containers can be packaged with all the packaged boxes, and the leftover boxes that cannot fill fully a container
			int packagedContainer = packagedBoxes / boxInContainer;
			int leftBoxes = packagedBoxes % boxInContainer;
			
			System.out.println("Case #" + (i + 1) + ": " + packagedContainer + " " + leftBoxes + " " + leftCookies);
			
			
			
		}
		
		
		
	}		//end of main()
	
	
}		//end of class
