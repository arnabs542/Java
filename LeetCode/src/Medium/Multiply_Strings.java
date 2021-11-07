package Medium;

//https://leetcode.com/problems/multiply-strings/
/*
 * This is a String, simulation problem.
 * 
 * In my solution, I implemented a stringAdd and stringMult function, which does two things:
 * 	stringAdd - Add two strings representing an integer together. Additionally, offset can be provided to pad zeroes
 * 				to num2.
 * 		
 * 				Eg: num1 = 123, num2 = 456, offset = 2
 * 				=> 123 + 45600
 * 				=> 45723
 * 
 * 	stringMult - Multiplies num1 with provided digit. Only 0 <= digit <= 9
 * 				Eg: num = 123, digit = 3
 * 				=>	369
 * 
 * 	Using these two functions, I can very well perform multiplications. Additionally, since given a fixed num1, I can
 * 	only have 10 ways of calling stringMult, so I can cache the result of stringMult immediately from the start.
 * 
 * 	Time complexity is O(M*N), because stringAdd is going to be called N times, each require to process at most M digits
 * 
 *  
 *  However, my solution is not the best. See leetcode's discussion panel for most shortest solution you ever seen
 */

public class Multiply_Strings {
	
	public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) return "0";
		
        // 1 - Generate all possible multiplications of num1 with a single digit
		String[] mult = new String[10];
		for (int i = 0; i < 10; ++i) 
			mult[i] = stringMult(num1, i);
		
		// 2 - For each digit in num2, obtain the multiplication value and add to result
		String res = "0";
		for (int i = 0; i < num2.length(); ++i) {
			res = stringAdd(res, mult[ num2.charAt( num2.length() - i - 1) - '0' ], i);
		}
		
		return res;
    }
	
	
	// Multiplies string a with a single digit represented by digit
	private String stringMult(String a, int digit) {
		if (digit == 0) return "0";
		
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		
		for (int i = a.length() - 1; i >= 0; --i) {
			int res = ( a.charAt(i) - '0' ) * digit + carry;
			carry = res / 10;
			res %= 10;
			sb.append( (char)(res + '0') );
		}
        
        if (carry != 0) sb.append( (char)(carry + '0'));
		
		return sb.reverse().toString();
	}
	
	
	// Adds string a and b together, where b is offsetted by offset amount from right, padded with zeroes.
	private String stringAdd(String a, String b, int offset) {
		StringBuilder sb = new StringBuilder();
		
		// 1 - Initialize sb with b, but with offset included
		for (int i = 0; i < b.length(); ++i)
			sb.append( b.charAt(i) );
		for (int i = 0; i < offset; ++i)
			sb.append('0');
		
		b = sb.toString();
		sb.delete(0, sb.length() );

		// 2 - Add the two numbers together
		int carry = 0;
		for (int i = 0; i < Math.max(a.length(), b.length()) || carry != 0; ++i) {
			int sum = (
				(i < a.length()? ( a.charAt( a.length() - 1 - i) - '0'): 0) + 
				(i < b.length()? ( b.charAt( b.length() - 1 - i) - '0'): 0) + 
				carry
			);
					
			carry = sum / 10;
			sum %= 10;
			sb.append( (char)(sum + '0') );
		}
		
		return sb.reverse().toString();
	}
}
