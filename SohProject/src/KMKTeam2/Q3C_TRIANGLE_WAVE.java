package KMKTeam2;

import java.util.Scanner;
public class Q3C_TRIANGLE_WAVE {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int frequency = scan.nextInt();
		int amplitude = scan.nextInt();
		
		
		//Each loop completes a wave
		for (int waveNum = 0; waveNum < frequency; waveNum ++) {
			
			//Indicates whether the highest amplitude is reached
			boolean reachPeak = false;
			
			//Each loop completes a line in the wave
			for (int line = 1; line > 0; ) {
				
				//Each loop adds one line number to the current line
				for (int count = 1; count <= line; count ++ ) {
					System.out.print(line);
				}
				System.out.println("");
				
				if (!reachPeak)
					line ++;
				else
					line --;
				
				if (line == amplitude)
					reachPeak = true;
			}
			
		}
		
		
	}		//end of main()

}		//end of class
