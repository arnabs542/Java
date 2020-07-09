package Snake;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * This SnakeWindow class represents the game board of 30x30, and contains the snake object as well as the food object
 * @author Soh
 *
 */
public class SnakeWindow extends GridPane {

	//References to the gridPane and the snake object itself
	private SnakeMainWindow mainW;
	private SnakeObject snake;
	private Food food;
    private SnakeThread snakeThread;
    
	Rectangle[][] box = new Rectangle[30][30];
	Color gridColor;
	Color snakeColor;
	Color foodColor;
	Color backColor;
	private int snakeSpeed = 60;
	
	//Constructor SnakeWindow
	public SnakeWindow (SnakeMainWindow mainW) {
		this.mainW = mainW;
		this.setBackColor(backColor);
		//Creation of the 30x30 game board
		this.setVgap(5);
		this.setHgap(5);
		this.setPadding( new Insets(10,10,10,10) );
		for (int row = 0; row < 30; row ++ ) {
			for (int col = 0; col < 30; col ++ ) {
				box[row][col] = new Rectangle(20,20, gridColor);
				this.add( box[row][col] , col, row);
			}
		}
		//End of Creation of game board
		this.setTheme(SettingPane.FOREST);
		
	}
	//End of constructor SnakeWindow
	
	public void setSnakeSpeed(int speed) {
		this.snakeSpeed = speed;
	}
	
	public void setTheme (int theme) {
		if (theme == SettingPane.FOREST) {
			gridColor = Color.SPRINGGREEN;
			snakeColor = Color.SADDLEBROWN;
			foodColor = Color.RED;
			backColor = Color.MEDIUMSEAGREEN;
		}
		else if (theme == SettingPane.DESERT) {
			gridColor = Color.ORANGE;
			snakeColor = Color.BROWN;
			foodColor = Color.FORESTGREEN;
			backColor = Color.LIGHTGOLDENRODYELLOW;
		}
		else {
			gridColor = Color.DODGERBLUE;
			snakeColor = Color.NAVAJOWHITE;
			foodColor = Color.ORANGERED;
			backColor = Color.LIGHTBLUE;
		}
		setBackColor(backColor);
		for (int row = 0; row < 30; row ++ ) {
			for (int col = 0; col < 30; col ++ ) {
				box[row][col].setFill(gridColor);
			}
		}
	}
	
	public Color getGridColor() {
		return gridColor;
	}
	
	public Color getSnakeColor() {
		return snakeColor;
	}
	
	public int getSnakeSpeed() {
		return snakeSpeed;
	}
	
	protected SnakeObject getSnakeObject() {
		return snake;
	}
	
	protected SnakeMainWindow getSnakeMainWindow() {
		return mainW;
	}
	
	protected Food getFoodObject() {
		return food;
	}
	
	public void setBackColor(Color color) {
		this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void resetSnake() {
		for (int row = 0; row < 30; row ++ ) {
			for (int col = 0; col < 30; col ++ ) {
				box[row][col].setFill(gridColor);
			}
		}
		snake = new SnakeObject(this);
		food = new Food(this);
		snakeThread = new SnakeThread(this);
		mainW.getSettingPane().resetScore();
		snakeThread.start();
	}
	
	public void paintSnake(int row, int col, int prevRow, int prevCol) {
		box[row][col].setFill(snakeColor);
		box[prevRow][prevCol].setFill(gridColor);
	}
	
	public void terminateThread() {
		if (snakeThread != null) {
			snakeThread.interrupt();
			mainW.getSettingPane().disableSelection(false);
		}
	}
	
	public void paintFood(int row, int col) {
		box[row][col].setFill(foodColor);
	}
	
	public void move(byte direction) {
		snakeThread.setDirection(direction);
	}
	
}
//End of SnakeWindow class
