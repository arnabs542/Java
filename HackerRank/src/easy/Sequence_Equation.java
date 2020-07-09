package easy;

//https://www.hackerrank.com/challenges/permutation-equation/problem

public class Sequence_Equation {
	static int[] permutationEquation(int[] p) {
		//Equivalent to a hash map storing from an integer value to its index value
		int[] map = new int[p.length];

		//However, remember that to find a value like '5', we have to use map[4] because of array indexing start from 0, but the question starts
		//from 1
		//The returned index reflects the actual indexing of problem, which starts from 1
		for (int i = 0; i < p.length; i ++ ) {
			map[p[i] - 1] = i + 1; 
		}
		
		int[] solution = new int[p.length];
		//Starting from 1, until n, find that y such that p[p[y]] = x
		//Let p[z] = x, p[z] = p[ p[y] ]
		//Then find index of pz
		for (int i = 1; i <= p.length; i ++ ) {
			int pz = map[i-1];
			//Remember that pz returns the value of indexing starting from 1, therefore need to -1
			solution[i-1] = map[pz - 1];
		}
		return solution;
    }
}
