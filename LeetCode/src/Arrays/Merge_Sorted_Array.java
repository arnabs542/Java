package Arrays;

//https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3253/

public class Merge_Sorted_Array {
	
	//A direct, naive O(n^2) solution
	public void mergeNaive(int[] nums1, int m, int[] nums2, int n) {
		int pointer1 = 0;
		int pointer2 = 0;
		
		while (pointer2 < n) {
			//I have to do an insertion in middle of nums1. Shifting is required
			if (nums1[pointer1] > nums2[pointer2]) {
				shift(nums1, pointer1);
				nums1[pointer1] = nums2[pointer2];
				pointer1 ++; pointer2 ++;
			}
			//Pointer1 is currently pointing to empty spaces. Pointer2 should be inserted
			else if (pointer1 - pointer2 >= m) {
				nums1[pointer1] = nums2[pointer2];
				pointer1 ++; pointer2 ++;
			}
			else 
				pointer1 ++;
		}
	}
	public static void shift(int[] arr, int until) {
		for (int i = arr.length - 1; i > until; i-- ) {
			arr[i] = arr[i-1];
		}
	}
	
	
	
	
	//Single pass, O(n) solution. We
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int pointer1 = m - 1;
		int pointer2 = n - 1;
		
		for (int i = nums1.length - 1; i >= 0; i --) {
			//Both pointers are valid, determine the maximum and put
			if (pointer1 >= 0 && pointer2 >= 0) {
				nums1[i] = nums1[pointer1] > nums2[pointer2]?  nums1[pointer1--]: nums2[pointer2--];
			}
			//Pointer1 is exhausted (meaning pointer2 is way smaller than nums1)
			else if (pointer2 >= 0) {
				nums1[i] = nums2[pointer2];
				pointer2--;
			}
			//No need to see if pointer2 exhausted because in that case we would not move anything
		}
	}
	 
	
	
}
