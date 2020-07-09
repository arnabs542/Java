package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/crossword-puzzle/problem
// This problem is hell

public class Crossword_Puzzle {

	static String[] crosswordPuzzle(String[] crossword, String words) {
		ArrayList<String> keywords = new ArrayList<String>( Arrays.asList( words.split(";") ) );
		return findSolution(crossword, keywords);

    }
	
	//Recursive function, when the argument words had become 0, means the crossword had been solved. Else if the puzzle is impossible,
	//will return null
	static String[] findSolution(String[] crossword, ArrayList<String> words) {
		if (words.size() == 0) return crossword;
		
		//For each row, check the length of empty spaces here using lengthEmpty() 
		//Then, loop through the words to find any word of length matching the length of empty spaces
		//Attempt the word by filling the crossword with the potential word, removing the word from the array,
		//and using recursion
		//If the recursion results in null return, this word is ignored and the loop continues with next potential word
		//Else if the recursion results in a solution, this whole crossword is solved
		for (int row = 0; row < crossword.length; row++) {
			int lengthEmpty = lengthEmpty( crossword[row] );
			if (lengthEmpty > 1) {
				for (String w: words) {
					if (w.length() == lengthEmpty) {
						String[] newCrossWord = crossword.clone();
						ArrayList<String> newWords = (ArrayList<String>) words.clone();
						newWords.remove(w);
						newCrossWord[row] = fillEmpty(crossword[row], w);
						String[] solution = findSolution(newCrossWord, newWords);
						if (solution != null)
							return solution;
						continue;
					}
				}
			}
		}
		
		for (int col = 0; col < crossword[0].length() ; col++ ) {
			String colStr = getColString(crossword, col);
			int lengthEmpty = colLengthEmpty(colStr);
			if (lengthEmpty != 0) {
				for (String w: words) {
					if (w.length() == lengthEmpty) {
						String tryfill = fillable(colStr, w);
						if (tryfill != null) {
							String[] newCrossWord = crossword.clone();
							ArrayList<String> newWords = (ArrayList<String>) words.clone();
							newWords.remove(w);
							newCrossWord = fillCol(newCrossWord, tryfill, col);
							String[] solution = findSolution(newCrossWord, newWords);
							if (solution != null)
								return solution;
							continue;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	//Pass in the crossword, the column number and the column represented in string,
	//returns the crossword with the column replaced
	private static String[] fillCol(String[] grid, String tryfill, int colNo) {
		String[] newGrid = new String[grid.length];
		for (int i = 0; i < grid.length; i ++ ) {
			newGrid[i] = grid[i].substring(0, colNo) + tryfill.charAt(i) + grid[i].substring(colNo + 1);
		}
		return newGrid;
	}
	
	//Checks if the column is fillable with the keyword key. If yes, returns the column represented in a string with the word filled
	//Else it just returns null
	private static String fillable(String col, String key) {
		int index = col.indexOf('-');
		for (int i = index - 1; i != -1 && col.charAt(i) != '+' && col.charAt(i) != 'X'; i--)
			index--;
		for (int i = 0; i < key.length(); i++ ) {
			if (col.charAt(index + i) != '-' && key.charAt(i) != col.charAt(index + i) )
				return null;
		}
		return col.substring(0, index) + key + col.substring(index + key.length() );
	}
	
	//Passes in the crossword and the column number, returns the column in string representation
	private static String getColString(String[] grid, int col) {
		String thisCol = "";
		for (String s: grid)
			thisCol = thisCol.concat("" + s.charAt(col) );
		return thisCol;
	}
	
	//Checks the length of keyword that would be fit in a column, including the filled horizontal words from before
	private static int colLengthEmpty(String s) {
		int counter = 1;
		if (s.indexOf('-') == -1) return 0;
		else {
			for (int i = s.indexOf('-') - 1; i >= 0 && s.charAt(i) != '+' && s.charAt(i) != 'X'; i-- )
				counter++;
			for (int i = s.indexOf('-') + 1; i < s.length() && s.charAt(i) != '+' && s.charAt(i) != 'X'; i++)
				counter++;
		}
		return counter;
	}
	
	//Checks the length of keyword that would be fit in a row
	private static int lengthEmpty(String s) {
		if (s.indexOf('-') == -1) return 0;
		else {
			int counter = 0;
			for (int i = s.indexOf('-'); i < s.length() && s.charAt(i) == '-'; i++) {
				counter ++;
			}
			return counter;
		}
	}
	
	//Fill the row empty spaces with the specified keyword, returns the row in filled form
	private static String fillEmpty(String row, String keyword) {
		int index = row.indexOf('-');
		return row.substring(0, index) + keyword + row.substring(index + keyword.length());
	}
	
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		String[] crossword = new String[10];
		System.out.println("Enter crossword");
		for (int i = 0; i < 10; i ++ ) {
			crossword[i] = scan.next();
		}
		System.out.println("Enter keys");
		String keys = scan.next();
		
		for (String s: crosswordPuzzle(crossword, keys) ) {
			System.out.println(s);
		}
	}
}
