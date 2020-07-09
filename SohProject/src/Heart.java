
public class Heart {
	
	public static void printHeart(String msg) {
		int len = msg.length() + 2;
		msg = " " + msg + " ";
		printUpper(len * 3);
		printLower(len * 3, msg);
	}
	
	
	private static void printUpper(int size) {
		int diameter = (size / 2 - 2);
		
		//This way of drawing is block-y//
//		for (int r = 1; r <= rows; r ++ ) {
//			for (int repeat = 0; repeat < 2; repeat ++ ) {
//				int space = (rows - r) * 2;
//				int stars = diameter - (rows - r) * 4;
//				for (int s = 0 ; s < space; s ++ )
//					System.out.print(" ");
//				for (int i = 0 ; i < stars; i ++ )
//					System.out.print("*");
//				for (int s = 0; s < space; s ++ )
//					System.out.print(" ");
//				
//				System.out.print("    ");
//			}
//			System.out.println();
//		}
		
		int radius = diameter / 2;
		
		//For a circle, we have to draw radius rows, except += 2 means compressed by factor of 2 for y scale
		for (int r = 1; r <= radius; r += 2 ) {
			//There are 2 semi-circles so we repeat 2 times
			for (int repeat = 0; repeat < 2; repeat ++ ) {
				//Position indicates the cursor position along y axis from 0 to diameter
				for (int pos = 0; pos <= diameter; pos ++ ) {
					//Finding distance from cursor to the center of circle (Hypotenuse) using Pythagoras theorem
					double dist = Math.sqrt(Math.pow(r - radius, 2) + Math.pow(pos - radius, 2) );
					if (dist <= radius + 0.5)
						System.out.print("*");
					else
						System.out.print(" ");
				}
				for (int i = 0; i < size - 2 - 2 * diameter; i ++ ) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
	}
	
	private static void printLower(int size, String msg) {
		int currsize = size;
		int offsetSize = size - (size / 16) * 4;
		while (currsize >= 4) {
			int spaces = (size - currsize) / 2;
			for (int i = 0; i < spaces; i ++ )
				System.out.print(" ");
			
			for (int i = 0; i < currsize; i ++ ) {

				int position = (currsize / 2 ) - (msg.length() / 2);
				if (currsize >= offsetSize - 4 && currsize <= offsetSize + 4) {
					if (currsize == offsetSize && i == position ) {
						System.out.print(msg);
						i += msg.length();
						continue;
					}
					else if (i == position) {
						for (int s = 0; s < msg.length(); s ++ ) System.out.print(" ");
						i += msg.length();
						continue;
					}
				}
				System.out.print("*");
			}
			
			System.out.println();
			currsize -= 4;
		}
	}
	
	public static void main(String[]args) {
//		int i = 80;
//		printUpper(i);
//		printLower(i);
		
		printHeart("HAPPY FUCKING VALENTINES");
	}

}




//			 **    **
//			****  ****
//			**********
//			 ********
//			  ******
//			   ****
//			    **
//			
//			
//			  ****        ****
//			********    ********		//Upper
//			********************		//Lower
//			  ****************
//			    ************
//			      ********
//			        ****