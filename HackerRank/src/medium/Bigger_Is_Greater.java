package medium;

//https://www.hackerrank.com/challenges/bigger-is-greater/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This is a Next Lexicographical Permutation problem. For more information visit the topics > algorithm section.
 */

public class Bigger_Is_Greater {
	
	static String biggerIsGreater(String w) {
		int pivotIndex = -1;
		for (int i = w.length() - 1; i >= 1; i -- ) {
			if (w.charAt(i) > w.charAt(i-1) ) {
				pivotIndex = i-1;
				break;
			}
		}
		if (pivotIndex == -1) return "no answer";
		
		int swapIndex = -1;
		for (int i = w.length() - 1; i > pivotIndex; i-- ) {
			if (w.charAt(pivotIndex) < w.charAt(i) ) {
				swapIndex = i;
				break;
			}
		}
		
		return swapAndReverse(w, pivotIndex, swapIndex);
	}
	
	//swap the characters at i and j, then reverse the substring from index i onwards (exclusive). Index i is always smaller than j
	public static String swapAndReverse(String s, int i, int j) {
		StringBuilder sb = new StringBuilder();
		sb.append(s.substring(0, i) );
		sb.append(s.charAt(j) );
		System.out.println(sb);
		//The part of the string which will be reversed
		StringBuilder sbRev = new StringBuilder();
		sbRev.append(s.substring(i+1, j) );
		sbRev.append(s.charAt(i) );
		sbRev.append(s.substring(j+1) );
		sbRev.reverse();
		
		sb.append(sbRev);
		return sb.toString();
	}
	
	public static void main(String[]args ) {
		System.out.println( biggerIsGreater("apple") );
	}
}
