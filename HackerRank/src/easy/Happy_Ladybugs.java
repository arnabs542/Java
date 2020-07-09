package easy;

//https://www.hackerrank.com/challenges/happy-ladybugs/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Happy_Ladybugs {
	
	static String happyLadybugs(String b) {

		boolean isEmpty = true;
		boolean isFull = true;
		boolean isHappy = true;
		int[] countBugs = new int[26];
		
		//Perform a linear scan through the String first
		for (int i = 0; i < b.length(); i ++ ) {
			char thisChar = b.charAt(i);
			
			//Determine to change value of isEmpty or isFull or not, also count the currentBug if this character is not '_'
			if (thisChar != '_') {
				isEmpty = false;
				
				if (i != 0 && i != b.length() - 1) {
					if (thisChar != b.charAt(i - 1) && thisChar != b.charAt(i + 1) )
						isHappy = false;
				}
				
				countBugs[ thisChar - 65] ++;
			}
			//Else if thisCHar is of empty seat
			else {
				isFull = false;
			}
			
			
			
		}		//end of linear scan
		
		boolean isLone = false;
		for (int c: countBugs) {
			if (c == 1) isLone = true;
		}
		
		if (isEmpty) return "YES";
		if (isLone) return "NO";
		if (isFull) {
			if (isHappy) return "YES";
			return "NO";
		}
		return "YES";
		

    }
	
	public static void main(String[]args) {
		System.out.println( happyLadybugs("BABABABA") );
	}
	
}
