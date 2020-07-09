package Easy;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/flood-fill/

/*
 * 	Key to notice: If the new color is exactly same as the old Color (The initial pixel to fill), no changes will be done on the image!
 */

public class Flood_Fill {
	
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		LinkedList<int[]> queue = new LinkedList<>();
		int oldColor = image[sr][sc];
		if (oldColor == newColor) return image;
		queue.add(new int[] {sr,sc});
		
		while (!queue.isEmpty()) {
			int[] coord = queue.pop();
			int row = coord[0];
			int col = coord[1];
			
			image[row][col] = newColor;
			
			if (row != 0 && image[row-1][col] == oldColor )
				queue.add(new int[] {row-1, col});
			if (row != image.length - 1 && image[row+1][col] == oldColor)
				queue.add(new int[] {row+1, col});
			if (col != 0 && image[row][col-1] == oldColor)
				queue.add(new int[] {row, col-1});
			if (col != image[0].length - 1 && image[row][col+1] == oldColor)
				queue.add(new int[] {row, col+1});
		}
		return image;
    }
	
	public int[][] floodFillDFS(int[][] image, int sr, int sc, int newColor) {
		int oldColor = image[sr][sc];
		boolean[][] filled = new boolean[image.length][image[0].length];
		image[sr][sc] = newColor;
		filled[sr][sc] = true;
		
		if (sr != 0 && image[sr-1][sc] == oldColor)
			image = DFS(image, sr-1, sc, newColor, filled);
		if (sr != image.length - 1 && image[sr+1][sc] == oldColor) 
			image = DFS(image, sr+1, sc, newColor, filled);
		if (sc != 0 && image[sr][sc-1] == oldColor)
			image = DFS(image, sr, sc-1, newColor, filled );
		if (sc != image[0].length - 1 && image[sr][sc+1] == oldColor)
			image = DFS(image, sr, sc+1, newColor, filled );
		return image;
	}
	
	public int[][] DFS(int[][] image, int sr, int sc, int newColor, boolean[][] filled) {
		if (filled[sr][sc]) return image;
		
		int oldColor = image[sr][sc];
		image[sr][sc] = newColor;
		filled[sr][sc] = true;
		
		if (sr != 0 && image[sr-1][sc] == oldColor)
			image = DFS(image, sr-1, sc, newColor, filled);
		if (sr != image.length - 1 && image[sr+1][sc] == oldColor) 
			image = DFS(image, sr+1, sc, newColor, filled);
		if (sc != 0 && image[sr][sc-1] == oldColor )
			image = DFS(image, sr, sc-1, newColor, filled);
		if (sc != image[0].length - 1 && image[sr][sc+1] == oldColor)
			image = DFS(image, sr, sc+1, newColor, filled);
		return image;
	}
}
