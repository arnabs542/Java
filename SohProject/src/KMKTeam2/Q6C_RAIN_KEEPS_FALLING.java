package KMKTeam2;

import java.util.Locale;
import java.util.Scanner;
import java.time.Month;
import java.time.format.TextStyle;

public class Q6C_RAIN_KEEPS_FALLING {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		for (int caseNum = 1; true; caseNum ++ ) {
			
			int year1 = scan.nextInt();
			int year2 = scan.nextInt();
			scan.nextLine();
			
			//If the both years inputted is 0, terminate program immediately
			if (year1 == 0 && year2 == 0)
				System.exit(0);
			
			int numYears = year2 - year1 + 1;
			//Stores the average for each of the years. Element at index 0 stores the starting year's average rainfall
			double[] average = new double[numYears];
			//Stores the month (in int) for each of the years highest and lowest recorded rainfall. Element at index 0
			//stores the starting year's average rainfall
			int[] highMonth = new int[numYears], lowMonth = new int[numYears];
			
			//Each loop obtains the rainfall data for a year, and finds its highest and lowest
			for (int yearNum = 0; yearNum < numYears; yearNum ++ ) {
				
				int highestMonth = 1, lowestMonth = 1;
				double total = 0, highest = -1, lowest = -1;
				
				//Each loop obtains the rainfall data for one month
				for (int monthNum = 1; monthNum <= 12; monthNum ++ ) {
					double thisMonthRainfall = scan.nextDouble();
					
					//If it was first time being looped, initialize the highest and lowest to first month's data
					if (monthNum == 1) {
						highest = thisMonthRainfall;
						lowest = thisMonthRainfall;
					}
					
					total += thisMonthRainfall;
					
					if (thisMonthRainfall < lowest) {
						lowest = thisMonthRainfall;
						lowestMonth = monthNum;
					}
					
					if (thisMonthRainfall > highest) {
						highest = thisMonthRainfall;
						highestMonth = monthNum;
					}
					
				}		//end of monthly obtaining rainfall data loop
				
				scan.nextLine();
				
				average[yearNum] = total / 12;
				highMonth[yearNum] = highestMonth; 
				lowMonth[yearNum] = lowestMonth;
				
			}		//end of yearly obtaining rainfall data loop
			
			System.out.println("Case #" + caseNum + ": ");
			
			//Each loop prints out the year, average, hi month and lo month
			for (int i = 0; i < numYears; i ++ ) {
				//Using the Month enum in java.time Package
				Month highMonthName = Month.of(highMonth[i]);
				Month lowMonthName = Month.of(lowMonth[i]);
				System.out.printf("Year %d: Avg: %.2f Lo: %s Hi: %s", (year1 + i), average[i] ,
								   lowMonthName.getDisplayName(TextStyle.SHORT, new Locale("en") ),
								   highMonthName.getDisplayName( TextStyle.SHORT, new Locale("en") ) );
				System.out.println();
			}		//end of printing loop for years
			
			
		}		//end of case completing loop
		
		
	}		//end of main()

}		//end of class
