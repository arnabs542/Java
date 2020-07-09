package easy;

//https://www.hackerrank.com/challenges/mars-exploration/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Mars_Exploration {

	static int marsExploration(String s) {
		char[] sos = {'S','O','S'};
		int counter = 0;
		int i = 0;
		
		for (int pos = 0; pos < s.length(); pos++ ) {
			if (!(s.charAt(pos) == sos[i]) ) counter ++;
			
			i = (i + 1 >= 3)? 0: ++i;
		}
		return counter;

    }
	
	
	public static void main(String[] args) {
		System.out.println(marsExploration("SOSSQSSOSSQQ") );
	}

}
