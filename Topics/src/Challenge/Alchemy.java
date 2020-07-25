package Challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//https://www.facebook.com/codingcompetitions/hacker-cup/2020/qualification-round/problems/B

public class Alchemy {
	
	public static char alchemy(String str) {
		String[] dp = new String[str.length() ];
		
		dp[0] = str.substring(0,1);
		dp[1] = str.substring(0,2);
		dp[2] = stoneFuser( str.charAt(2), str.substring(0,2) );
		
		for (int i = 3; i < str.length(); i ++ ) {
			char c = str.charAt(i);
			
			String notFuse = stoneFuser( c, dp[i-1] );
			String fuse = stoneFuser( c, str.substring(i-2, i) );
			String fuseRes = null;
			if ( !Character.isDigit( fuse.charAt(0) ) )
				fuseRes = stoneFuser( fuse.charAt(0) , dp[i-3] );
			
			
			if (notFuse.charAt(0) == '0') {
				char a = notFuse.charAt( notFuse.length() - 1);
				dp[i] = (i % 2 != 0)? String.format("%c%c", a,a) : String.valueOf(a);
			}
			else dp[i] = fuseRes != null && !Character.isDigit( fuseRes.charAt(0) )? fuseRes: notFuse;
		}
		
//		for (int i = 0; i < dp.length; i ++ ) {
//			System.out.println(i+1 + " " + dp[i]);
//		}
		
		return Character.isDigit( dp[str.length() - 1].charAt(0) )? 'N':'Y';
		
	}
	
	private static String stoneFuser(char stone, String stones) {
		
		//If there is only 2 stones to be fused, fuse them only
		if (stones.length() == 1) {
			return stone + stones;
		}
		
		if ( Character.isDigit(stones.charAt(0) ) ) {
			int digit = Integer.parseInt( stones.substring(0, stones.length() - 1) );
			char c = stones.charAt( stones.length() - 1);
			
			return String.format("%d%c", digit + ( (stone != c)? -1: 1), c);
		}
		
		//If the 3 stones are of same color, then return invalid
		if (stone == stones.charAt(0) && stone == stones.charAt(1) ) {
			return String.format("%d%c", 1, stone);
		}
		//If the 2 stones are same in stones, then it is the majority one. Return the same color stone as stones
		if (stones.charAt(0) == stones.charAt(1) )
			return stones.substring(1);
		//Else the 2 stones are different. Therefore the majority will dependent on the single stone itself
		return String.valueOf(stone);
	}
	
	
	public static void main(String[]args) throws FileNotFoundException {
		File file = new File("src/Challenge/alchemy_input.txt");
		Scanner scan = new Scanner(file);
		
		int numCases = scan.nextInt();
		scan.nextLine();
		
		for (int i = 0; i < numCases; i ++ ) {
			int numStone = scan.nextInt();
			scan.nextLine();
			String stones = scan.nextLine();
			char res = alchemy(stones);
			
			System.out.println("Case #" + (i + 1) + ": " + res);
		}
		
		scan.close();
		
//		String test = "AAAABBBBB";
//		System.out.println( alchemy(test) );
	}
	
}
