package Easy;

import java.util.HashMap;

//https://leetcode.com/explore/featured/card/may-leetcoding-challenge/534/week-1-may-1st-may-7th/3321/

public class Majority_Element {
	public static int majorityElement(int[] nums) {
		int half = nums.length / 2;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i: nums) {
			map.putIfAbsent(i, 0);
			map.replace(i, map.get(i) + 1);
			if (map.get(i) > half) return i;
		}
		return -1;
    }
	
	public static void main(String[]args) {
		System.out.println(majorityElement(new int[] {1,1,1,1,1,3,3,3,3,3,3,3,3,3,3,3,4,5}));
	}
	
}
