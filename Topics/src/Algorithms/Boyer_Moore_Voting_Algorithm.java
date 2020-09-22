package Algorithms;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * 	Say we are asked to find an element which occurs more than half of the elements in the array. ( count of E > n/2 )
 * 	We can approach this using hashmap, keeping the frequency of each of the elements in the hash table (Frequency table).
 * 	The time complexity of this algorithm will be then O(N) which is good, but space complexity of O(N) as well. Can we optimize the
 * 	space complexity down to O(1)?
 * 
 * 	Boyer Moore's Voting Algorithm allows us to find the majority element in the array. The easiest one is to find the element
 * 	which occurs more than N/2 times.
 * 
 * 	First, we have to know this:
 * 
 * 	>	If majority element is elements that occur more than N/2 times, there can be at most ONE majority element
 * 	>	If majority element is elements that occur more than N/3 times, there can be at most TWO majority elements
 * 	>	If majority element is elements that occurs more than N/4 times, there can be at most THREE majority elements
 * 		and so on...
 * 
 * 	The basic reasoning behind this is because once there is one majority element occupying more than N/2 space in array,
 * 	there is simply not enough space for another majority element to occupy more than N/2 space anymore.
 * 
 * 	Note that I am using 'AT MOST'. This means there may be no majority elements at all. See array:
 * 		[1,2,3,4,5,6,7]
 * 	It is clear there is no majority element here
 * 
 * 	--------------------------------------------------------------------------------------------------------------------------
 * 
 * 	Boyer Moore Voting Algorithm is split into 2 parts:
 * 	>	Selecting possible candidates which may be the majority element
 * 	>	Verifying the majority element
 * 
 * 	The verifying part is easy. We just count the occurrences of the candidates selected in step 1 and verify that the frequency is more
 * 	than N/2 or equivalent ratios. 
 * 
 * 	As for selection of possible candidates, we will use the N/2 problem as example.
 * 
 * 	We will basically keep 2 variables for that. One storing the possible candidate, and one storing the counter for the possible
 * 	candidate.
 * 	The algorithm is, initialize the first element as the possible candidate but with counter of 0. Iterate through the array
 * 		>	If the counter is 0 (Which it is initially), set the possible candidate to be the next element
 * 		>	If the next element is same as the one element stored in possible candidate, increment counter by 1
 * 		>	If the next element is not same as the one stored in possible candidate, decrement counter by 1.
 * 
 * 	Since the possible candidate occurs more than N/2 times, no matter how the array pattern is, it shall always end up with the
 * 	majority element in the possible candidate element. Then we can verify it with a second pass.
 * 	
 * 	If say array is [1,2,3,4,5,6], the final possible candidate is 6, but will be eliminated with the verifying step. Unless it is
 * 	guaranteed there is a majority element, then this step can be omitted.
 * 
 * 	-----------------------------------------------------------------------------------------------------------------------------
 * 
 * 	This algorithm can be extended to work for identifying majority elements occurring more than N/3, N/4 etc.
 * 
 * 	What we have to do is basically use extra 2 pointers for N/3 case, which stores the second potential candidate, and second counter.
 * 	
 * 	The core concept for this to work is:
 * 		If it influences one of possible candidate, don't touch the other possible candidate
 * 
 * 	>	Initially both possible candidate shall be None, with count as 0. Once first element is met, set it into first possible candidate.
 * 		Do Not Touch the Second Candidate.
 * 		SImilarly, if next element is not first possible candidate, and second candidate is still None, set it as second possible
 * 		candidate. Do Not Decrement First Candidate Count
 * 
 * 	>	If next element matches the first possible candidate, increment the first counter without touching second counter.
 * 		Likewise, if next element matches the second possible candidate, increment the second counter without touching first counter.
 * 
 * 	>	Upon next element, if the first counter is 0, change the first possible candidate into the current element, WITHOUT
 * 		DECREMENTING SECOND COUNTER
 * 		Likewise, if the second counter is 0, change the second possible candidate into the current element, WITHOUT
 * 		DECREMENTING FIRST COUNTER
 * 	
 * 	>	Only if both counter is not 0, and the current element does not match any of the possible candidate, we decrement both
 * 		counters.
 * 
 * 
 * 
 */

public class Boyer_Moore_Voting_Algorithm {
	
	//	Implementation for N/2 case. Must know that at most there will be 1 Majority element!
	public static int BoyerMooreMajorityElem1( int[] arr ) {
		int counter = 0;
		int candidate = Integer.MIN_VALUE;
		
		//	First pass. Eliminate other minorities
		for (int n: arr) {
			if (counter == 0) {
				candidate = n;
				counter ++;
			} else if (n == candidate) {
				counter ++;
			} else {
				counter --;
			}
		}
		
		//	Second pass: to verify the majority element
		counter = 0;
		for (int n: arr) {
			if (candidate == n) counter ++;
		}
		
		return (arr.length/2 < counter)? candidate: Integer.MIN_VALUE;
	}
	
	
	
	
	//	Implementation for case N/3. Must know that at most 2 majority element exists!
	public static List<Integer> BoyerMooreMajorityElem2 ( int[] arr ) {
		int counter1 = 0, candidate1 = Integer.MIN_VALUE;
		int counter2 = 0, candidate2 = Integer.MIN_VALUE;
		
		//	First pass. Eliminate until left only 2 possible candidates
		for (int n: arr) {
			
			//	Matching candidates must be before counter = 0 checking. This is because if second counter is 0 but element matches
			//	the first candidate, it should be the first counter incrementing, not the second candidate being reset
			if (candidate1 == n) {
				counter1 ++;
			} else if (candidate2 == n) {
				counter2 ++;
			} else if (counter1 == 0) {
				candidate1 = n;
				counter1 ++;
			} else if (counter2 == 0) {
				candidate2 = n;
				counter2 ++;
			} else {
				counter1 --;
				counter2 --;
			}
		}
		
		//	Second pass. To verify both the candidates
		counter1 = 0; counter2 = 0;
		
		for (int n: arr) {
			if (candidate1 == n) counter1 ++;
			else if (candidate2 == n) counter2 ++;
		}
		
		int bound = arr.length / 3;
		
		List<Integer> res = new ArrayList<>();
		
		if (counter1 > bound) res.add(candidate1);
		if (counter2 > bound) res.add(candidate2);
		return res;
	}
	
}
