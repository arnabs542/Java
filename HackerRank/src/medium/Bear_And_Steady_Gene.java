package medium;

//https://www.hackerrank.com/challenges/bear-and-steady-gene/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	The question asks to find a substring so that when characters are replaced, each characters of ACTG must occur exactly O(n/4) times.
 * 	For this, we can find out the excessive (excess) character by first running a linear scan and count the frequencies of each
 * 	The goal is to find the shortest substring which includes all the excessive characters
 * 
 * 	We could attempt to be greedy and try from shortest length of substring. If it fails, then try longer string, probably by trying to increase by 1.
 * 	However, this attempt is O(N) time and we could do better. To keep trying substring lengths, we could simply perform a binary search to reduce 
 * 	to O(log n) time.
 * 	If the mid value of length works, try going shorter length, but include this working length as well (right = mid)
 * 	If the mid value doesn't work, try longer length, do not include this length which is not working (left = mid + 1)
 * 
 * 	To scan through the substrings and check if they contains excessive characters, we could use a sliding window to do so. This reduce time complexity to O(N) time
 * 	
 * 	The approach is O(N log n) time.
 * 
 * 	--------------------------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * 	To do this in O(N) time complexity is also possible, using two pointers.
 * 	First we also need to identify the excess characters through linear scan, which is O(N) time.
 * 
 * 	Now, initiate two pointers. We will first try to expand the substring (increment right pointer) such that the substring included in it is a valid one, which
 * 	includes all excess characters.
 * 	Then, try to be greedy and remove the characters from the left. As long as it is still a valid substring after removing the character, keep doing so until
 * 	it becomes invalid. Now, record the length of the substring.
 * 	All of this is also done with sliding window
 * 
 * 	Repeat the above process until the right pointer reached the end of substring, then we could return the minimum length of the substring so far recorded.
 * 	
 */

public class Bear_And_Steady_Gene {
	
	static int steadyGene(String gene) {
		int[] dna = new int[4];
		
		for (char c: gene.toCharArray() )
			dna[ dnaC(c) ] ++;
		
		int left = 0;
		for (int i = 0; i < 4; i ++ ) {
			dna[i] = (dna[i] - gene.length() / 4 <= 0)? 0: dna[i] - gene.length() / 4;
			left += dna[i];
		}
		if (left == 0) return 0;
		
		int right = gene.length();
		
		outer:
		while (left < right) {
			int mid = left + (right - left) / 2;
			int[] window = new int[4];
			
			for (int i = 0; i < mid; i ++ ) {
				window[ dnaC(gene.charAt(i) ) ] ++;
			}
			if (check(dna, window) ) {
				right = mid;
				continue;
			}
			
			for (int i = mid; i < gene.length(); i ++ ) {
				window[ dnaC(gene.charAt(i) ) ] ++;
				window[ dnaC(gene.charAt(i - mid) ) ] --;
				if (check(dna, window) ) {
					right = mid;
					continue outer;
				}
			}
			left = mid + 1;
			
		}
		return left;
	}
	
	static int steadyGeneImprv(String gene) {
		int[] dna = new int[4];
		
		for (char c: gene.toCharArray() )
			dna[ dnaC(c) ] ++;
		
		int exc = 0;
		for (int i = 0; i < 4; i ++ ) {
			dna[i] = (dna[i] - gene.length() / 4 <= 0)? 0: dna[i] - gene.length() / 4;
			exc += dna[i];
		}
		if (exc == 0) return 0;
		
		int left = 0, right = 0;
		int minLen = Integer.MAX_VALUE;
		int[] window = new int[4];
		window[ dnaC(gene.charAt(0) ) ] ++;
		
		while (right < gene.length() ) {
			do {
				window[ dnaC(gene.charAt(++right) ) ] ++;
			} while (!check(dna, window) );
			
			window[ dnaC(gene.charAt(left) ) ] --;
			while (check(dna, window) ) {
				left ++;
				window[ dnaC(gene.charAt(left) ) ] --;
			}
			window[ dnaC(gene.charAt(left) ) ] ++;
			
			minLen = Math.min(right - left + 1, minLen);
		}
		
		return minLen;
	}
	
	//Checks if the current window is valid given the source array, where the source array denotes how many characters shall be included (to be eliminated) in the
	//current array
	static boolean check(int[] source, int[] current) {
		for (int i = 0; i < 4; i ++ ) {
			if (source[i] > current[i] ) return false;
		}
		return true;
	}
	
	//Convert a character to array indexing
	static int dnaC(char c) {
		switch (c) {
			case 'A': return 0;
			case 'G': return 1;
			case 'C': return 2;
			case 'T': return 3;
		}
		return -1;
	}
	
	public static void main(String[]args) {
		System.out.println( steadyGene("ACT") );
	}
}
