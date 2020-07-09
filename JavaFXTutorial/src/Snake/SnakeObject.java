package Snake;

import java.util.ArrayList;

import application.OptionPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class SnakeObject {

	private int headRow, headCol;
	private int prevHeadRow, prevHeadCol;
	private int[] foodCoord = new int[2];
	private static Media oof = new Media(SnakeObject.class.getResource("/Snake/oof.mp3").toString() );
	
	private ArrayList<Body> snakeBody = new ArrayList<Body>();
	
	private SnakeWindow parentWindow;
	
	public static final byte UP = 1, DOWN = -1, LEFT = 2, RIGHT = -2;
	
	//Constructor
	public SnakeObject(SnakeWindow parentWindow) {
		this.parentWindow = parentWindow;
		
		headRow = 15; headCol = 15;
		prevHeadRow = 0; prevHeadCol = 0;
		parentWindow.paintSnake(headRow, headCol, 0, 0);
	}
	//End of constructor
	
	//Returns coordinate of the tail of snake
	public int[] getTail() {
		if (snakeBody.isEmpty() )
			return new int[]{ prevHeadRow, prevHeadCol };
		else {
			Body tail = snakeBody.get(snakeBody.size() - 1);
			return new int[]{tail.prevRow, tail.prevCol};
		}
	}
	
	//Returns coordinate of the head of snake
	public int[] getHead() {
		return new int[]{ headRow, headCol};
	}
	
	//Returns the array of body coordinates. first index is the body segment, while the second index of size 2 represents row and column
	public int[][] getBodiesCoord () {
		int[][] coord = new int[snakeBody.size() + 1][2];
		coord[0][0] = headRow;
		coord[0][1] = headCol;
		for (int segment = 1; segment < snakeBody.size() + 1; segment++ ) {
			coord[segment][0] = snakeBody.get(segment - 1).row;
			coord[segment][1] = snakeBody.get(segment - 1).col;
		}
		return coord;
	}
	
	public void setFoodCoord(int[] coord) {
		foodCoord = coord;
	}
	
	//Moves the head of the snake with specified direction 
	void move(byte direction) {
		if (direction == UP ) {
			if (isOutOfBound(headRow - 1, headCol) || isCollision(headRow - 1, headCol)) {
				hitWall();
				return;
			}
			else {
				prevHeadRow = headRow; prevHeadCol = headCol;
				headRow--;
				parentWindow.paintSnake(headRow, headCol, prevHeadRow, prevHeadCol);
			}
		}
		else if (direction == DOWN ) {
			if (isOutOfBound(headRow + 1, headCol) || isCollision(headRow + 1, headCol) ) {
				hitWall();
				return;
			}
			else {
				prevHeadRow = headRow; prevHeadCol = headCol;
				headRow++;
				parentWindow.paintSnake(headRow, headCol, prevHeadRow, prevHeadCol);
			}
		}
		else if (direction == LEFT ) {
			if (isOutOfBound(headRow, headCol - 1) || isCollision(headRow, headCol -1) ) {
				hitWall();
				return;
			}
			else {
				prevHeadRow = headRow; prevHeadCol = headCol;
				headCol--;
				parentWindow.paintSnake(headRow, headCol, prevHeadRow, prevHeadCol);
			}
		}
		else if (direction == RIGHT ) {
			if (isOutOfBound(headRow, headCol + 1) || isCollision(headRow, headCol + 1) ) {
				hitWall();
				return;
			}
			else {
				prevHeadRow = headRow; prevHeadCol = headCol;
				headCol++;
				parentWindow.paintSnake(headRow, headCol, prevHeadRow, prevHeadCol);
			}
		}
		if (!snakeBody.isEmpty())
			snakeBody.get(0).move(prevHeadRow, prevHeadCol, 0);
		if (isFood(headRow, headCol) ) {
			parentWindow.getFoodObject().foodEaten();
		}
		
	}	
	//end of move()
	
	void addBodySegment() {
		int[] tail = getTail();
		snakeBody.add(new Body(tail[0], tail[1] ) );
	}
	
	
	private boolean isOutOfBound(int row, int col) {
		return row < 0 || row > 29 || col < 0 || col > 29;
	}
	
	private boolean isCollision(int row, int col) {
		for (Body b: snakeBody) {
			if (row == b.row && col == b.col ) return true;
		}
		return false;
	}
	
	private boolean isFood(int row, int col) {
		return row == foodCoord[0] && col == foodCoord[1];
	}
	
	private void hitWall() {
		new MediaPlayer(oof).play();
		parentWindow.terminateThread();
	}
	
	
	//Class for an object representing one body of snake
	public class Body {
		int row, col;
		int prevRow, prevCol;
		
		Body(int row, int col) {
			this.row = row; this.col = col;
			parentWindow.paintSnake(row, col, prevRow, prevCol);
		}
		
		void move(int row, int col, int index) {
			prevRow = this.row; prevCol = this.col;
			this.row = row; this.col = col;
			parentWindow.paintSnake(row, col, prevRow, prevCol);

			if (index + 1 < snakeBody.size() )
				snakeBody.get(index + 1).move(prevRow, prevCol, index + 1);
		}
		
	}
	//End of Body class
	
}
