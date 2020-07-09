package Snake;

import java.util.Random;

import Snake.SnakeObject.Body;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class Food {

	private SnakeWindow parentWindow;
	private static Random rand = new Random();
	int row, col;
	
	private static Media bitesound = new Media(Food.class.getResource("/Snake/bite.mp3").toString() );
	
	public Food (SnakeWindow parentWindow) {
		this.parentWindow = parentWindow;
		
		newFood();
	}
	
	void foodEaten() {
		parentWindow.getSnakeObject().addBodySegment();
		new MediaPlayer(bitesound).play();
		Platform.runLater(() -> {
			parentWindow.getSnakeMainWindow().getSettingPane().incrementScore();
		});
		newFood();
	}
	
	private boolean isBody(int[] test) {
		int[][] coord = parentWindow.getSnakeObject().getBodiesCoord();
		for (int[] body: coord) {
			if (test[0] == body[0] && test[1] == body[1] ) return true;
		}
		return false;
	}
	
	void newFood() {
		row = rand.nextInt(30);
		col = rand.nextInt(30);
		while ( isBody(new int[] {row, col} )) {
			row = rand.nextInt(30);
			col = rand.nextInt(30);
		}
		parentWindow.paintFood(row, col);
		parentWindow.getSnakeObject().setFoodCoord(new int[] {row,col});
	}
	
}
