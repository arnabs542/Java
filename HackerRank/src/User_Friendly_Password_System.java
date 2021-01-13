import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * 	Simple hashing program only.
 * 
 * 	Instead of generating each possible hash combinations on authorization, simply generate the hashes when
 * 	password is set or reset, store it in a Set, and cache it.
 * 
 * 	This way authorization takes O(1) time, but resetting password takes longer. 
 * 
 * 	In real world, it is more probable to have authorization process launched rather than password reset. Do you
 * 	reset your password more often than logging in? 
 * 	However just keep in mind that this way takes up some extra space.
 */

public class User_Friendly_Password_System {
	
	static final long mod = (long)Math.pow(10, 9) + 7;

    public static List<Integer> authEvents(List<List<String>> events) {
	    Set<Long> possibles = null;
	    List<Integer> res = new LinkedList<>();
	    
	    for (List<String> e: events) {
	    	if (e.get(0).equals("setPassword") ) {
	    		possibles = possibleCombs( hashPassword(e.get(1) ) );
	    	}
	    	else {
	    		long hash = Long.parseLong( e.get(1) );
	    		res.add( possibles.contains(hash)? 1:0 );
	    	}
	    }
	    
	    return res;
    }
    
    
    private static long hashPassword(String s) {
    	final int len = s.length();
    	long hash = 0;
    	
    	for (int i = 0; i < len; ++i) {
    		char c = s.charAt(i);
    		
    		hash = (hash * 131) % mod;
    		hash = (hash + c) % mod;
    	}
    	return hash;
    }
    
    
    private static Set<Long> possibleCombs(long h) {
    	Set<Long> sets = new HashSet<>();
    	sets.add(h);
    	h = (h * 131) % mod;
    	
    	for (int i = 0; i <= 127; ++i) {
    		sets.add( (h + i) % mod );
    	}
    	
    	return sets;
    }
    
    public static void main(String[]args) {
    	System.out.println( hashPassword("cAr1") );
    }
}
