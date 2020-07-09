package easy;

//https://www.hackerrank.com/challenges/jumping-on-the-clouds-revisited/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class JumpingOnTheCloudsRevisited {
	
	static int jumpingOnClouds(int[] c, int k) {
		int step = 0;
		int energy = 100;
		do {
			step = (step + k) % c.length;
			energy -= (c[step] == 0)? 1: 3;
			
		} while (step != 0);
		return energy;
    }
}
