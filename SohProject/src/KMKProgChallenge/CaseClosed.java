package KMKProgChallenge;

import java.util.Scanner;
public class CaseClosed {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int numCases = scan.nextInt();
		
		for (int caseNo = 1; caseNo <= numCases; caseNo ++ ) {
			
			int startHour = scan.nextInt();
			int suspectRoomNo = scan.nextInt();
			
			int numDays = 0;
			int minutes = 0;
			
			for (int currentRoom = 1; currentRoom <= suspectRoomNo; currentRoom ++ ) {

				minutes += 30;
				
				
				
				//If the time is 60 minutes, change to 1 hour
				if ( minutes == 60) {
					startHour ++;
					minutes -= 60;
				}
				
				System.out.printf("Room %d searched. Day %d Time %02d%02d \n", currentRoom, numDays, startHour, minutes);
				
				//Current day is finished. Proceed new day
				if (startHour == 18) {
					startHour = 8;
					numDays ++;
					System.out.println("New Day");
				}
				//Lunch break
				if (startHour == 13) {
					startHour = 14;
					System.out.println("Lunch break");
				}
				
				
			}
			
			if (startHour == 14 && minutes == 0) {
				startHour --;
			}
			if (startHour == 8 && minutes == 0) {
				startHour = 18;
				numDays --;
			}
			
			System.out.printf("Target acquired at day %d time %02d%02d \n", numDays, startHour, minutes);
			
		}		//end of case completing loop
		
		

	}		//end of main()

}		//end of class
