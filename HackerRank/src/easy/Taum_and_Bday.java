package easy;

//https://www.hackerrank.com/challenges/taum-and-bday/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	Be careful of the long format. Integer causes overflowing
 */

public class Taum_and_Bday {
	
	public static long taumBday(int b, int w, int bc, int wc, int z) {
		long bl = b, wl = w, bcl = bc, wcl = wc, zl = z;
		//If the cost of conversion is cheaper than buying directly one of them, just mass buy the cheaper one and convert
		if (Math.max(bcl, wcl) - Math.min(bcl, wcl) > zl) {
			return (Math.min(bcl, wcl) * (bl+wl) ) + (long)( (bcl > wcl)? bl*zl: wl*zl );
		}
		//Else the conversion is actually not needed
		else {
			return (bl * bcl + wl * wcl);
		}
	}
}
