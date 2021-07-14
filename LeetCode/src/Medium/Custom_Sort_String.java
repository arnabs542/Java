package Medium;

//https://leetcode.com/problems/custom-sort-string/
/*
 *  This is a string sorting problem, kind of?
 * 
 *  First thing to observe is that the type of values we have to sort is very small, only 26 possible values. In those
 *  cases, the algorithm we can use is Counting Sort, which counts the frequencies of occurrences, then sort.
 *  
 *  Therefore, obtain the frequency table of characters from str. Then, from the order string, we refer to the frequency
 *  table and append however many we needed to the result string.
 *  
 *  After the order string is done iteration, we can append the remaining characters that are not inside order string. Those
 *  not yet appended character shall still have non-zero frequency in the frequency table. 
 */

public class Custom_Sort_String {
	// Counting sort inspired solution.
	public String customSortString(String order, String str) {
        final int len = str.length();
        int[] count = new int[26];
        StringBuilder sb = new StringBuilder(len);
        
        for (char c: str.toCharArray())
        	++count[c - 'a'];
        
        for (char o: order.toCharArray())
        	while (--count[o - 'a'] >= 0)
        		sb.append(o);
        
        // For those character not inside order str, we append it last
        for (char c = 'a'; c <= 'z'; ++c)
        	while (--count[c - 'a'] >= 0)
        		sb.append(c);
        
        return sb.toString();
    }
}
