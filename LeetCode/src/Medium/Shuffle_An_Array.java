package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/shuffle-an-array/
/*
 * 	This is a Shuffle problem, which usually we use the popular shuffling algorithm: Fisher Yates Algorithm
 * 
 * 	For the details, see Topics > Algorithms > Fisher_Yates_Shuffle.java
 */

class Solution {
	int length;
	int[] ori;
	int[] perm;

    public Solution(int[] nums) {
    	length = nums.length;
        ori = Arrays.copyOf(nums, length);
        perm = Arrays.copyOf(nums, length);
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        perm = Arrays.copyOf(ori, length);
        return perm;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = length-1; i > 0; --i) {
        	int j = (int)(Math.random() * (i+1));
        	int temp = perm[i];
        	perm[i] = perm[j];
        	perm[j] = temp;
        }
        return perm;
    }
}



public class Shuffle_An_Array {}
