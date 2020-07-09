package Medium;

import java.util.PriorityQueue;

//https://leetcode.com/problems/k-closest-points-to-origin/

public class K_Closest_Points_To_Origin {
//	public int[][] kClosest(int[][] points, int K) {
//		
//		PriorityQueue<int[] > heap = new PriorityQueue<>(points.length, (x,y) -> {
//			return (Math.sqrt(x[0] * x[0] + x[1] * x[1]) > Math.sqrt(y[0] *y[0] + y[1] * y[1] ) )? 1:-1;
//		} );
//		for (int[] p: points ) {
//			heap.add(p);
//		}
//		
//		int[][] result = new int[K][2];
//		for (int i = 0; i < K; i ++ ) {
//			result[i] = heap.poll();
//		}
//		return result;
//	}
	
	public static int[] kClosest(int[] points, int K) {
		int[] dist = points.clone();
//		for (int i = 0; i < points.length; i ++ ) {
//			dist[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
//		}
		
		int i = 0;
		int right = points.length - 1;
		int j;
		int pos = -1;
		
		while (pos != K) {
			j = i - 1;
			int pivot = i + (j - i) / 2;
			
			for (int p = i; p <= right; p ++ ) {
				if (dist[p] < dist[pivot] ) {
					swap(dist, p, ++j);
				}
			}
			pos = j+1;
			
			if (pos > K) {
				right = pos - 1;
			}
			else if (pos < K) {
				i = pos + 1;
			}
			
		}
		
		int[] soln = new int[K];
		for (int ind = 0; ind < K; ind ++ ) {
			soln[ind] = dist[ind];
		}
		return soln;
		
		
	}
	
	public static void swap(int[]arr, int x, int y) {
		int temp = arr[y];
		arr[y] = arr[x];
		arr[x] = temp;
	}
	
	public static void main(String[]args) {
		int[] arr = {1,5,7,3,8,9,4,2};
		int[] ans = kClosest(arr, 6);
		
		for (int i: ans) {
			System.out.println(i);
		}
	}
	
}
