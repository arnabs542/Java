package Medium;

//https://leetcode.com/problems/mirror-reflection/

/*
 * 	This is a Math problem.
 * 
 * 	Given a ray that shoots from a corner of a square room of length p, where it aims at q height from the floor,
 * 	such that q <= p, guaranteed that it will hit one of the corner, identify the corner that it will hit.
 * 
 * 	Now. The key is to think 1D first: on a single vertical line of height.
 * 	What will happen when laser rebounces?
 * 
 * 	Eg: At a room of 10 unit tall, i aim from bottomleft corner, at height of 3m of opposite wall.
 * 	The laser will have altitudes of:
 * 		3 -> 6 -> 9 -> HIT CEILING, NOW OPPOSITE DIRECTION -> 8 -> 5 -> 2 -> HIT FLOOR, NOW OPPOSITE DIRECTION -> 1 -> 4...
 * 
 * 	Observe that the laser will hit the receptors when:
 * 		>	Height of laser is 0
 * 		>	Height of laser is p
 * 
 * 	Generalizing this, if we ignore the rebounce of ceiling and floor, and just keep track of the distance laser travelled...
 * 		3 - 6 - 9 - 12 - 15 - 18 - 21 - 24 - 27 - 30 ...
 * 	Hey! When the laser travelled a distance which is multiple of p, it must had hit the receptor!!!
 * 
 * 	Since we want to find first receptor hit, it must be the Least Common Multiple of the p and q.
 * 
 * 	
 * 	How do we know which receptor out of the 3 is hit?
 * 		>	Notice that on first entry, laser hit the right wall.
 * 						on second entry, laser hit the left wall.
 * 			Thus just find out that which entry it is in the sequence, done by dividing p with q.
 * 		
 * 		>	Find out for the LCM, what is the integer f such that f * p = LCM.
 * 			Because if f is 1, we are travelling upwards. We hit receptor in ceiling
 * 					if f is 2, we are travelling downwards. We hit receptor in floor
 * 			Thus just find out if f is odd or even, done by dividing LCM with p.
 * 
 */

public class Mirror_Reflection {
	public int mirrorReflection(int p, int q) {
		
        int multiplier = 0;		//	Multiples of p
        
        for (int i = 1; true; i ++ ) {
        	multiplier += p;
        	
        	//	Brute force LCM, for more efficient finding LCM, goto Topics > Misc > Greatest_Common_Divisor
        	if (multiplier % q == 0) {
        		if (i % 2 == 0) return 0;		//	f is even means its Floor Receptor. Only 1 there
        		
        		if ( (multiplier / q) % 2 != 0 ) return 1;	//	odd entry means Right wall
        		return 2;	//	Even entry means Left wall
        	}
        }
    }
}
