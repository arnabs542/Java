package easy;

//https://www.hackerrank.com/challenges/hackerrank-in-a-string/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Hackerrank_In_A_String {

	static String hackerrankInString(String s) {
		char[] sequence = {'h','a','c','k','e','r','r','a','n','k'};	//last index is 9
		int pos = 0;
		
		for (int i = 0; i < s.length() && pos <= 9 ; i++ ) {
			
			if (s.charAt(i) == sequence[pos] ) {
				pos ++;
			}
			
		}
		return (pos > 9)? "YES": "NO";

    }
	
}
