package Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/super-palindromes/
/*
 * 	This is a palindrome problem
 * 
 * 	My approach is, first notice the range is at top 10^18, which when square root, is only 10^9 (Still large)
 * 	
 * 	Therefore, instead of finding a number and checking if the square root is also palindrome, I find square root
 * 	and check its square is also palindrome or not.
 * 
 * 	We cannot iterate from 1 to 10^9. Therefore, I only will iterate those who are palindromes itself, and check
 * 	if the square is palindrome or not, and whether it falls in range.
 * 
 * 	I cached all the palindrome of various lengths. The cached size will have 222220 entries, which is still in
 * 	reasonable size.
 * 
 * 	To generate such palindrome, I consider the fact that all palindrome has following property:
 * 		>	First and last digit same
 * 		>	Middle must be palindrome
 * 	Using previously cached palindromes, i can generate palindromes for current length palindromes. However you must
 * 	consider also "000" etc, because later the larger palindrome might use this one in their generation
 * 
 * 	--------------------------
 * 
 * 	Another idea is that, the largest num is 10^9. But using the fact that other half is simply reflection of current
 * 	one, one can simply iterate until 10^5, while generating the other half algorithmicly.
 */

public class Super_Palindromes {
	
	static List<List<String>> cache; 
	static {
		cache = new ArrayList<>();
		cache.add( Arrays.asList("") );
		cache.add( Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9") );
		
		for (int places = 2; places < 10; ++places) {
			List<String> li = new ArrayList<>();
			for (int d = 0; d <= 9; ++d)
				for (String s: cache.get(places - 2) )
					li.add(d + s + d);
			cache.add(li);
		}
	}
	
	
    public int superpalindromesInRange(String left, String right) {
    	long l = Long.parseLong(left), r = Long.parseLong(right);
    	int min = Math.min(9, Integer.toString( (int)Math.floor( Math.sqrt(l) ) ).length() );
    	int max = Math.min(9, Integer.toString( (int)Math.floor( Math.sqrt(r) ) ).length() );
    	
    	int res = 0;
    	for (int len = min; len <= max; ++len) {
    		for (String s: cache.get(len) ) {
    			long n = Long.parseLong(s);
    			long n2 = n * n;
    			if (isPalindrome(n2) && n2 >= l && n2 <= r) ++res;
    		}
    	}
    	return res;
    }
    
    private boolean isPalindrome(long n) {
    	String s = Long.toString(n);
    	final int l = s.length();
    	for (int i = 0; i < l/2; ++i)
    		if (s.charAt(i) != s.charAt(l - i - 1) ) return false;
    	return true;
    }
}
