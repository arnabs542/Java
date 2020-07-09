//package KMKTeam2;

import java.util.Scanner;
public class Q1E_FLOORING_TILES {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numLabs = scan.nextInt();
		
		//Assumption: Cut tiles cannot be reused
		for (int i = 0; i < numLabs; i ++) {
			
			int width = scan.nextInt();
			int length = scan.nextInt();
			
			//Required numbers of tiles to be used for ONE row or column only
			int reqLength = length / 30;
			int reqWidth = width / 30;
			
			//Required number of tiles for filling in the lab without cutting the tiles first
			int baseTiles = reqLength * reqWidth;
			int reqTiles;
			
			if (width % 30 != 0 && length % 30 != 0) 
				reqTiles = baseTiles + reqLength + reqWidth + 1;
			else if (width % 30 != 0)
				reqTiles = baseTiles + reqLength;
			else if (length % 30 != 0)
				reqTiles = baseTiles + reqWidth;
			else
				reqTiles = baseTiles;
			
			System.out.println("Case #" + (i + 1) + ": " + reqTiles);
			
		}
		
	}		//end of main()
	
	
}		//end of class
