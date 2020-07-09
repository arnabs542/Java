package Arrays;

//https://leetcode.com/explore/featured/card/fun-with-arrays/511/in-place-operations/3157/

/*
 * 	We can use the two pointers method here. Every time the i index is met with non zero element, copy that into index j and make the index i
 *  to be zero
 *  
 *  Or we can just move all elements to the left side, and fill the right side with zeroes afterwards
 */

public class Move_Zeroes {

	public void moveZeroes(int[] nums) {
		int pointI = 0;
		int pointJ = 0;
		while (pointI < nums.length) {
			if (nums[pointI] == 0 ) pointI ++;
			else {
				int temp = nums[pointI];
				nums[pointI] = 0;
				nums[pointJ] = temp;
				pointI ++; pointJ ++;
			}
		}
	}
	
	
	public void moveZeroesAlt(int[] nums) {
		int pointJ = 0;
		for (int i = 0; i < nums.length; i ++) {
			if (nums[i] != 0) {
				nums[pointJ++] = nums[i];
			}
		}
		
		while (pointJ < nums.length) {
			nums[pointJ ++ ] = 0;
		}
	}
	
}
