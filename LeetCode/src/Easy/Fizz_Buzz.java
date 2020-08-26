package Easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/fizz-buzz/

public class Fizz_Buzz {
	
	public List<String> fizzBuzz(int n) {
        List<String> li = new ArrayList<String>();
        
        for (int i = 1; i <= n; i ++ ) {
        	String ans = "";
        	if (i % 3 == 0) 
        		ans += "Fizz";
        	if (i % 5 == 0)
        		ans += "Buzz";
        	if ( ans.isEmpty() ) 
        		ans = Integer.toString(i);
        	li.add(ans);
        }
        
        return li;
    }

}
