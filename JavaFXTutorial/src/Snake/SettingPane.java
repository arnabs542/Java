package Snake;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SettingPane extends VBox {

	private SnakeMainWindow mainW;
	
	private Label title = new Label("Snaek");
	private Label credit = new Label("By Soh Jun Wei");
	private Button startButton = new Button("Start");
	private SelectPane selectPane = new SelectPane();
	private Separator separator = new Separator();
	private StatsPane statsPane = new StatsPane();
	
	public static final int EASY = 0, MEDIUM = 1, HARD = 2;
	public static final int FOREST = 0, DESERT = 1, SEA = 2;
	
	public SettingPane (SnakeMainWindow mainW) {
		this.mainW = mainW;
		
		title.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, FontPosture.ITALIC, 100) );
		credit.setFont( Font.font("Arial", FontPosture.ITALIC, 20));
		
		startButton.setPrefSize(400, 100);
		startButton.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 50));
		startButton.setOnAction(e -> {
			mainW.getSnakeWindow().terminateThread();
			launchSpeed();
			disableSelection(true);
			mainW.getSnakeWindow().resetSnake();
		} );
		
		separator.setPadding( new Insets(200, 0, 0, 0) );
		
		this.setPadding( new Insets(10,10,10,10) );
		this.setSpacing(40);
		this.setAlignment(Pos.TOP_CENTER);
		
		this.getChildren().addAll(title, credit, startButton, selectPane, separator, statsPane);
	}
	
	private void launchSpeed() {
		if (selectPane.speedChoice.getSelectionModel().getSelectedIndex() == EASY)
			mainW.getSnakeWindow().setSnakeSpeed(400);
		else if (selectPane.speedChoice.getSelectionModel().getSelectedIndex() == MEDIUM)
			mainW.getSnakeWindow().setSnakeSpeed(200);
		else 
			mainW.getSnakeWindow().setSnakeSpeed(75);
	}
	
	public void disableSelection(boolean disable) {
		if (disable) {
			selectPane.speedChoice.setDisable(true);
			selectPane.colorChoice.setDisable(true);
		}
		else {
			selectPane.speedChoice.setDisable(false);
			selectPane.colorChoice.setDisable(false);
		}
	}
	
	public void incrementScore() {
		statsPane.score.setText( String.valueOf(Integer.parseInt(statsPane.score.getText() ) + 1) ); 
	}
	
	public void resetScore() {
		statsPane.score.setText("0");
	}
	
	//Select Pane class
	private class SelectPane extends HBox {
		ChoiceBox<String> speedChoice = new ChoiceBox<String>(FXCollections.observableArrayList("Easy", "Medium", "Hard"));
		ChoiceBox<String> colorChoice = new ChoiceBox<String>(FXCollections.observableArrayList("Forest", "Desert", "Sea"));
		
		public SelectPane() {
			this.setSpacing(50);
			this.setAlignment(Pos.CENTER);
			
			speedChoice.setPrefSize(150, 50);
			speedChoice.getSelectionModel().select(0);
			colorChoice.setPrefSize(150, 50);
			colorChoice.getSelectionModel().select(0);
			
			colorChoice.getSelectionModel().selectedItemProperty().addListener(e -> {
				mainW.getSnakeWindow().setTheme( colorChoice.getSelectionModel().getSelectedIndex() );
			});
			
			this.getChildren().addAll(speedChoice, colorChoice);
		}
		
	}
	//End of SelectPane
	
	//Stats Pane class
	private class StatsPane extends GridPane {
		private Label scoreLabel = new Label("Score: ");
		private Label score = new Label("0");
		
		public StatsPane() {
			scoreLabel.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 30));
			score.setFont( Font.font("Arial Rounded MT Bold", FontWeight.BOLD, 30));
			
			this.setPadding( new Insets(0,0,0,30));
			this.setVgap(20);
			this.setAlignment(Pos.CENTER_LEFT);
			
			this.add(scoreLabel, 0, 0);
			this.add(score, 1, 0);
		}
		
	}
	
}
