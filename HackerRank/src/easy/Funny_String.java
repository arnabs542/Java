package easy;

//https://www.hackerrank.com/challenges/funny-string/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Funny_String {

	static String funnyString(String s) {
		String reverse = "";
		for (int i = s.length() - 1; i >= 0; i-- )
			reverse = reverse.concat( Character.toString(s.charAt(i)) );
		
		for (int i = 1; i < s.length(); i ++) {
			if ( Math.abs( (int)s.charAt(i) - (int)s.charAt(i -1) ) != Math.abs( (int)reverse.charAt(i) - (int)reverse.charAt(i - 1) ) )
				return "Not Funny";
		}
		return "Funny";
    }
	
	public static void main(String[]args) {
		System.out.println( funnyString("bcxz"));
	}
}
