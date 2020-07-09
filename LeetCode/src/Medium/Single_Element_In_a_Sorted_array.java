package Medium;

//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3327/

/*
 * 	Key: For a sorted array and each element MUST OCCUR TWICE EXCEPT ONE, we conclude that
 * 		-The array must initially be of length ODD in nature
 * 		-If the left side array is odd in length, then the right side must be even in length
 * 
 * 		-For any split, EVEN left-side array, -> if the tail of left matches the head of right, means the single element is in the LEFT side
 * 												 Therefore recurse on that but need to exclude the matched element, since it is identified to
 * 												 have a pair
 * 											  -> if the tail of left doesn't match the head of right, means the single element is in the RIGHT
 * 												 side. Recurse on that
 * 	
 * 		-For any split, ODD left-side array, -> if the tail of left matches the head of right, means the single element is in the RIGHT side
 * 												Therefore recurse on that but need to exclude the matched element
 * 											 -> if the tail of left doesn't match the head of right, means the single element is in the LEFT
 * 												side. Recurse on that
 */

public class Single_Element_In_a_Sorted_array {
	 
	public int singleNonDuplicate(int[] nums) {
		 return recurse(nums, 0, nums.length-1);
	}
	
	public int recurse(int[] nums, int left, int right) {
		if (left >= right) return nums[left];
		
		int lefttail = (left + (right - left) / 2) - 1;
		int righthead = lefttail + 1;
		
		//If the left side is even in length (Right side is odd):
		if ( (lefttail - left + 1) % 2 == 0 ) {
			//The tail matches the head
			if (nums[lefttail] == nums[righthead] ) {
				return recurse(nums, left, lefttail-1);
			}
			//The tail doesn't matches the head
			else {
				return recurse(nums, righthead, right);
			}
		}
		//Else if the left side is odd in length
		else {
			//The tail matches the head
			if (nums[lefttail] == nums[righthead] ) {
				return recurse(nums, righthead+1, right);
			}
			//The tail doesn't matches the head
			else {
				return recurse(nums, left, lefttail);
			}
		}
		
	}
}
