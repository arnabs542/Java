package Hard;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

//https://leetcode.com/problems/stamping-the-sequence/
/*
 * 	This is a Greedy, String iteration problem.
 * 
 * 	Think of chopping stamps. Say If I have 'abcdef' and I chop 'zzz' starting at index 1, then string becomes
 * 	'azzzef'.
 * 	Notice how zzz essentially replaces 'bcd', we can think of it as a layer of 'zzz' being stacked on top of it!
 * 	Now, target is formed by stamping multiple times, thus, it is like a stack of stamping layers!
 * 
 * 	Therefore, the intuition is that, we can simply 'unstack' the layers. In other words, if we can reverse the target
 * 	back into "??????", then our job is done!
 * 
 * 	The algorithm then becomes:
 * 		While there is still stamping layer, aka not all characters are unstamped, we shall iterate:
 * 			Check all substrings of same size as stamp. Check if they can be unstamped or not
 * 			If they can be unstamped, unstamp by replace the characters by wildcard like '*'
 * 			
 *	Note that unstamped characters are replaced by '*', so in future iterations, stamp 'abc' is able to match 'a**' or 'ab*'
 *	or '**c'. However, we shouldn't match '***' because the entire substring is already unstamped before!
 */

public class Stamping_The_Sequence {
	
	//	Iterate while it can be stamped -> iterate start indices -> check string matches solution O(T*(T-S)*S)
	public int[] movesToStamp(String stamp, String target) {
		final int slen = stamp.length();
		final int tlen = target.length();
		
		int charStamped = 0;
		Set<Integer> stamped = new HashSet<>();
		Deque<Integer> rev = new LinkedList<>();
		StringBuilder t = new StringBuilder(target);
		
		while (true) {
			boolean inserted = false;
			
			for (int i = 0; i <= tlen - slen && !inserted; ++i) {
				if (stamped.contains(i) ) continue;
				if (matches(stamp, t, i) ) {
					stamped.add(i);
					rev.push(i);
					charStamped += unstamp(t, i, slen);
					inserted = true;
				}
			}
			if (!inserted) return new int[] {};
			if (charStamped == tlen) break;
		}
		
		
		int[] res = new int[rev.size()];
		for (int i = 0; i < res.length; ++i)
			res[i] = rev.pop();
		return res;
    }
	
	
	private boolean matches(String stamp, StringBuilder target, int idx) {
		for (int i = 0; i < stamp.length(); ++i) {
			if (target.charAt(i + idx) != stamp.charAt(i) && target.charAt(i + idx) != '*')
				return false;
		}
		return true;
	}
	
	private int unstamp(StringBuilder target, int idx, int len) {
		int count = 0;
		for (int i = 0; i < len; ++i) {
			if (target.charAt(idx+i) != '*') ++count;
			target.setCharAt(idx+i, '*');
		}
		return count;
	}
	
}
