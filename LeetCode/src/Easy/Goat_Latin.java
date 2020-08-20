package Easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
 * 	This is a String problem
 * 	
 * 	First we split the string using the space as delimiter. Then for each split string, we map each one to processed word, while
 * 	keeping track of the word index (which word it is in the string)
 * 	
 * 	Each string, we first have to check only if it is starting with vowel or not
 * 	If it is vowel, we do nothing
 * 	It it is consonant, we will put the first character at the last of the word, while shifting the rest one step to front.
 * 	This is actually just doing s.substring(1) + s.charAt(0)
 * 	Then we will append 'ma' to the end of string.
 * 	Lastly, we add the 'aaaa+' strings based on the word index itself. We can actually just create another StringBuilder for this,
 * 	appending a to it each time, and use it to add to our processed string.
 * 
 * 	
 */

public class Goat_Latin {
	
	public String toGoatLatin(String S) {
		
		HashSet<Character> vowel = new HashSet<>();
		vowel.addAll( Arrays.asList('a','e','i','o','u','A','E','I','O','U') );
		
        String[] split = S.split(" ");
        AtomicInteger idx = new AtomicInteger();
        idx.set(0);
        String res = Arrays.stream( split )
		        .map( str -> {
		        	idx.addAndGet(1);
		        	
		        	StringBuilder sb = new StringBuilder();
		        	sb.append(str);
		        	
		        	if ( !vowel.contains(str.charAt(0) ) ) {
		        		int len = str.length();
		        		char first = str.charAt(0);
		        		for (int i = 0; i < len - 1; i ++ ) {
		        			sb.setCharAt(i, sb.charAt(i+1) );
		        		}
		        		sb.setCharAt(len - 1, first);
		        	}
	
		        	sb.append("ma");
		        	for (int i = 0; i < idx.get(); i ++ ) {
		        		sb.append('a');
		        	}
		        	return sb.toString();
		        	
		        })
		        .reduce("", (String a, String b) -> {
		        	return String.format("%s %s", a, b);
		        });
        
        return res.trim();
    }

}
