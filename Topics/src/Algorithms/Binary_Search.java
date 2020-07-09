package Algorithms;

public class Binary_Search {
    
	static int size = 10;
	static int firstGood = 4;
	
    static boolean isBadVersion(int a) {
    	return a >= firstGood;
    }
    
    static int recursive(int bottom, int top) {
        if (top == bottom) return bottom;
        int mid = bottom + (top - bottom)/2;
        
        if (isBadVersion(mid) ) {
        	return recursive(bottom, mid);
        }
        else {
        	return recursive(mid + 1, top);
        }
    }
    
    static int iterative(int top) {
    	int bottom = 1;
    	while (bottom < top) {
    		int mid = bottom + (top - bottom) /2;
    		if ( isBadVersion(mid) ) {
    			top = mid;
    		}
    		else {
    			bottom = mid + 1;
    		}
    	}
    	return bottom;
    }
    
    public static void main(String[]args) {
    	System.out.println( iterative(size) );
    	System.out.println( recursive(1, size) );
    }
}
