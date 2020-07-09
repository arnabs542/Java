//package KMKTeam2;
import java.util.Scanner;
public class Q2E_FORWARD_AND_BACKWARD {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		for (int i = 0; true; i ++ ) {
			
			int input = scan.nextInt();
			
			if (input == -1)
				break;
			
			String num = Integer.toString(input);
			String reverse = "";
			for (int j = num.length() - 1; j >= 0 ; j-- ) {
				reverse += ( num.charAt(j) );
			}
		
			
			if (reverse.equals(num))
				System.out.println("Case #" + (i + 1) + ": Yes");
			else
				System.out.println("Case #" + (i + 1) + ": No");
			
		} 
		
		
	}		//end of main()

}		//end of class
