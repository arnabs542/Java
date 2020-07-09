package Algorithms;

/*
 * 	Counting sort is a O(n) sorting algorithm, which is stable (Meaning it will remain in the original order even though sorted).
 * 		**Stable example: 1(b), 2(a), 1(a), 2(c), 2(b) ----Sorting by number----> 1(b), 1(a), 2(a), 2(c), 2(b) (The alphabets still in original order)
 *
 *	Although O(n) time complexity seems promising, it is efficient only if the range of values to sort is small (Eg: Ages of people where we assume
 *	age will not go over 150)
 *	
 *	To break it into simple ways, First we create a bucket (or mapping) where the key is the value and it maps to the frequency.
 *	Then, The bucket/mapping is manipulated into a cumulative frequency table (Adding the previous frequencies)
 *	The values in the bucket are then shifted 1 step to the right, leaving the first index's value to be 0. Now the bucket itself
 *	represents the starting index where the value should go in the newly created array!
 *	Lastly, we iterate through the original array again and each time, we have to determine where this element should go by referencing to the
 *	bucket. Don't forget to increment 1 to the bucket's value after putting the value so that next occurrence of the same value would go to the
 *	next place after the last one!
 *
 *	Example: [1,0,3,1,3,1] where each value are in bounds 0 <= x <= 3
 *	We will create a bucket of size 4 minimum (or size 5 for the sake of simplicity)
 *
 *	Since we have to shift the value to the right by 1 block, why don't we do it initially? So value 0 goes to index 1, value 1 goes to index 2
 *	in the bucket...
 *	After counting the frequencies, the bucket would look like as follows:
 *		0 (Representing starting index of value 0) --- 0	(Will always be 0 as it is shifted to right)
 *		1 (Representing starting index of value 1) --- 1	(Frequency of value 0)
 *		2 (Representing starting index of value 2) --- 3	(Frequency of value 1)
 *		3 (Representing starting index of value 3) --- 0	(Frequency of value 2)
 *		4 (Dummy index. Since we have to shift, we created this one so that we don't run into array index out of bounds) --- 2	(Frequency of value 3)
 *
 *	Now we transform it into a cumulative frequency bucket
 *		0 --- 0
 *		1 --- 1
 *		2 --- 4
 *		3 --- 4
 *		4 --- 6 (See that this is the actual length of the array itself?)
 *
 *	Now Finally step, we create a new empty array and iterate through the original array again. Instead this time for each element we met we'll
 *	put it into the newly created empty array by referencing to the bucket.
 *	Remember to increment the bucket index by 1 after placing the copy into the array so that the next occurrence will end up in correct place!
 *
 *	> We met 1, bucket points to index 1. (Bucket value now +1 -> 2)
 *	> We met 0, bucket points to index 0. (Bucket value now +1 -> 1)
 *	> We met 3, bucket points to index 4. (Bucket value now +1 -> 5)
 *	> We met 1, bucket points to index 2. (Bucket value now +1 -> 3)
 *	> We met 3, bucket points to index 5. (Bucket value now +1 -> 6)
 *	> We met 1, bucket points to index 3. (Bucket value now +1 -> 4)
 *
 *	Volia! Sorted array [0,1,1,1,3,3]
 *
 *	Note that this sorting algorithm is not in place sorting. Therefore it can take up quite some space. This is exactly why it is efficient only
 *	if the values bound are small
 */

public class Counting_Sort {

	//Each value in the array must be in the range [lowerBound, upperBound], inclusively
	public static int[] countingSort(int lowerBound, int upperBound, int[] arr) {
		int[] bucket = new int[upperBound - lowerBound + 2];
		
		//i - lowerBound maps to the respective index in the bucket. + 1 to shift it to right by 1 unit so that we don't have to do it later
		for (int i: arr) {
			bucket[i - lowerBound + 1] ++;
		}
		//Creating cumulative frequency bucket
		for (int i = 1; i < bucket.length; i++ ) {
			bucket[i] += bucket[i-1];
		}
		
		int[] result = new int[arr.length];
		//Remember to refer to the value, in this case Eg: 3, we take away lowerBound which is 3 - 3 = index 0
		for (int i: arr) {
			result[ bucket[i - lowerBound] ] = i;
			bucket[i - lowerBound] ++;
		}
		return result;
	}
	
	
	public static void main(String[]args) {
		int[] original = {5,4,3,4,5,3,4,3};
		int[] fin = countingSort(3, 5, original);
		for (int i: fin) {
			System.out.println(i);
		}
	}
	
}
