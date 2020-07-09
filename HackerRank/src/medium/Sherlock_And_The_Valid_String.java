package medium;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	Initially it may seems easy but actually turn out to have a lot of edge cases and this that
 */

public class Sherlock_And_The_Valid_String {
	
	static String isValid(String s) {
		//If the string is less than one char, then it must be valid
		if (s.length() <= 1) return "YES";
		
		//Use an array to count the frequency of each characters. Also, count the number of different characters present in the string
		int[] count = new int[26];
		int chars = 0;
		for (char c: s.toCharArray() ) {
			if (count[c-'a'] == 0) chars ++;
			count[c-'a'] ++;
		}
		
		//This is an array that will store the frequency of characters without the characters that are not present (freq = 0)
		int[] li = new int[chars];
		int index = 0;
		for (int i: count) {
			if (i != 0) {
				li[index] = i;
				index ++;
			}
		}
		//Sort the array
		Arrays.parallelSort(li);
		
		//Now, it can be divided into several cases:
		/*1. 	If the first frequency is 1 and the rest is not 1 (Means 1 is the only one different), then we can
		 * 		just remove the lone character and the rest of string is valid
		 *2.	Otherwise for the string to be valid, it must have one REMOVABLE character where its frequency extra with others
		 *		by 1. Since array is sorted, it would be at the end only
		 */
		
		//See if it is an edge case or not
		boolean isEdge = (li[0] == 1 && li[1] != li[0]);
		//The loop checks whether the sub array elements are all equal in value or not. If it was edge case (First is 1),
		//Then we have to exclude the first one and check until the end of array is equal in value.
		//Else we have to check from first element to last second element is equal in value or not.
		for (int i = (isEdge)? 2:1; i < ( (isEdge)? li.length: li.length - 1 ); i ++ ) {
			if (li[i] != li[i - 1] ) return "NO";
		}
		//Until here if it is edge case then it passed the checking so return yes
		if (isEdge) return "YES";
		//Else we still need to check if the last element is 1 extra, or same in value for it to be valid
		return (Math.abs(li[li.length - 1] - li[0]) <= 1)? "YES": "NO";
	}
}
