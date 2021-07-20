package Algorithms;

import java.util.Arrays;

/*
 * Fisher Yates Algotrithm is a simple shuffling algorithm which is based on swapping of elements in an array
 * Time complexity of this algorithm is O(N) time.
 * 
 * This algorithm is derived from the idea of: Given an array of N elements, we put those N elements into a bag. Then from
 * index 0,1,2... we always draw one element from the bag and place it in the index.
 * 
 * Starting from the last index N-1, keep generating a random index of range [0, i] where i is inclusive of the current index.
 * Then, we swap the element at current index with the one at random index
 * 
 * You can see how it is done here: Initially we have bag of N elements. From the last index, we pick from N elements to place it.
 * Once we placed it, we no longer need to consider element in the last index because the element is chosen.
 * 
 * This is said to be better than using sort with a comparator function of random value (0.5 - random) which is said to have biases... i think?
 */

public class Fisher_Yates_Shuffle {
	
	//Shuffle based on the algorithm itself
	private static void shuffleFisher(int[] toShuffle) {
		for (int i = toShuffle.length - 1; i > 0; i -- ) {
			int j = (int)((i+1) * Math.random() );
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
