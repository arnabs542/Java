package Hard;

//https://leetcode.com/problems/poor-pigs/

/*
 * 	This is a binary state encoding problem.
 * 
 * 	Let's start with a simpler problem: 8 buckets, one shot only. How many minimum pigs required to identify the
 * 	poisoned bucket?
 * 
 * 	You may say 8 or 7 pigs, and that's incorrect. Through encoding each unique states, it can be done with only 3 pigs
 * 	only! Let's see how it is done:
 * 
 * 		I will feed the pig 1 the bucket: { 1, 2, 3, 4 }
 * 		I will feed the pig 2 the bucket: { 1,2, 5,6 }
 * 		I will feed the pig 3 the bucket: {1, 3, 5, 7 }
 * 
 * 	How would this help me know which bucket is poisoned? Although it may not seem clear, each of the bucket is actually
 * 	assigned a unique state. Let's see one by one case:
 * 
 * 	The bucket #N is poisoned when:
 * 		#1	-	When pig 1, 2 and 3 all died.
 * 		#2	-	When pig 1 and 2 died
 * 		#3	- 	When pig 1 and 3 died
 * 		#4	-	When pig 1 died, alone
 * 		#5	-	When pig 2 and 3 died
 * 		#6	-	When pig 2 died, alone
 * 		#7	-	When pig 3 died, alone
 * 		#8	-	When no pigs die
 * 
 * 	AS you can see, all of the states are unique identified by the general states of the pig, whether they are alive or not
 * 	generates a permutation in which we can determine if buckets are poisoned or not!
 * 
 * 	------------------------------------------------------------------------
 * 
 * 	However, is there any ways to make it so we can easily compute the result? Yes it is! Using binary code!
 * 	Say we have 8 buckets, let's list them all out and observe:
 * 
 * 		000		001		010		011		100		101		110		111
 * 
 * 	Each binary code is unique, and what we've got here? There are at most 3 binary digits used. Which means, there should
 * 	be 3 pigs (bits) in total that should be used to identify the poisoned bucket.
 * 		>	The place value encodes the pig. Eg: 100, the 1 means pig 3
 * 		>	The value encodes if the pig drinks it or not. Eg: 100 means pig 3 will drink bucket 5 (1-indexed), others will not
 * 
 * 	Thus we can just find out Most SIgnificant Bit (MSB), and know how many pigs required. The formula to find out largest
 * 	place:
 * 
 * 		N = 2^x		where N is number of buckets -1, (Due to 0 indexing)
 * 	Then
 * 		log2( N ) = 2^x. The answer essentially had to +1, because when N is 1, 1 pig is required but its log is 0.
 * 	
 * 	======================================================================================================================
 * 
 * 	Now let's say we have M iterations available in our disposal. How would we go and find out how many pigs needed?
 * 	Maybe we have to extend the concept of using binary code states.
 * 	
 * 	Since we have to be greedy on number of pigs used, we would use all of the times available, if possible, to test the
 * 	poison. Therefore, how would we represent the state of days in the binary code?
 * 
 * 	Since
 * 		>	The position of binary code encodes the pig index
 * 		>	The value of the binary represents bucket index
 * 	An ingenious idea is to encode the days in the value of each digit itself! For 2 interations, we basically have
 * 	3 choices:
 * 		>	Make the pig not drink this bucket at all
 * 		>	Make the pig drink at day 1
 * 		>	Make the pig drink at day 2
 * 
 * 	Therefore, If we were to encode buckets in base 3:
 * 
 * 		000		001		002		010		011		012		020		021
 * 
 * 	This, will also cover all the states. Digit 1 means the pig at that index will drink at day 1. Digit 2 means that the pig
 * 	at that index will drink at day 2.
 * 
 * 	To put simply, we simply also have to find out the most significant digit used to encode all buckets. Just this time,
 * 	it is not longer binary, but other bases: Base of available iteration counts
 * 
 * 	N = M^x
 * 	logm(N) = x
 * 	
 * 	where M should be iteration count + 1,
 * 	and N is number of buckets - 1
 * 	
 */

public class Poor_Pigs {
    public int poorPigs(int buckets, int m, int p) {
	    int t = p/m + 1;
	    
	    return (int)Math.ceil( Math.log(buckets) / Math.log(t) );
	    
    }
}
