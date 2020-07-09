package Easy;

//https://leetcode.com/problems/plus-one/

/*
 * 	This is a pointer problem. Initialize the pointer to the last node of the array. If by case the number the pointer pointing to is 9, then set it to 0
 * 	and move the pointer left by one node.
 * 	Basically what the pointer is pointing is indicating the node which need to be +1. In case of 9, it will carry forward to the left node to be increment by one
 * 	This process continues until we've met one digit that isn't 9, which can be directly returned
 * 	
 * 	In case it loops until pointer is -1 (Meaning the whole digit given is 999...) then make a new array of size 1 larger than the original, set the head to 1, and
 * 	return it, since 99999... added by 1 will be 100000...
 */

public class Plus_One {
	
	public int[] plusOne(int[] digits) {
		int pointer = digits.length - 1;
		while (pointer >= 0) {
			if (digits[pointer] == 9) {
				digits[pointer] = 0;
				pointer --;
			}
			else {
				digits[pointer] ++;
				pointer = Integer.MIN_VALUE;
			}
		}
		
		if (pointer == -1) {
			int[] newArr = new int[digits.length + 1];
			newArr[0] = 1;
			return newArr;
		}
		return digits;
	}
	
	
	public int[] plusOneImprv(int[]digits) {
		for (int i = digits.length - 1; i >= 0; i --) {
			if (digits[i] != 9) {
				digits[i] ++;
				return digits;
			}
			digits[i] = 0;
		}
		int[] newInt = new int[digits.length + 1];
		newInt[0] = 1;
		return newInt;
	}
	
}
