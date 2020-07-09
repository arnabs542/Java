package ticTacToeV2;

import application.OptionPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TitlePane extends BorderPane {

	private Main rootWindow;
	private static ImageView ticTacPic = new ImageView( TitlePane.class.getResource("/TicTacToe/tictactoe.png").toString() );
	
	//Constructor
	public TitlePane(Main rootWindow) {
		super();
		
		this.rootWindow = rootWindow;
		
		this.setTop(new TitleLayout() );
		this.setRight(new ButtonLayout() );
		this.setCenter(ticTacPic);
		
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	
	//Inner class for the Title text and credit layout
	private class TitleLayout extends VBox {
		
		private Label title = new Label("TIC TAC TOE");
		private Label credit = new Label("v1.0 by Soh Jun Wei");
		
		public TitleLayout() {
			super();
			
			title.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 100) );
			credit.setFont( Font.font("Arial", FontPosture.ITALIC, 20) );
			credit.setTextFill(Color.DARKGREY);
			
			this.setPadding( new Insets(50, 0, 0 ,0) );
			this.setSpacing(5);
			this.setAlignment(Pos.CENTER);
			
			this.getChildren().addAll(title, credit);
		}
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	
	//Inner class for the buttons to start or load game
	private class ButtonLayout extends VBox {
		
		private Button startButton = new Button("> Start New Game");
		private Button loadButton = new Button("> Load Game");
		
		public ButtonLayout () {
			super();
			
			startButton.setPrefSize(350, 100);
			loadButton.setPrefSize(350, 100);
			startButton.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 30) );
			loadButton.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 30));
			
			this.setSpacing(30);
			this.setPadding( new Insets(20, 20, 20, 20) );
			this.setAlignment(Pos.CENTER_RIGHT);
			
			this.getChildren().addAll(startButton, loadButton);
			
			//Creates a new game by first prompting player name, and selecting save file directory
			startButton.setOnAction(e -> {
				String playerName = OptionPane.showInputDialog("New Player", "Enter your name!", OptionPane.ALERT_MESSAGE);
				while (playerName.isEmpty() ) {
					OptionPane.showMessageDialog("Error", "Your name cannot be Empty!", OptionPane.ERROR_MESSAGE);
					playerName = OptionPane.showInputDialog("New Player", "Enter your name!", OptionPane.ALERT_MESSAGE);
				}
				if ( !playerName.equals( OptionPane.CANCEL_BUTTON ) ) {
					rootWindow.createSaveFile( rootWindow.getStage(), playerName);;
				}
			} );
			
			//Loads the game session. If save file loaded is empty, prompt to locate save file first
			loadButton.setOnAction(e -> {
				if (rootWindow.getSaveFile() == null)
					rootWindow.loadSaveFile(rootWindow.getStage() );
				else
					rootWindow.loadSaveFile(rootWindow.getStage(), rootWindow.getSaveFile());
			});
		}
		
	}
	
}
