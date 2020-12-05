package Contest;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;


public class Bomb_And_$100_Bills {
	
	public static void main(String[]args) {
		String[] inputs = getInput();
		String[] nmk = inputs[0].split(" ");
		int n = Integer.parseInt( nmk[0] );
		int m = Integer.parseInt( nmk[1] );
		int k = Integer.parseInt( nmk[2] );
		
		//	Construction of Grid
		char[][] grid = new char[n][m];
		
		for (int i = 0; i < n; i ++ ) {
			for (int j = 0; j < m; j++ ) {
				grid[i][j] = inputs[i+1].charAt(j);
			}
		}
		
		//	Identifying the bomb grids
		Set<Integer> bombrows = new HashSet<>();
		Set<Integer> bombcols = new HashSet<>();
		for (int i = 0; i < n; i ++ ) {
			for (int j = 0; j < m; j++ ) {
				if (grid[i][j] == 'B') {
					bombrows.add(i);
					bombcols.add(j);
				}
			}
		}
		
		//	Constructing the Min Heap for those not involved in bombing, Max Heap for those involved (To swap)
		//	Becuase we prioritize to swap out the max bills first to minimum.
		int valueNotBombed = 0;
		PriorityQueue<Integer> bombbills = new PriorityQueue<>( (x,y) -> {		//	Custom comparator for max heap
			return y - x;
		});
		PriorityQueue<Integer> nonbombbills = new PriorityQueue<>();
		for (int i = 0; i < n; i ++ ) {
			for (int j = 0; j < m; j++ ) {
				char c = grid[i][j];
				if (c == 'B') continue;
				
				if (bombrows.contains(i) || bombcols.contains(j) ) {
					bombbills.add( c - '0' );
				} else {
					valueNotBombed += c - '0';
					nonbombbills.add(c - '0');
				}
			}
		}
		
		//	Time to do swappings!
		while (!bombbills.isEmpty() && !nonbombbills.isEmpty() && k-- > 0) {
			if ( bombbills.peek() > nonbombbills.peek() ) {
				valueNotBombed -= nonbombbills.poll();
				valueNotBombed += bombbills.poll();
			} else break;
		}
		
		System.out.println(valueNotBombed);
		
	}
	
	private static String[] getInput() {
		Scanner scan = new Scanner(System.in);
		List<String> strs = new ArrayList<>();
		while (scan.hasNextLine() ) {
			strs.add( scan.nextLine() );
		}
		scan.close();
		return strs.toArray( new String[strs.size() ] );
	}

}
