package Medium;

//https://leetcode.com/contest/weekly-contest-186/problems/maximum-points-you-can-obtain-from-cards

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
