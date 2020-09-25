package Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

//https://leetcode.com/problems/largest-number/

/*
 * 	This is actually a sorting problem, which requires us to implement a custom comparator
 * 
 * 	Given 2 strings x and y. We just have to compare whether x + y is greater than y + x.
 * 	If x + y is greater, put x in front of y, vice versa.
 * 
 * 	Lastly, join them into a string and return
 */

public class Largest_Number {
	
	
	
	/*
	 * 	A failed attempt at sorting using customized radix sort with each digits.
	 */
	
//	public String largestNumber(int[] nums) {
//		int len = nums.length;
//		AtomicInteger max = new AtomicInteger(0);
//		
//		//	Mapping each number into string for easier processing
//		String[] str = IntStream.of( nums )
//				.mapToObj( x -> {
//					String s = Integer.toString(x);
//					if (max.get() < s.length() ) max.set( s.length() );
//					return s;
//				})
//				.toArray(String[]::new);
//		
//		//	Loop from the least significant place, until the first place
//		for (int p = max.get() - 1; p >= 0; p -- ) {
//			int[] counting = new int[10];
//			String[] res = new String[len];
//			
//			for (String s: str) {
//				int digit = (p >= s.length()? s.charAt(0): s.charAt(p) ) - '0';
//				counting[digit] ++;
//			}
//			
//			int prev = 0;
//			int sum = 0;
//			for (int i = 0; i < 10; i ++ ) {
//				sum += counting[i];
//				counting[i] = prev;
//				prev = sum;
//			}
//			
//			for (String s: str) {
//				int digit = (p >= s.length()? s.charAt(0): s.charAt(p) ) - '0';
//				res[ counting[digit]++ ] = s;
//			}
//			
//			str = res;
//		}
//		
//		StringBuilder res = new StringBuilder();
//		
//		for (int i = len - 1; i >= 0; i --) {
//			res.append(str[i] );
//		}
//		
//		if (res.charAt(0) == '0') return "0";
//		return res.toString();
//		
//	}
	
	
	public String largestNumber(int[] nums) {
		int len = nums.length;
		
		//	Mapping each number into string for easier processing
		String[] str = IntStream.of( nums )
				.mapToObj( Integer::toString )
				.toArray(String[]::new);
		
		Arrays.sort(str, new Comparator<String>() {
			public int compare(String x, String y) {
				String s1 = x + y;
				String s2 = y + x;
				
				//	If s2 is greater, this func will return 1, which means x get pushed behind, which is correct
				return s2.compareTo(s1);
			}
		});
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < len; i ++) {
			sb.append(str[i] );
		}
		
		if (sb.charAt(0) == '0' ) return "0";
		return sb.toString();
		
	}

}
