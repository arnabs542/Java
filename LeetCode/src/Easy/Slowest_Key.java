package Easy;

//https://leetcode.com/problems/slowest-key/
/*
 * Simple array problem.
 * 
 * For each char, compute the duration for this key. Compare with the global maximum duration
 * if current duration is longer or not.
 * In case duration is same, compare the characters.
 */

public class Slowest_Key {
	public char slowestKey(int[] releaseTimes, String keysPressed) {
        char res = 'a';
        int prev = 0;
        int maxdur = 0;
        
        for (int i = 0; i < releaseTimes.length; ++i) {
        	int duration = releaseTimes[i] - prev;
        	char c = keysPressed.charAt(i);
        	
        	if (duration > maxdur) {
        		maxdur = duration;
        		res = c;
        	}
        	else if (duration == maxdur)
        		res = c > res? c: res;
        	prev = releaseTimes[i];
        }
        return res;
    }
}
