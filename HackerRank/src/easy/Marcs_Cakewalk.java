package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://www.hackerrank.com/challenges/marcs-cakewalk/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	Sort the array, then choose the maximum calories as the first index (0) to multiply, yields the minimum calorie count
 */

public class Marcs_Cakewalk {
	
	static long marcsCakewalk(int[] calorie) {
		Arrays.sort(calorie);
		long miles = 0;
		for (int index = 0; index < calorie.length; index ++ ) {
			miles += Math.pow(2, index) * calorie[calorie.length-1-index];
		}
		return miles;

    }
	
	public static void main(String[]args) {
		System.out.println(marcsCakewalk(new int[] {7,4,9,6}));
	}
	
}
