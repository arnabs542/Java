package Medium;

//https://leetcode.com/contest/weekly-contest-186/problems/maximum-points-you-can-obtain-from-cards
/*
 * 	This is an array problem
 * 
 * 	Since the cards are all positive, I would want to use up all k available moves to get maximum score.
 * 	However, how many cards from left side and how many from right side is the problem
 * 
 * 	Let's try to obtain all cards from left side first. One by one, I will get one card from right side while
 * 	discarding the leftmost card from left section. Compare the scores to see what maximum score I can get.
 */

public class Maximum_Points_You_Can_Obtain_from_Cards {
	
	public static int maxScore(int[] cardPoints, int k) {
		int max;
		int score = 0;
		for (int i = 0; i < k; i ++ )
			score += cardPoints[i];
		max = score;
		
		for (int i = 0; i < k; i ++ ) {
			score = score + cardPoints[cardPoints.length -i-1] - cardPoints[k-i-1];
			max = (score > max)? score: max;
		}
		return max;
		
    }
	
	public static void main(String[]args) {
		System.out.println(maxScore(new int[] {1,2,3,4,5,6}, 3));
	}
	
	
}
