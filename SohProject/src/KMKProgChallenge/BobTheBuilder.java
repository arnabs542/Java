package KMKProgChallenge;

import java.util.Scanner;
public class BobTheBuilder {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCase = scan.nextInt();
		
		for (int caseNum = 0; caseNum < numCase; caseNum ++ ) {
			
			int brick5 = scan.nextInt();
			int brick2 = scan.nextInt();
			int goal = scan.nextInt();
			boolean isPossible;
			
			
			//If goal is an even number case
			if (goal % 2 == 0) {
				
				//If the 5 inch bricks total up exceeds the goal
				if (brick5 * 5 >= goal) {
					System.out.println("Even number, exceed goal");
					//After the 5 inch bricks is lay down until closest Even number, what's left is 2 inch bricks needed
					int smallInchNeeded = goal % 10;
					isPossible = (smallInchNeeded / 2) <= brick2;
				}	//end of exceeded goal case
				
				//If the 5 inch bricks total up doesn't exceed the goal
				else {
					int smallInchNeeded;
					System.out.println("Even number, not excced");
					//Notice that even*odd = even, odd*odd = odd
					//If brick5 is even, then i can lay out all since it will end up in multiples of 10
					if (brick5 % 2 == 0)
						smallInchNeeded = goal - (brick5 * 5);
					//If brick5 is odd, then i must lay down ONLY until closest multiples of 10
					else
						smallInchNeeded = goal - ( (brick5 - 1) * 5);
					
					isPossible = (smallInchNeeded / 2) <= brick2;
				}	//end of not exceeded goal case
				
			}		//end of even number case
			
			
			//Else if the goal is an odd number case
			else {
				//If the 5 inch bricks total up exceeds the goal
				if (brick5 * 5 >= goal) {
					System.out.println("Odd number, exceed goal");
					//We can only lay down 5 inch bricks until 5, 15, 25, 35, 45 etc...
					//To determine the closest we can lay down
					int closestOdd;
					//For cases where goal/5 is even; Eg: goal = 23, we take 23/5 = 4, then take 4 - 1 = 3.
					if (goal / 5 % 2 == 0)
						closestOdd = ( (goal / 5) - 1 ) * 5;
					//For cases where goal/5 is odd like goal = 27, we take 27/5 = 5, take 5 only.
					else
						closestOdd = (goal / 5) * 5;
					
					int smallInchNeeded = goal - closestOdd;
				
					if (smallInchNeeded % 2 != 0)
						isPossible = false;
					else
						isPossible = (smallInchNeeded / 2) <= brick2;
				}	//end of exceeded goal case
				
				//Else if the 5 inch bricks total up doesn't exceeds the goal
				else {
					System.out.println("Odd number, not exceed");
					int closestOdd;
					//Notice even*odd = even, odd*odd = odd
					//Lay down to closest 5, 15, 25, 35, 45 etc...
					//Careful! brick5 cannot -1 if its 0!!!!!!!
					if (brick5 % 2 == 0 && brick5 != 0)
						closestOdd = (brick5 - 1) * 5;
					else
						closestOdd = brick5 * 5;
					
					int smallInchNeeded = goal - closestOdd;
					
					if (smallInchNeeded % 2 != 0)
						isPossible = false;
					else
						isPossible = (smallInchNeeded / 2) <= brick2;
				}	//end of not exceeded goal case
				
			}		//end of odd number goal case
			
			System.out.println("Case #" + (caseNum + 1) + ": " + isPossible);
			
			
		}		//end of case completing loop

	}		//end of main()

}		//end of class
