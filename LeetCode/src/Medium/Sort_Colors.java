package Medium;

//https://leetcode.com/problems/sort-colors/
/*
 * 	This question can be done easily with a two-pass algorithm, the first pass counts the number of 0's (Red) and 1's (White), then the second
 * 	pass will replace the existing array with 0's and 1's, and the rest with 2's. Resulting in approximately O(2n) time and O(1) space
 * 
 * 	To achieve a single pass algorithm, we have to use two pointers method. The left pointer points at the location where the next Red (0) should
 *  be placed, and the right pointer points at the location where the next Blue (2) should be placed, initialized at the index 0 and length(nums) -1
 *  respectively.
 *  With a reading pointer, it will run a linear scan through the array, starting from start of array, and follows the following algorithm:
 *  	1. If the color is Red(0): it will swap with the leftmost pointer elements, and move both the reader pointer and left pointer + 1
 *  	2. If the color is Blue(2): it will swap with the rightmost pointer elements, and move the right pointer - 1, the reader pointer is
 *  		not moved (To further verify the swapped elements)
 *  	3. If the color is White(1): no action will be done, and the reader pointer moves + 1
 *  
 * 	The reading pointer will iterate until it is greater than the right pointer.
 * 
 * 	Notice why in case 1, we can move the reader pointer forward without checking the swapped elements? This is because of the fact that
 * 	the reader pointer will always be equal or greater than left pointer. Any of the Blues(2) will have been handled by reader pointer beforehand,
 * 	resulting in only White(1) or Red(1) to handle.
 */

public class Sort_Colors {
	
	public void sortColors(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int pointer = 0;
		
		while (pointer <= right ) {
			//The element is 2(Blue). Swap with right pointer. Reader pointer unmoved, and right pointer decremented
			if (nums[pointer] == 2) {
				swap(nums, pointer, right--);
			}
			//The element is 0(Red). Swap with left pointer. Reader pointer forward + 1, and left pointer incremented
			else if (nums[pointer] == 0) {
				swap(nums, pointer ++, left++);
			}
			//The element is 1(White). Do nothing but reader pointer incremented + 1
			else
				pointer ++;
		}
    }
	
	public static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
}
