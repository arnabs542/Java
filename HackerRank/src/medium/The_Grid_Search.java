package medium;

//https://www.hackerrank.com/challenges/the-grid-search/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class The_Grid_Search {

	static String gridSearch(String[] G, String[] P) {
		
		//Loop to scan each row (line) until impossible (The row index > P[] size)
		for (int row = 0; row <= G.length - P.length; row ++ ) {
			
			//Loop to scan each substring (linear scan) until impossible (The column index > P[row].length() )
			for (int pos = 0; pos <= G[row].length() - P[0].length(); pos ++ ) {
				
				//If the substring is found to be equal, check with the same position value but row below it
				//If all rows does match, return YES, else, wait until finish scanning
				if ( G[row].substring(pos, pos + P[0].length() ).equals(P[0] ) ) {
					
					boolean matches = true;
					//Since the search for P[0] matched, search whether P[1]... P[n] matches with same column position, but subsequent row
					for (int checkRow = 1; checkRow < P.length; checkRow ++) {
						if ( !G[row + checkRow].substring(pos, pos + P[checkRow].length()).equals( P[checkRow] ) ) {
							matches = false;
						}
					}
					if (matches) return "YES";
					
				}
				
			}		//end of column scanning loop
			
		}		//end of row scanning loop
		
		return "NO";
		
    }		//end of gridSearch method
	
	public static void main(String[] args) {
		String[] one = {"123412","561212","123612", "781234"};
		String[] two = {"12","34"};
		
		System.out.println( gridSearch(one, two ));
	}

}
