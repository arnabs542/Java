package Easy;

//https://leetcode.com/problems/find-numbers-with-even-number-of-digits/discuss/613855/Java-O(n)-using-Math.log()-1ms

/*
 * 		For a more Math-y way to check a number's digit, use log base 10.
 * 		log 10 = 1 (+1 = 2)
 * 		log 100 = 2 (+1 = 3)
 * 		log 99 = 1.99 (+1 = 2.99, but floored to 2)
 * 
 */

public class Find_Numbers_with_Even_Number_of_Digits {
	public static int findNumbers(int[] nums) {
		int counter = 0;
		for (int i: nums)
			if (isEven(i) ) counter++;
		return counter;
    }
	
	public static boolean isEven(int n) {
		boolean isEven = false;
		for (int i = 10; true; i *= 10) {
			if (n / i == 0) return isEven;
			isEven = !isEven;
		}
	}
	
	public static void main(String[]args) {
		System.out.println( findNumbers(new int[] {1,31,3,1,1,1,14,1313,31233}));
	}
}
