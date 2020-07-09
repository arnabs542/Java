package medium;


//https://www.hackerrank.com/challenges/richie-rich/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	A greedy method were to be applied here.
 * 	In the first run, we want to ensure that the string is in palindromic situation. Therefore, we will iterate from 0 to n / 2 exclusive
 * 	to check the character pairs (first and last one for index 0). If they are different, change them to the max of them two at the expense
 * 	of one step (k --)
 * 
 * 	Then, if we have leftover k's, we shall be greedy and try to change the leftmost numbers to all 9. Changing both the character pairs
 * 	would cost 2 steps (k -= 2), but this is not the case if the characters is already changed before in palindrome ensuring loop.
 * 	Therefore if we find out this pair is changed before (using some boolean storage), then we shall revert the change and make them both 9,
 * 	which only cost 1 k (k--). Be careful if the character pair is already both 9, no need to do anything
 * 
 * 	Now after the loop, if k is still got extra, check if the string is odd in length (there is lone middle value). If yes,
 * 	change that to 9 as well.
 */

public class Highest_Value_Palindrome {
	
	static String highestValuePalindrome(String s, int n, int k) {
		StringBuilder sb = new StringBuilder(s);
		boolean[] changed = new boolean[n/2];
		
		for (int i = 0; i < n / 2; i ++ ) {
			if (k <= 0) break;
			
			char c1 = sb.charAt(i), c2 = sb.charAt(n - i - 1);
			if (c1 != c2) {
				sb.setCharAt(i, (c1 > c2)? c1: c2);
				sb.setCharAt(n - i - 1, (c1 > c2)? c1: c2);
				changed[i] = true;
				k--;
			}
		}
		
		for (int i = 0; i < n / 2; i ++ ) {
			if (k <= 0) break;
			if (sb.charAt(i) == '9' && sb.charAt(n - i - 1) == '9' )
				continue;
			if (changed[i] && k >= 1) {
				k --;
				sb.setCharAt(i, '9');
				sb.setCharAt(n - i - 1, '9');
			}
			else if (k >= 2) {
				k -= 2;
				sb.setCharAt(i, '9');
				sb.setCharAt(n - i - 1, '9');
			}
			
		}
		if (k != 0 && n % 2 != 0) {
			sb.setCharAt(n / 2, '9');
		}
		
		int count = 0;
		for (int i = 0; i < n / 2; i ++ ) {
			count += (sb.charAt(i) == sb.charAt(n - i - 1) )? 1: 0;
		}
		
		return (count == n / 2)? sb.toString(): "-1";
	}
	
	
	public static void main(String[]args) {
		String num = "1111911";
		int n = 4 ;
		System.out.println( highestValuePalindrome(num, num.length(), n) );
	}
}
