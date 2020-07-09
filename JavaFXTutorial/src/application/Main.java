package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

	private class InfoPane extends GridPane {
		private Label welcome = new Label("Welcome");
		private Text username = new Text("Username: ");
		private Text password = new Text("Password: ");
		private TextField userField = new TextField();
		private PasswordField passField = new PasswordField();
		final private Text status = new Text();
		
		InfoPane() {
			super();
			
			welcome.setFont(Font.font("Arial", FontWeight.BOLD, 25) );
			username.setFont(Font.font(15) );
			password.setFont(Font.font(15) );
			status.setFill(Color.GREEN);
			
			HBox buttonBox = new HBox();
			Button login = new Button("Login");
			buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
			buttonBox.getChildren().add(login);
			login.setOnAction(e -> System.out.println(OptionPane.showConfirmDialog("doit", "You want to popp?daddad da dad ad ad a da a dada da", OptionPane.ACCEPT_MESSAGE) ) );
			
			this.setVgap(5);
			this.setHgap(5);
			this.setPadding( new Insets(25,25,25,25) );
			
			this.add(welcome, 0, 0, 2, 1);
			this.add(username, 0, 1);
			this.add(password, 0, 2);
			this.add(userField, 1, 1);
			this.add(passField, 1, 2);
			this.add(buttonBox, 1, 3);
			this.add(status,  1, 4);
			
			this.setAlignment(Pos.CENTER);
			
		}
		
	}		//end of InfoPane class
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new InfoPane(), 400, 400);
		scene.getStylesheets().add("/application/application.css");
		
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
	}
	
	public static void main(String[]args) {
		launch(args);
	}
	
	
	
}		//end of class