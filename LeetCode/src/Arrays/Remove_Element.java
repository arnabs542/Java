package Arrays;

//https://leetcode.com/explore/featured/card/fun-with-arrays/526/deleting-items-from-an-array/3247/

public class Remove_Element {
	
	//Original, one pass O(n^2) solution. Do a linear scan and each time a matching val is detected, shift all stuff from right side to left by
	//1 spaces
	//However kindly be reminded that after a shifting left occurs, the index shouldn't be incremented, otherwise we'll skip one value ahead!
	public int removeElement(int[] nums, int val) {
		int len = nums.length;
		
		for (int i = 0; i < len; i ++ ) {
			//Perform deletion here
			if (nums[i] == val) {
				for (int pos = i + 1; pos < len; pos ++ ) {
					nums[pos-1] = nums[pos];
				}
				len--;
				//Prevent skipping of value. We have to remain on this index
				i --;
			}
		}	
		return len;
	}
	
	
	//Notice the question specify that we do not have to keep the elements in order. Just DELETE them.
	//This specification allows us to do a O(n) solution with two passes, two pointers, and swapping
	public int removeElementOpti1(int[] nums, int val) {
		int len = nums.length;
		
		int delCount = 0;
		//First pass, determine how many elements will be removed from the array
		for (int i: nums)
			delCount += (i == val)? 1: 0;
		
		//The actual length of the array after deletion
		len -= delCount;
		
		int pointer1 = 0;
		int pointer2 = len;
		
		while (pointer1 < len) {
			//The value at this point is the element that shall be deleted, and should be replaced with non-deleting element the deletion part
			if (nums[pointer1] == val) {
				//From the deletion segment, find the value that is not supposed to be deleted
				while (nums[pointer2] == val) 
					pointer2++;
				//We don't have to swap the values, just copying to the non-deleted part is enough
				nums[pointer1] = nums[ pointer2++];
			}
			pointer1++;
		}
		return len;
	}
	
	//An even effective solution is to use a fast runner and slow runner pointer to perform deletion
	//Every time the fast runner pointer j meets a non deleting val, it copies to the slow pointer i.
	//Fast runner pointer skips the deleting val, and it just happens that the final value of i is the length of the array after deletion
	// (and the index that the next element should be inserted)
	public int removeElementOpti2(int[] nums, int val) {
		
		int i = 0;
		for (int j = 0; j < nums.length; j ++ ) {
			if (nums[j] != val) {
				nums[i] = nums[j];
				i++;
			}
		}
		return i;
	}
	
	//Also another solution is to replace any deleting elements with the last element of the array, and discard the last element of the array
	//and continue
	public int removeElementOpti3(int[] nums, int val) {
		int len = nums.length - 1; 
		
		int i = 0;
		while (i <= len) {
			//Replace this deleting element with the last element of the array, and discarding the last element of the array to shorten length
			//Notice in this case i does not increment, so that this element will be checked again if it is still a deleting element
			if (nums[i] == val) {
				nums[i] = nums[len];
				len --;
			}
			else {
				i++;
			}
		}
		return len + 1;
	}
}
