package Easy;

//https://leetcode.com/explore/challenge/card/may-leetcoding-challenge/535/week-2-may-8th-may-14th/3324/

/*
 * 	To find solution to valid perfect square we would need to determine from 1 to n-1, which of these squared is equal to num
 * 	You could do this by brute force, but that is slow. A better approach is using binary search
 * 
 */
public class Valid_Perfect_Square {
    
	public static boolean isPerfectSquare(int num) {
		if (num == 1) return true;
		
		long left = 1;
		long right = num / 2;
		long mid, sq;
		
		while (left < right) {
			mid = left + (right - left) / 2;
			sq = mid * mid;
			
			if (sq > num)
				right = mid - 1;
			else if (sq < num)
				left = mid + 1;
			else
				return true;
		}
		return left*left == num;
    }
	
	public static void main(String[]args) {
		isPerfectSquare(598);
	}
}
