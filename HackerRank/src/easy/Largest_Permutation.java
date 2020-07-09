package easy;

import java.util.HashMap;

//https://www.hackerrank.com/challenges/largest-permutation/problem

/*
 * 	The key point to simplify this question is the fact that every number in the array must be up to array size N, and no repetition is allowed
 * 	
 * 	Therefore by greedy algorithm, no matter what size the array is, given k swaps, the final result of the array must be at least of
 * 	in descending order up to size k
 * 			Eg: 4,3,5,2,1 and swap k = 2, the front 2 of the final array must be 5,4 ...
 * 
 * 	However if the number is already in its "supposed" place then we shall not include it into number of swaps, since no swapping is done
 * 	
 * 	To tackle the problem we make a mapping of numbers to its respective index, so that we can do the swapping between the "supposed" number and
 * 	the actual number there. Remember to update their indices in the mapping too!
 * 
 * 	And lastly, if it is found that the "supposed" number is in place, do not count into the number of swappings!
 */

public class Largest_Permutation {

	 static int[] largestPermutation(int k, int[] arr) {
		 HashMap<Integer, Integer> index = new HashMap<>();
		 
		 //Create a mapping of ( Number: Index )
		 for (int i = 0; i < arr.length; i ++ ) 
			 index.put(arr[i], i);
		 
		 // i indicates the position of the array we are currently in, starting from 0 (which shall be the largest number)
		 for (int i = 0; i < k && i < arr.length; i++ ) {
			 //	The "supposed" number to be placed here
			 int numToPlace = arr.length - i;
			 
			 //	If it was found that the "supposed" number was already in place, we don't count this as one swapping
			 if (arr[i] == numToPlace) {
				 k++;
				 continue;
			 }
			 
			 //	Where was the "supposed" number was prior to swapping?
			 int oriIndexOfNum = index.get(numToPlace);
			 
			 int temp = arr[i];
			 arr[i] = numToPlace;
			 arr[oriIndexOfNum] = temp;
			 
			 index.replace(numToPlace, i);
			 index.replace(temp, oriIndexOfNum);
		 }
		 return arr;
		 
	 }
	 
	 public static void main(String[]args) {
		for (int i : largestPermutation(2, new int[] {3,1,2} ) )
			System.out.println(i);
	 }
}
