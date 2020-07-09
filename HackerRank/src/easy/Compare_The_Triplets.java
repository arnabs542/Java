package easy;

//https://www.hackerrank.com/challenges/compare-the-triplets/problem?h_r=profile
import java.util.ArrayList;	

public class Compare_The_Triplets {

	static ArrayList<Integer> compareTriplets(ArrayList<Integer> a, ArrayList<Integer> b) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(0);
		scores.add(0);
		for (int i = 0; i < 3; i ++ ) {
			//If Alice at this category wins bob
			if (a.get(i) > b.get(i) ) scores.set(0, scores.get(0) + 1);
			//If bob at this category wins alice
			else if (a.get(i) < b.get(i) ) scores.set(1, scores.get(1) + 1);
		}
		
		return scores;
    }
	
	//An even shorter attempt:
	static ArrayList<Integer> compareTripletsAlt(ArrayList<Integer> a, ArrayList<Integer> b) {
		int scoreA = ( (a.get(0) > b.get(0))? 1:0 ) + ( (a.get(1) > b.get(1))? 1:0 ) + ( (a.get(2) > b.get(2))? 1:0 );
		int scoreB = ( (b.get(0) > a.get(0))? 1:0 ) + ( (b.get(1) > a.get(1))? 1:0 ) + ( (b.get(2) > a.get(2))? 1:0 );
		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(scoreA);
		scores.add(scoreB);
		return scores;
	}
	
}
