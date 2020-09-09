package Medium;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//https://leetcode.com/problems/compare-version-numbers/


/*
 * 	The base idea is to split the numbers on the delimiter dot '.'
 * 	Then iterate the number until the end of one of the shorter version numbers. 
 * 	
 * 	If in the midst of the iteration one of the version number is larger, then return -1 or 1 immediately.
 * 	
 * 	After the iteration, if one of the version number is actually longer, then continue the other one. If it is found a non-zero
 * 	number, then it is larger version. Return -1 or 1 immediately.
 * 
 * 	If after iteration of all the numbers still is equal, return 0
 * 
 * 
 * 	Note on the split method. Since it takes in a regex string, the dot . is actually a wildcard. We need to pass in
 * 	the escape character "\.", but the escape character by itself need to be escaped as well. Therefore "\\."
 * 
 */

public class Compare_Version_Numbers {
	
//	public int compareVersion(String version1, String version2) {
//        int[] ver1 = Arrays.stream( version1.split("\\.") )
//        				.mapToInt( e -> Integer.parseInt(e) )
//        				.toArray();
//        int[] ver2 = Arrays.stream( version2.split("\\.") )
//        				.mapToInt( e -> Integer.parseInt(e) )
//        				.toArray();
//        
//        int shorter = Math.min( ver1.length , ver2.length );
//        int index1 = 0;
//        int index2 = 0;
//        
//        while (index1 < shorter && index2 < shorter) {
//        	if ( ver1[index1] > ver2[index2] )
//        		return 1;
//        	else if ( ver1[index1] < ver2[index2] )
//        		return -1;
//        	index1 ++;
//        	index2 ++;
//        }
//        
//        while (index1 < ver1.length) {
//        	if ( ver1[index1++] > 0 )
//        		return 1;
//        }
//        while (index2 < ver2.length ) {
//        	if ( ver2[index2++] > 0 )
//        		return -1;
//        }
//        
//        return 0;
//    }
	
	
	
	public int compareVersion(String version1, String version2) {
		String[] ver1 = version1.split(".");
		String[] ver2 = version2.split(".");
		
		int min = Math.min( ver1.length , ver2.length );
		
		for (int i = 0; i < min; i ++ ) {
			int v1 = Integer.parseInt( ver1[i] );
			int v2 = Integer.parseInt( ver2[i] );
			
			if (v1 > v2) return 1;
			if (v2 > v1) return -1;
		}
		
		for (int i = min; i < ver1.length; i ++ ) {
			if ( Integer.parseInt( ver1[i] ) > 0) return 1;
		}
		for (int i = min; i < ver2.length; i ++ ) {
			if ( Integer.parseInt( ver2[i] ) > 0 ) return -1;
		}
		
		return 0;
	}

	
	
	
	
	
}
