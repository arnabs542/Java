package Intro_To_Tutorial_Challenges;

//https://www.hackerrank.com/challenges/tutorial-intro/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Intro_To_Tutorial_Challenges {
	
	static int introTutorial(int V, int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] == V)
				return mid;
			else if (arr[mid] > V)
				right = mid - 1;
			else
				left = mid + 1;
		}
		return left;
	}
}
