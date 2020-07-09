package easy;

public class Modified_Kaprekar_Numbers {
	static void kaprekarNumbers(int p, int q) {
		boolean containsNum = false;
		for (int i = p; i <= q; i ++ ) {
			if (isKaprekar(i) ) {
				containsNum = true;
				System.out.println(i);
			}
		}
		if (!containsNum) System.out.println("INVALID RANGE");
    }
	
	static boolean isKaprekar(int n) { 
		String square = String.valueOf(n*n);
		int length = String.valueOf(n).length();
		
		long rhs = Long.valueOf( square.substring(square.length() - length) );
		long lhs = Long.valueOf( square.substring(0, square.length() - length) );
		
		if (rhs + lhs == n) return true;
		return false;
	}
}
