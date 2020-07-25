package Challenge;

import java.util.Scanner;

//https://www.facebook.com/codingcompetitions/hacker-cup/2020/qualification-round/problems/A

public class Travel_Restrictions {

	public static StringBuilder[] travelRestrictions(String in, String out) {
		int len = in.length();
		
		StringBuilder base = new StringBuilder();
		for (int i = 0; i < len; i ++ ) base.append('N');
		
		StringBuilder[] dp = new StringBuilder[len];
		for (int i = 0; i < len; i ++ ) {
			dp[i] = new StringBuilder(base);
			dp[i].setCharAt(i, 'Y');
		}
		
		for (int r = 0; r < len; r ++ ) {
			if ( out.charAt(r) == 'N') continue;
			StringBuilder curr = dp[r];
			
			for (int colB4 = r - 1; colB4 >= 0; colB4 -- ) {
				if ( curr.charAt(colB4 + 1) == 'N' || out.charAt(colB4 + 1) == 'N' || in.charAt(colB4) == 'N' )
					break;
				curr.setCharAt(colB4, 'Y');
			}
			
			for (int colAt = r + 1; colAt < len; colAt ++ ) {
				if ( curr.charAt(colAt - 1) == 'N' || out.charAt(colAt - 1) == 'N' || in.charAt(colAt) == 'N' )
					break;
				curr.setCharAt(colAt, 'Y');
			}
			
		}
		
		return dp;
	}
	
	
	
	
	
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		int numCases = scan.nextInt();
		scan.nextLine();
		
		for (int i = 0; i < numCases; i ++ ) {
			int num = scan.nextInt();
			scan.nextLine();
			String in = scan.nextLine();
			String out = scan.nextLine();
			
			StringBuilder[] res = travelRestrictions(in, out);
			System.out.println("Case #" + (i + 1) + ": ");
			for (StringBuilder r: res) {
				System.out.println(r.toString() );
			}
			
		}
				
		scan.close();
	}
	
}
