package Algorithms;

import java.util.Arrays;

/*
 * Fisher Yates Algotrithm is a simple shuffling algorithm which is based on swapping of elements in an array
 * Starting from the last index, generate a random number including the last index and swap their contents.
 * There is no need for iteration on index 0 (Since 0 * random is still 0)
 * 
 * This is said to be better than using sort with a comparator function of random value (0.5 - random) which is said to have biases... i think?
 */

public class Fisher_Yates_Shuffle {
	
	//Shuffle based on the algorithm itself
	private static void shuffleFisher(int[] toShuffle) {
		for (int i = toShuffle.length - 1; i > 0; i -- ) {
			int j = (int)(i * Math.random() );
			int temp = toShuffle[i];
			toShuffle[i] = toShuffle[j];
			toShuffle[j] = temp;
		}
	}
	
	//Shuffle using sort() method with random comparator
	private static void shuffleBias(int[] toShuffle) {
		Integer[] clone = new Integer[toShuffle.length];
		for (int i = 0; i < toShuffle.length; i ++ )
			clone[i] = toShuffle[i];
		
		Arrays.sort(clone, (x, y) -> {
			double rand = 0.5 - Math.random();
			return (rand >= 0)? 1: -1;
		});
		for (int i = 0; i < toShuffle.length; i ++ )
			toShuffle[i] = clone[i];
	}
	
	
	public static void main(String[]args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		shuffleBias(arr);
		
		for (int i: arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
}
