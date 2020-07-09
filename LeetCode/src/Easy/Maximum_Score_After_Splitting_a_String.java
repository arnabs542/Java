package Easy;

//https://leetcode.com/contest/weekly-contest-186/problems/maximum-score-after-splitting-a-string/

// Problem is a sliding window problem

public class Maximum_Score_After_Splitting_a_String {
	public static int maxScore(String s) {
		int max;
		int left = (s.charAt(0) == '0')? 1:0;
		int right = 0;
		for (int i = 1; i < s.length(); i++ ) {
			if (s.charAt(i) == '1') right++;
		}
		max = left + right;
		
		for (int i = 1; i < s.length() - 1; i ++ ) {
			if (s.charAt(i) == '1') right--;
			else left++;
			max = (left + right > max)? left+right: max;
		}
		return max;
    }
	
	public static void main(String[]args) {
		System.out.println(maxScore("10"));
	}
}
