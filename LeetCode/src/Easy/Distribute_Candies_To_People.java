package Easy;

//https://leetcode.com/problems/distribute-candies-to-people/

/*
 * 	This question can be solved using mathematical formulas.
 * 	Given n persons and number of candies, we can first find out the number of full rounds (Give each person candies)
 * 	1		2		3		4
 * 	(1)		(2)		(3)		(4)
 * 	(1+n)	(2+n)	(3+n)	(4+n)
 * 	(1+2n)	(2+2n)	(3+2n)	(4+2n)
 * 
 */

public class Distribute_Candies_To_People {
	
	public int[] distributeCandies(int candies, int num_people) {
		int k = findK(num_people, candies);
		int left = (int)( candies - findGiven(k, num_people) );
		
		int[] res = new int[num_people];
		for (int i = 0; i < num_people; i ++ ) {
			int base = (int)( findIndividualBase(k, i, num_people, left) );
			int lastround = (i + 1) + (k + 1) * num_people;
			res[i] = base + (left >= lastround? lastround: left);
			left -= (left >= lastround? lastround: left);
		}
		return res;
        
    }
	
	//	n is the number of people, and x is number of candies available
	private static int findK(int n, int x) {
		long b = 2 * n + 1;
		long c = n + 1 - ( 2 * x / n);
		
		double res = ( -b + Math.sqrt(b * b - 4 * n * c ) ) / (2 * n);
		return (int) Math.floor(res);
	}
	
	//	k is number of rounds to give. n is number of people
	private static double findGiven(int k, int n) {
		return (k + 1.0) / 2 * ( n * n * (k + 1) + n);
	}
	
	private static double findIndividualBase(int k, int i, int n, int left) {
		return (k + 1) * (i + 1) + n * (k * (k + 1.0) ) / 2;
	}
	
}
