package Hard;

//https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
/*
 * 	This is a advanced binary search problem - Inherits idea from problem Kth Smallest Element in Sorted Matrix
 * 	https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * 
 * 	The property of such multiplication table is:
 * 		- From one grid, moving to the right, below or diagonally \ will always have an larger element.
 * 		  From another perspective, each row / column of the matrix is sorted.
 * 
 * 	Facing this problem, we could do binary search - Searching for the possible value of kth smallest number
 * 	(Let it be e) such that there are k smaller or equal numbers before it.
 * 	Initially, the left and right of binary search will be 1*1 = 1, and m*n = mn. The element e must fall within
 * 	this range.
 * 
 * 	For each iteration of binary search, we find the mid value. This process takes O(log MN) iterations.
 * 	Then, we would go ahead and count the number of elements that are smaller than or equal to it. How?
 * 
 * 	Inheriting from the idea of Kth Smallest Element in Sorted Matrix, observe that this is multiplication table
 * 	we are talking about. Every grid cell is basically rowNo * colNo. 
 * 	Therefore, we could easily fix one of rowNo and colNo, and derive the other such that rowNo * colNo = mid.
 * 	(Of course, it may be not perfectly multiply to mid, but we can use integer division here)
 * 
 * 	Therefore, we iterate through all the rowNo to fix the value, and determine the maximum colNo such that
 * 	every elements until colNo is <= e.
 * 	Conveniently, colNo is the number of elements in this row that is <= e! Add to the counter.
 * 
 * 	At the end, compare counter and narrow the binary search respectively.
 */

public class Kth_Smallest_Number_In_Multiplication_Table {
	
	public int findKthNumber(int m, int n, int k) {
		int left = 1, right = m * n;
		
		while (left < right) {
			int mid = left + (right - left) / 2;
			int count = 0;
			
			// Run a N scan to count number of elements smaller or equal to mid
			for (int i = 1; i <= m; ++i) {
				int rightBound = Math.min(n, mid / i);	// <- Remember, we only have n columns. mid/i can go beyond
				
				if (rightBound == 0) break;
				count += rightBound;
			}
			
			// There will be cases of overcounting - Like when k=4, where e=3.
			// However, if m = n = 3, we will count to 5.
			// The way of narrowing below, will ensure that we don't eliminate the e during an overcounting
			// case
			if (count < k)
				left = mid + 1;
			else
				right = mid;
		}
		
		return left;
    }
	
}
