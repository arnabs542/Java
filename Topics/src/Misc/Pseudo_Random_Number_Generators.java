package Misc;

// Pseudo Random Number Generator - PRNG
/*
 * 	Randomness can be thought as unpredictable. Given a number that a PRNG just output to you, it will
 * 	be very hard to guess what is the next number going to be. In general, you should be unable to observe
 * 	a pattern that help you determine what is the next number.
 * 
 * 	Randomness is everywhere in real life - Electrical noises, radioactive emissions etc, gases distribution
 * 	at a particular time. 
 * 	However, computers are deterministic machines - It works by taking an input, run through some algorithm,
 * 	and generating an output. Without some external hardware supporting (Like Geiger Counter) to measure
 * 	physical aspects stated above, it would be nearly impossible for a computer to generate TRUE random
 * 	number sequence by itself.
 * 
 * 	Random numbers obtained from common programming language libraries like Java's or Javascript's Math.random(),
 * 	is actully using something called PSEUDO RANDOM NUMBER GENERATORS - PRNG
 * 	PRNG is capable to generate numbers that seemingly possess characteristics of true randomness. Without
 * 	much digging deep into it, one might even perceive that it is a truely random sequence!
 
 * 	Almost all PRNG will require a 'seed'. A seed is simply an initial value that will be feed to the PRNG
 * 	to generate the next 'random' number. Afterwards, the new generated number will become the seed itself
 * 	for the purpose of generation of the very next number.
 * 
 * 	Also, because of how the PRNG work with the seed, you should realize that given the same seed and the 
 * 	same PRNG algorithm, it is always able to generate the same sequences, EVERYTIME.
 * 	If you've played Minecraft or other games with map generation, you might be familiar with the seed already.
 * 	Given some seed, you will always generate the same map. 
 * 
 *  This introduces the concept of PERIOD. The period is simply stating how many numbers it can generate
 *  until a repeating pattern occurs.
 *  For example, we have a PRNG that when using a seed of '1234', will generate the following:
 *  
 *  		50, 100, 73, 89, 211, 23, 50, 100... (Repeating)
 *  
 *  In this case, the period would be 6. We can generate  6 numbers until the pattern eventually repeats.
 *  Therefore, the larger our seed, the more likely is we will have a larger period, which is a good thing.
 *  
 * 	Here, two basic PRNG that will be discussed:
 * 		>	Middle Square Method
 * 		>	Linear Congruential Method
 * 
 * 	Let's see each one in detail:
 * 
 * 	------------------------------------------------------------------------------------
 * 	1. Middle Square Method
 * 
 * 	Considered as one of the earliest method to generate pseudo random numbers, proposed by John von Neumann in
 * 	year 1946. 
 * 	The idea of the method is simple. 
 * 		- Square the seed
 * 		- Take from the middle of the result however many digits you require.
 * 		- The middle part will be the new seed
 * 
 *  However, it had many flaws. For example, if the seed is 0, then you know however you square it, it will
 *  always be 0, and thus this PRNG ends up with a period of 1.
 *  
 *  ------------------------------------------------------------------------------------
 *  2. Linear Congruential Method
 *  
 *  A simple algorithm, yet many languages use it. 
 *  In Linear Congruential method, we will have 4 important parameters:
 *  	-	Seed
 *  	-	Multiplier (a)
 *  	-	Increment  (c)
 *  	-	Modulus	   (m)
 *  
 *  The algorithm is so simple that it can be described by the formula:
 *  
 *  	rand number = ( seed * multiplier + increment ) % modulus
 *  
 *  	rand number = ( s * a + c ) % m
 *  
 *  You may see how some programming languages implement this in their native PRNG in the wikipedia:
 *  https://en.wikipedia.org/wiki/Linear_congruential_generator
 *  	
 */

public class Pseudo_Random_Number_Generators {
	
	long modulus = (1l << 48) - 1;	 // Modulus of 2^48, same result obtained by AND with 1111...11 (48 bits)
	long incrementer = 11;			 // Value used by Java
	long multiplier = 25214903917l;  // Value used by Java - 0x5DEECE66D
	long seed = 1234;

	// Example Implementation of Linear Congruential Method
	public int nextInt_Linear(int maxExclusive) {
		seed = ( seed * multiplier + incrementer ) & modulus;
		return (int)(seed % maxExclusive);
	}
	public double nextFloat_Linear() {
		seed = ( seed * multiplier + incrementer ) & modulus;
		return (seed * 1.0 / modulus);
	}
	
	
	
	
	
	public static void main(String[] args) {
		Pseudo_Random_Number_Generators prng = new Pseudo_Random_Number_Generators();
		
		for (int i = 0; i < 20; ++i)
			System.out.println( prng.nextInt_Linear(100) );
		
		for (int i = 0; i < 20; ++i)
			System.out.println( prng.nextFloat_Linear() );
		
	}
}
