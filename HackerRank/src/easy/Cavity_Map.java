package easy;

//https://www.hackerrank.com/challenges/cavity-map/problem?h_r=next-challenge&h_v=zen

public class Cavity_Map {

	static String[] cavityMap(String[] grid) {
		String[] modifiedMap = new String[grid.length];
		
		for (int row = 0; row < grid.length; row ++ ) {
			String thisRow = "";
			
			for (int col = 0; col < grid.length; col ++ ) {
				if (!isBorder(grid, row, col) && isX(grid, row, col) ) 
					thisRow = thisRow.concat("X");
				else 
					thisRow = thisRow.concat( grid[row].substring(col, col+1) );
				
			}
			modifiedMap[row] = thisRow;
			
		}
		return modifiedMap;
    }
	
	private static boolean isBorder(String[] grid, int row, int col) {
		return (row == 0) || (col == 0) || (row == grid.length - 1) || (col == grid.length - 1);
	}
	
	private static boolean isX (String[] grid, int row, int col) {
		int thisInt = Integer.parseInt( grid[row].substring(col, col+1) );
		int above   = Integer.parseInt( grid[row - 1].substring(col, col+1) );
		int left    = Integer.parseInt( grid[row].substring(col - 1, col) );
		int below   = Integer.parseInt( grid[row + 1].substring(col, col+1) );
		int right   = Integer.parseInt( grid[row].substring(col + 1, col + 2) );
		
		return (thisInt > above) && (thisInt > left) && (thisInt > below) && (thisInt > right);
	}
	
	
	public static void main(String[]args) {
		String[] map = cavityMap(new String[] {"1112", "1912", "1892", "1234"} );
		for (String a: map) System.out.println(a);
	}
}
