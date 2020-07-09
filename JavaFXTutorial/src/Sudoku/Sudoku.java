package Sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class Sudoku {
	
	int[][] unsolvedSudoku;
	int[][] solvedSudoku;
	ArrayList<Set<Integer> > numberSets = new ArrayList<>();
	
	//Constructor//---------------------------------------------------------------
	public Sudoku() {
		//Initialize the numberSets to have 9 HashSets, each having integer from 1 to 9 inclusive
		for (int i = 0; i < 9; i ++ ) {
			HashSet<Integer> set = new HashSet<>();
			for (int j = 1; j <= 9; j ++ ) {
				set.add(j);
			}
			numberSets.add(set);
		}
		unsolvedSudoku = null;
		solvedSudoku = null;
	}
	
	public Sudoku(int[][] sudoku) {
		this();
		setSudoku(sudoku);
	}
	//End of Constructor//------------------------------------------------------------
	
	//set the new Sudoku to unsolvedSudoku as well as copy it to solvedSudoku to be solved later
	public void setSudoku(int[][]sudoku ) {
		this.unsolvedSudoku = sudoku;
		this.solvedSudoku = new int[9][9];
		copyToSolved();
		scanAndUpdate();
	}
	
	//Prints the unsolved Sudoku out into the console
	public void printUnsolvedSudoku() {
		for (int[] row: unsolvedSudoku) {
			for (int ele: row) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------");
	}
	

	public void printSolvedSudoku() {
		for (int[] row: solvedSudoku) {
			for (int ele: row) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------");
	}
	
	//Removes the unavailable solutions based on the current Sudoku block by block
	private void scanAndUpdate() {
		for (Set<Integer> s: numberSets) {
			for (int i = 1; i <= 9; i ++ ) {
				s.add(i);
			}
		}
		for (int row = 0; row < 9; row ++ ) {
			for (int col = 0; col < 9; col ++ ) {
				if ( unsolvedSudoku[row][col] != 0)
					getSet(row, col).remove(unsolvedSudoku[row][col]);
			}
		}
	}
	
	//Solve the sudoku, which calls the recursive backtracking solveSudoku(row,col) below
	public boolean solveSudoku() {
		return solveSudoku(0,0);
	}
	
	private boolean solveSudoku(int row, int col) {
		//Base Cases
		if (row == 9) return true;
		
		int nextRow = (col == 8)? row + 1: row;
		int nextCol = (col == 8)? 0: col + 1;
		
		if (solvedSudoku[row][col] != 0) return solveSudoku(nextRow, nextCol);
		
		Set<Integer> thisSet = getSet(row, col);
		List<Integer> arr = new ArrayList<>(thisSet);
		
		//While the iterator still has possible solutions, check the number if it can be inserted into this current place
		for (int attempt: arr) {
			
			//Only attempt to recurse if the number can be inserted here
			if ( canInsert(attempt, row, col) ) {
				thisSet.remove(attempt);
				solvedSudoku[row][col] = attempt;
				
				//Recurse method here
				boolean isSolved = solveSudoku(nextRow, nextCol);
				//Using this attempt number was a mistake! Revert the changes
				if (!isSolved ) {
					thisSet.add(attempt);
					solvedSudoku[row][col] = 0;
					continue;
				}
				//The recursion returned true which means we've reached the row 9 (All numbers filled), pass TRUE up to the beginning
				else {
					return true;
				}
				
			}
		}
		//There is no more solution to this grid in current state. One or more previous solution must be wrong! Backtrack
		return false;
	}
	//end of solveSudoku()
	
	public int countSolutions() {
		return countSolutions(0,0);
	}
	
	private int countSolutions(int row, int col) {
		//Base case
		if (row == 9) {
			return 1;
		}

		int nextRow = (col == 8)? row+1: row;
		int nextCol = (col == 8)? 0: col+1;

		int counter = 0;
		
		if (solvedSudoku[row][col] != 0) return countSolutions(nextRow,nextCol);
		
		Set<Integer> thisSet = getSet(row,col);
		List<Integer> arr = new ArrayList<>(thisSet);
		
		for (int attempt: arr) {	
			
			if (canInsert(attempt, row, col) ) {
				thisSet.remove(attempt);
				solvedSudoku[row][col] = attempt;
				
				counter += countSolutions(nextRow, nextCol);
				thisSet.add(attempt);
				solvedSudoku[row][col] = 0;
			}
		}
		return counter;
	}
	
	private static final byte NO_SOLUTION = 0;
	private static final byte UNIQUE_SOLUTION = 1;
	private static final byte MULTIPLE_SOLUTION = 2;
	
	public boolean isUnique() {
		return isUnique(0,0) == UNIQUE_SOLUTION;
	}
	
	private byte isUnique(int row, int col) {
		byte solution = NO_SOLUTION;
		//Base case
		if (row == 9)
			return UNIQUE_SOLUTION;
		
		int nextRow = (col == 8)? row+1: row;
		int nextCol = (col == 8)? 0: col+1;
		
		if (solvedSudoku[row][col] != 0 ) return isUnique(nextRow,nextCol);
		
		Set<Integer> thisSet = getSet(row,col);
		ArrayList<Integer> arr = new ArrayList<>(thisSet);
		
		for (int attempt: arr) {
			if (canInsert(attempt, row, col) ) {
				thisSet.remove(attempt);
				solvedSudoku[row][col] = attempt;
				
				byte result = isUnique(nextRow, nextCol);
				if (result == MULTIPLE_SOLUTION || solution == UNIQUE_SOLUTION && result == UNIQUE_SOLUTION) 
					return MULTIPLE_SOLUTION;
				if (result == UNIQUE_SOLUTION)
					solution = UNIQUE_SOLUTION;
				
				thisSet.add(attempt);
				solvedSudoku[row][col] = 0;
			}
		}
		return solution;
	}
	
	
	//Returns the set to be operate on based on the row and column
	private Set<Integer> getSet(int row, int col) {
		//either block 1, 2 or 3
		if (row <= 2) {
			if (col <= 2) return numberSets.get(0);
			else if (col <= 5) return numberSets.get(1);
			else return numberSets.get(2);
		}
		//either block 4,5 or 6
		else if (row <= 5) {
			if (col <= 2) return numberSets.get(3);
			else if (col <= 5) return numberSets.get(4);
			else return numberSets.get(5);
		}
		//either block 7,8 or 9
		else {
			if (col <= 2) return numberSets.get(6);
			else if (col <= 5) return numberSets.get(7);
			else return numberSets.get(8);
		}
	}
	
	private void copyToSolved() {
		for (int i = 0; i < 9; i ++ ) {
			solvedSudoku[i] = unsolvedSudoku[i].clone();
		}
	}
	
	//Method to check if a number can be possibly inserted into the specified row and column.
	//All the checking does is see whether there is duplicate number in same row and column
	//Do note that the checking of number among block is skipped because the available number in disposal is eliminated by scanAndUpdate() method
	private boolean canInsert(int num, int row, int col) {
		for (int rowPos = 0; rowPos < 9; rowPos ++ ) {
			if (solvedSudoku[rowPos][col] == num) return false;
		}
		for (int colPos = 0; colPos < 9; colPos ++ ) {
			if (solvedSudoku[row][colPos] == num) return false;
		}
		return true;
	}
	
	
	
}		//end of Sudoku class
