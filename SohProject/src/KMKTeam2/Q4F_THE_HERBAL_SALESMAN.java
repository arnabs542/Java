//package KMKTeam2;

import java.util.Scanner;
public class Q4F_THE_HERBAL_SALESMAN {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		//Each loop completes a case
		for (int i = 0; i < numCases; i ++ ) {
			
			int numDays = scan.nextInt();
			//Record the maximum/ minimum number of sales throughout the case
			int maxSales = 0, minSales = -1;
			//Counts the number of occurrences of maximum sales/ minimum sales
			int maxCounter = 0, minCounter = 0;
			
			//Each loop obtain an input of one day, then processes it
			for (int day = 1; day <= numDays; day ++ ) {
				int saleOfDay = scan.nextInt();
				
				//if number of sales happen again equal to maximum sales
				if (saleOfDay == maxSales) 
					maxCounter ++;
				
				//if number of sales happen again equal to minimum sales
				if (saleOfDay == minSales)
					minCounter ++;

				//if number of sales greater than maximum sales recorded
				if (saleOfDay > maxSales) {
					maxSales = saleOfDay;
					maxCounter = 1;
				}
				
				//if number of sales greater than minimum sales recorded, or minimum sales never been recorded before
				if (saleOfDay < minSales || minSales == -1) {
					minSales = saleOfDay;
					minCounter = 1;
				}
				
			}		//end of day input and process loop
			
			System.out.printf("Case #%d: %d %d %d %d" , (i + 1), minSales, minCounter, maxSales, maxCounter);
			System.out.println();
			
		}		//end of case building loop
		
		
		
		
		
	}		//end of main()

}		//end of class
