package Arrays;

//https://leetcode.com/explore/featured/card/fun-with-arrays/526/deleting-items-from-an-array/3248/

public class Remove_Duplicates_From_Sorted_Array {
	
	//A direct inefficient O(n^2) solution
	//Keep in mind that when a duplicate is detected and all the element to the right is shifted, we have to check again if the current element
	//is a duplicate
	public int removeDuplicates(int[] nums) {
		int len = nums.length;
        for (int i = 1; i < len; i ++ ) {
        	if (nums[i] == nums[i-1] ) {
        		for (int r = i+1; r < len; r ++ ) {
        			nums[r-1] = nums[r];
        		}
        		len--;
        		//Avoid element skipping
        		i--;
        	}
        }
        return len;
    }
	
	
	//An O(n) solution using two pointers in one pass
	//We start with a fast running pointer and slow running pointer, with slow pointer indicating where the next non-duplicating value
	//should be placed
	//It just so happens that the slow pointer is the length of the resulting array, and also pointing to the index that the next element should
	//be placed
	public int removeDuplicatesOpti(int[] nums) {
		if (nums.length == 0) return 0;
		
		int fast = 1;
		int slow = 1;
		while (fast < nums.length) {
			//This element is never met before so far(First encounter), put it in slow pointer!
			if (nums[fast] != nums[fast-1] ) {
				nums[slow] = nums[fast];
				slow++;
			}
			fast++;
		}
		return slow;
	}
	
}
