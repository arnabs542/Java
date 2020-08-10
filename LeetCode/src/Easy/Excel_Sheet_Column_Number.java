package Easy;

//https://leetcode.com/problems/excel-sheet-column-number/

/*
 * 	In Excel, column numbers are just a Base 26 numbering system and each character is from A to Z
 * 	Therefore using base number formula, we can simply obtain the answer
 */

public class Excel_Sheet_Column_Number {

//	 public int titleToNumber(String s) {
//        int res = 0;
//        int power = 0;
//        for (int i = s.length() - 1; i >= 0; i -- ) {
//            res += ( s.charAt(i) - 'A' + 1) * Math.pow(26, power);
//            power ++;
//        }
//        
//        return res;
//	 }
	
	public int titleToNumber(String s) {
		// String.chars() is a Stream method that provides an INT stream (ASCII code stream), from the character at index 0
		// until last character. Therefore we can use a reduce function where each element introduced, we just multiply the accumulator
		// value with 26, then add the latest added ASCII value - 'A' + 1
		return s.chars().
				reduce(0, (acc, e) -> {
					return acc * 26 + e - 'A' + 1;
				});
	}
	
}
