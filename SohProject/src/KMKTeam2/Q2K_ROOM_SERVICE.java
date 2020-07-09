//package KMKTeam2;

import java.util.Scanner;
import java.text.DecimalFormat;
public class Q2K_ROOM_SERVICE {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			int foodNo = scan.nextInt();
			int quantity = scan.nextInt();
			double foodPrice = 0;
			
			switch (foodNo) {
				case 1: foodPrice = 2.5;
						break;
				case 2: foodPrice = 2;
						break;
				case 3: foodPrice = 2.8;
						break;
				case 4: foodPrice = 3.2;
						break;
				case 5: foodPrice = 3;
						break;
			}
			
			double totalPrice = foodPrice * quantity;
			
			System.out.println("Case #" + (i + 1) + ": RM" + formatter.format(totalPrice));
			
		}
		
		
		
	}		//end of main()

}		//end of class
