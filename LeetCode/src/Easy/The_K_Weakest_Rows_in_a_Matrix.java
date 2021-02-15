package Easy;

import java.util.PriorityQueue;

//https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
/*
 * Generally when we see the Kth smallest or Kth largest, it is a good idea to use Heap, since it allows us to
 * use only K spaces.
 * 
 * Traverse through the matrices row by row. We can get the power of each row by following:
 * 	>	Naive counting - O(N) where N is number of elements in a row
 * 	>	Binary search - O(Log N) since every army is at front
 * 
 * Then, store them as pair of index:value so that index can be compared in case power is same in the heap. The heap
 * will handle the sorting
 * Once heap size exceeded K, pop out the useless value, which is largest one. Therefore we'll use max heap for the job
 * 
 * 	If use binary Search n heap, time will be O(M log N) where M is row, and N is col. If use linear scan, then it is 
 * 	O(MN) instead.
 * 	Space is maintained in O(K)
 */

public class The_K_Weakest_Rows_in_a_Matrix {
	
	public int[] kWeakestRows(int[][] mat, int k) {
		//	stores array of size 2: (idx, val)
        PriorityQueue<int[]> heap = new PriorityQueue<>( (x,y)-> {
        	if (y[1] == x[1]) return y[0] - x[0];
        	return y[1] - x[1];
        });
        
        for (int i = 0; i < mat.length; ++i) {
        	heap.add( new int[] {i, linear(mat[i]) } );
        	if (heap.size() > k) heap.poll();
        }
        
        int[] res = new int[k];
        for (int i = k-1; i >= 0; --i)
        	res[i] = heap.poll()[0];
        
        return res;
    }
	

	//	Obtain number of soldiers in a row simply by counting
	private int linear(int[] arr) {
		int n = 0;
		for (int i: arr) {
			if (i == 0) break;
			++n;
		}
		return n;
	}
	
	
	//	Since all 'army' at front, use binary search is workable
	private int binarySearch(int[] arr) {
		int l = 0, r = arr.length;
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (arr[mid] == 1) l = mid + 1;
			else r = mid;
		}
		return l;
	}

}
