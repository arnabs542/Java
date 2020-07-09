package ticTacToeV2;

import java.util.ArrayList;
import java.util.Random;

import application.OptionPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class GamePane extends BorderPane {

	private Main rootWindow;
	private ButtonPane buttonPane;
	private InfoPane infoPane;
	private Media slapEffect = new Media(GamePane.class.getResource("/TicTacToe/slap.mp3").toString() );
	private Media oofEffect = new Media(GamePane.class.getResource("/TicTacToe/oof.mp3").toString() );
	private Media blingEffect = new Media(GamePane.class.getResource("/TicTacToe/bling.mp3").toString() );
	
	private boolean isPracticeMode = true;
	private static Image xImg = new Image(GamePane.class.getResource("/TicTacToe/cross.png").toString() );
	private static Image oImg = new Image(GamePane.class.getResource("/TicTacToe/round.png").toString() );
	
	//Constructor of GamePane, the topmost layout for the game Scene
	public GamePane(Main rootWindow) {
		super();
		
		this.rootWindow = rootWindow;
		this.buttonPane = new ButtonPane();
		this.infoPane = new InfoPane();
		
		this.setCenter(buttonPane );
		this.setRight(infoPane );
	}
	
	
	//Clears the board, triggered when a game ends or the clear button is clicked
	public void clearBoard () {
		for (ButtonPane.XOButton b: buttonPane.buttons) {
			 b.changeIconAndState(ButtonPane.XOButton.EMPTY);
			 b.setDisable(false);
		}
	}
	
	
	public void setPlayerName(String name) {
		infoPane.statsPane.name.setText(name);
	}
	
	public void setPlayerScore(int score) {
		infoPane.statsPane.score.setText( String.valueOf(score) );
	}
	
	public void setPlayerHighScore(int score) {
		infoPane.statsPane.highscore.setText( String.valueOf(score) ); 
	}
	
	public String getPlayerName() {
		return infoPane.statsPane.name.getText();
	}
	
	public int getPlayerScore() {
		return Integer.parseInt( infoPane.statsPane.score.getText() );
	}
	
	public int getPlayerHighScore() {
		return Integer.parseInt( infoPane.statsPane.highscore.getText() ); 
	}
	
	public void incrementScore() {
		infoPane.statsPane.score.setText( String.valueOf(Integer.parseInt( infoPane.statsPane.score.getText() ) + 1) ); 
	}
	
	public void decrement2Score() {
		infoPane.statsPane.score.setText( String.valueOf(Integer.parseInt( infoPane.statsPane.score.getText() ) - 2) ); 
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	
	//Inner class for the Right pane which contains buttons and player's stats
	private class InfoPane extends VBox {
		
		private Label mode = new Label("PRACTICE MODE");
		private Button switchButton = new Button("Switch Mode");
		private Button clearButton = new Button("Clear");
		private Button mainMenuButton = new Button("Quit to Menu");
		private StatsPane statsPane = new StatsPane();
		
		//Constructor
		public InfoPane () {
			super();
			
			mode.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 50) );
			mode.setWrapText(true);
			mode.setTextAlignment(TextAlignment.CENTER);
			
			mode.setPadding( new Insets(0,0,70,0));
			switchButton.setPrefSize(200, 50);
			switchButton.setTextAlignment(TextAlignment.CENTER);
			clearButton.setPrefSize(200, 50);
			mainMenuButton.setPrefSize(200, 50);
			
			Separator separator = new Separator();
			separator.setPadding( new Insets(100,0,0,0) );

			this.setSpacing(10);
			this.setPadding( new Insets(10, 10, 10, 10) );
			this.setMaxWidth(300);
			this.setAlignment(Pos.TOP_CENTER);
			
			this.getChildren().addAll(mode, switchButton, clearButton, mainMenuButton, separator, statsPane);
			
			//When switch button is clicked, simply switch between the 2 available modes: Practice mode and CPU vs Player mode
			switchButton.setOnAction(e -> {
				if (isPracticeMode) {
					clearBoard();
					isPracticeMode = false;
					clearButton.setDisable(true);
					infoPane.mode.setText("PLAYER vs COMP");
				}
				else {
					clearBoard();
					isPracticeMode = true;
					clearButton.setDisable(false);
					infoPane.mode.setText("PRACTICE MODE");
				}
			});
			//When clear button is clicked, clear the board
			clearButton.setOnAction(e -> clearBoard() );
			//When main menu is clicked, quit to main menu
			mainMenuButton.setOnAction(e -> {
				String choice = OptionPane.showConfirmDialog("Save Progress", "Progress will be lost. Save Progress?", OptionPane.ALERT_MESSAGE);
				if (choice.equals( OptionPane.YES_BUTTON )) {
					rootWindow.saveProgress();
					rootWindow.switchScene();
				}
				else if (choice.equals(OptionPane.NO_BUTTON)) rootWindow.switchScene();
			} );
			
		}
		
		//----------------------------------------------------------------------------------------------------------------
		//Inner inner class for Displaying player stats, like name and score
		private class StatsPane extends GridPane {
			private Label nameLabel = new Label("Name: ");
			private Label scoreLabel = new Label("Score: ");
			private Label highscoreLabel = new Label("High Score: ");
			private Label name = new Label("");
			private Label score = new Label("0");
			private Label highscore = new Label("0");
			
			//Constructor
			public StatsPane() {
				super();
				
				name.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 20) );
				score.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 20) ); 
				highscore.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 20) ); 
				
				this.setAlignment(Pos.CENTER_LEFT);
				this.setPadding( new Insets(20, 20, 20, 20) );
				this.setHgap(20);
				this.setVgap(30);
				
				this.add(nameLabel, 0, 0);
				this.add(scoreLabel, 0, 1);
				this.add(highscoreLabel, 0, 2);
				this.add(name, 1, 0);
				this.add(score, 1, 1);
				this.add(highscore, 1, 2);
				
				score.textProperty().addListener( new ChangeListener<String>() {
					
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						if ( Integer.parseInt(score.getText() ) > Integer.parseInt(highscore.getText() ) )
							highscore.setText( score.getText() );
					}
				});
			}
		}
		//End of statsPane inner class
		
	}
	//end of information pane inner class
	
	//-------------------------------------------------------------------------------------------------------------------
	
	//Inner class for the game board, the 3x3 grid
	private class ButtonPane extends GridPane {
		
		//the index traverses from left to right, then to new row
		//EG: index 1 represents button at row 0, column 1; Index 2 represents button at row 0, column 2
		private ArrayList<XOButton> buttons = new ArrayList<>();
		private Random rand = new Random();
		
		//Constructor
		public ButtonPane () {
			this.setAlignment(Pos.CENTER);
			for (int row = 0; row < 3; row ++ ) {			//Creates 9 buttons, 3 at each row
				for (int col = 0; col < 3; col ++ ) {
					XOButton xoButton = new XOButton();
					buttons.add(xoButton );
					
					this.add(xoButton, col, row);
				}
			}
			
		}
		
		//A method which simulates a CPU's move, which is completely at random
		public void cPUMove() {
			int random = rand.nextInt(9);
			while ( buttonPane.buttons.get(random).getState() != ButtonPane.XOButton.EMPTY )
				random = rand.nextInt(9);
			buttonPane.buttons.get(random).changeIconAndState( ButtonPane.XOButton.ROUND );
			buttonPane.buttons.get(random).setDisable(true);
		}
		
		
		//The game logic is mainly here:
		//To check the game if any side has win. If yes, display respective message, increment or decrement the score and reset the board
		public void checkWin() {
			byte winner = XOButton.EMPTY;
			
			//Do Horizontal Scan
			if ( (buttons.get(0).getState() == buttons.get(1).getState() && buttons.get(1).getState() == buttons.get(2).getState()) && buttons.get(0).getState() != XOButton.EMPTY)
				winner = buttons.get(0).getState();
			if ( (buttons.get(3).getState() == buttons.get(4).getState() && buttons.get(4).getState() == buttons.get(5).getState()) && buttons.get(3).getState() != XOButton.EMPTY)
				winner = buttons.get(3).getState();
			if ( (buttons.get(6).getState() == buttons.get(7).getState() && buttons.get(7).getState() == buttons.get(8).getState()) && buttons.get(6).getState() != XOButton.EMPTY)
				winner = buttons.get(6).getState();
			//------------------
			
			//Do Vertical Scan
			if ( (buttons.get(0).getState() == buttons.get(3).getState() && buttons.get(3).getState() == buttons.get(6).getState()) && buttons.get(0).getState() != XOButton.EMPTY)
				winner = buttons.get(0).getState();
			if ( (buttons.get(1).getState() == buttons.get(4).getState() && buttons.get(4).getState() == buttons.get(7).getState()) && buttons.get(1).getState() != XOButton.EMPTY)
				winner = buttons.get(1).getState();
			if ( (buttons.get(2).getState() == buttons.get(5).getState() && buttons.get(5).getState() == buttons.get(8).getState()) && buttons.get(2).getState() != XOButton.EMPTY)
				winner = buttons.get(2).getState();
			//--------------------
			
			//Do the Vertices Scan
			if ( (buttons.get(0).getState() == buttons.get(4).getState() && buttons.get(4).getState() == buttons.get(8).getState()) && buttons.get(0).getState() != XOButton.EMPTY)
				winner = buttons.get(0).getState();
			if ( (buttons.get(2).getState() == buttons.get(4).getState() && buttons.get(4).getState() == buttons.get(6).getState()) && buttons.get(2).getState() != XOButton.EMPTY)
				winner = buttons.get(2).getState();
			//---------------------
			
			//Since the player is representing cross, a winner of CROSS means player has won
			if (winner == XOButton.CROSS) {
				new MediaPlayer(blingEffect).play();
				OptionPane.showMessageDialog("You Win", "You have win this round", OptionPane.ACCEPT_MESSAGE);
				incrementScore();
				clearBoard();
			}
			else if (winner == XOButton.ROUND) {
				new MediaPlayer(oofEffect).play();
				OptionPane.showMessageDialog("You Lose", "You've lost against brainless CPU!", OptionPane.ERROR_MESSAGE);
				decrement2Score();
				clearBoard();
			}
			//If the board has gone full without anyone winning, it is a draw
			else if (isFull() ) {
				OptionPane.showMessageDialog("Draw", "This game ends in a draw.", OptionPane.ALERT_MESSAGE);
				clearBoard();
			}
			
		}
		
		//Helper method to check if the board is filled
		private boolean isFull() {
			for (XOButton b: buttons) {
				if (b.getState() == XOButton.EMPTY) return false;
			}
			return true;
		}
		
		
		//-------------------------------------------------------------------------------------------------------------------
		
		//Inner inner class which represents each individual button
		private class XOButton extends Button {
			
			private byte state = EMPTY;
			//The imageView item for the X and O logo
			
			private ImageView crossLogo = new ImageView(xImg);
			private ImageView roundLogo = new ImageView(oImg);
			
			public static final byte EMPTY = 0, CROSS = 1, ROUND = 2;
			
			//Constructor
			public XOButton() {
				this.setPrefSize(200, 200);
				crossLogo.setFitHeight(150);
				crossLogo.setFitWidth(150);
				crossLogo.setPreserveRatio(true);
				crossLogo.setSmooth(true);
				
				roundLogo.setFitHeight(150);
				roundLogo.setFitWidth(150);
				roundLogo.setPreserveRatio(true);
				roundLogo.setSmooth(true);
				
				this.setOnMousePressed(e -> {
					//If it is currently practice mode, simply change icon according to the mouse clicked
					if (isPracticeMode) {
						if (e.getButton().equals(MouseButton.PRIMARY) ) {
							if (getState() == CROSS) changeIconAndState (EMPTY);
							else changeIconAndState (CROSS);
						}
						else if (e.getButton().equals(MouseButton.SECONDARY) ) {
							if (getState() == ROUND) changeIconAndState (EMPTY);
							else changeIconAndState (ROUND);
						}	
					}
					//Else it is currently in Competitive mode, do the real game logic stuff
					else {
						changeIconAndState(CROSS);
						MediaPlayer slap = new MediaPlayer(slapEffect);
						slap.setVolume(0.1);
						slap.play();
						this.setDisable(true);
						checkWin();
						cPUMove();
						checkWin();
					}
					
				});
				
			}
			
			byte getState () {
				return state;
			}
			
			//Method which changes the state and the icon on the button
			void changeIconAndState (byte state) {
				switch (state) {
					case CROSS: this.setGraphic( crossLogo);
								this.state = CROSS;
								break;
					case ROUND: this.setGraphic( roundLogo);
								this.state = ROUND;
								break;
					default:    this.setGraphic( null);
								this.state = EMPTY;
								break;
				}
			}
			
		}	
		//end of individual button inner class
		
	}
	//end of game board inner class
	
	//--------------------------------------------------------------------------------------------------------------
	
	
}
