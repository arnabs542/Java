package Medium;

import java.util.Arrays;

//https://leetcode.com/problems/queue-reconstruction-by-height/

/*
 * 	A greedy problem, with quite a bit of logic and thinking.
 * 	We have to sort the array firstly based on the height of the people themselves, in descending order. 
 * 			Eg: 7,0 -> 6,1 -> 5,2 -> 4,3 ....
 * 
 * 	For those who have the same height, we have to sort them based on how many people in front of them, in ascending order
 * 			Eg: 7,0 -> 7,1 -> 7,2 -> 7,3 ....
 * 
 * 	(It kind of makes sense if you think about it)
 * 
 * 	Now we have to check each one of them. For the people standing in index i, there is ALSO i people standing in front of it (There's importance
 * 	of k kicking in).
 * 	If the people we're checking has a value k that is less than its index i value, that means this people is behind its supposed standing place.
 * 	We have to shift it to the left.
 * 	Here's the wonder: No matter how we shift it to the left, k value of all the people to the left wouldn't be really affected (unless you are
 * 	shifting left to the person of same height, but that wouldn't really happen if done correctly since we've sorted in ascending order of k)
 * 			Eg: 7,0 -> 6,1 -> 5,0			Since clearly, the 3rd person has 2 higher people in front of it but the k value is 0, therefore
 * 											we have to shift it to the left by (index) - (k val) = 2 times.
 * 				5,0 -> 7,0 -> 6,1			Notice that after shifting, other people k value respective to height still hold firm. This is because
 * 											height 5 was initially already lower than those people!
 */

public class Queue_Reconstruction_By_Height {
	
	public int[][] reconstructQueue(int[][] people) {
		Arrays.parallelSort(people, (x,y) -> {
			//Their height not of the same, so sort them by height, descending order
			if (x[0] != y[0] )
				return y[0] - x[0];
			//Height are of the same, sort them by k value, ascending order
			else
				return x[1] - y[1];
		});
		
		//Iterate through the people. If their k value isn't their index value, shift them left by (index - k) times
		for (int i = 0; i < people.length; i ++ ) {
			if (people[i][1] != i) {
				shift(people, i, i - people[i][1] );
			}
		}
		
		return people;
    }
	
	public static void shift(int[][] arr, int index, int shift) {
		int[] temp = arr[index];
		
		for (int i = index; i > index - shift; i -- ) {
			arr[i] = arr[i-1];
		}
	
		arr[index - shift] = temp;
	}
	
}
