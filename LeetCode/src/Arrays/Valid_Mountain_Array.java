package Arrays;

public class Valid_Mountain_Array {
	
	public boolean validMountainArray(int[] A) {
		if (A.length < 3) return false;
		//The array starts with decreasing trend, not a mountain, else, it has increased
		if (A[0] >= A[1] ) return false;
		
		boolean isDecreasing = false;
		for (int i = 1; i < A.length; i ++ ) {
			if (A[i] == A[i-1] ) return false;
			if (isDecreasing && A[i] > A[i-1] ) return false;
			if (!isDecreasing && A[i] < A[i-1] ) {
				isDecreasing = true;
			}
		}
		return isDecreasing;
    }
}
