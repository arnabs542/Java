package Medium;

//https://leetcode.com/problems/integer-to-roman/
/*
 * 	This is a string problem, a little bit of math involved.
 * 
 * 	First of all, the value can be separated into 4 parts: ones, tenths, hundreds, and thousands.
 * 	If we have a Map from value to its roman numeral representation, everything is going to be easy.
 * 
 * 	------------------------------------------------------------------
 * 
 * 	We can also do it logically. The only special cases is when the places value is 4 or 9. Once that
 * 	case is handled, problem is reduced to handling 5 and individual 1s only now.
 */

public class Integer_To_Roman {
	
	private static String[] map_ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
	private static String[] map_tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	private static String[] map_hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	private static String[] map_thousands = {"", "M", "MM", "MMM"};
	
	//	Map solution
	public String intToRoman(int num) {
		
		int ones = num % 10;
		int tens = (num % 100) / 10;
		int hundreds = (num % 1000) / 100;
		int thousands = (num % 10000) / 1000;
		
		return map_thousands[thousands] + map_hundreds[hundreds] + map_tens[tens] + map_ones[ones];
    }
	
	
	
	//	Logical solution
	public String intToRoman2(int num) {
		StringBuilder sb = new StringBuilder();
		int ones = num % 10;
		int tens = (num % 100) / 10;
		int hundreds = (num % 1000) / 100;
		int thousands = (num % 10000) / 1000;
		
		//	Thousands
		while (thousands-- > 0) sb.append('M');
		
		//	Hundreds
		if (hundreds == 4) sb.append("CD");
		else if (hundreds == 9) sb.append("CM");
		else {
			if (hundreds >= 5) sb.append('D');
			for (hundreds %= 5; hundreds > 0; --hundreds ) sb.append('C');
		}
		
		//	Tens
		if (tens == 4) sb.append("XL");
		else if (tens == 9) sb.append("XC");
		else {
			if (tens >= 5) sb.append('L');
			for (tens %= 5; tens > 0; --tens ) sb.append('X');
		}
		
		//	Ones
		if (ones == 4) sb.append("IV");
		else if (ones == 9) sb.append("IX");
		else {
			if (ones >= 5) sb.append('V');
			for (ones %= 5; ones > 0; --ones ) sb.append('I');
		}
		
		return sb.toString();
	}
	
}
