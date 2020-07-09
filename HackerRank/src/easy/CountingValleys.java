package easy;

//https://www.hackerrank.com/challenges/counting-valleys/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup

public class CountingValleys {

//	static int countingValleys(int n, String s) {
//		boolean isValley = false;
//		int count = 0;
//		int altitude = 0;
//		for (char c: s.toCharArray() ) {
//			altitude += (c == 'U')? 1:-1;
//			
//			if (!isValley && altitude < 0) {
//				isValley = true;
//				count++;
//			}
//			else if (isValley && altitude >= 0) {
//				isValley = false;
//			}
//		}
//		return count;
//    }
	
	/*
	 * 	Even better solution is that, realise the following fact
	 * 		It is counted as 1 valley only if we got back (UP) to (SEA LEVEL)
	 */
	
	static int countingValleys(int n, String s) {
		int count = 0;
		int altitude = 0;
		for (char c: s.toCharArray() ) {
			altitude += (c == 'U')? 1:-1;
			
			if (altitude == 0 && c == 'U') count++;
		}
		return count;
	}
	
}
