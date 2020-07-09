package Medium;

import java.util.Arrays;
import java.util.HashSet;

//https://leetcode.com/problems/prison-cells-after-n-days/

/*
 * 	This is a type of question where it has a large amount of state transitions (Every day the cell changes), but with a small state space (In other words,
 * 	the possible permutation of the states is small. In this case, only the 6 middle cells seems to be changing. Since the cells can only be 0 or 1,
 * 	the possible permutations are 2^6 which is only 64)
 * 
 * 	Since the state space is small, it must go into a cycle after at most 64 cycles, due to pigeon hole principle. Therefore, we can easily predict the future outcome
 * 	already if it has gone into a cycle, since we already calculated it at least once.
 * 
 * 	By storing the cell states (As string or hashing) into a set and check each time if the cell states is met before, we can easily find the cycle and break the
 * 	iteration. After that, we would just modulo the required times of iteration with the cycle size to see how much steps it'll take to reach the final result.
 * 	
 */

public class Prison_Cells_After_N_Days {
	
//	public static int[] prisonAfterNDays(int[] cells, int N) {
//		int[][] records = new int[64][8];
//		
//		int hashed = 0;
//		for (int i = 1; i <= 6; i++ ) {
//			records[1][i] = cells[i-1] == cells[i+1]? 1: 0;
//			hashed += records[1][i] << (i - 1);
//		}
//		
//		int day = 2;
//		for ( ; day < 64 && day <= N; day ++ ) {
//			int hash = 0;
//			for (int j = 1; j <= 6; j ++ ) {
//				records[day][j] = records[day-1][j-1] == records[day-1][j+1]? 1: 0;
//				hash += records[day][j] << (j - 1);
//			}
//			if (hash == hashed) break;
//		}
//		return records[ (N-1) % (day-1) + 1];
//	}
	
	public static int[] prisonAfterNDays(int[] cells, int N) {
		HashSet<String> set = new HashSet<>();
		int[] copy = Arrays.copyOf(cells, 8);
		
		int days = 0;
		while (days < N) {
			int[] next = next(copy);
			String nextStr = Arrays.toString(next);
			if (set.contains(nextStr) ) 
				break;
			
			set.add(nextStr);
			copy = next;
			days++;
		}
		if (days >= N) return copy;
		N %= days;
		
		for (int i = 0; i < N; i ++ ) {
			copy = next(copy);
		}
		return copy;
	}
	
	private static int[] next (int[] prev) {
		int[] temp = new int[8];
		for (int i = 1; i <= 6; i ++ ) {
			temp[i] = prev[i-1] == prev[i+1]? 1: 0;
		}
		return temp;
	}
	
	public static void main(String[]args) {
		int[] arr = {1,1,1,1,1,1,1,1};
		for (int i = 0; i < 20; i ++ ) {
			int[] res = prisonAfterNDays(arr, i);
			System.out.print("Day " + i + ": ");
			for (int j: res) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}
