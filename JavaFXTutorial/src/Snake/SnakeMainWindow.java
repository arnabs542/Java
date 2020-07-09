package Snake;

import application.OptionPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SnakeMainWindow extends Application {
	
	private SettingPane settingPane;
	private SnakeWindow snakeWindow;
	private static Media crabrave = new Media(SnakeMainWindow.class.getResource("crabrave.mp3").toString() );
	
	@Override
	public void start(Stage window) throws Exception {

		settingPane = new SettingPane(this);
		settingPane.setStyle("-fx-background-color: #edf5e1");
		snakeWindow = new SnakeWindow(this);
		Scene scene = new Scene(new Layout() );
		scene.setFill(Color.ALICEBLUE);
		
		MediaPlayer player = new MediaPlayer(crabrave);
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.setVolume(0.05);
		player.play();
		
		window.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler() );
		window.setTitle("Snaek");
		window.setScene(scene);
		window.setOnCloseRequest(e -> snakeWindow.terminateThread() );
		window.show();

	}
	
	protected SnakeWindow getSnakeWindow() {
		return snakeWindow;
	}
	
	protected SettingPane getSettingPane() {
		return settingPane;
	}
	
	//BorderLayout class for the scene
	private class Layout extends BorderPane {
		
		public Layout() {
			this.setPadding( new Insets(10,10,10,10));
			
			
			this.setCenter(snakeWindow);
			this.setRight(settingPane);
		}
	}
	
	//EventHandler class when key is pressed
		private class KeyHandler implements EventHandler<KeyEvent> {
			@Override
			public void handle(KeyEvent event) {
				KeyCode keyPressed = event.getCode();
				
				if (keyPressed.equals(KeyCode.UP) || keyPressed.equals(KeyCode.W) ) {
					snakeWindow.move(SnakeObject.UP);
				}
				else if (keyPressed.equals(KeyCode.DOWN) || keyPressed.equals(KeyCode.S) ) {
					snakeWindow.move(SnakeObject.DOWN);
				}
				else if (keyPressed.equals(KeyCode.LEFT) || keyPressed.equals(KeyCode.A) ) {
					snakeWindow.move(SnakeObject.LEFT);
				}
				else if (keyPressed.equals(KeyCode.RIGHT) || keyPressed.equals(KeyCode.D) ) {
					snakeWindow.move(SnakeObject.RIGHT);
				}
			}
		}
		//End of key pressed event handler
	
	public static void main(String[]args) {
		launch(args);
	}

}
