package KMKTeam2;

import java.util.Scanner;
public class Q2D_ALTERNATING_SUM {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		for (int i = 0; true; i ++) {
			int num = scan.nextInt();
			
			if (num == -1)
				break;
			
			int sum = 0;
			char operand = '+';
		
			for (int j = 1; j <= num; j++) {
				if (operand == '+') {
					sum += j;
					operand = '-';
				}
				else if (operand == '-') {
					sum -= j;
					operand = '+';
				}
			}
			
			System.out.println("Case #" + (i + 1) + ": " + sum);
			
		}
	}		//end of main()

}		//end of class
