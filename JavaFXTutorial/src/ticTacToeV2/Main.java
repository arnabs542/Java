package ticTacToeV2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import application.OptionPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage window;
	private Scene titleScene;
	private Scene gameScene;
	private TitlePane titlePane;
	private GamePane gamePane;
	private MediaPlayer astronomia = new MediaPlayer( new Media(Main.class.getResource("/TicTacToe/astronomia.mp3").toString() ) );
	private MediaPlayer naruto = new MediaPlayer( new Media(Main.class.getResource("/TicTacToe/naruto.mp3").toString() ) );
	private File savefile = null;
	private FileChooser fileChooser = new FileChooser();
	private DirectoryChooser dirChooser = new DirectoryChooser();
	
	
	public void start(Stage window) throws Exception {
		this.window = window;
		titlePane = new TitlePane(this);
		gamePane = new GamePane(this);
		gameScene = new Scene(gamePane , 900, 680);
		titleScene = new Scene(titlePane, 900, 680);
		
		naruto.setCycleCount(MediaPlayer.INDEFINITE);
		naruto.setVolume(0.07);
		astronomia.setCycleCount(MediaPlayer.INDEFINITE);
		astronomia.setVolume(0.05);
		astronomia.play();
		
		//If the close button is clicked while in game, prompt to save file first
		window.setOnCloseRequest(e -> {
			if (!gamePane.getPlayerName().isEmpty() && this.getStage().getScene().equals( gameScene) ) {
				String choice = OptionPane.showConfirmDialog("Save Progress", "Progress will be lost. Save Progress?", OptionPane.ALERT_MESSAGE);
				if ( choice.equals(OptionPane.YES_BUTTON) ) saveProgress();
				else if (choice.equals(OptionPane.CANCEL_BUTTON) ) e.consume();
			}
		});
		window.setTitle("Tic Tac Toe v1.0");
		window.setScene(titleScene );
		window.show();
		
		
	}
	
	//Method upon called, switch between title scene and game scene, clearing the game board in the process
	public void switchScene() {
		if (window.getScene().equals(titleScene) ) {
			astronomia.stop();
			naruto.play();
			window.setScene(gameScene);
		}
		else {
			naruto.stop();
			astronomia.play();
			window.setScene(titleScene);
			gamePane.clearBoard();
		}
	}
	
	public GamePane getGamePane() {
		return gamePane;
	}
	
	public TitlePane getTitlePane() {
		return titlePane;
	}
	
	public Stage getStage() {
		return window;
	}
	
	public File getSaveFile() {
		return savefile;
	}
	
	
	//Create a save file method, triggered when the new game button is pressed
	public void createSaveFile(Stage window, String playerName) {
		dirChooser.setTitle("Select save file directory...");
		File dir = dirChooser.showDialog(window);
		
		//If the cancel button or close button is pressed
		if (dir == null) {
			OptionPane.showMessageDialog("No save", "No save file selected. Starting game without saving...", OptionPane.ALERT_MESSAGE);
			getGamePane().setPlayerName(playerName);
			getGamePane().setPlayerScore(0);
			getGamePane().setPlayerHighScore(0);
			savefile = null;
			switchScene();
		}
		else {
			dir = new File( dir.toString().concat("\\ticTacToeSave.txt") );
			
			//If the said save file already exists in the current directory, ask the user whether they want to overwrite it
			if (dir.exists() ) {
				String selection = OptionPane.showConfirmDialog("Save existed", "The previous save file exists in this directory. Overwrite?", OptionPane.ALERT_MESSAGE);
				//If the user does not select to overwrite, do nothing (remains on title screen)
				if (selection != OptionPane.YES_BUTTON)
					return;
			}
			
			try (FileWriter fw = new FileWriter(dir) ) {
				fw.append( String.format("%s\n%d\n%d", playerName, 0, 0) );
				savefile = dir;
			} catch (IOException e) {
				System.out.println(e);
			}
			getGamePane().setPlayerName(playerName);
			getGamePane().setPlayerScore(0);
			getGamePane().setPlayerHighScore(0);
			switchScene();
		}
		
	}
	//end of createSaveFile method
	
	
	//Load a save file method, triggered when the load game button is pressed
	public void loadSaveFile(Stage window) {
		
		fileChooser.setTitle("Open ticTacToeSave.txt file...");
		savefile = fileChooser.showOpenDialog(window);
		//The save file selected is not valid (Exclude when user selects nothing or when press cancel button)
		while (savefile != null && !savefile.toString().endsWith("\\ticTacToeSave.txt") ) {
			OptionPane.showMessageDialog("Error", "Not a valid savefile loaded. Select again!", OptionPane.ERROR_MESSAGE);
			savefile = fileChooser.showOpenDialog(window);
		}
		//The save file selected is valid, then load the file using overloaded method below
		if (savefile != null && savefile.toString().endsWith("\\ticTacToeSave.txt") )
			loadSaveFile(window, savefile);
	}
	
	//Overloaded save file method
	public void loadSaveFile(Stage window, File dir) {
		try (BufferedReader br = new BufferedReader( new FileReader(dir) ) ) {
			gamePane.setPlayerName( br.readLine() );
			gamePane.setPlayerScore( Integer.parseInt(br.readLine() ) );
			gamePane.setPlayerHighScore( Integer.parseInt(br.readLine() ) );
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		switchScene();
	}
	//End of load save file method
	
	
	//Save the game progress method, triggered when the user tries to quit to menu or presses the close button
	public void saveProgress() {
		File dir = savefile;
		//If currently, the save file loaded is empty, prompt user to select a directory to save
		if (savefile == null) {
			dirChooser.setTitle("Select save file directory...");
			dir = dirChooser.showDialog(window);
			if (dir == null) return;
			else {
				dir = new File( dir.toString().concat("\\ticTacToeSave.txt") );
				if (dir.exists() ) {
					String choice = OptionPane.showConfirmDialog("Save existed", "This directory already has a save file. Overwrite?", OptionPane.ALERT_MESSAGE);
					if (choice.equals(OptionPane.NO_BUTTON) ) return;
				}
			}
		}
		//Start writing the save file whenever the savefile is valid
		try (FileWriter fw = new FileWriter(dir, false) ) {
			fw.append( String.format("%s\n%d\n%d", gamePane.getPlayerName(), gamePane.getPlayerScore(), gamePane.getPlayerHighScore()) );
		} catch (IOException e) {}
	}
	
	public static void main(String[]args) {
		launch(args);
	}

	
	
}
