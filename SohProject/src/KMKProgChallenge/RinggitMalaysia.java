package KMKProgChallenge;

import java.util.Scanner;
public class RinggitMalaysia {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int amt = scan.nextInt();
		
		for (int i = 1; amt != 0; i ++) {
			int sumBills = 0;
			
			//How many RM100 needed?
			sumBills += amt / 100;
			amt %= 100;
			
			//How many RM50 needed?
			sumBills += amt / 50;
			amt %= 50;
			
			//How many RM10 needed?
			sumBills += amt / 10;
			amt %= 10;
			
			//How many RM1 needed?
			sumBills += amt / 1;
			
			System.out.println("Case #" + i + ": " + sumBills);
			
			amt = scan.nextInt();
		}
		
	}

}
