package Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

//https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/

// Make a copy of the array, {8,1,2,2,3}
// Sort the copy of array, {1,2,2,3,8}
// Notice the indexes in sorted array represent how many numbers are smaller than it. Eg: 1 at index (0), 2 at index(1), 3 at index(3)...
// Put them into a hash map of relationship <number: index>
// Then create a new array mentioning the count of smaller number by using the hash map

public class How_Many_Numbers_Are_Smaller_Than_Current_Number {
	
	public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arrCopy = nums.clone();
        int[] newArr = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        Arrays.sort(arrCopy);
        for (int i = 0; i < nums.length; i ++ ) {
        	map.putIfAbsent(arrCopy[i], i);
        }
        
        for (int i = 0; i < nums.length; i ++ ) {
        	newArr[i] = map.get(nums[i]);
        }
        
        return newArr;
        
    }
	
	public static void main(String[]args) {
		for (int i: smallerNumbersThanCurrent(new int[] {8,1,2,2,3})) 
			System.out.println(i);
	}
}

