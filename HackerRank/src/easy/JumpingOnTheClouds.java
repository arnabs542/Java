package easy;

//https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup

/*
 * Be greedy. Jump 2 spaces whenever you can
 * Loop until the current cloud is 1 cloud or 2 cloud away from the final cloud to prevent ArrayIndexOutOfBounds error, but remember
 * to return the (count + 1) with this approach
 */

public class JumpingOnTheClouds {
	 static int jumpingOnClouds(int[] c) {
		int index = 0;
	 	int count = 0;
	 	while (index < c.length - 3 ) {
	 		index += (c[index+2] == 1)? 1: 2;
	 		count++;
	 	}
	 	return ++count;
	 }
}
